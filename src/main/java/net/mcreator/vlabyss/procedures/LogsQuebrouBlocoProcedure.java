package net.mcreator.vlabyss.procedures;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.CloseableHttpResponse;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.registries.ForgeRegistries;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.*;

@Mod.EventBusSubscriber
public class LogsQuebrouBlocoProcedure {
    
    // Configurações constantes
    private static final Logger LOGGER = Logger.getLogger(LogsQuebrouBlocoProcedure.class.getName());
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy • HH:mm:ss");
    private static final String WEBHOOK_URL = "https://discord.com/api/webhooks/1398544096586502144/fi-xlijWIIma6VPHMLRVS-lTolVtJnNZSParXYpVA8YZ4JysRQ-qLETw78PCqOKKkCNm";
    
    // Configurações de rate limiting otimizadas
    private static final int MAX_REQUESTS_PER_MINUTE = 25; // Margem de segurança
    private static final int BATCH_SIZE = 8; // Increased batch size
    private static final long BATCH_TIMEOUT_MS = 2000L; // Reduced timeout
    private static final long PLAYER_COOLDOWN_MS = 800L; // Reduced cooldown
    private static final long RATE_LIMIT_COOLDOWN_MS = 65000L; // Slightly longer
    private static final int MAX_CACHE_SIZE = 100;
    
    // Estruturas de dados otimizadas
    private static final Set<String> ALLOWED_SERVERS = Set.of(
        "vlmpsmp.elgaehost.com.br",
        "elgae-sp1-08.elgaehost.com.br:25863", 
        "elgae-sp1-08.elgaehost.com.br",
        "127.0.0.1",
        "localhost"
    );
    
    // Cache para nomes de blocos (evita lookups repetidos)
    private static final Map<Block, String> BLOCK_NAME_CACHE = new ConcurrentHashMap<>();
    
    // Controles de rate limiting com atomic operations
    private static final Map<String, Long> playerCooldowns = new ConcurrentHashMap<>();
    private static final ArrayBlockingQueue<WebhookRequest> pendingRequests = new ArrayBlockingQueue<>(1000);
    private static final AtomicLong lastRateLimitTime = new AtomicLong(0);
    private static final AtomicInteger requestsThisMinute = new AtomicInteger(0);
    private static final AtomicLong lastMinuteReset = new AtomicLong(System.currentTimeMillis());
    
