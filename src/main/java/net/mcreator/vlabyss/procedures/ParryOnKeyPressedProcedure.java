package net.mcreator.vlabyss.procedures;

import net.minecraftforge.network.NetworkDirection;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.Connection;

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.List;
import java.util.Iterator;

public class ParryOnKeyPressedProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!entity.getPersistentData().getBoolean("perry") && !entity.getPersistentData().getBoolean("cooldown")) {
			entity.getPersistentData().putBoolean("perry", true);
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:swords")))
					|| (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:swords")))
					|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))
					|| (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))) {
				if (!(entity instanceof LivingEntity _livEnt5 && _livEnt5.hasEffect(VlAbyssModMobEffects.RESISTENCIA_QUEBRADA.get()))) {
					if (world.isClientSide()) {
						SetupAnimationsProcedure.setAnimationClientside((Player) entity, "defesaarma", false);
					}
					if (!world.isClientSide()) {
						if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
							List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
							synchronized (connections) {
								Iterator<Connection> iterator = connections.iterator();
								while (iterator.hasNext()) {
									Connection connection = iterator.next();
									if (!connection.isConnecting() && connection.isConnected())
										VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("defesaarma"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
								}
							}
						}
					}
					VlAbyssMod.queueServerWork(10, () -> {
						if (entity.getPersistentData().getBoolean("perry")) {
							if (!(entity instanceof LivingEntity _livEnt8 && _livEnt8.hasEffect(VlAbyssModMobEffects.QUEBRA.get()))) {
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEBRA.get(), 600, 0));
								entity.getPersistentData().putBoolean("quebra1", true);
								VlAbyssMod.queueServerWork(600, () -> {
									entity.getPersistentData().remove("quebra1");
								});
							} else if (entity.getPersistentData().getBoolean("quebra1")) {
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEBRA.get(), 1200, 1));
								entity.getPersistentData().putBoolean("quebra2", true);
								entity.getPersistentData().remove("quebra1");
								VlAbyssMod.queueServerWork(1200, () -> {
									entity.getPersistentData().remove("quebra2");
								});
							} else if (entity.getPersistentData().getBoolean("quebra2")) {
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEBRA.get(), 2400, 2));
								entity.getPersistentData().putBoolean("quebra3", true);
								entity.getPersistentData().remove("quebra2");
								VlAbyssMod.queueServerWork(2400, () -> {
									entity.getPersistentData().remove("quebra3");
								});
							} else if (entity.getPersistentData().getBoolean("quebra3")) {
								entity.getPersistentData().putBoolean("quebra4", true);
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEBRA.get(), 3600, 3));
								entity.getPersistentData().remove("quebra3");
								VlAbyssMod.queueServerWork(3600, () -> {
									entity.getPersistentData().remove("quebra4");
								});
							} else if (entity.getPersistentData().getBoolean("quebra4")) {
								entity.getPersistentData().remove("quebra4");
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.RESISTENCIA_QUEBRADA.get(), 200, 0));
								if (entity instanceof LivingEntity _entity)
									_entity.removeEffect(VlAbyssModMobEffects.QUEBRA.get());
							}
							entity.getPersistentData().remove("perry");
							entity.getPersistentData().putBoolean("cooldown", true);
							VlAbyssMod.queueServerWork(60, () -> {
								entity.getPersistentData().remove("cooldown");
							});
						}
					});
				} else {
					entity.getPersistentData().remove("perry");
				}
			} else {
				if (!(entity instanceof LivingEntity _livEnt41 && _livEnt41.hasEffect(VlAbyssModMobEffects.RESISTENCIA_QUEBRADA.get()))) {
					if (world.isClientSide()) {
						SetupAnimationsProcedure.setAnimationClientside((Player) entity, "defende", false);
					}
					if (!world.isClientSide()) {
						if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
							List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
							synchronized (connections) {
								Iterator<Connection> iterator = connections.iterator();
								while (iterator.hasNext()) {
									Connection connection = iterator.next();
									if (!connection.isConnecting() && connection.isConnected())
										VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("defende"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
								}
							}
						}
					}
					VlAbyssMod.queueServerWork(10, () -> {
						if (entity.getPersistentData().getBoolean("perry")) {
							if (!(entity instanceof LivingEntity _livEnt44 && _livEnt44.hasEffect(VlAbyssModMobEffects.QUEBRA.get()))) {
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEBRA.get(), 600, 0));
								entity.getPersistentData().putBoolean("quebra1", true);
								VlAbyssMod.queueServerWork(600, () -> {
									entity.getPersistentData().remove("quebra1");
								});
							} else if (entity.getPersistentData().getBoolean("quebra1")) {
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEBRA.get(), 1200, 1));
								entity.getPersistentData().putBoolean("quebra2", true);
								entity.getPersistentData().remove("quebra1");
								VlAbyssMod.queueServerWork(1200, () -> {
									entity.getPersistentData().remove("quebra2");
								});
							} else if (entity.getPersistentData().getBoolean("quebra2")) {
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEBRA.get(), 2400, 2));
								entity.getPersistentData().putBoolean("quebra3", true);
								entity.getPersistentData().remove("quebra2");
								VlAbyssMod.queueServerWork(2400, () -> {
									entity.getPersistentData().remove("quebra3");
								});
							} else if (entity.getPersistentData().getBoolean("quebra3")) {
								entity.getPersistentData().putBoolean("quebra4", true);
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEBRA.get(), 3600, 3));
								entity.getPersistentData().remove("quebra3");
								VlAbyssMod.queueServerWork(3600, () -> {
									entity.getPersistentData().remove("quebra4");
								});
							} else if (entity.getPersistentData().getBoolean("quebra4")) {
								entity.getPersistentData().remove("quebra4");
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.RESISTENCIA_QUEBRADA.get(), 200, 0));
								if (entity instanceof LivingEntity _entity)
									_entity.removeEffect(VlAbyssModMobEffects.QUEBRA.get());
							}
							entity.getPersistentData().remove("perry");
							entity.getPersistentData().putBoolean("cooldown", true);
							VlAbyssMod.queueServerWork(100, () -> {
								entity.getPersistentData().remove("cooldown");
							});
						}
					});
				} else {
					entity.getPersistentData().remove("perry");
				}
			}
		}
	}
}