package net.mcreator.vlabyss.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class StormReconjurationMobEffect extends MobEffect {
	public StormReconjurationMobEffect() {
		super(MobEffectCategory.NEUTRAL, -16777216);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "06a27741-8c15-31d4-b90c-122aacca86cc", -0.02, AttributeModifier.Operation.ADDITION);
		this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "7a052d62-d182-3e1d-ba23-d089a7624b73", -2, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}