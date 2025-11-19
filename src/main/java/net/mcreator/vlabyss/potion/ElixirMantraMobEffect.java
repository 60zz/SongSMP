package net.mcreator.vlabyss.potion;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.vlabyss.init.VlAbyssModAttributes;

public class ElixirMantraMobEffect extends MobEffect {
	public ElixirMantraMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -16777216);
		this.addAttributeModifier(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get(), "e807b8ca-8a44-38ca-a1e9-b7b96f3b0165", 0.2, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}