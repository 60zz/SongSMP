package net.mcreator.vlabyss.procedures;

import org.checkerframework.checker.units.qual.m;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

public class SkullCrusherToolInHandTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double vidaatual = 0;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Berserker == true
				|| (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Tanque == true) {
			vidaatual = (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) - (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1);
			if (vidaatual >= 8 && !(vidaatual >= 10)) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0));
				{
					Entity _entity = entity;
					if (_entity instanceof LivingEntity _livingEntity) {
						Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.knockback_resistance"));
						if (_attribute != null) {
							AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
							if (_attr != null) {
								String _modifierName = "knockbackresis1";
								boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
								if (!_hasModifier) {
									AttributeModifier _modifier = new AttributeModifier(_modifierName, 0.2, AttributeModifier.Operation.ADDITION);
									_attr.addPermanentModifier(_modifier);
								}
							}
						}
					}
				}
			} else if (vidaatual >= 10 && !(vidaatual >= 15)) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1));
				{
					Entity _entity = entity;
					if (_entity instanceof LivingEntity _livingEntity) {
						Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.knockback_resistance"));
						if (_attribute != null) {
							AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
							if (_attr != null) {
								String _modifierName = "knockbackresis2";
								boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
								if (!_hasModifier) {
									AttributeModifier _modifier = new AttributeModifier(_modifierName, 0.4, AttributeModifier.Operation.ADDITION);
									_attr.addPermanentModifier(_modifier);
								}
							}
						}
					}
				}
				{
					Entity _entity = entity;
					if (_entity instanceof LivingEntity _livingEntity) {
						Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.knockback_resistance"));
						if (_attribute != null) {
							AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
							if (_attr != null) {
								_attr.getModifiers().forEach((_modifier) -> {
									if (_modifier.getName().equals("knockbackresis1")) {
										_attr.removeModifier(_modifier);
									}
								});
							}
						}
					}
				}
			} else if (vidaatual >= 15) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 2));
				{
					Entity _entity = entity;
					if (_entity instanceof LivingEntity _livingEntity) {
						Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.knockback_resistance"));
						if (_attribute != null) {
							AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
							if (_attr != null) {
								String _modifierName = "knockbackresis3";
								boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
								if (!_hasModifier) {
									AttributeModifier _modifier = new AttributeModifier(_modifierName, 0.8, AttributeModifier.Operation.ADDITION);
									_attr.addPermanentModifier(_modifier);
								}
							}
						}
					}
				}
				{
					Entity _entity = entity;
					if (_entity instanceof LivingEntity _livingEntity) {
						Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.knockback_resistance"));
						if (_attribute != null) {
							AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
							if (_attr != null) {
								_attr.getModifiers().forEach((_modifier) -> {
									if (_modifier.getName().equals("knockbackresis2")) {
										_attr.removeModifier(_modifier);
									}
								});
							}
						}
					}
				}
				{
					Entity _entity = entity;
					if (_entity instanceof LivingEntity _livingEntity) {
						Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.knockback_resistance"));
						if (_attribute != null) {
							AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
							if (_attr != null) {
								_attr.getModifiers().forEach((_modifier) -> {
									if (_modifier.getName().equals("knockbackresis1")) {
										_attr.removeModifier(_modifier);
									}
								});
							}
						}
					}
				}
			} else {
				{
					Entity _entity = entity;
					if (_entity instanceof LivingEntity _livingEntity) {
						Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.knockback_resistance"));
						if (_attribute != null) {
							AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
							if (_attr != null) {
								_attr.getModifiers().forEach((_modifier) -> {
									if (_modifier.getName().equals("knockbackresis1")) {
										_attr.removeModifier(_modifier);
									}
								});
							}
						}
					}
				}
				{
					Entity _entity = entity;
					if (_entity instanceof LivingEntity _livingEntity) {
						Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.knockback_resistance"));
						if (_attribute != null) {
							AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
							if (_attr != null) {
								_attr.getModifiers().forEach((_modifier) -> {
									if (_modifier.getName().equals("knockbackresis2")) {
										_attr.removeModifier(_modifier);
									}
								});
							}
						}
					}
				}
				{
					Entity _entity = entity;
					if (_entity instanceof LivingEntity _livingEntity) {
						Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.knockback_resistance"));
						if (_attribute != null) {
							AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
							if (_attr != null) {
								_attr.getModifiers().forEach((_modifier) -> {
									if (_modifier.getName().equals("knockbackresis3")) {
										_attr.removeModifier(_modifier);
									}
								});
							}
						}
					}
				}
			}
		}
	}
}