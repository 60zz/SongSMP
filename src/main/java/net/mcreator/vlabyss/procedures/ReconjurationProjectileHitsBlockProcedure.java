package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;

public class ReconjurationProjectileHitsBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof net.minecraft.server.level.ServerLevel) {
			net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
			int particleCount = (int) 40;
			double centerX = x;
			double centerY = y;
			double centerZ = z;
			double particleSpeed = 0.8;
			net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.POOF;
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