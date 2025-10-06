package net.mcreator.vlabyss.procedures;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.CloseableHttpResponse;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

import net.minecraft.world.entity.player.Player;
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
public class LogsSaiuServerProcedure {
    
    private static final List<String> ALLOWED_SERVERS = Arrays.asList(
        "vlmpsmp.elgaehost.com.br",
        "elgae-sp1-08.elgaehost.com.br:25863",
        "elgae-sp1-08.elgaehost.com.br",
        "127.0.0.1",
        "localhost"
    );
    
    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event != null && event.getEntity() != null) {
            execute(event);
        }
    }

    public static void execute() {
        execute(null);
    }

    private static void execute(@Nullable Event event) {
        if (event instanceof PlayerEvent.PlayerLoggedOutEvent) {
            PlayerEvent.PlayerLoggedOutEvent logoutEvent = (PlayerEvent.PlayerLoggedOutEvent) event;
            Player player = logoutEvent.getEntity();
            
            if (player.level().isClientSide()) {
                return;
            }
            
            if (!isAllowedServer()) {
                return;
            }
            
            String playerName = player.getName().getString();
            
            CompletableFuture.runAsync(() -> sendDiscordWebhook(playerName));
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
    
    private static void sendDiscordWebhook(String playerName) {
        try {
            String timestamp = Instant.now().toString();
            String skinUrl = "https://cravatar.eu/helmavatar/" + playerName + "/128.png";
            
            JsonObject mainObject = new JsonObject();
            mainObject.add("content", null);
            
            JsonArray embedsArray = new JsonArray();
            JsonObject embedObject = new JsonObject();
            
            embedObject.addProperty("title", "🍂 SSMP | REGISTROS");
            embedObject.addProperty("description", "**" + playerName + "** saiu do servidor");
            embedObject.addProperty("color", 57599);
            embedObject.addProperty("timestamp", timestamp);
            
            JsonObject thumbnailObject = new JsonObject();
            thumbnailObject.addProperty("url", skinUrl);
            embedObject.add("thumbnail", thumbnailObject);
            
            JsonObject footerObject = new JsonObject();
            footerObject.addProperty("text", "Sistema de Logs Abyss Archives | SSMP");
            embedObject.add("footer", footerObject);
            
            embedsArray.add(embedObject);
            mainObject.add("embeds", embedsArray);
            mainObject.add("attachments", new JsonArray());
            
            String jsonBody = mainObject.toString();
            
            // CORREÇÃO: CloseableHttpClient ao invés de CloseableHttpResponse
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpPost httppost = new HttpPost("https://discord.com/api/webhooks/1398537527081828375/BOQZ_4BMfggC_rrltbVv6qesOxF451I8Pe0zBSY8TOMYTgimxXVr5Saj0_H5WB3QzS8W");
                
                StringEntity params = new StringEntity(jsonBody, "UTF-8");
                httppost.addHeader("Content-Type", "application/json");
                httppost.setEntity(params);
                
                try (CloseableHttpResponse httpresponse = httpclient.execute(httppost)) {
                    // Webhook enviado - sem logs desnecessários
                    int statusCode = httpresponse.getStatusLine().getStatusCode();
                    if (statusCode != 200 && statusCode != 204) {
                        System.err.println("VLMP Logout Log - Discord returned error code: " + statusCode);
                    }
                }
            }
            
        } catch (IOException e) {
            System.err.println("VLMP Logout Log - HTTP Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("VLMP Logout Log - General Error: " + e.getMessage());
        }
    }
}