    // Single-threaded executor otimizado
    private static final ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r, "WebhookProcessor");
        t.setDaemon(true);
        return t;
    });
    
    // HTTP client reutilizável (connection pooling)
    private static final CloseableHttpClient HTTP_CLIENT = HttpClients.custom()
        .setMaxConnPerRoute(5)
        .setMaxConnTotal(10)
        .build();
    
    static {
        // Processador principal com rate limiting inteligente
        EXECUTOR.scheduleWithFixedDelay(LogsQuebrouBlocoProcedure::processBatch, 500, 1000, TimeUnit.MILLISECONDS);
        
        // Limpeza de cache periódica
        EXECUTOR.scheduleWithFixedDelay(LogsQuebrouBlocoProcedure::cleanupCaches, 30, 30, TimeUnit.SECONDS);
        
        // Shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                EXECUTOR.shutdown();
                if (!EXECUTOR.awaitTermination(5, TimeUnit.SECONDS)) {
                    EXECUTOR.shutdownNow();
                }
                HTTP_CLIENT.close();
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "Error during shutdown", e);
            }
        }));
    }
    
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        // Fast path validations
        if (event == null || event.getPlayer() == null || 
            event.getPlayer().level().isClientSide() || !isAllowedServer()) {
            return;
        }
        
        Player player = event.getPlayer();
        String playerName = player.getName().getString();
        long currentTime = System.currentTimeMillis();
        
        // Rate limiting check with atomic operation
        if (!checkPlayerCooldown(playerName, currentTime) || 
            isInGlobalCooldown(currentTime)) {
            return;
        }
        
        // Fast block info extraction
        BlockState blockState = event.getState();
        String blockName = getCachedBlockName(blockState.getBlock());
        String coordinates = formatCoordinates(event.getPos());
        
        // Queue for batch processing
        if (!pendingRequests.offer(new WebhookRequest(playerName, blockName, coordinates, currentTime))) {
            LOGGER.warning("Webhook queue full - dropping request");
        }
    }
    
    private static boolean checkPlayerCooldown(String playerName, long currentTime) {
        Long lastBreak = playerCooldowns.put(playerName, currentTime);
        return lastBreak == null || (currentTime - lastBreak) >= PLAYER_COOLDOWN_MS;
    }
    
    private static boolean isInGlobalCooldown(long currentTime) {
        return (currentTime - lastRateLimitTime.get()) < RATE_LIMIT_COOLDOWN_MS;
    }
    
    private static String getCachedBlockName(Block block) {
        return BLOCK_NAME_CACHE.computeIfAbsent(block, b -> {
            ResourceLocation id = ForgeRegistries.BLOCKS.getKey(b);
            return id != null ? formatBlockName(id.getPath()) : "UNKNOWN BLOCK";
        });
    }
    
    private static String formatBlockName(String path) {
        return path.replace("_", " ").toUpperCase();
    }
    
    private static void processBatch() {
        try {
            long currentTime = System.currentTimeMillis();
            
            // Reset rate limit counter if needed
            if (shouldResetRateLimit(currentTime)) {
                resetRateLimit(currentTime);
            }
            
            // Check if we can send more requests
            if (isInGlobalCooldown(currentTime) || 
                requestsThisMinute.get() >= MAX_REQUESTS_PER_MINUTE) {
                return;
            }
            
            // Collect batch efficiently
            List<WebhookRequest> batch = collectBatch();
            if (!batch.isEmpty()) {
                sendBatchWebhook(batch);
                requestsThisMinute.incrementAndGet();
            }
            
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error processing batch", e);
        }
    }
    
    private static boolean shouldResetRateLimit(long currentTime) {
        return (currentTime - lastMinuteReset.get()) >= 60000L;
    }
    
    private static void resetRateLimit(long currentTime) {
        lastMinuteReset.set(currentTime);
        requestsThisMinute.set(0);
    }
    
    private static List<WebhookRequest> collectBatch() {
        List<WebhookRequest> batch = new ArrayList<>(BATCH_SIZE);
        long deadline = System.currentTimeMillis() + BATCH_TIMEOUT_MS;
        
        while (batch.size() < BATCH_SIZE && System.currentTimeMillis() < deadline) {
            WebhookRequest request = pendingRequests.poll();
            if (request == null) break;
            batch.add(request);
        }
        
        return batch;
    }
    
    private static void sendBatchWebhook(List<WebhookRequest> batch) {
        try {
            String jsonBody = buildOptimizedWebhookJson(batch);
            HttpPost post = createHttpPost(jsonBody);
            
            try (CloseableHttpResponse response = HTTP_CLIENT.execute(post)) {
                handleHttpResponse(response, batch);
            }
            
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "HTTP error", e);
            requeueBatch(batch);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error", e);
        }
    }
    
    private static HttpPost createHttpPost(String jsonBody) throws IOException {
        HttpPost post = new HttpPost(WEBHOOK_URL);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("User-Agent", "VLMP-Logger/2.1");
        post.setEntity(new StringEntity(jsonBody, "UTF-8"));
        return post;
    }
    
    private static void handleHttpResponse(CloseableHttpResponse response, List<WebhookRequest> batch) {
        int status = response.getStatusLine().getStatusCode();
        
        switch (status) {
            case 200:
            case 204:
                LOGGER.fine("Batch sent successfully (" + batch.size() + " blocks)");
                break;
            case 429:
                lastRateLimitTime.set(System.currentTimeMillis());
                LOGGER.warning("Rate limited - entering cooldown");
                requeueBatch(batch);
                break;
            default:
                LOGGER.warning("Webhook failed - Status: " + status);
                break;
        }
    }
    
    private static void requeueBatch(List<WebhookRequest> batch) {
        for (WebhookRequest req : batch) {
            if (!pendingRequests.offer(req)) {
                break; // Queue full, drop remaining
            }
        }
    }
    
    private static String buildOptimizedWebhookJson(List<WebhookRequest> batch) {
        String dateTime = LocalDateTime.now().format(DATETIME_FORMATTER);
        String timestamp = Instant.now().toString();
        
        JsonObject root = new JsonObject();
        root.add("content", null);
        
        JsonArray embeds = new JsonArray();
        JsonObject embed = createEmbed(batch, dateTime, timestamp);
        embeds.add(embed);
        
        root.add("embeds", embeds);
        root.add("attachments", new JsonArray());
        
        return root.toString();
    }
    
    private static JsonObject createEmbed(List<WebhookRequest> batch, String dateTime, String timestamp) {
        JsonObject embed = new JsonObject();
        embed.addProperty("color", 57599);
        embed.addProperty("timestamp", timestamp);
        
        if (batch.size() == 1) {
            WebhookRequest req = batch.get(0);
            embed.addProperty("title", "🍂 SSMP | BLOCK BROKEN");
            embed.addProperty("description", "**" + req.playerName + "** broke a block");
            embed.add("fields", createSingleBlockFields(req, dateTime));
        } else {
            embed.addProperty("title", "🍂 SSMP | BATCH LOG");
            embed.addProperty("description", buildBatchDescription(batch));
            embed.add("fields", createBatchFields(batch.size(), dateTime));
        }
        
        // Footer
        JsonObject footer = new JsonObject();
        footer.addProperty("text", "Abyss Archives Logger v2.1");
        embed.add("footer", footer);
        
        return embed;
    }
    
    private static JsonArray createSingleBlockFields(WebhookRequest req, String dateTime) {
        JsonArray fields = new JsonArray();
        addField(fields, "Time & Date", dateTime, true);
        addField(fields, "Block", req.blockName, true);
        addField(fields, "Coordinates", req.coordinates, true);
        return fields;
    }
    
    private static JsonArray createBatchFields(int count, String dateTime) {
        JsonArray fields = new JsonArray();
        addField(fields, "Time & Date", dateTime, true);
        addField(fields, "Total Blocks", String.valueOf(count), true);
        addField(fields, "Status", "Batch Processed", true);
        return fields;
    }
    
    private static String buildBatchDescription(List<WebhookRequest> batch) {
        StringBuilder desc = new StringBuilder("**").append(batch.size()).append(" blocks broken:**\n\n");
        
        for (WebhookRequest req : batch) {
            desc.append("• **").append(req.playerName)
                .append("** broke **").append(req.blockName)
                .append("** at ").append(req.coordinates).append("\n");
        }
        
        return desc.toString();
    }
    
    private static void addField(JsonArray fields, String name, String value, boolean inline) {
        JsonObject field = new JsonObject();
        field.addProperty("name", name);
        field.addProperty("value", value);
        field.addProperty("inline", inline);
        fields.add(field);
    }
    
    private static boolean isAllowedServer() {
        try {
            MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
            if (server == null) return false;
            
            String host = server.getLocalIp();
            if (host == null) return false;
            
            return ALLOWED_SERVERS.contains(host) ||
                   ALLOWED_SERVERS.contains(host + ":" + server.getPort()) ||
                   (("0.0.0.0".equals(host) || host.isEmpty()) && 
                    (ALLOWED_SERVERS.contains("localhost") || ALLOWED_SERVERS.contains("127.0.0.1")));
                    
        } catch (Exception e) {
            return false;
        }
    }
    
    private static String formatCoordinates(BlockPos pos) {
        return String.format("X:%d Y:%d Z:%d", pos.getX(), pos.getY(), pos.getZ());
    }
    
    private static void cleanupCaches() {
        try {
            long threshold = System.currentTimeMillis() - (PLAYER_COOLDOWN_MS * 10);
            playerCooldowns.entrySet().removeIf(entry -> entry.getValue() < threshold);
            
            if (BLOCK_NAME_CACHE.size() > MAX_CACHE_SIZE) {
                BLOCK_NAME_CACHE.clear(); // Simple cleanup strategy
            }
            
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Error during cache cleanup", e);
        }
    }
    
    // Optimized data class with reduced memory footprint
    private static final class WebhookRequest {
        final String playerName;
        final String blockName;
        final String coordinates;
        final long timestamp;
        
        WebhookRequest(String playerName, String blockName, String coordinates, long timestamp) {
            this.playerName = playerName;
            this.blockName = blockName;
            this.coordinates = coordinates;
            this.timestamp = timestamp;
        }
    }
}