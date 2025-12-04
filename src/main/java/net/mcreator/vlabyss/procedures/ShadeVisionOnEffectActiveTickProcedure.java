package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.phys.AABB;
import net.minecraft.ChatFormatting;
import net.minecraft.world.scores.Team;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.server.level.ServerLevel;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class ShadeVisionOnEffectActiveTickProcedure {

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null || !(world instanceof ServerLevel))
            return;
        
        ServerLevel serverLevel = (ServerLevel) world;
        
        // Define o raio de detecção (em blocos)
        double detectionRadius = 25.0;
        
        // Cria uma área de busca ao redor do jogador
        AABB searchArea = new AABB(
            x - detectionRadius, y - detectionRadius, z - detectionRadius,
            x + detectionRadius, y + detectionRadius, z + detectionRadius
        );
        
        // Conjunto para rastrear entidades que devem ter glow
        Set<String> entitiesWithGlow = new HashSet<>();
        
        // Aplica o efeito de glow roxo em TODAS as entidades (usando Entity ao invés de LivingEntity)
        List<Entity> nearbyEntities = serverLevel.getEntities(entity, searchArea,
            e -> (e instanceof LivingEntity || e instanceof ItemEntity) && !e.isSpectator());
        
        for (Entity target : nearbyEntities) {
            // Aplica glow
            if (!target.isCurrentlyGlowing()) {
                target.setGlowingTag(true);
            }
            applyPurpleGlow(target, serverLevel);
            entitiesWithGlow.add(target.getStringUUID());
        }
        
        // Remove glow de entidades distantes
        removeGlowFromDistantEntities(serverLevel, entity, detectionRadius, entitiesWithGlow);
    }
    
    // Método auxiliar para aplicar o glow roxo
    private static void applyPurpleGlow(Entity entity, ServerLevel level) {
        Scoreboard scoreboard = level.getScoreboard();
        PlayerTeam purpleTeam = scoreboard.getPlayerTeam("purple_glow");
        
        if (purpleTeam == null) {
            purpleTeam = scoreboard.addPlayerTeam("purple_glow");
            purpleTeam.setColor(ChatFormatting.LIGHT_PURPLE);
        }
        
        String uuid = entity.getStringUUID();
        
        // Remove de qualquer time anterior e adiciona ao time roxo
        if (entity.getTeam() != purpleTeam) {
            Team currentTeam = entity.getTeam();
            if (currentTeam != null && currentTeam instanceof PlayerTeam) {
                scoreboard.removePlayerFromTeam(uuid, (PlayerTeam) currentTeam);
            }
            scoreboard.addPlayerToTeam(uuid, purpleTeam);
        }
    }
    
    // Método para remover glow de entidades distantes
    private static void removeGlowFromDistantEntities(ServerLevel level, Entity sourceEntity, 
                                                      double radius, Set<String> currentEntities) {
        Scoreboard scoreboard = level.getScoreboard();
        PlayerTeam purpleTeam = scoreboard.getPlayerTeam("purple_glow");
        
        if (purpleTeam == null)
            return;
        
        // Área um pouco maior para verificar entidades que podem ter saído
        AABB extendedArea = new AABB(
            sourceEntity.getX() - radius * 1.5, sourceEntity.getY() - radius * 1.5, sourceEntity.getZ() - radius * 1.5,
            sourceEntity.getX() + radius * 1.5, sourceEntity.getY() + radius * 1.5, sourceEntity.getZ() + radius * 1.5
        );
        
        // Remove glow de entidades que saíram do alcance
        List<Entity> allNearbyEntities = level.getEntities(sourceEntity, extendedArea,
            e -> (e instanceof LivingEntity || e instanceof ItemEntity) && !e.isSpectator());
        
        for (Entity ent : allNearbyEntities) {
            String uuid = ent.getStringUUID();
            if (!currentEntities.contains(uuid) && purpleTeam.getPlayers().contains(uuid)) {
                scoreboard.removePlayerFromTeam(uuid, purpleTeam);
                ent.setGlowingTag(false);
            }
        }
    }
}