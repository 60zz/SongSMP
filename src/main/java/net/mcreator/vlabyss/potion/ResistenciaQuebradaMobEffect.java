package net.mcreator.vlabyss.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.vlabyss.procedures.ResistenciaQuebradaEffectStartedappliedProcedure;

public class ResistenciaQuebradaMobEffect extends MobEffect {
	public ResistenciaQuebradaMobEffect() {
		super(MobEffectCategory.HARMFUL, -16777216);
		this.addAttributeModifier(Attributes.ARMOR, "33923ded-1dee-36cb-aad7-c8190cc6beed", -4, AttributeModifier.Operation.ADDITION);
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