package net.mcreator.vlabyss.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.vlabyss.procedures.RegenMantraOnEffectActiveTickProcedure;

public class RegenMantraMobEffect extends MobEffect {
	public RegenMantraMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -16777216);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		RegenMantraOnEffectActiveTickProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}