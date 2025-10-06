package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.vlabyss.init.VlAbyssModParticleTypes;

public class CooldownRespiroOnEffectActiveTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Math.random() >= 0.8) {
			world.addParticle((SimpleParticleType) (VlAbyssModParticleTypes.VENTO_PARTICULA_2.get()), x, y, z, (Mth.nextDouble(RandomSource.create(), 0.1, 0.8)), (Mth.nextDouble(RandomSource.create(), 1, 2.5)),
					(Mth.nextDouble(RandomSource.create(), 0.1, 0.8)));
		}
	}
}