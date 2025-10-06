package net.mcreator.vlabyss.procedures;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.CloseableHttpResponse;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.MinecraftServer;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import javax.annotation.Nullable;
import java.time.Instant;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber
public class LogsMorreuProcedureProcedure {
    
    private static final List<String> ALLOWED_SERVERS = Arrays.asList(
        "vlmpsmp.elgaehost.com.br",
        "elgae-sp1-08.elgaehost.com.br:25863",
        "elgae-sp1-08.elgaehost.com.br",
        "127.0.0.1",
        "localhost"
    );
    
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event != null && event.getEntity() != null) {
            execute(event);
        }
    }

    public static void execute() {
        execute(null);
    }

    private static void execute(@Nullable Event event) {
        if (event instanceof LivingDeathEvent) {
            LivingDeathEvent deathEvent = (LivingDeathEvent) event;
            
            Entity victim = deathEvent.getEntity();
            if (!(victim instanceof Player)) {
                return;
            }
            
            if (victim.level().isClientSide()) {
                return;
            }
            
            if (!isAllowedServer()) {
                return;
            }
            
            String victimName = victim.getName().getString();
            DamageSource damageSource = deathEvent.getSource();
            Entity killer = damageSource.getEntity();
            
            String killerName = getKillerName(killer, damageSource);
            
            CompletableFuture.runAsync(() -> sendDiscordWebhook(victimName, killerName));
        }
    }
    
    private static boolean isAllowedServer() {
        try {
            MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
            if (server == null) {
                return false;
            }
            
            String serverHost = server.getLocalIp();
            int serverPort = server.getPort();
            
            String hostWithPort = serverHost + ":" + serverPort;
            String hostOnly = serverHost;
            
            for (String allowedServer : ALLOWED_SERVERS) {
                if (allowedServer.equalsIgnoreCase(hostOnly) || 
                    allowedServer.equalsIgnoreCase(hostWithPort)) {
                    return true;
                }
            }
            
            if (serverHost != null && (serverHost.equals("0.0.0.0") || serverHost.isEmpty())) {
                for (String allowedServer : ALLOWED_SERVERS) {
                    if (allowedServer.equalsIgnoreCase("localhost") || allowedServer.equalsIgnoreCase("127.0.0.1")) {
                        return true;
                    }
                }
            }
            
            return false;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    private static String getKillerName(Entity killer, DamageSource damageSource) {
        if (killer instanceof Player) {
            return killer.getName().getString();
        } else if (killer != null) {
            return killer.getName().getString();
        } else {
            String damageType = damageSource.getMsgId();
            switch (damageType) {
                case "fall": return "Dano de Queda";
                case "lava": return "Lava";
                case "fire": return "Fogo";
                case "drown": return "Afogamento";
                case "starve": return "Fome";
                case "cactus": return "Cacto";
                case "void": return "Void";
                case "explosion": return "Explosão";
                case "magic": return "Magia";
                case "wither": return "Wither";
                default: return "Causa Desconhecida (" + damageType + ")";
            }
        }
    }
    
    private static void sendDiscordWebhook(String victimName, String killerName) {
        try {
            String timestamp = Instant.now().toString();
            
            JsonObject mainObject = new JsonObject();
            mainObject.add("content", null);
            
            JsonArray embedsArray = new JsonArray();
            JsonObject embedObject = new JsonObject();
            
            embedObject.addProperty("title", "🍂 SSMP | REGISTROS");
            embedObject.addProperty("description", "**" + victimName + "** foi morto por **" + killerName + "**");
            embedObject.addProperty("color", 57599);
            embedObject.addProperty("timestamp", timestamp);
            
            JsonObject footerObject = new JsonObject();
            footerObject.addProperty("text", "Sistema de Logs Abyss Archives | SSMP");
            embedObject.add("footer", footerObject);
            
            embedsArray.add(embedObject);
            mainObject.add("embeds", embedsArray);
            mainObject.add("attachments", new JsonArray());
            
            String jsonBody = mainObject.toString();
            
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpPost httppost = new HttpPost("https://discord.com/api/webhooks/1398516453887574149/hJXiTGmzRToKn5SaoyM8WZ3ZZ4f1Pq6fEj5f1D-UNMulq135xJsT6cJbuKKIW3qF5pQi");
                
                StringEntity params = new StringEntity(jsonBody, "UTF-8");
                httppost.addHeader("Content-Type", "application/json");
                httppost.setEntity(params);
                
                try (CloseableHttpResponse httpresponse = httpclient.execute(httppost)) {
                    // Webhook enviado - sem logs
                }
            }
            
        } catch (Exception e) {
            // Erro silencioso
        }
    }
}