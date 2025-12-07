package net.mcreator.vlabyss.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.vlabyss.procedures.BodyFireOnEffectActiveTickProcedure;

public class BodyFireMobEffect extends MobEffect {
	public BodyFireMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -16777216);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		BodyFireOnEffectActiveTickProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ());
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}