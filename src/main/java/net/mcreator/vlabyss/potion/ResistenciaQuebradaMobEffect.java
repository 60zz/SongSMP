package net.mcreator.vlabyss.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.vlabyss.procedures.ResistenciaQuebradaEffectStartedappliedProcedure;

public class ResistenciaQuebradaMobEffect extends MobEffect {
	public ResistenciaQuebradaMobEffect() {
		super(MobEffectCategory.HARMFUL, -16777216);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		ResistenciaQuebradaEffectStartedappliedProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}