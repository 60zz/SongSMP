package net.mcreator.vlabyss.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.vlabyss.procedures.LastHopeEffectStartedappliedProcedure;

public class LastHopeMobEffect extends MobEffect {
	public LastHopeMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -16777216);
		this.addAttributeModifier(Attributes.MAX_HEALTH, "4d8cf6dd-ea59-3256-a0b2-97aaa53dfdf9", 20, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.addAttributeModifiers(entity, attributeMap, amplifier);
		LastHopeEffectStartedappliedProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}