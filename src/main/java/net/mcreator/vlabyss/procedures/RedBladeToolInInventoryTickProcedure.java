package net.mcreator.vlabyss.procedures;

import org.checkerframework.checker.units.qual.m;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModItems;

public class RedBladeToolInInventoryTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Guerreiro == true
				|| (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Berserker == true) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == VlAbyssModItems.RED_BLADE.get()) {
				{
					Entity _entity = entity;
					if (_entity instanceof LivingEntity _livingEntity) {
						Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("forge:entity_reach"));
						if (_attribute != null) {
							AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
							if (_attr != null) {
								String _modifierName = "reachblade";
								boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
								if (!_hasModifier) {
									AttributeModifier _modifier = new AttributeModifier(_modifierName, 1, AttributeModifier.Operation.ADDITION);
									_attr.addPermanentModifier(_modifier);
								}
							}
						}
					}
				}
			} else {
				{
					Entity _entity = entity;
					if (_entity instanceof LivingEntity _livingEntity) {
						Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("forge:entity_reach"));
						if (_attribute != null) {
							AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
							if (_attr != null) {
								_attr.getModifiers().forEach((_modifier) -> {
									if (_modifier.getName().equals("reachblade")) {
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