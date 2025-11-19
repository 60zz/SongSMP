package net.mcreator.vlabyss.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.vlabyss.procedures.MedoOnEffectActiveTickProcedure;
import net.mcreator.vlabyss.procedures.MedoEffectStartedappliedProcedure;
import net.mcreator.vlabyss.procedures.MedoEffectExpiresProcedure;

public class MedoMobEffect extends MobEffect {
	public MedoMobEffect() {
		super(MobEffectCategory.HARMFUL, -16777216);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "dd364a74-0883-30ab-8426-074bf5acdc44", -300, AttributeModifier.Operation.ADDITION);
		this.addAttributeModifier(Attributes.JUMP_STRENGTH, "e49348b2-9439-3f68-9885-b36ae5a16a18", -300, AttributeModifier.Operation.ADDITION);
		this.addAttributeModifier(Attributes.FLYING_SPEED, "959c842f-2766-3994-9a7d-484a647976cc", -300, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.addAttributeModifiers(entity, attributeMap, amplifier);
		MedoEffectStartedappliedProcedure.execute(entity);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		MedoOnEffectActiveTickProcedure.execute(entity);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		MedoEffectExpiresProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}