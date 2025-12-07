package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;

public class BurnArmorEffectExpiresProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.BURN_ARMOR.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.BURN_ARMOR.get()).getDuration() : 0) < 10) {
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.BURN_ARMOR.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.BURN_ARMOR.get()).getAmplifier() : 0) >= 1) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.BURN_ARMOR.get(), 80,
							(entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.BURN_ARMOR.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.BURN_ARMOR.get()).getAmplifier() : 0) - 1));
			}
		}
	}
}