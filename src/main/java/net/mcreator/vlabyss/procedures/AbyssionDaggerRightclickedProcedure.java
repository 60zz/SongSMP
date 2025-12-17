package net.mcreator.vlabyss.procedures;

import net.minecraftforge.network.NetworkDirection;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.network.Connection;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModItems;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.List;
import java.util.Iterator;

public class AbyssionDaggerRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (!(entity instanceof Player _plrCldCheck0 && _plrCldCheck0.getCooldowns().isOnCooldown(VlAbyssModItems.ABYSSION_DAGGER.get()))) {
			if (itemstack.getItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()) {
				if (world.isClientSide()) {
					SetupAnimationsProcedure.setAnimationClientside((Player) entity, "abyssiondaggeranim", false);
				}
				if (!world.isClientSide()) {
					if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
						List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
						synchronized (connections) {
							Iterator<Connection> iterator = connections.iterator();
							while (iterator.hasNext()) {
								Connection connection = iterator.next();
								if (!connection.isConnecting() && connection.isConnected())
									VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("abyssiondaggeranim"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
							}
						}
					}
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(VlAbyssModItems.ABYSSION_DAGGER.get(), 24000);
				VlAbyssMod.queueServerWork(20, () -> {
					if (itemstack.getItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()) {
						{
							double _setval = 0;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.vidas = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), 1);
						if (entity instanceof LivingEntity _entity)
							_entity.setHealth(2);
						if (entity instanceof ServerPlayer _player && !_player.level().isClientSide()) {
							ResourceKey<Level> destinationType = ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("vl_abyss:abismo"));
							if (_player.level().dimension() == destinationType)
								return;
							ServerLevel nextLevel = _player.server.getLevel(destinationType);
							if (nextLevel != null) {
								_player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
								_player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
								_player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
								for (MobEffectInstance _effectinstance : _player.getActiveEffects())
									_player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance));
								_player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
							}
						}
						VlAbyssMod.queueServerWork(10, () -> {
							if (itemstack.getItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()) {
								{
									try {
										net.minecraft.world.entity.Entity targetEntity = entity;
										double teleportX = x;
										double teleportY = (y + 100);
										double teleportZ = z;
										if (targetEntity != null) {
											if (targetEntity instanceof net.minecraft.server.level.ServerPlayer _player && !_player.level().isClientSide()) {
												_player.connection.teleport(teleportX, teleportY, teleportZ, _player.getYRot(), _player.getXRot());
											} else {
												targetEntity.teleportTo(teleportX, teleportY, teleportZ);
											}
										}
									} catch (Exception e) {
									}
								}
							}
						});
					}
				});
			}
		}
	}
}