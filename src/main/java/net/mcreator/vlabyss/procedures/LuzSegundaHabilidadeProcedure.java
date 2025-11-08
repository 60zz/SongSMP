package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkDirection;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
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
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.List;
import java.util.Iterator;
import java.util.Comparator;

public class LuzSegundaHabilidadeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Entity target = null;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Luz >= 1
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).MantraRegistrada == true
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).habilidade2 == true) {
			if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).mantra1_cooldown < 1) {
				if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 130) {
					if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).hab2_nivel == 1) {
						if (Minecraft.getInstance().options.keyShift.isDown()) {
							if (!world.getEntitiesOfClass(LivingEntity.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX()),
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY()),
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())))
									.inflate(5 / 2d), e -> true).isEmpty()) {
								target = findEntityInWorldRange(world, LivingEntity.class,
										(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos()
												.getX()),
										(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos()
												.getY()),
										(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos()
												.getZ()),
										5);
								if (!(target == null)) {
									if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
										_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.LAST_HOPE.get(), 160, 0));
									{
										double _setval = 40;
										entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.mantra2_cooldown = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
									if (world.isClientSide()) {
										SetupAnimationsProcedure.setAnimationClientside((Player) entity, "luzprimeira", false);
									}
									if (!world.isClientSide()) {
										if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
											List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
											synchronized (connections) {
												Iterator<Connection> iterator = connections.iterator();
												while (iterator.hasNext()) {
													Connection connection = iterator.next();
													if (!connection.isConnecting() && connection.isConnected())
														VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("luzprimeira"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
												}
											}
										}
									}
									if (entity instanceof Player _player) {
										if (_player.level().isClientSide()) {
											Minecraft _mc = Minecraft.getInstance();
											if (_mc.player != null && _mc.player.equals(_player)) {
												_mc.options.setCameraType(CameraType.THIRD_PERSON_BACK);
											}
										}
									}
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:usamantraluzum")), SoundSource.MASTER, 1, 1);
										} else {
											_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:usamantraluzum")), SoundSource.MASTER, 1, 1, false);
										}
									}
									if (world instanceof net.minecraft.server.level.ServerLevel) {
										net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
										int particleCount = (int) 40;
										double centerX = (target.getX());
										double centerY = (target.getY());
										double centerZ = (target.getZ());
										double particleSpeed = 0.8;
										net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.CLOUD;
										for (int i = 0; i < particleCount; i++) {
											double u = Math.random();
											double v = Math.random();
											double theta = 2 * Math.PI * u;
											double phi = Math.acos(2 * v - 1);
											double directionX = Math.sin(phi) * Math.cos(theta);
											double directionY = Math.cos(phi);
											double directionZ = Math.sin(phi) * Math.sin(theta);
											double velocityX = directionX * particleSpeed;
											double velocityY = directionY * particleSpeed;
											double velocityZ = directionZ * particleSpeed;
											_level.sendParticles(particleType, centerX, centerY, centerZ, 0, velocityX, velocityY, velocityZ, particleSpeed);
										}
									}
									{
										double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 130;
										entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.Ethir = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
								}
							}
						} else {
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.LAST_HOPE.get(), 160, 0));
							{
								double _setval = 40;
								entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.mantra2_cooldown = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							if (world.isClientSide()) {
								SetupAnimationsProcedure.setAnimationClientside((Player) entity, "luzprimeira", false);
							}
							if (!world.isClientSide()) {
								if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
									List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
									synchronized (connections) {
										Iterator<Connection> iterator = connections.iterator();
										while (iterator.hasNext()) {
											Connection connection = iterator.next();
											if (!connection.isConnecting() && connection.isConnected())
												VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("luzprimeira"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
										}
									}
								}
							}
							if (entity instanceof Player _player) {
								if (_player.level().isClientSide()) {
									Minecraft _mc = Minecraft.getInstance();
									if (_mc.player != null && _mc.player.equals(_player)) {
										_mc.options.setCameraType(CameraType.THIRD_PERSON_BACK);
									}
								}
							}
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:usamantraluzum")), SoundSource.MASTER, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:usamantraluzum")), SoundSource.MASTER, 1, 1, false);
								}
							}
							if (world instanceof net.minecraft.server.level.ServerLevel) {
								net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
								int particleCount = (int) 40;
								double centerX = (entity.getX());
								double centerY = (entity.getY());
								double centerZ = (entity.getZ());
								double particleSpeed = 0.8;
								net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.CLOUD;
								for (int i = 0; i < particleCount; i++) {
									double u = Math.random();
									double v = Math.random();
									double theta = 2 * Math.PI * u;
									double phi = Math.acos(2 * v - 1);
									double directionX = Math.sin(phi) * Math.cos(theta);
									double directionY = Math.cos(phi);
									double directionZ = Math.sin(phi) * Math.sin(theta);
									double velocityX = directionX * particleSpeed;
									double velocityY = directionY * particleSpeed;
									double velocityZ = directionZ * particleSpeed;
									_level.sendParticles(particleType, centerX, centerY, centerZ, 0, velocityX, velocityY, velocityZ, particleSpeed);
								}
							}
							{
								double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 130;
								entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.Ethir = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
						}
					} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).hab2_nivel >= 2) {
						if (Minecraft.getInstance().options.keyShift.isDown()) {
							if (!world.getEntitiesOfClass(LivingEntity.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX()),
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY()),
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())))
									.inflate(5 / 2d), e -> true).isEmpty()) {
								target = findEntityInWorldRange(world, LivingEntity.class,
										(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos()
												.getX()),
										(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos()
												.getY()),
										(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos()
												.getZ()),
										5);
								if (!(target == null)) {
									if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
										_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.LAST_HOPE.get(), 320, 0));
									{
										double _setval = 40;
										entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.mantra2_cooldown = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
									if (world.isClientSide()) {
										SetupAnimationsProcedure.setAnimationClientside((Player) entity, "luzprimeira", false);
									}
									if (!world.isClientSide()) {
										if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
											List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
											synchronized (connections) {
												Iterator<Connection> iterator = connections.iterator();
												while (iterator.hasNext()) {
													Connection connection = iterator.next();
													if (!connection.isConnecting() && connection.isConnected())
														VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("luzprimeira"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
												}
											}
										}
									}
									if (entity instanceof Player _player) {
										if (_player.level().isClientSide()) {
											Minecraft _mc = Minecraft.getInstance();
											if (_mc.player != null && _mc.player.equals(_player)) {
												_mc.options.setCameraType(CameraType.THIRD_PERSON_BACK);
											}
										}
									}
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:usamantraluzum")), SoundSource.MASTER, 1, 1);
										} else {
											_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:usamantraluzum")), SoundSource.MASTER, 1, 1, false);
										}
									}
									if (world instanceof net.minecraft.server.level.ServerLevel) {
										net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
										int particleCount = (int) 40;
										double centerX = (target.getX());
										double centerY = (target.getY());
										double centerZ = (target.getZ());
										double particleSpeed = 0.8;
										net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.CLOUD;
										for (int i = 0; i < particleCount; i++) {
											double u = Math.random();
											double v = Math.random();
											double theta = 2 * Math.PI * u;
											double phi = Math.acos(2 * v - 1);
											double directionX = Math.sin(phi) * Math.cos(theta);
											double directionY = Math.cos(phi);
											double directionZ = Math.sin(phi) * Math.sin(theta);
											double velocityX = directionX * particleSpeed;
											double velocityY = directionY * particleSpeed;
											double velocityZ = directionZ * particleSpeed;
											_level.sendParticles(particleType, centerX, centerY, centerZ, 0, velocityX, velocityY, velocityZ, particleSpeed);
										}
									}
									{
										double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 130;
										entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.Ethir = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
								}
							}
						} else {
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.LAST_HOPE.get(), 320, 0));
							{
								double _setval = 40;
								entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.mantra2_cooldown = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							if (world.isClientSide()) {
								SetupAnimationsProcedure.setAnimationClientside((Player) entity, "luzprimeira", false);
							}
							if (!world.isClientSide()) {
								if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
									List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
									synchronized (connections) {
										Iterator<Connection> iterator = connections.iterator();
										while (iterator.hasNext()) {
											Connection connection = iterator.next();
											if (!connection.isConnecting() && connection.isConnected())
												VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("luzprimeira"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
										}
									}
								}
							}
							if (entity instanceof Player _player) {
								if (_player.level().isClientSide()) {
									Minecraft _mc = Minecraft.getInstance();
									if (_mc.player != null && _mc.player.equals(_player)) {
										_mc.options.setCameraType(CameraType.THIRD_PERSON_BACK);
									}
								}
							}
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:usamantraluzum")), SoundSource.MASTER, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:usamantraluzum")), SoundSource.MASTER, 1, 1, false);
								}
							}
							if (world instanceof net.minecraft.server.level.ServerLevel) {
								net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
								int particleCount = (int) 40;
								double centerX = (entity.getX());
								double centerY = (entity.getY());
								double centerZ = (entity.getZ());
								double particleSpeed = 0.8;
								net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.CLOUD;
								for (int i = 0; i < particleCount; i++) {
									double u = Math.random();
									double v = Math.random();
									double theta = 2 * Math.PI * u;
									double phi = Math.acos(2 * v - 1);
									double directionX = Math.sin(phi) * Math.cos(theta);
									double directionY = Math.cos(phi);
									double directionZ = Math.sin(phi) * Math.sin(theta);
									double velocityX = directionX * particleSpeed;
									double velocityY = directionY * particleSpeed;
									double velocityZ = directionZ * particleSpeed;
									_level.sendParticles(particleType, centerX, centerY, centerZ, 0, velocityX, velocityY, velocityZ, particleSpeed);
								}
							}
							{
								double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 130;
								entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.Ethir = _setval;
									capability.syncPlayerVariables(entity);
								});
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

	private static Entity findEntityInWorldRange(LevelAccessor world, Class<? extends Entity> clazz, double x, double y, double z, double range) {
		return (Entity) world.getEntitiesOfClass(clazz, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
	}
}