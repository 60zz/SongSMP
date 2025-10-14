package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkDirection;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.Connection;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModItems;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.List;
import java.util.Iterator;

public class DashFrenteOnKeyPressedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).dash_reto == true) {
			if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).desligadash == true) {
				if (!entity.getPersistentData().getBoolean("hitado") && !((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).dash == true)) {
					if (!(entity instanceof Player _plrCldCheck1 && _plrCldCheck1.getCooldowns().isOnCooldown(VlAbyssModItems.DESBLOQUEIO_ESQUIVA.get()))) {
						{
							boolean _setval = true;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.dash = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						VlAbyssMod.queueServerWork(4, () -> {
							{
								boolean _setval = false;
								entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.dash = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							if (entity instanceof Player _player)
								_player.getCooldowns().addCooldown(VlAbyssModItems.DESBLOQUEIO_ESQUIVA.get(), 10);
						});
					}
				} else if (!(entity instanceof Player _plrCldCheck4 && _plrCldCheck4.getCooldowns().isOnCooldown(VlAbyssModItems.DESBLOQUEIO_ESQUIVA.get()))
						&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).dash == true) {
					{
						boolean _setval = false;
						entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.dash = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					if (world.isClientSide()) {
						SetupAnimationsProcedure.setAnimationClientside((Player) entity, "dashfrente", false);
					}
					if (!world.isClientSide()) {
						if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
							List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
							synchronized (connections) {
								Iterator<Connection> iterator = connections.iterator();
								while (iterator.hasNext()) {
									Connection connection = iterator.next();
									if (!connection.isConnecting() && connection.isConnected())
										VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("dashfrente"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
								}
							}
						}
					}
					if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == VlAbyssModItems.ADAGA_ASSASSINOS.get())) {
						if ((entity.getDirection()) == Direction.NORTH) {
							entity.setDeltaMovement(new Vec3((entity.getLookAngle().x), 0, (entity.getLookAngle().z + -0.25)));
						} else if ((entity.getDirection()) == Direction.SOUTH) {
							entity.setDeltaMovement(new Vec3((entity.getLookAngle().x), 0, (entity.getLookAngle().z + 0.25)));
						} else if ((entity.getDirection()) == Direction.WEST) {
							entity.setDeltaMovement(new Vec3((entity.getLookAngle().x + -0.25), 0, (entity.getLookAngle().z)));
						} else if ((entity.getDirection()) == Direction.EAST) {
							entity.setDeltaMovement(new Vec3((entity.getLookAngle().x + 0.25), 0, (entity.getLookAngle().z)));
						}
					} else {
						if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).Assassino == true) {
							if ((entity.getDirection()) == Direction.NORTH) {
								entity.setDeltaMovement(new Vec3((entity.getLookAngle().x), 0, (entity.getLookAngle().z + -0.5)));
							} else if ((entity.getDirection()) == Direction.SOUTH) {
								entity.setDeltaMovement(new Vec3((entity.getLookAngle().x), 0, (entity.getLookAngle().z + 0.5)));
							} else if ((entity.getDirection()) == Direction.WEST) {
								entity.setDeltaMovement(new Vec3((entity.getLookAngle().x + -0.5), 0, (entity.getLookAngle().z)));
							} else if ((entity.getDirection()) == Direction.EAST) {
								entity.setDeltaMovement(new Vec3((entity.getLookAngle().x + 0.5), 0, (entity.getLookAngle().z)));
							}
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 40, 9));
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 2));
						} else {
							if ((entity.getDirection()) == Direction.NORTH) {
								entity.setDeltaMovement(new Vec3((entity.getLookAngle().x), 0, (entity.getLookAngle().z + -0.25)));
							} else if ((entity.getDirection()) == Direction.SOUTH) {
								entity.setDeltaMovement(new Vec3((entity.getLookAngle().x), 0, (entity.getLookAngle().z + 0.25)));
							} else if ((entity.getDirection()) == Direction.WEST) {
								entity.setDeltaMovement(new Vec3((entity.getLookAngle().x + -0.25), 0, (entity.getLookAngle().z)));
							} else if ((entity.getDirection()) == Direction.EAST) {
								entity.setDeltaMovement(new Vec3((entity.getLookAngle().x + 0.25), 0, (entity.getLookAngle().z)));
							}
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:dash_som")), SoundSource.MASTER, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:dash_som")), SoundSource.MASTER, 1, 1, false);
						}
					}
					world.addParticle(ParticleTypes.POOF, (entity.getX()), (entity.getY()), (entity.getZ()), 0, 0, 0);
					world.addParticle(ParticleTypes.CLOUD, (entity.getX()), (entity.getY()), (entity.getZ()), 0, 0, 0);
					VlAbyssMod.queueServerWork(5, () -> {
						if (entity instanceof Player _player)
							_player.getCooldowns().addCooldown(VlAbyssModItems.DESBLOQUEIO_ESQUIVA.get(), 100);
					});
				}
			}
		}
	}
}