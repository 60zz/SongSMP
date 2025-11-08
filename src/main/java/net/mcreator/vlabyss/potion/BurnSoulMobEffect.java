package net.mcreator.vlabyss.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class BurnSoulMobEffect extends MobEffect {
	public BurnSoulMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -16777216);
		this.addAttributeModifier(Attributes.ARMOR, "5fb31853-51a7-327d-a32e-7f1108277d98", -4, AttributeModifier.Operation.ADDITION);
		this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "40cfbca2-c418-38a9-85e5-94fe9444b97e", -2, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}