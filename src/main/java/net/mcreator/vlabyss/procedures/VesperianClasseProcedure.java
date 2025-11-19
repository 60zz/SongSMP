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
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.BoolArgumentType;

public class VesperianClasseProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		try {
			for (Entity entityiterator : EntityArgument.getEntities(arguments, "entity")) {
				if (BoolArgumentType.getBool(arguments, "value") == true) {
					{
						boolean _setval = true;
						entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Vesperian = _setval;
							capability.syncPlayerVariables(entityiterator);
						});
					}
					{
						Entity _entity = entityiterator;
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
						Entity _entity = entityiterator;
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
					if (entityiterator instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7bVoc\u00EA agora est\u00E1 na classe \u00A73VESPERIAN"), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7bVoc\u00EA colocou " + entityiterator.getDisplayName().getString() + " na classe \u00A73VESPERIAN")), false);
				} else {
					{
						boolean _setval = false;
						entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Vesperian = _setval;
							capability.syncPlayerVariables(entityiterator);
						});
					}
					if (entityiterator instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7bVoc\u00EA foi removido da classe \u00A73VESPERIAN"), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7bVoc\u00EA removeu " + entityiterator.getDisplayName().getString() + " na classe \u00A73VESPERIAN")), false);
					{
						Entity _entity = entityiterator;
						if (_entity instanceof LivingEntity _livingEntity) {
							Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.attack_damage"));
							if (_attribute != null) {
								AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
								if (_attr != null) {
									_attr.getModifiers().forEach((_modifier) -> {
										if (_modifier.getName().equals("vesperian_alt")) {
											_attr.removeModifier(_modifier);
										}
									});
								}
							}
						}
					}
					{
						Entity _entity = entityiterator;
						if (_entity instanceof LivingEntity _livingEntity) {
							Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.attack_speed"));
							if (_attribute != null) {
								AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
								if (_attr != null) {
									_attr.getModifiers().forEach((_modifier) -> {
										if (_modifier.getName().equals("vesperian_alt2")) {
											_attr.removeModifier(_modifier);
										}
									});
								}
							}
						}
					}
				}
			}
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
	}
}