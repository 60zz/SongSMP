package net.mcreator.vlabyss.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class CortaRegenMobEffect extends MobEffect {
	public CortaRegenMobEffect() {
		super(MobEffectCategory.HARMFUL, -16777216);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}