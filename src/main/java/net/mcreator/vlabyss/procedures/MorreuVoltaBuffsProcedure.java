package net.mcreator.vlabyss.procedures;

import org.checkerframework.checker.units.qual.m;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MorreuVoltaBuffsProcedure {
	@SubscribeEvent
	public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Arkanthi == true) {
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("vl_abyss:inteligencia"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "arkanthi_int";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, 1, AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Vesperian == true) {
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.attack_damage"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "vesperian_alt";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, 2, AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.attack_speed"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "vesperian_alt2";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, 1, AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Nimren == true) {
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("vl_abyss:inteligencia"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "nim'ren_c";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, 2, AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Simikari == true) {
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.attack_damage"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "simikari";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, 1, AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("vl_abyss:inteligencia"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "simikari2";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, 1, AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Scribari == true) {
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.attack_damage"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "scribari";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, 1, AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("vl_abyss:inteligencia"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "scribari2";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, 2, AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Capridel == true) {
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("vl_abyss:inteligencia"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "capidrel";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, 3, AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Lumivivo == true) {
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("vl_abyss:inteligencia"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "lumivivo";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, 2, AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
		}
	}
}