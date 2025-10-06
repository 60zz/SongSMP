package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.VlAbyssMod;

public class InstantRegenMantraEffectStartedappliedProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		VlAbyssMod.queueServerWork(20, () -> {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(VlAbyssModMobEffects.INSTANT_REGEN_MANTRA.get());
		});
	}
}