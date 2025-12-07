package net.mcreator.vlabyss.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.vlabyss.procedures.BurnArmorEffectExpiresProcedure;

public class BurnArmorMobEffect extends MobEffect {
	public BurnArmorMobEffect() {
		super(MobEffectCategory.HARMFUL, -16777216);
		this.addAttributeModifier(Attributes.ARMOR, "50d5c8da-3841-3e34-b8c2-5f7200dcc69f", -2, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		BurnArmorEffectExpiresProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}