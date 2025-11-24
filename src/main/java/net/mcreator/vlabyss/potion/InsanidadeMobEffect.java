package net.mcreator.vlabyss.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class InsanidadeMobEffect extends MobEffect {
	public InsanidadeMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -16777216);
		this.addAttributeModifier(Attributes.LUCK, "8c754a1e-0b4e-3319-9978-ee5546d444c8", -4, AttributeModifier.Operation.ADDITION);
		this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "cefe72b7-1cca-3ef9-a0d0-3df4ca6e1d7b", -2, AttributeModifier.Operation.ADDITION);
		this.addAttributeModifier(Attributes.ARMOR, "91393a56-c878-3d88-91b7-3f8d1c4bd5d2", -4, AttributeModifier.Operation.ADDITION);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "3b898400-d3df-351a-be78-d9be8e910fca", -0.02, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}