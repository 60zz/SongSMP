package net.mcreator.vlabyss.potion;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;

public class FlamingCutMobEffect extends MobEffect {
	public FlamingCutMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -1);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.addAttributeModifiers(entity, attributeMap, amplifier);
		entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:ativahab_chama")), entity.getSoundSource(), 1.0F, 1.0F);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}