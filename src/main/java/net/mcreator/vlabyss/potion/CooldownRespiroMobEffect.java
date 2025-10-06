package net.mcreator.vlabyss.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.vlabyss.procedures.CooldownRespiroOnEffectActiveTickProcedure;

public class CooldownRespiroMobEffect extends MobEffect {
	public CooldownRespiroMobEffect() {
		super(MobEffectCategory.NEUTRAL, -16777216);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		CooldownRespiroOnEffectActiveTickProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ());
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}