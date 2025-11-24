package net.mcreator.vlabyss.procedures;

import org.checkerframework.checker.units.qual.m;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.game.ClientboundUpdateAdvancementsPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.BoolArgumentType;

public class AssassinoClasseProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		try {
			for (Entity entityiterator : EntityArgument.getEntities(arguments, "entity")) {
				if (BoolArgumentType.getBool(arguments, "value") == true) {
					if ((entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Guerreiro == false
							&& (entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Assassino == false
							&& (entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Berserker == false
							&& (entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Tanque == false) {
						{
							boolean _setval = BoolArgumentType.getBool(arguments, "value");
							entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.Assassino = _setval;
								capability.syncPlayerVariables(entityiterator);
							});
						}
						{
							Entity _entity = entityiterator;
							if (_entity instanceof LivingEntity _livingEntity) {
								Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.movement_speed"));
								if (_attribute != null) {
									AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
									if (_attr != null) {
										String _modifierName = "assassino1";
										boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
										if (!_hasModifier) {
											AttributeModifier _modifier = new AttributeModifier(_modifierName, 0.02, AttributeModifier.Operation.ADDITION);
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
										String _modifierName = "assassino2";
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
							Entity _entity = entityiterator;
							if (_entity instanceof LivingEntity _livingEntity) {
								Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.attack_damage"));
								if (_attribute != null) {
									AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
									if (_attr != null) {
										String _modifierName = "assassino3";
										boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
										if (!_hasModifier) {
											AttributeModifier _modifier = new AttributeModifier(_modifierName, 3, AttributeModifier.Operation.ADDITION);
											_attr.addPermanentModifier(_modifier);
										}
									}
								}
							}
						}
					} else {
						{
							Entity _entity = entity;
							if (_entity instanceof ServerPlayer _player) {
								ItemStack _icon = new ItemStack(ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse("minecraft:netherite_sword")));
								Component _title = Component.literal("title");
								Component _description = Component.literal("Usu\u00E1rio j\u00E1 det\u00E9m de uma classe");
								ResourceLocation _advId = ResourceLocation.tryParse("custom:toast_" + System.currentTimeMillis());
								DisplayInfo _display = new DisplayInfo(_icon, _description, _title, null, FrameType.TASK, true, true, false);
								Advancement.Builder _builder = Advancement.Builder.advancement().display(_display).addCriterion("trigger", new ImpossibleTrigger.TriggerInstance());
								Advancement _adv = _builder.build(_advId);
								AdvancementProgress _progress = new AdvancementProgress();
								_progress.update(_adv.getCriteria(), _adv.getRequirements());
								_progress.grantProgress("trigger");
								Map<ResourceLocation, AdvancementProgress> _progressMap = new HashMap<>();
								_progressMap.put(_advId, _progress);
								_player.connection.send(new ClientboundUpdateAdvancementsPacket(false, List.of(_adv), Set.of(), _progressMap));
								new Thread(() -> {
									try {
										Thread.sleep((long) (40 * 50));
										_player.connection.send(new ClientboundUpdateAdvancementsPacket(false, List.of(), Set.of(_advId), Map.of()));
									} catch (Exception e) {
									}
								}).start();
							}
						}
					}
				} else {
					{
						boolean _setval = BoolArgumentType.getBool(arguments, "value");
						entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Assassino = _setval;
							capability.syncPlayerVariables(entityiterator);
						});
					}
					{
						Entity _entity = entity;
						if (_entity instanceof ServerPlayer _player) {
							ItemStack _icon = new ItemStack(ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse("minecraft:netherite_sword")));
							Component _title = Component.literal("title");
							Component _description = Component.literal(("Classe removida de " + entityiterator.getDisplayName().getString()));
							ResourceLocation _advId = ResourceLocation.tryParse("custom:toast_" + System.currentTimeMillis());
							DisplayInfo _display = new DisplayInfo(_icon, _description, _title, null, FrameType.TASK, true, true, false);
							Advancement.Builder _builder = Advancement.Builder.advancement().display(_display).addCriterion("trigger", new ImpossibleTrigger.TriggerInstance());
							Advancement _adv = _builder.build(_advId);
							AdvancementProgress _progress = new AdvancementProgress();
							_progress.update(_adv.getCriteria(), _adv.getRequirements());
							_progress.grantProgress("trigger");
							Map<ResourceLocation, AdvancementProgress> _progressMap = new HashMap<>();
							_progressMap.put(_advId, _progress);
							_player.connection.send(new ClientboundUpdateAdvancementsPacket(false, List.of(_adv), Set.of(), _progressMap));
							new Thread(() -> {
								try {
									Thread.sleep((long) (40 * 50));
									_player.connection.send(new ClientboundUpdateAdvancementsPacket(false, List.of(), Set.of(_advId), Map.of()));
								} catch (Exception e) {
								}
							}).start();
						}
					}
					{
						Entity _entity = entityiterator;
						if (_entity instanceof LivingEntity _livingEntity) {
							Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.movement_speed"));
							if (_attribute != null) {
								AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
								if (_attr != null) {
									_attr.getModifiers().forEach((_modifier) -> {
										if (_modifier.getName().equals("assassino1")) {
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
										if (_modifier.getName().equals("assassino2")) {
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
							Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.attack_damage"));
							if (_attribute != null) {
								AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
								if (_attr != null) {
									_attr.getModifiers().forEach((_modifier) -> {
										if (_modifier.getName().equals("assassino3")) {
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