package net.mcreator.vlabyss.potion;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.vlabyss.procedures.LightningCloakOnEffectActiveTickProcedure;
import net.mcreator.vlabyss.procedures.LightningCloakEffectExpiresProcedure;
import net.mcreator.vlabyss.init.VlAbyssModAttributes;

public class LightningCloakMobEffect extends MobEffect {
	public LightningCloakMobEffect() {
		super(MobEffectCategory.NEUTRAL, -16777216);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "85917a37-05d0-38c7-94d8-30d5a62a67ed", 0.04, AttributeModifier.Operation.ADDITION);
		this.addAttributeModifier(Attributes.ATTACK_SPEED, "21ba85c1-ae69-37d8-a6cb-25ed8d5f3689", 4, AttributeModifier.Operation.ADDITION);
		this.addAttributeModifier(VlAbyssModAttributes.THUNDERSTORM_BONUS.get(), "5bc6a3f2-548b-32c7-a9ca-c56e005a503d", 0.25, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.addAttributeModifiers(entity, attributeMap, amplifier);
		entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:acabacloak_lightning")), entity.getSoundSource(), 1.0F, 1.0F);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		LightningCloakOnEffectActiveTickProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ());
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		LightningCloakEffectExpiresProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ());
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}