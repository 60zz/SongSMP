package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.CommandEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ProcedureIconicSpyProcedure {
    @SubscribeEvent
    public static void onCommand(CommandEvent event) {
        Entity entity = event.getParseResults().getContext().getSource().getEntity();
        if (entity != null) {
            execute(event, entity);
        }
    }

    public static void execute(Entity entity) {
        execute(null, entity);
    }

    private static void execute(@Nullable Event event, Entity entity) {
        if (entity == null || event == null)
            return;
        
        // Verifica se o VL_Spy está ativado
        if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                .orElse(new VlAbyssModVariables.PlayerVariables())).VL_Spy == true) {
            
            // Obtém o comando completo que foi executado
            CommandEvent commandEvent = (CommandEvent) event;
            String fullCommand = commandEvent.getParseResults().getReader().getString();
            
            // Verifica se é um comando de tell/msg/w (comandos de mensagem privada)
            if (fullCommand.startsWith("tell ") || fullCommand.startsWith("msg ") || 
                fullCommand.startsWith("w ") || fullCommand.startsWith("/tell ") || 
                fullCommand.startsWith("cmi ") || fullCommand.startsWith("/cmi ") || 
                fullCommand.startsWith("minecraft:give ") || fullCommand.startsWith("/minecraft:give ") || 
                fullCommand.startsWith("give ") || fullCommand.startsWith("/give ") || 
                fullCommand.startsWith("minecraft:effect ") || fullCommand.startsWith("/minecraft:effect ") || 
                fullCommand.startsWith("/msg ") || fullCommand.startsWith("/w ")) {
                
                // Obtém o nome do jogador que executou o comando
                String playerName = "Unknown";
                CommandSourceStack source = commandEvent.getParseResults().getContext().getSource();
                if (source.getEntity() instanceof Player) {
                    playerName = ((Player) source.getEntity()).getName().getString();
                }
                
                // Formata e envia a mensagem de spy
                String spyMessage = "§3" + playerName + "§b � /" + fullCommand;
                
                // Envia a mensagem para todos os jogadores com VL_Spy ativo
                if (entity instanceof Player _player && !_player.level().isClientSide()) {
                    _player.displayClientMessage(Component.literal(spyMessage), false);
                }
            }
        }
    }
}