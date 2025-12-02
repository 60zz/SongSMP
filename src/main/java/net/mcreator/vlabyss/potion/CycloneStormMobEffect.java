package net.mcreator.vlabyss.potion;

import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class CycloneStormMobEffect extends MobEffect {
	public CycloneStormMobEffect() {
		super(MobEffectCategory.HARMFUL, -16777216);
		this.addAttributeModifier(ForgeMod.ENTITY_GRAVITY.get(), "5009d78f-ccaf-3f83-939f-05c234402d14", -0.03, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}