package net.mcreator.vlabyss.procedures;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.CloseableHttpResponse;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

import net.minecraft.world.entity.player.Player;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import com.google.gson.JsonObject;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Mod.EventBusSubscriber
public class LogsEnviouMensagemProcedureProcedure {
    
    // Configurações constantes
    private static final Set<String> ALLOWED_SERVERS = new HashSet<>();
    private static final String WEBHOOK_URL = "https://discord.com/api/webhooks/1398537527081828375/BOQZ_4BMfggC_rrltbVv6qesOxF451I8Pe0zBSY8TOMYTgimxXVr5Saj0_H5WB3QzS8W";
    private static final String SKIN_URL_TEMPLATE = "https://cravatar.eu/helmavatar/%s/64.png";
    private static final long CACHE_DURATION = 2000L; // 2 segundos
    private static final int MAX_CACHE_SIZE = 100;
    
    // Padrões de regex para limpeza de mensagens
    private static final Pattern COLOR_CODES = Pattern.compile("[&§][0-9a-fk-or]");
    private static final Pattern CHAT_PREFIXES = Pattern.compile("^(?:<[^>]+>|\\[[^\\]]+\\]|[^:»]+[»:]|\\*[^*]+\\*)\\s*");
    private static final Pattern EXTRA_SPACES = Pattern.compile("\\s+");
    
    private static final Map<String, Long> messageCache = new ConcurrentHashMap<>();
    
