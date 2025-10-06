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

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.MinecraftServer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import javax.annotation.Nullable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber
public class PlayerDeuRespawnProcedure {
    
    private static final List<String> ALLOWED_SERVERS = Arrays.asList(
        "vlmpsmp.elgaehost.com.br",
        "elgae-sp1-08.elgaehost.com.br:25863",
        "elgae-sp1-08.elgaehost.com.br",
        "127.0.0.1",
        "localhost"
    );
    
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        execute(event, event.getEntity());
    }

    public static void execute(Entity entity) {
        execute(null, entity);
    }

    private static void execute(@Nullable Event event, Entity entity) {
        if (entity == null)
            return;
            
        if (!isAllowedServer()) {
            return;
        }
        
        if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).vidas == 0) {
            String playerName = entity.getName().getString();
            
            CompletableFuture.runAsync(() -> sendDiscordWebhook(playerName));
            
            {
                Entity _ent = entity;
                if (!_ent.level().isClientSide() && _ent.getServer() != null) {
                    _ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
                            _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "minecraft:kick @s Você perdeu TODAS as suas vidas e sucumbiu ao abismo.");
                }
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
    
    private static void sendDiscordWebhook(String playerName) {
        try {
            Instant now = Instant.now();
            String currentDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                    .withZone(ZoneId.systemDefault())
                    .format(now);
            String timestamp = now.toString();
            
            JsonObject mainObject = new JsonObject();
            mainObject.add("content", null);
            
            JsonArray embedsArray = new JsonArray();
            JsonObject embedObject = new JsonObject();
            
            embedObject.addProperty("title", "❤ SSMP | JOGADOR FINALIZADO");
            embedObject.addProperty("description", "**" + playerName + "** acabou de perder **TODAS** suas vidas e está \ntentando logar no servidor");
            embedObject.addProperty("color", 5814783);
            embedObject.addProperty("timestamp", timestamp);
            
            JsonArray fieldsArray = new JsonArray();
            JsonObject fieldObject = new JsonObject();
            fieldObject.addProperty("name", "Data & Hora");
            fieldObject.addProperty("value", currentDateTime);
            fieldObject.addProperty("inline", true);
            fieldsArray.add(fieldObject);
            embedObject.add("fields", fieldsArray);
            
            JsonObject footerObject = new JsonObject();
            footerObject.addProperty("text", "Sistema de Logs Abyss Archives | SSMP");
            embedObject.add("footer", footerObject);
            
            embedsArray.add(embedObject);
            mainObject.add("embeds", embedsArray);
            mainObject.add("attachments", new JsonArray());
            
            String jsonBody = mainObject.toString();
            
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpPost httppost = new HttpPost("https://discord.com/api/webhooks/1405407672576118784/tLp6x5nMLXEVDFvxKj2oYuvWxofFRtMdcyhLDcpqTJqjRrjjz6tAmihYiwdjUE5NWbjd");
                
                StringEntity params = new StringEntity(jsonBody, "UTF-8");
                httppost.addHeader("Content-Type", "application/json");
                httppost.setEntity(params);
                
                try (CloseableHttpResponse httpresponse = httpclient.execute(httppost)) {
                }
            }
            
        } catch (Exception e) {
            // Erro silencioso
        }
    }
}