package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.vlabyss.init.VlAbyssModParticleTypes;
import net.mcreator.vlabyss.entity.CloneEntity;

import java.util.function.BiFunction;
import java.util.UUID;

public class CloneOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (Math.random() >= 0.5) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (VlAbyssModParticleTypes.BODY_FIRE_PARTICLE.get()), (x + Mth.nextDouble(RandomSource.create(), -0.5, 0.5)), (y + Mth.nextDouble(RandomSource.create(), 0, 1.5)),
						(z + Mth.nextDouble(RandomSource.create(), -0.5, 0.5)), 1, 0, 0, 0, 0);
		}
		if (!((entity instanceof CloneEntity _datEntS ? _datEntS.getEntityData().get(CloneEntity.DATA_invocador) : "").equals("non"))) {
			if (entity instanceof TamableAnimal _toTame && ((new BiFunction<LevelAccessor, String, Entity>() {
				@Override
				public Entity apply(LevelAccessor levelAccessor, String uuid) {
					if (levelAccessor instanceof ServerLevel serverLevel) {
						try {
							return serverLevel.getEntity(UUID.fromString(uuid));
						} catch (Exception e) {
						}
					}
					return null;
				}
			}).apply(world, (entity instanceof CloneEntity _datEntS ? _datEntS.getEntityData().get(CloneEntity.DATA_invocador) : ""))) instanceof Player _owner)
				_toTame.tame(_owner);
		}
	}
}