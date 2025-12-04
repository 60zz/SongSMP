package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

public class EchoOfShadowOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Math.random() >= 0.8) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.PORTAL, x, y, z, 50, 1, 1, 1, 0);
		}
	}
}