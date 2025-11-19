package net.mcreator.vlabyss.potion;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.vlabyss.init.VlAbyssModAttributes;

public class RegenMantraMobEffect extends MobEffect {
	public RegenMantraMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -16777216);
		this.addAttributeModifier(VlAbyssModAttributes.MANTRA_REGENERATION.get(), "c32ac10d-bd42-3fdf-947a-67ac01b09652", 1, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}