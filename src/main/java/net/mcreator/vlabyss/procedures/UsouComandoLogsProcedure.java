package net.mcreator.vlabyss.procedures;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.CloseableHttpResponse;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

import net.minecraft.world.entity.Entity;
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
public class UsouComandoLogsProcedure {
    
    private static final List<String> ALLOWED_SERVERS = Arrays.asList(
        "vlmpsmp.elgaehost.com.br",
        "elgae-sp1-08.elgaehost.com.br:25863",
        "elgae-sp1-08.elgaehost.com.br",
        "127.0.0.1",
        "localhost"
    );
    
    @SubscribeEvent
    public static void onCommand(CommandEvent event) {
        Entity entity = event.getParseResults().getContext().getSource().getEntity();
        if (entity != null) {
            execute(event);
        }
    }

    public static void execute() {
        execute(null);
    }

    private static void execute(@Nullable Event event) {
        if (event instanceof CommandEvent) {
            CommandEvent commandEvent = (CommandEvent) event;
            
            if (!isAllowedServer()) {
                return;
            }
            
            String staffName = commandEvent.getParseResults().getContext().getSource().getTextName();
            String commandUsed = commandEvent.getParseResults().getReader().getString();
            
            if (shouldLogCommand(commandUsed)) {
                CompletableFuture.runAsync(() -> sendDiscordWebhook(staffName, commandUsed));
            }
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
    
    private static boolean shouldLogCommand(String command) {
        String lowerCommand = command.toLowerCase().trim();
        
        return lowerCommand.startsWith("vlcmd ") || 
               lowerCommand.startsWith("/vlcmd ") ||
               lowerCommand.startsWith("/vldamage ") ||
               lowerCommand.startsWith("vldamage ") ||
               lowerCommand.startsWith("tell ") || 
               lowerCommand.startsWith("/tell ") ||
               lowerCommand.startsWith("minecraft:tp ") ||
               lowerCommand.startsWith("cmi tp ") ||
               lowerCommand.startsWith("/cmi tp ") ||
               lowerCommand.startsWith("/minecraft:tp ") ||
               lowerCommand.startsWith("msg ") || 
               lowerCommand.startsWith("/msg ") ||
               lowerCommand.startsWith("w ") || 
               lowerCommand.startsWith("/w ") ||
               lowerCommand.startsWith("minecraft:give ") || 
               lowerCommand.startsWith("/minecraft:give ") ||
               lowerCommand.startsWith("gamemode ") || 
               lowerCommand.startsWith("/gamemode ") ||
               lowerCommand.startsWith("cmi ");
    }
    
    private static void sendDiscordWebhook(String staffName, String command) {
        try {
            String timestamp = Instant.now().toString();
            
            JsonObject mainObject = new JsonObject();
            mainObject.add("content", null);
            
            JsonArray embedsArray = new JsonArray();
            JsonObject embedObject = new JsonObject();
            
            embedObject.addProperty("title", "🍂 SSMP | REGISTROS");
            embedObject.addProperty("description", "O " + staffName + " utilizou o comando:\n\n" +
                   "**Comando utilizado:**\n/" + command);
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
                HttpPost httppost = new HttpPost("https://discord.com/api/webhooks/1398503918602027079/UUtgHiVdxyS3-bqwW_v9kO2bE3fyVi_5lTEkF3FLzmO_nzTpxM3ZN-_qMfvBKTzxrUcn");
                
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