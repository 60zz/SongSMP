package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.entity.WindVortexEntity;
import net.mcreator.vlabyss.entity.RespiroPrimariaEntity;

public class CounterSpellProjectileHitsLivingEntityProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof WindVortexEntity) {
			if (world instanceof net.minecraft.server.level.ServerLevel) {
				net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
				int particleCount = (int) 40;
				double centerX = (entity.getX());
				double centerY = (entity.getY());
				double centerZ = (entity.getZ());
				double particleSpeed = 0.6;
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
			if (!entity.level().isClientSide())
				entity.discard();
		}
		if (entity instanceof RespiroPrimariaEntity) {
			if (world instanceof net.minecraft.server.level.ServerLevel) {
				net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
				int particleCount = (int) 40;
				double centerX = (entity.getX());
				double centerY = (entity.getY());
				double centerZ = (entity.getZ());
				double particleSpeed = 0.6;
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
			if (!entity.level().isClientSide())
				entity.discard();
		}
		if (entity instanceof LivingEntity _livEnt12 && _livEnt12.hasEffect(VlAbyssModMobEffects.SHADOW_COPY.get())) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(VlAbyssModMobEffects.SHADOW_COPY.get());
		}
		if (entity instanceof LivingEntity _livEnt14 && _livEnt14.hasEffect(VlAbyssModMobEffects.FLAMING_CUT.get())) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(VlAbyssModMobEffects.FLAMING_CUT.get());
		}
		if (entity instanceof LivingEntity _livEnt16 && _livEnt16.hasEffect(VlAbyssModMobEffects.BURN_SOUL.get())) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(VlAbyssModMobEffects.BURN_SOUL.get());
		}
		if (entity instanceof LivingEntity _livEnt18 && _livEnt18.hasEffect(VlAbyssModMobEffects.LAST_HOPE.get())) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(VlAbyssModMobEffects.LAST_HOPE.get());
		}
		if (entity instanceof LivingEntity _livEnt20 && _livEnt20.hasEffect(VlAbyssModMobEffects.ENFRAQUECIDO.get())) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(VlAbyssModMobEffects.ENFRAQUECIDO.get());
		}
	}
}