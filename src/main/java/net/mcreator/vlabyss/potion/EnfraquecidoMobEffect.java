package net.mcreator.vlabyss.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class EnfraquecidoMobEffect extends MobEffect {
	public EnfraquecidoMobEffect() {
		super(MobEffectCategory.NEUTRAL, -16777216);
		this.addAttributeModifier(Attributes.ARMOR, "24a331b9-582f-3bb2-b7f4-9e8f67fb54b6", -2, AttributeModifier.Operation.ADDITION);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "b243b430-04b5-37cf-af60-3b1612c46ced", -1, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}