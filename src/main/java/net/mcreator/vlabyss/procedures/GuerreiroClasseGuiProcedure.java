package net.mcreator.vlabyss.procedures;

import org.checkerframework.checker.units.qual.m;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

public class GuerreiroClasseGuiProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Assassino == false
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Berserker == false
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Guerreiro == false
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Tanque == false) {
			{
				boolean _setval = true;
				entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Guerreiro = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.max_health"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "guerreiro";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, 8, AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.movement_speed"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "guerreiro1";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, (-0.03), AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.attack_damage"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "guerreiro2";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, 2, AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
			if (entity instanceof Player _player)
				_player.closeContainer();
		}
	}
}