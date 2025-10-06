package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.util.RandomSource;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MorreuFezParticulaProcedure {
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event != null && event.getEntity() != null) {
            execute(event, event.getEntity());
        }
    }

    public static void execute(Entity entity) {
        execute(null, entity);
    }

    private static void execute(@Nullable Event event, Entity entity) {
        if (entity == null)
            return;
        
        if (entity instanceof Player) {
            createDeathParticleExplosion(entity);
        }
    }
    
    private static void createDeathParticleExplosion(Entity entity) {
        if (entity.level().isClientSide() || entity.getServer() == null) {
            return;
        }
        
        RandomSource random = entity.level().getRandom();
        
        // Partículas principais - explosão central
        executeParticleCommand(entity, 
            "particle vl_abyss:particulamorreu_2 ~ ~1 ~ 1.2 1.5 1.2 0.3 25 force @a[distance=..32]");
        
        // Partículas secundárias - mais espalhadas
        executeParticleCommand(entity, 
            "particle vl_abyss:particulamorreu_1 ~ ~1 ~ 0.8 1.2 0.8 0.5 20 force @a[distance=..32]");
        
        // Partículas que voam para cima (efeito principal)
        for (int i = 0; i < 8; i++) {
            double offsetX = (random.nextDouble() - 0.5) * 2.0;
            double offsetZ = (random.nextDouble() - 0.5) * 2.0;
            double velocityY = 0.8 + random.nextDouble() * 0.4; // Velocidade para cima
            
            String particleCommand = String.format(
                "particle vl_abyss:particulamorreu_2 ~%.2f ~0.5 ~%.2f 0.1 %.2f 0.1 0.1 1 force @a[distance=..32]",
                offsetX, offsetZ, velocityY
            );
            executeParticleCommand(entity, particleCommand);
        }
        
        // Partículas menores em círculo (efeito de onda)
        for (int angle = 0; angle < 360; angle += 30) {
            double radians = Math.toRadians(angle);
            double radius = 1.5 + random.nextDouble() * 0.5;
            double x = Math.cos(radians) * radius;
            double z = Math.sin(radians) * radius;
            
            String particleCommand = String.format(
                "particle vl_abyss:particulamorreu_1 ~%.2f ~0.3 ~%.2f 0.1 0.3 0.1 0.2 1 force @a[distance=..32]",
                x, z
            );
            executeParticleCommand(entity, particleCommand);
        }
        
        // Partículas que caem lentamente (efeito de chuva)
        scheduleDelayedParticles(entity, 10); // Delay de 10 ticks (0.5 segundos)
        scheduleDelayedParticles(entity, 20); // Delay de 20 ticks (1 segundo)
    }
    
    private static void executeParticleCommand(Entity entity, String command) {
        entity.getServer().getCommands().performPrefixedCommand(
            new CommandSourceStack(
                CommandSource.NULL, 
                entity.position(), 
                entity.getRotationVector(), 
                entity.level() instanceof ServerLevel ? (ServerLevel) entity.level() : null, 
                4,
                entity.getName().getString(), 
                entity.getDisplayName(), 
                entity.level().getServer(), 
                entity
            ), 
            command
        );
    }
    
    private static void scheduleDelayedParticles(Entity entity, int delayTicks) {
        if (!(entity.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        
        // Agenda partículas para aparecer depois de um delay
        serverLevel.getServer().tell(new net.minecraft.server.TickTask(
            serverLevel.getServer().getTickCount() + delayTicks, () -> {
                if (!entity.isRemoved()) { // Verifica se a entidade ainda existe
                    RandomSource random = entity.level().getRandom();
                    
                    // Partículas que caem de cima
                    for (int i = 0; i < 5; i++) {
                        double offsetX = (random.nextDouble() - 0.5) * 3.0;
                        double offsetZ = (random.nextDouble() - 0.5) * 3.0;
                        double startY = 3.0 + random.nextDouble() * 2.0;
                        
                        String particleCommand = String.format(
                            "particle vl_abyss:particulamorreu_1 ~%.2f ~%.2f ~%.2f 0.0 -0.3 0.0 0.1 1 force @a[distance=..32]",
                            offsetX, startY, offsetZ
                        );
                        executeParticleCommand(entity, particleCommand);
                    }
                }
            }
        ));
    }
}