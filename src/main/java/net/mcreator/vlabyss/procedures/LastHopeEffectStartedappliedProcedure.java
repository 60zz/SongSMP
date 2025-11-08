package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class LastHopeEffectStartedappliedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			if (entity instanceof LivingEntity _entity) {
				java.util.List<net.minecraft.world.effect.MobEffect> effectsToRemove = new java.util.ArrayList<>();
				for (net.minecraft.world.effect.MobEffectInstance effectInstance : _entity.getActiveEffects()) {
					if (effectInstance.getEffect().getCategory() == net.minecraft.world.effect.MobEffectCategory.HARMFUL) {
						effectsToRemove.add(effectInstance.getEffect());
					}
				}
				for (net.minecraft.world.effect.MobEffect effect : effectsToRemove) {
					_entity.removeEffect(effect);
				}
			}
		}
	}
}