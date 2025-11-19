package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkDirection;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.Connection;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.CameraType;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.init.VlAbyssModAttributes;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.List;
import java.util.Iterator;

public class ChamaPrimeiraHabilidadeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Chama >= 1
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).MantraRegistrada == true
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).habilidade1 == true) {
			if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).mantra1_cooldown < 1) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:swords")))
						|| (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:swords")))
						|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))
						|| (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))) {
					if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 70) {
						{
							double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 70;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.Ethir = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = 20;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.mantra1_cooldown = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if (world.isClientSide()) {
							SetupAnimationsProcedure.setAnimationClientside((Player) entity, "mantrachama1", false);
						}
						if (!world.isClientSide()) {
							if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
								List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
								synchronized (connections) {
									Iterator<Connection> iterator = connections.iterator();
									while (iterator.hasNext()) {
										Connection connection = iterator.next();
										if (!connection.isConnecting() && connection.isConnected())
											VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("mantrachama1"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
									}
								}
							}
						}
						if (world instanceof net.minecraft.server.level.ServerLevel) {
							net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
							int particleCount = (int) 30;
							double centerX = x;
							double centerY = y;
							double centerZ = z;
							double particleSpeed = 0.6;
							net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.FLAME;
							double initialRadius = 4.0;
							for (int i = 0; i < particleCount; i++) {
								double u = Math.random();
								double v = Math.random();
								double theta = 2 * Math.PI * u;
								double phi = Math.acos(2 * v - 1);
								double startX = centerX + initialRadius * Math.sin(phi) * Math.cos(theta);
								double startY = centerY + initialRadius * Math.cos(phi);
								double startZ = centerZ + initialRadius * Math.sin(phi) * Math.sin(theta);
								double deltaX = centerX - startX;
								double deltaY = centerY - startY;
								double deltaZ = centerZ - startZ;
								double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
								double velocityX = (deltaX / distance) * particleSpeed;
								double velocityY = (deltaY / distance) * particleSpeed;
								double velocityZ = (deltaZ / distance) * particleSpeed;
								_level.sendParticles(particleType, startX, startY, startZ, 0, velocityX, velocityY, velocityZ, particleSpeed);
							}
						}
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.FLAMING_CUT.get(),
									(int) (100
											* (entity instanceof LivingEntity _livingEntity4 && _livingEntity4.getAttributes().hasAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get())
													? _livingEntity4.getAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get()).getValue()
													: 0)
											* (entity instanceof LivingEntity _livingEntity5 && _livingEntity5.getAttributes().hasAttribute(VlAbyssModAttributes.FLAME_BONUS.get())
													? _livingEntity5.getAttribute(VlAbyssModAttributes.FLAME_BONUS.get()).getValue()
													: 0)),
									1));
						if (entity instanceof Player _player) {
							if (_player.level().isClientSide()) {
								Minecraft _mc = Minecraft.getInstance();
								if (_mc.player != null && _mc.player.equals(_player)) {
									_mc.options.setCameraType(CameraType.THIRD_PERSON_BACK);
								}
							}
						}
					} else {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal("\u00A7cSem \"ETHIR\" o suficiente"), true);
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")), SoundSource.MASTER, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")), SoundSource.MASTER, 1, 1, false);
							}
						}
					}
				} else {
					if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 50) {
						{
							double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 50;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.Ethir = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = 10;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.mantra1_cooldown = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if (world.isClientSide()) {
							SetupAnimationsProcedure.setAnimationClientside((Player) entity, "mantrachama1", false);
						}
						if (!world.isClientSide()) {
							if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
								List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
								synchronized (connections) {
									Iterator<Connection> iterator = connections.iterator();
									while (iterator.hasNext()) {
										Connection connection = iterator.next();
										if (!connection.isConnecting() && connection.isConnected())
											VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("mantrachama1"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
									}
								}
							}
						}
						if (world instanceof net.minecraft.server.level.ServerLevel) {
							net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
							int particleCount = (int) 30;
							double centerX = x;
							double centerY = y;
							double centerZ = z;
							double particleSpeed = 0.6;
							net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.FLAME;
							double initialRadius = 4.0;
							for (int i = 0; i < particleCount; i++) {
								double u = Math.random();
								double v = Math.random();
								double theta = 2 * Math.PI * u;
								double phi = Math.acos(2 * v - 1);
								double startX = centerX + initialRadius * Math.sin(phi) * Math.cos(theta);
								double startY = centerY + initialRadius * Math.cos(phi);
								double startZ = centerZ + initialRadius * Math.sin(phi) * Math.sin(theta);
								double deltaX = centerX - startX;
								double deltaY = centerY - startY;
								double deltaZ = centerZ - startZ;
								double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
								double velocityX = (deltaX / distance) * particleSpeed;
								double velocityY = (deltaY / distance) * particleSpeed;
								double velocityZ = (deltaZ / distance) * particleSpeed;
								_level.sendParticles(particleType, startX, startY, startZ, 0, velocityX, velocityY, velocityZ, particleSpeed);
							}
						}
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.FLAMING_CUT.get(),
									(int) (100
											* (entity instanceof LivingEntity _livingEntity12 && _livingEntity12.getAttributes().hasAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get())
													? _livingEntity12.getAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get()).getValue()
													: 0)
											* (entity instanceof LivingEntity _livingEntity13 && _livingEntity13.getAttributes().hasAttribute(VlAbyssModAttributes.FLAME_BONUS.get())
													? _livingEntity13.getAttribute(VlAbyssModAttributes.FLAME_BONUS.get()).getValue()
													: 0)),
									0));
						if (entity instanceof Player _player) {
							if (_player.level().isClientSide()) {
								Minecraft _mc = Minecraft.getInstance();
								if (_mc.player != null && _mc.player.equals(_player)) {
									_mc.options.setCameraType(CameraType.THIRD_PERSON_BACK);
								}
							}
						}
					} else {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal("\u00A7cSem \"ETHIR\" o suficiente"), true);
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")), SoundSource.MASTER, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")), SoundSource.MASTER, 1, 1, false);
							}
						}
					}
				}
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7cHabilidade em recarga"), true);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")), SoundSource.MASTER, 1, 1, false);
					}
				}
			}
		}
	}
}