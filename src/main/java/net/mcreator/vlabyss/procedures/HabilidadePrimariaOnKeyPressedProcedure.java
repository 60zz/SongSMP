package net.mcreator.vlabyss.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkDirection;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.Connection;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.CameraType;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.init.VlAbyssModItems;
import net.mcreator.vlabyss.init.VlAbyssModEntities;
import net.mcreator.vlabyss.entity.RespiroPrimariaEntity;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.List;
import java.util.Iterator;
import java.util.Comparator;

public class HabilidadePrimariaOnKeyPressedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Entity target = null;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Respiro >= 1
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).MantraRegistrada == true) {
			if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).mantra1_cooldown < 1) {
				if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 40) {
					{
						double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 40;
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
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:respiro_hab1")), SoundSource.MASTER, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:respiro_hab1")), SoundSource.MASTER, 1, 1, false);
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:respiro_hab1_1")), SoundSource.MASTER, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:respiro_hab1_1")), SoundSource.MASTER, 1, 1, false);
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:respiro_hab1_2")), SoundSource.MASTER, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:respiro_hab1_2")), SoundSource.MASTER, 1, 1, false);
						}
					}
					if (entity instanceof LivingEntity _entity) {
						if (true) {
							CompoundTag _data = _entity.getPersistentData();
							if (!_data.contains("orig_speed")) {
								_data.putDouble("orig_speed", _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getBaseValue());
								if (_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
									_data.putDouble("orig_jump", _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).getBaseValue());
								}
							}
							_entity.setDeltaMovement(Vec3.ZERO);
							_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(0.0);
							if (_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
								_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).setBaseValue(0.0);
							}
							_data.putBoolean("is_immobile", true);
							if (_entity instanceof Mob _mob) {
								_mob.getNavigation().stop();
								_mob.setNoAi(true);
							}
						} else {
							CompoundTag _data = _entity.getPersistentData();
							if (_data.contains("orig_speed")) {
								_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(_data.getDouble("orig_speed"));
								_data.remove("orig_speed");
							}
							if (_data.contains("orig_jump") && _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
								_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).setBaseValue(_data.getDouble("orig_jump"));
								_data.remove("orig_jump");
							}
							_data.remove("is_immobile");
							if (_entity instanceof Mob _mob) {
								_mob.setNoAi(false);
							}
						}
					}
					if (world.isClientSide()) {
						SetupAnimationsProcedure.setAnimationClientside((Player) entity, "mantrarespiro1", false);
					}
					if (!world.isClientSide()) {
						if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
							List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
							synchronized (connections) {
								Iterator<Connection> iterator = connections.iterator();
								while (iterator.hasNext()) {
									Connection connection = iterator.next();
									if (!connection.isConnecting() && connection.isConnected())
										VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("mantrarespiro1"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
								}
							}
						}
					}
					VlAbyssMod.queueServerWork(20, () -> {
						if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(VlAbyssModItems.CAPUZ_ESQUECIDO.get(), lv).isPresent() : false) {
							{
								Entity _shootFrom = entity;
								Level projectileLevel = _shootFrom.level();
								if (!projectileLevel.isClientSide()) {
									Projectile _entityToSpawn = initArrowProjectile(createArrowWeaponItemStack(new RespiroPrimariaEntity(VlAbyssModEntities.RESPIRO_PRIMARIA.get(), 0, 0, 0, projectileLevel), 1, (byte) 0), entity, 12, true, false,
											false, AbstractArrow.Pickup.DISALLOWED);
									_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
									_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 1, 0);
									projectileLevel.addFreshEntity(_entityToSpawn);
								}
							}
						} else {
							{
								Entity _shootFrom = entity;
								Level projectileLevel = _shootFrom.level();
								if (!projectileLevel.isClientSide()) {
									Projectile _entityToSpawn = initArrowProjectile(createArrowWeaponItemStack(new RespiroPrimariaEntity(VlAbyssModEntities.RESPIRO_PRIMARIA.get(), 0, 0, 0, projectileLevel), 1, (byte) 0), entity, 8, true, false,
											false, AbstractArrow.Pickup.DISALLOWED);
									_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
									_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 1, 0);
									projectileLevel.addFreshEntity(_entityToSpawn);
								}
							}
						}
						if (entity instanceof LivingEntity _entity) {
							if (false) {
								CompoundTag _data = _entity.getPersistentData();
								if (!_data.contains("orig_speed")) {
									_data.putDouble("orig_speed", _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getBaseValue());
									if (_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
										_data.putDouble("orig_jump", _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).getBaseValue());
									}
								}
								_entity.setDeltaMovement(Vec3.ZERO);
								_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(0.0);
								if (_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
									_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).setBaseValue(0.0);
								}
								_data.putBoolean("is_immobile", true);
								if (_entity instanceof Mob _mob) {
									_mob.getNavigation().stop();
									_mob.setNoAi(true);
								}
							} else {
								CompoundTag _data = _entity.getPersistentData();
								if (_data.contains("orig_speed")) {
									_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(_data.getDouble("orig_speed"));
									_data.remove("orig_speed");
								}
								if (_data.contains("orig_jump") && _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
									_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).setBaseValue(_data.getDouble("orig_jump"));
									_data.remove("orig_jump");
								}
								_data.remove("is_immobile");
								if (_entity instanceof Mob _mob) {
									_mob.setNoAi(false);
								}
							}
						}
					});
					if (Minecraft.getInstance().options.keyShift.isDown()) {
						entity.setDeltaMovement(new Vec3(0, 1, 0));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 2));
					}
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
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Escuridao >= 1
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).MantraRegistrada == true) {
			if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).mantra1_cooldown < 1) {
				if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 85) {
					{
						double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 85;
						entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Ethir = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SHADOW_COPY.get(), 1200, 0));
					{
						double _setval = 180;
						entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.mantra1_cooldown = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					if (world.isClientSide()) {
						SetupAnimationsProcedure.setAnimationClientside((Player) entity, "shadowcopy", false);
					}
					if (!world.isClientSide()) {
						if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
							List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
							synchronized (connections) {
								Iterator<Connection> iterator = connections.iterator();
								while (iterator.hasNext()) {
									Connection connection = iterator.next();
									if (!connection.isConnecting() && connection.isConnected())
										VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("shadowcopy"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
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
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Luz >= 1
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).MantraRegistrada == true) {
			if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).mantra1_cooldown < 1) {
				if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 95) {
					if (!world.getEntitiesOfClass(Player.class,
							new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX()),
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY()),
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())))
									.inflate(3 / 2d),
							e -> true).isEmpty()) {
						target = findEntityInWorldRange(world, Player.class,
								(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX()),
								(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY()),
								(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ()), 3);
						if (!(target == null)) {
							if (target instanceof LivingEntity _entity)
								_entity.setHealth((target instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) + 10);
							if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 160, 1));
							{
								double _setval = 300;
								entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.mantra1_cooldown = _setval;
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
							if (entity instanceof LivingEntity _entity) {
								if (true) {
									CompoundTag _data = _entity.getPersistentData();
									if (!_data.contains("orig_speed")) {
										_data.putDouble("orig_speed", _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getBaseValue());
										if (_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
											_data.putDouble("orig_jump", _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).getBaseValue());
										}
									}
									_entity.setDeltaMovement(Vec3.ZERO);
									_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(0.0);
									if (_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
										_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).setBaseValue(0.0);
									}
									_data.putBoolean("is_immobile", true);
									if (_entity instanceof Mob _mob) {
										_mob.getNavigation().stop();
										_mob.setNoAi(true);
									}
								} else {
									CompoundTag _data = _entity.getPersistentData();
									if (_data.contains("orig_speed")) {
										_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(_data.getDouble("orig_speed"));
										_data.remove("orig_speed");
									}
									if (_data.contains("orig_jump") && _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
										_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).setBaseValue(_data.getDouble("orig_jump"));
										_data.remove("orig_jump");
									}
									_data.remove("is_immobile");
									if (_entity instanceof Mob _mob) {
										_mob.setNoAi(false);
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
							VlAbyssMod.queueServerWork(40, () -> {
								if (entity instanceof LivingEntity _entity) {
									if (false) {
										CompoundTag _data = _entity.getPersistentData();
										if (!_data.contains("orig_speed")) {
											_data.putDouble("orig_speed", _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getBaseValue());
											if (_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
												_data.putDouble("orig_jump", _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).getBaseValue());
											}
										}
										_entity.setDeltaMovement(Vec3.ZERO);
										_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(0.0);
										if (_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
											_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).setBaseValue(0.0);
										}
										_data.putBoolean("is_immobile", true);
										if (_entity instanceof Mob _mob) {
											_mob.getNavigation().stop();
											_mob.setNoAi(true);
										}
									} else {
										CompoundTag _data = _entity.getPersistentData();
										if (_data.contains("orig_speed")) {
											_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(_data.getDouble("orig_speed"));
											_data.remove("orig_speed");
										}
										if (_data.contains("orig_jump") && _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
											_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).setBaseValue(_data.getDouble("orig_jump"));
											_data.remove("orig_jump");
										}
										_data.remove("is_immobile");
										if (_entity instanceof Mob _mob) {
											_mob.setNoAi(false);
										}
									}
								}
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:atingemagialuzum")), SoundSource.MASTER, 1, 1);
									} else {
										_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:atingemagialuzum")), SoundSource.MASTER, 1, 1, false);
									}
								}
							});
							{
								double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 95;
								entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.Ethir = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
						}
					} else if (!world.getEntitiesOfClass(Monster.class,
							new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX()),
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY()),
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())))
									.inflate(3 / 2d),
							e -> true).isEmpty()) {
						target = findEntityInWorldRange(world, Monster.class,
								(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX()),
								(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY()),
								(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ()), 3);
						if (!(target == null)) {
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
							if (entity instanceof LivingEntity _entity) {
								if (true) {
									CompoundTag _data = _entity.getPersistentData();
									if (!_data.contains("orig_speed")) {
										_data.putDouble("orig_speed", _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getBaseValue());
										if (_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
											_data.putDouble("orig_jump", _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).getBaseValue());
										}
									}
									_entity.setDeltaMovement(Vec3.ZERO);
									_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(0.0);
									if (_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
										_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).setBaseValue(0.0);
									}
									_data.putBoolean("is_immobile", true);
									if (_entity instanceof Mob _mob) {
										_mob.getNavigation().stop();
										_mob.setNoAi(true);
									}
								} else {
									CompoundTag _data = _entity.getPersistentData();
									if (_data.contains("orig_speed")) {
										_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(_data.getDouble("orig_speed"));
										_data.remove("orig_speed");
									}
									if (_data.contains("orig_jump") && _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
										_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).setBaseValue(_data.getDouble("orig_jump"));
										_data.remove("orig_jump");
									}
									_data.remove("is_immobile");
									if (_entity instanceof Mob _mob) {
										_mob.setNoAi(false);
									}
								}
							}
							{
								double _setval = 300;
								entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.mantra1_cooldown = _setval;
									capability.syncPlayerVariables(entity);
								});
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
							VlAbyssMod.queueServerWork(40, () -> {
								if (entity instanceof LivingEntity _entity) {
									if (false) {
										CompoundTag _data = _entity.getPersistentData();
										if (!_data.contains("orig_speed")) {
											_data.putDouble("orig_speed", _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getBaseValue());
											if (_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
												_data.putDouble("orig_jump", _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).getBaseValue());
											}
										}
										_entity.setDeltaMovement(Vec3.ZERO);
										_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(0.0);
										if (_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
											_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).setBaseValue(0.0);
										}
										_data.putBoolean("is_immobile", true);
										if (_entity instanceof Mob _mob) {
											_mob.getNavigation().stop();
											_mob.setNoAi(true);
										}
									} else {
										CompoundTag _data = _entity.getPersistentData();
										if (_data.contains("orig_speed")) {
											_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(_data.getDouble("orig_speed"));
											_data.remove("orig_speed");
										}
										if (_data.contains("orig_jump") && _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
											_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).setBaseValue(_data.getDouble("orig_jump"));
											_data.remove("orig_jump");
										}
										_data.remove("is_immobile");
										if (_entity instanceof Mob _mob) {
											_mob.setNoAi(false);
										}
									}
								}
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:atingemagialuzum")), SoundSource.MASTER, 1, 1);
									} else {
										_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:atingemagialuzum")), SoundSource.MASTER, 1, 1, false);
									}
								}
							});
							if (target instanceof LivingEntity _entity)
								_entity.setHealth((target instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) - 6);
							if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 0));
							if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.CORTA_REGEN.get(), 200, 0));
							{
								double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 95;
								entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.Ethir = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
						}
					} else if (!world.getEntitiesOfClass(PathfinderMob.class,
							new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX()),
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY()),
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())))
									.inflate(3 / 2d),
							e -> true).isEmpty()) {
						target = findEntityInWorldRange(world, PathfinderMob.class,
								(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX()),
								(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY()),
								(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ()), 3);
						if (!(target == null)) {
							if (target instanceof LivingEntity _entity)
								_entity.setHealth((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) + 10);
							{
								double _setval = 300;
								entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.mantra1_cooldown = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 160, 1));
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
							if (entity instanceof LivingEntity _entity) {
								if (true) {
									CompoundTag _data = _entity.getPersistentData();
									if (!_data.contains("orig_speed")) {
										_data.putDouble("orig_speed", _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getBaseValue());
										if (_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
											_data.putDouble("orig_jump", _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).getBaseValue());
										}
									}
									_entity.setDeltaMovement(Vec3.ZERO);
									_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(0.0);
									if (_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
										_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).setBaseValue(0.0);
									}
									_data.putBoolean("is_immobile", true);
									if (_entity instanceof Mob _mob) {
										_mob.getNavigation().stop();
										_mob.setNoAi(true);
									}
								} else {
									CompoundTag _data = _entity.getPersistentData();
									if (_data.contains("orig_speed")) {
										_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(_data.getDouble("orig_speed"));
										_data.remove("orig_speed");
									}
									if (_data.contains("orig_jump") && _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
										_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).setBaseValue(_data.getDouble("orig_jump"));
										_data.remove("orig_jump");
									}
									_data.remove("is_immobile");
									if (_entity instanceof Mob _mob) {
										_mob.setNoAi(false);
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
							VlAbyssMod.queueServerWork(40, () -> {
								if (entity instanceof LivingEntity _entity) {
									if (false) {
										CompoundTag _data = _entity.getPersistentData();
										if (!_data.contains("orig_speed")) {
											_data.putDouble("orig_speed", _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getBaseValue());
											if (_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
												_data.putDouble("orig_jump", _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).getBaseValue());
											}
										}
										_entity.setDeltaMovement(Vec3.ZERO);
										_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(0.0);
										if (_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
											_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).setBaseValue(0.0);
										}
										_data.putBoolean("is_immobile", true);
										if (_entity instanceof Mob _mob) {
											_mob.getNavigation().stop();
											_mob.setNoAi(true);
										}
									} else {
										CompoundTag _data = _entity.getPersistentData();
										if (_data.contains("orig_speed")) {
											_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(_data.getDouble("orig_speed"));
											_data.remove("orig_speed");
										}
										if (_data.contains("orig_jump") && _entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null) {
											_entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).setBaseValue(_data.getDouble("orig_jump"));
											_data.remove("orig_jump");
										}
										_data.remove("is_immobile");
										if (_entity instanceof Mob _mob) {
											_mob.setNoAi(false);
										}
									}
								}
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:atingemagialuzum")), SoundSource.MASTER, 1, 1);
									} else {
										_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:atingemagialuzum")), SoundSource.MASTER, 1, 1, false);
									}
								}
							});
							{
								double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 95;
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
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Chama >= 1
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).MantraRegistrada == true) {
			if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).mantra1_cooldown < 1) {
				if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem())
						&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem())) {
					if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:swords")))
							|| (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:swords")))
							|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))
							|| (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))) {
						if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 100) {
							{
								double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 100;
								entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.Ethir = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							{
								double _setval = 40;
								entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.mantra1_cooldown = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							if (entity instanceof LivingEntity _entMainHand109 && _entMainHand109.getMainArm() == HumanoidArm.LEFT) {
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
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.FLAMING_CUT.get(), 100, 2));
							} else if (entity instanceof LivingEntity _entMainHand113 && _entMainHand113.getMainArm() == HumanoidArm.RIGHT) {
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
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.FLAMING_CUT.get(), 100, 2));
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
				} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:swords")))
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
						if (entity instanceof LivingEntity _entMainHand121 && _entMainHand121.getMainArm() == HumanoidArm.LEFT) {
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
								_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.FLAMING_CUT.get(), 100, 1));
						} else if (entity instanceof LivingEntity _entMainHand125 && _entMainHand125.getMainArm() == HumanoidArm.RIGHT) {
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
								_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.FLAMING_CUT.get(), 100, 1));
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
						if (entity instanceof LivingEntity _entMainHand131 && _entMainHand131.getMainArm() == HumanoidArm.LEFT) {
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
								_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.FLAMING_CUT.get(), 100, 0));
						} else if (entity instanceof LivingEntity _entMainHand135 && _entMainHand135.getMainArm() == HumanoidArm.RIGHT) {
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
								_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.FLAMING_CUT.get(), 100, 0));
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

	private static AbstractArrow initArrowProjectile(AbstractArrow entityToSpawn, Entity shooter, float damage, boolean silent, boolean fire, boolean particles, AbstractArrow.Pickup pickup) {
		entityToSpawn.setOwner(shooter);
		entityToSpawn.setBaseDamage(damage);
		if (silent)
			entityToSpawn.setSilent(true);
		if (fire)
			entityToSpawn.setSecondsOnFire(100);
		if (particles)
			entityToSpawn.setCritArrow(true);
		entityToSpawn.pickup = pickup;
		return entityToSpawn;
	}

	private static AbstractArrow createArrowWeaponItemStack(AbstractArrow entityToSpawn, int knockback, byte piercing) {
		if (knockback > 0)
			entityToSpawn.setKnockback(knockback);
		if (piercing > 0)
			entityToSpawn.setPierceLevel(piercing);
		return entityToSpawn;
	}

	private static Entity findEntityInWorldRange(LevelAccessor world, Class<? extends Entity> clazz, double x, double y, double z, double range) {
		return (Entity) world.getEntitiesOfClass(clazz, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
	}
}