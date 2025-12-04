package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.server.level.ServerLevel;

import java.util.List;

public class ShadeVisionEffectExpiresProcedure {

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null || !(world instanceof ServerLevel))
            return;
        
        ServerLevel serverLevel = (ServerLevel) world;
        
        // Remove o glow de todas as entidades próximas quando o efeito expirar
        double detectionRadius = 75.0; // Um pouco maior para garantir limpeza completa
        
        AABB searchArea = new AABB(
            x - detectionRadius, y - detectionRadius, z - detectionRadius,
            x + detectionRadius, y + detectionRadius, z + detectionRadius
        );
        
        Scoreboard scoreboard = serverLevel.getScoreboard();
        PlayerTeam purpleTeam = scoreboard.getPlayerTeam("purple_glow");
        
        if (purpleTeam == null)
            return;
        
        // Remove glow de todas as entidades
        List<Entity> nearbyEntities = serverLevel.getEntities(entity, searchArea,
            e -> (e instanceof LivingEntity || e instanceof ItemEntity) && !e.isSpectator());
        
        for (Entity ent : nearbyEntities) {
            String uuid = ent.getStringUUID();
            if (purpleTeam.getPlayers().contains(uuid)) {
                scoreboard.removePlayerFromTeam(uuid, purpleTeam);
                ent.setGlowingTag(false);
            }
        }
    }
}