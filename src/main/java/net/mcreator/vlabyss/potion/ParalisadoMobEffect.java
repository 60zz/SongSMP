package net.mcreator.vlabyss.potion;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.vlabyss.procedures.ParalisadoOnEffectActiveTickProcedure;

public class ParalisadoMobEffect extends MobEffect {
	public ParalisadoMobEffect() {
		super(MobEffectCategory.HARMFUL, -16777216);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "7278f954-08a0-3f84-8926-1235e0e2b241", -0.07, AttributeModifier.Operation.ADDITION);
		this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "e4bdd009-fcd5-3ef8-8b9b-cc1f38ff761d", -4, AttributeModifier.Operation.ADDITION);
		this.addAttributeModifier(Attributes.ATTACK_SPEED, "4a34fb34-6a31-35f0-ae0e-aa34070b9484", -2, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.addAttributeModifiers(entity, attributeMap, amplifier);
		entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:ativacloak_lightning")), entity.getSoundSource(), 1.0F, 1.0F);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		ParalisadoOnEffectActiveTickProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ());
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}