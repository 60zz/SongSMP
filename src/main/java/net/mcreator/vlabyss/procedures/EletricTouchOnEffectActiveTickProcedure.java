package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.vlabyss.init.VlAbyssModParticleTypes;

public class EletricTouchOnEffectActiveTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Math.random() >= 0.5) {
			world.addParticle((SimpleParticleType) (VlAbyssModParticleTypes.LIGHTNING_CLOAK_PARTICLE.get()), (x + 0.25), (y + Mth.nextDouble(RandomSource.create(), 0.5, 1.5)), (z + Mth.nextDouble(RandomSource.create(), 0.25, 0.5)), 0, 0, 0);
		}
	}
}