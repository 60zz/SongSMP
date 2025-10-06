package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.util.RandomSource;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlayerRenasceuProcedure {
    @SubscribeEvent
    public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
        execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
    }

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        execute(null, world, x, y, z, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        
        // Criar efeito de reviver com partículas místicas
        createRespawnParticleEffect(entity);
        
        // Som original mantido
        if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
                _level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("vl_abyss:spawnou")), SoundSource.MASTER, 1, 1);
            } else {
                _level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("vl_abyss:spawnou")), SoundSource.MASTER, 1, 1, false);
            }
        }
    }
    
    private static void createRespawnParticleEffect(Entity entity) {
        if (entity.level().isClientSide() || entity.getServer() == null) {
            return;
        }
        
        // Efeito de "materialização" - partículas surgindo do chão
        createMaterializationEffect(entity);
        
        // Espiral ascendente de partículas
        createSpiralEffect(entity);
        
        // Partículas caindo suavemente (como neve mágica)
        createMagicSnowfall(entity);
        
        // Pulso de energia ao final
        scheduleEnergyPulse(entity, 15); // Após 0.75 segundos
        
        // Partículas finais flutuantes
        scheduleFloatingParticles(entity, 25); // Após 1.25 segundos
    }
    
    private static void createMaterializationEffect(Entity entity) {
        RandomSource random = entity.level().getRandom();
        
        // Partículas emergindo do solo em círculos concêntricos
        for (int ring = 1; ring <= 3; ring++) {
            for (int angle = 0; angle < 360; angle += 45) {
                double radians = Math.toRadians(angle);
                double radius = ring * 0.8;
                double x = Math.cos(radians) * radius;
                double z = Math.sin(radians) * radius;
                double velocityY = 0.4 + random.nextDouble() * 0.3;
                
                String particleCommand = String.format(
                    "particle vl_abyss:particulamorreu_1 ~%.2f ~-0.2 ~%.2f 0.0 %.2f 0.0 0.15 1 force @a[distance=..32]",
                    x, z, velocityY
                );
                executeParticleCommand(entity, particleCommand);
            }
        }
    }
    
    private static void createSpiralEffect(Entity entity) {
        // Espiral de partículas subindo ao redor do jogador
        for (int i = 0; i < 20; i++) {
            double angle = i * 18; // 18 graus por partícula
            double radians = Math.toRadians(angle);
            double radius = 1.2 - (i * 0.05); // Raio diminui conforme sobe
            double height = i * 0.15; // Altura aumenta
            double x = Math.cos(radians) * radius;
            double z = Math.sin(radians) * radius;
            
            String particleCommand = String.format(
                "particle vl_abyss:particulamorreu_2 ~%.2f ~%.2f ~%.2f 0.0 0.2 0.0 0.1 1 force @a[distance=..32]",
                x, height, z
            );
            executeParticleCommand(entity, particleCommand);
        }
    }
    
    private static void createMagicSnowfall(Entity entity) {
        RandomSource random = entity.level().getRandom();
        
        // Partículas caindo suavemente de cima (como neve mágica)
        for (int i = 0; i < 12; i++) {
            double offsetX = (random.nextDouble() - 0.5) * 4.0;
            double offsetZ = (random.nextDouble() - 0.5) * 4.0;
            double startY = 4.0 + random.nextDouble() * 2.0;
            
            String particleCommand = String.format(
                "particle vl_abyss:particulamorreu_1 ~%.2f ~%.2f ~%.2f 0.0 -0.1 0.0 0.05 1 force @a[distance=..32]",
                offsetX, startY, offsetZ
            );
            executeParticleCommand(entity, particleCommand);
        }
    }
    
    private static void scheduleEnergyPulse(Entity entity, int delayTicks) {
        if (!(entity.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        
        serverLevel.getServer().tell(new net.minecraft.server.TickTask(
            serverLevel.getServer().getTickCount() + delayTicks, () -> {
                if (!entity.isRemoved()) {
                    // Pulso de energia - explosão controlada para fora
                    for (int angle = 0; angle < 360; angle += 20) {
                        double radians = Math.toRadians(angle);
                        double x = Math.cos(radians) * 2.0;
                        double z = Math.sin(radians) * 2.0;
                        
                        String particleCommand = String.format(
                            "particle vl_abyss:particulamorreu_2 ~%.2f ~1 ~%.2f %.2f 0.1 %.2f 0.3 1 force @a[distance=..32]",
                            x * 0.3, z * 0.3, x * 0.15, z * 0.15
                        );
                        executeParticleCommand(entity, particleCommand);
                    }
                }
            }
        ));
    }
    
    private static void scheduleFloatingParticles(Entity entity, int delayTicks) {
        if (!(entity.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        
        serverLevel.getServer().tell(new net.minecraft.server.TickTask(
            serverLevel.getServer().getTickCount() + delayTicks, () -> {
                if (!entity.isRemoved()) {
                    RandomSource random = entity.level().getRandom();
                    
                    // Partículas flutuando suavemente ao redor do jogador
                    for (int i = 0; i < 8; i++) {
                        double offsetX = (random.nextDouble() - 0.5) * 2.5;
                        double offsetY = random.nextDouble() * 2.0 + 0.5;
                        double offsetZ = (random.nextDouble() - 0.5) * 2.5;
                        
                        String particleCommand = String.format(
                            "particle vl_abyss:particulamorreu_1 ~%.2f ~%.2f ~%.2f 0.0 0.05 0.0 0.02 1 force @a[distance=..32]",
                            offsetX, offsetY, offsetZ
                        );
                        executeParticleCommand(entity, particleCommand);
                    }
                }
            }
        ));
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
}