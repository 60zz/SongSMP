package net.mcreator.vlabyss.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class HolyPurgeMobEffect extends MobEffect {
	public HolyPurgeMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -16777216);
		this.addAttributeModifier(Attributes.ARMOR, "9b1256dc-c82a-352d-8b77-65a9d82d8c18", 8, AttributeModifier.Operation.ADDITION);
		this.addAttributeModifier(Attributes.ATTACK_SPEED, "5d947d4b-74ed-3574-9d5f-7fb778b1aa31", 0.025, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}