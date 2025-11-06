package net.mcreator.vlabyss.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkDirection;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
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
import net.mcreator.vlabyss.init.VlAbyssModItems;
import net.mcreator.vlabyss.init.VlAbyssModEntities;
import net.mcreator.vlabyss.entity.RespiroPrimariaEntity;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.List;
import java.util.Iterator;

public class RespiroPrimeiraHabilidadeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Respiro >= 1
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).MantraRegistrada == true
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).habilidade1 == true) {
			if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).mantra1_cooldown < 1) {
				if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).hab1_nivel == 1) {
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
				} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).hab1_nivel >= 2) {
					if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 40) {
						{
							double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 50;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.Ethir = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = 15;
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
										Projectile _entityToSpawn = initArrowProjectile(createArrowWeaponItemStack(new RespiroPrimariaEntity(VlAbyssModEntities.RESPIRO_PRIMARIA.get(), 0, 0, 0, projectileLevel), 2, (byte) 0), entity, 16, true, false,
												false, AbstractArrow.Pickup.DISALLOWED);
										_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
										_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, (float) 1.3, 0);
										projectileLevel.addFreshEntity(_entityToSpawn);
									}
								}
							} else {
								{
									Entity _shootFrom = entity;
									Level projectileLevel = _shootFrom.level();
									if (!projectileLevel.isClientSide()) {
										Projectile _entityToSpawn = initArrowProjectile(createArrowWeaponItemStack(new RespiroPrimariaEntity(VlAbyssModEntities.RESPIRO_PRIMARIA.get(), 0, 0, 0, projectileLevel), 2, (byte) 0), entity, 12, true, false,
												false, AbstractArrow.Pickup.DISALLOWED);
										_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
										_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, (float) 1.3, 0);
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
							entity.setDeltaMovement(new Vec3(0, 1.2, 0));
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
}