    static {
        // Inicializar servidores permitidos
        ALLOWED_SERVERS.add("vlmpsmp.elgaehost.com.br");
        ALLOWED_SERVERS.add("elgae-sp1-08.elgaehost.com.br:25863");
        ALLOWED_SERVERS.add("elgae-sp1-08.elgaehost.com.br");
        ALLOWED_SERVERS.add("127.0.0.1");
        ALLOWED_SERVERS.add("localhost");
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onServerChat(ServerChatEvent event) {
        if (!isAllowedServer() || event.getPlayer() == null) return;
        
        try {
            String playerName = event.getPlayer().getName().getString();
            String message = extractChatMessage(event);
            
            if (isValidMessage(message)) {
                processMessage(playerName, message);
            }
        } catch (Exception e) {
            // Falha silenciosa para não poluir o console
        }
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)  
    public static void onCommand(CommandEvent event) {
        if (!isAllowedServer()) return;
        
        try {
            CommandSourceStack source = event.getParseResults().getContext().getSource();
            if (!(source.getEntity() instanceof ServerPlayer)) return;
            
            ServerPlayer player = (ServerPlayer) source.getEntity();
            String playerName = player.getName().getString();
            String fullCommand = event.getParseResults().getReader().getString();
            
            String message = extractCommandMessage(fullCommand, playerName);
            if (isValidMessage(message)) {
                processMessage(playerName, message);
            }
        } catch (Exception e) {
            // Falha silenciosa
        }
    }
    
    private static String extractChatMessage(ServerChatEvent event) {
        // Tentar múltiplos métodos de extração de mensagem
        String message = null;
        
        try {
            // Método 1: Tentar getRawText() se disponível
            message = event.getRawText();
            if (isValidMessage(message)) return message;
        } catch (Exception ignored) {}
        
        try {
            // Método 2: Usar getMessage().getString()
            Component messageComponent = event.getMessage();
            if (messageComponent != null) {
                message = messageComponent.getString();
                if (isValidMessage(message)) return message;
            }
        } catch (Exception ignored) {}
        
        try {
            // Método 3: Tentar getContents().toString() se disponível
            Component messageComponent = event.getMessage();
            if (messageComponent != null && messageComponent.getContents() != null) {
                message = messageComponent.getContents().toString();
                if (isValidMessage(message)) return message;
            }
        } catch (Exception ignored) {}
        
        try {
            // Método 4: Usar reflection para acessar campos internos
            Object messageObj = event.getMessage();
            if (messageObj != null) {
                // Tentar alguns campos comuns de mensagem
                java.lang.reflect.Field[] fields = messageObj.getClass().getDeclaredFields();
                for (java.lang.reflect.Field field : fields) {
                    field.setAccessible(true);
                    Object value = field.get(messageObj);
                    if (value instanceof String && isValidMessage((String) value)) {
                        return (String) value;
                    }
                }
            }
        } catch (Exception ignored) {}
        
        return null;
    }
    
    private static String extractCommandMessage(String command, String playerName) {
        if (command == null || command.trim().isEmpty()) {
            return null;
        }
        
        String cmd = command.trim();
        if (cmd.startsWith("/say ")) {
            return cmd.substring(5).trim();
        } else if (cmd.startsWith("say ")) {
            return cmd.substring(4).trim();
        } else if (cmd.startsWith("/me ")) {
            return "*" + playerName + " " + cmd.substring(4).trim() + "*";
        } else if (cmd.startsWith("me ")) {
            return "*" + playerName + " " + cmd.substring(3).trim() + "*";
        }
        
        return null;
    }
    
    private static boolean isValidMessage(String message) {
        return message != null && !message.trim().isEmpty() && message.length() > 0;
    }
    
    private static void processMessage(String playerName, String rawMessage) {
        String cleanMessage = cleanMessage(rawMessage, playerName);
        if (!isValidMessage(cleanMessage) || isDuplicate(playerName, cleanMessage)) {
            return;
        }
        
        // Executar webhook de forma assíncrona
        CompletableFuture.runAsync(() -> sendDiscordWebhook(playerName, cleanMessage));
        cleanupCache();
    }
    
    private static String cleanMessage(String message, String playerName) {
        if (!isValidMessage(message)) return null;
        
        // Remover códigos de cor
        String cleaned = COLOR_CODES.matcher(message).replaceAll("");
        
        // Remover prefixos comuns de chat (<nick>, [nick], nick:, nick», *action*)
        cleaned = CHAT_PREFIXES.matcher(cleaned).replaceFirst("");
        
        // Remover espaços extras
        cleaned = EXTRA_SPACES.matcher(cleaned).replaceAll(" ");
        
        // Trim final
        cleaned = cleaned.trim();
        
        // Se a mensagem limpa for igual ao nome do jogador, provavelmente é só o prefixo
        if (cleaned.equalsIgnoreCase(playerName) || cleaned.isEmpty()) {
            return null;
        }
        
        return cleaned;
    }
    
    private static boolean isDuplicate(String playerName, String message) {
        String cacheKey = playerName + ":" + message.hashCode();
        long currentTime = System.currentTimeMillis();
        
        Long lastSent = messageCache.get(cacheKey);
        if (lastSent != null && (currentTime - lastSent) < CACHE_DURATION) {
            return true;
        }
        
        messageCache.put(cacheKey, currentTime);
        return false;
    }
    
    private static void cleanupCache() {
        if (messageCache.size() <= MAX_CACHE_SIZE) return;
        
        long threshold = System.currentTimeMillis() - (CACHE_DURATION * 10);
        messageCache.entrySet().removeIf(entry -> entry.getValue() < threshold);
    }
    
    private static boolean isAllowedServer() {
        try {
            MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
            if (server == null) return false;
            
            String serverHost = server.getLocalIp();
            if (serverHost == null) return false;
            
            int serverPort = server.getPort();
            
            return ALLOWED_SERVERS.contains(serverHost) ||
                   ALLOWED_SERVERS.contains(serverHost + ":" + serverPort) ||
                   (("0.0.0.0".equals(serverHost) || serverHost.isEmpty()) && 
                    (ALLOWED_SERVERS.contains("localhost") || ALLOWED_SERVERS.contains("127.0.0.1")));
                    
        } catch (Exception e) {
            return false;
        }
    }
    
    private static void sendDiscordWebhook(String playerName, String message) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(WEBHOOK_URL);
            
            JsonObject payload = new JsonObject();
            payload.addProperty("content", message);
            payload.addProperty("username", playerName);
            payload.addProperty("avatar_url", String.format(SKIN_URL_TEMPLATE, playerName));
            
            post.setEntity(new StringEntity(payload.toString(), "UTF-8"));
            post.setHeader("Content-Type", "application/json");
            post.setHeader("User-Agent", "Minecraft-Discord-Bridge/1.0");
            
            try (CloseableHttpResponse response = httpClient.execute(post)) {
                // Verificar se foi enviado com sucesso silenciosamente
                int status = response.getStatusLine().getStatusCode();
                if (status != 200 && status != 204) {
                    // Log apenas em caso de erro real
                    System.err.println("Erro no webhook Discord: " + status);
                }
            }
            
        } catch (Exception e) {
            System.err.println("Erro ao enviar webhook: " + e.getMessage());
        }
    }
    
    // Método de teste (remover em produção)
    public static void testWebhook() {
        sendDiscordWebhook("TesteBot", "Teste de webhook - sistema funcionando!");
    }
    
    // Métodos de compatibilidade
    public static void execute() {
        execute(null);
    }
    
    private static void execute(@Nullable Event event) {
        // Método mantido para compatibilidade com MCreator
    }
}