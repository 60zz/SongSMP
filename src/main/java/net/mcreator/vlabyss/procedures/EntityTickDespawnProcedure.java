package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.entity.WindVortexEntity;
import net.mcreator.vlabyss.entity.RespiroPrimariaEntity;
import net.mcreator.vlabyss.entity.ReconjurationEntity;
import net.mcreator.vlabyss.entity.MantraSoulEntity;
import net.mcreator.vlabyss.entity.MantraSoulCorrompidaEntity;
import net.mcreator.vlabyss.entity.FireEruptionEntity;
import net.mcreator.vlabyss.entity.EchoOfShadowEntity;
import net.mcreator.vlabyss.entity.CloneEntity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class EntityTickDespawnProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof RespiroPrimariaEntity) {
			if (new Object() {
				public int getTicksExisted(Entity entity) {
					if (entity == null)
						return 0;
					return entity.tickCount;
				}
			}.getTicksExisted(entity) >= 40) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
		if (entity instanceof WindVortexEntity) {
			if (new Object() {
				public int getTicksExisted(Entity entity) {
					if (entity == null)
						return 0;
					return entity.tickCount;
				}
			}.getTicksExisted(entity) >= 300) {
				if (!entity.level().isClientSide())
					entity.discard();
				if (world instanceof net.minecraft.server.level.ServerLevel) {
					net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
					int particleCount = (int) 30;
					double centerX = x;
					double centerY = y;
					double centerZ = z;
					double particleSpeed = 0.7;
					net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.END_ROD;
					for (int i = 0; i < particleCount; i++) {
						double u = Math.random();
						double v = Math.random();
						double theta = 2 * Math.PI * u;
						double phi = Math.acos(2 * v - 1);
						double directionX = Math.sin(phi) * Math.cos(theta);
						double directionY = Math.cos(phi);
						double directionZ = Math.sin(phi) * Math.sin(theta);
						double velocityX = directionX * particleSpeed;
						double velocityY = directionY * particleSpeed;
						double velocityZ = directionZ * particleSpeed;
						_level.sendParticles(particleType, centerX, centerY, centerZ, 0, velocityX, velocityY, velocityZ, particleSpeed);
					}
				}
			}
		}
		if (entity instanceof ReconjurationEntity) {
			if (new Object() {
				public int getTicksExisted(Entity entity) {
					if (entity == null)
						return 0;
					return entity.tickCount;
				}
			}.getTicksExisted(entity) >= 40) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
		if (entity instanceof FireEruptionEntity) {
			if (new Object() {
				public int getTicksExisted(Entity entity) {
					if (entity == null)
						return 0;
					return entity.tickCount;
				}
			}.getTicksExisted(entity) >= 10) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
		if (entity instanceof CloneEntity) {
			if (new Object() {
				public int getTicksExisted(Entity entity) {
					if (entity == null)
						return 0;
					return entity.tickCount;
				}
			}.getTicksExisted(entity) >= 150) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
		if (entity instanceof EchoOfShadowEntity) {
			if (new Object() {
				public int getTicksExisted(Entity entity) {
					if (entity == null)
						return 0;
					return entity.tickCount;
				}
			}.getTicksExisted(entity) >= 20) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
		if (entity instanceof MantraSoulCorrompidaEntity) {
			if (new Object() {
				public int getTicksExisted(Entity entity) {
					if (entity == null)
						return 0;
					return entity.tickCount;
				}
			}.getTicksExisted(entity) >= 600) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
		if (entity instanceof MantraSoulEntity) {
			if (new Object() {
				public int getTicksExisted(Entity entity) {
					if (entity == null)
						return 0;
					return entity.tickCount;
				}
			}.getTicksExisted(entity) >= 600) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
	}
}