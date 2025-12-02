package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.vlabyss.init.VlAbyssModParticleTypes;
import net.mcreator.vlabyss.VlAbyssMod;

public class ReconjurationWhileProjectileFlyingTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity immediatesourceentity) {
		if (immediatesourceentity == null)
			return;
		world.addParticle((SimpleParticleType) (VlAbyssModParticleTypes.VENTO_PARTICULA_2.get()), (x + Mth.nextDouble(RandomSource.create(), -1, 1)), (y - Mth.nextDouble(RandomSource.create(), 0.1, 1)),
				(z + Mth.nextDouble(RandomSource.create(), -1, 1)), 0, 0, 0);
		immediatesourceentity.setNoGravity(true);
		VlAbyssMod.queueServerWork(40, () -> {
			if (!immediatesourceentity.level().isClientSide())
				immediatesourceentity.discard();
		});
	}
}