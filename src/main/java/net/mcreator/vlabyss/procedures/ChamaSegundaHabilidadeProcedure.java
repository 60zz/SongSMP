package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkDirection;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobSpawnType;
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
import net.mcreator.vlabyss.init.VlAbyssModEntities;
import net.mcreator.vlabyss.init.VlAbyssModAttributes;
import net.mcreator.vlabyss.entity.FireEruptionEntity;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.List;
import java.util.Iterator;
import java.util.Comparator;

public class ChamaSegundaHabilidadeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Chama >= 1
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).MantraRegistrada == true
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).habilidade1 == true) {
			if (!((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).mantra2_cooldown > 0)) {
				if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).opcao_mantra2 == 1) {
					if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 95) {
						{
							double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 95;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.Ethir = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = Math.round(30 / (entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get())
									? _livingEntity0.getAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get()).getValue()
									: 0));
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.mantra1_cooldown = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if (world.isClientSide()) {
							SetupAnimationsProcedure.setAnimationClientside((Player) entity, "bodyfiremantra", false);
						}
						if (!world.isClientSide()) {
							if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
								List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
								synchronized (connections) {
									Iterator<Connection> iterator = connections.iterator();
									while (iterator.hasNext()) {
										Connection connection = iterator.next();
										if (!connection.isConnecting() && connection.isConnected())
											VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("bodyfiremantra"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
									}
								}
							}
						}
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.BODY_FIRE.get(),
									(int) (300
											* (entity instanceof LivingEntity _livingEntity2 && _livingEntity2.getAttributes().hasAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get())
													? _livingEntity2.getAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get()).getValue()
													: 0)
											* (entity instanceof LivingEntity _livingEntity3 && _livingEntity3.getAttributes().hasAttribute(VlAbyssModAttributes.FLAME_BONUS.get())
													? _livingEntity3.getAttribute(VlAbyssModAttributes.FLAME_BONUS.get()).getValue()
													: 0)),
									0));
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:bodyfiremantra")), SoundSource.MASTER, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:bodyfiremantra")), SoundSource.MASTER, 1, 1, false);
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
				} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).opcao_mantra2 == 2) {
					if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 120) {
						{
							double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 120;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.Ethir = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = Math.round(70 / (entity instanceof LivingEntity _livingEntity9 && _livingEntity9.getAttributes().hasAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get())
									? _livingEntity9.getAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get()).getValue()
									: 0));
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.mantra1_cooldown = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if (world.isClientSide()) {
							SetupAnimationsProcedure.setAnimationClientside((Player) entity, "fireeruptionanimation", false);
						}
						if (!world.isClientSide()) {
							if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
								List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
								synchronized (connections) {
									Iterator<Connection> iterator = connections.iterator();
									while (iterator.hasNext()) {
										Connection connection = iterator.next();
										if (!connection.isConnecting() && connection.isConnected())
											VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("fireeruptionanimation"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
									}
								}
							}
						}
						VlAbyssMod.queueServerWork(15, () -> {
							if (world instanceof ServerLevel _level) {
								Entity entityToSpawn = VlAbyssModEntities.FIRE_ERUPTION.get().spawn(_level, BlockPos.containing(entity.getX() + 3, entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED);
								if (entityToSpawn != null) {
								}
							}
							if (world instanceof ServerLevel _level) {
								Entity entityToSpawn = VlAbyssModEntities.FIRE_ERUPTION.get().spawn(_level, BlockPos.containing(entity.getX() + 2, entity.getY(), entity.getZ() + 2), MobSpawnType.MOB_SUMMONED);
								if (entityToSpawn != null) {
								}
							}
							if (world instanceof ServerLevel _level) {
								Entity entityToSpawn = VlAbyssModEntities.FIRE_ERUPTION.get().spawn(_level, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ() + 3), MobSpawnType.MOB_SUMMONED);
								if (entityToSpawn != null) {
								}
							}
							if (world instanceof ServerLevel _level) {
								Entity entityToSpawn = VlAbyssModEntities.FIRE_ERUPTION.get().spawn(_level, BlockPos.containing(entity.getX() + -2, entity.getY(), entity.getZ() + 2), MobSpawnType.MOB_SUMMONED);
								if (entityToSpawn != null) {
								}
							}
							if (world instanceof ServerLevel _level) {
								Entity entityToSpawn = VlAbyssModEntities.FIRE_ERUPTION.get().spawn(_level, BlockPos.containing(entity.getX() + -3, entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED);
								if (entityToSpawn != null) {
								}
							}
							if (world instanceof ServerLevel _level) {
								Entity entityToSpawn = VlAbyssModEntities.FIRE_ERUPTION.get().spawn(_level, BlockPos.containing(entity.getX() + -2, entity.getY(), entity.getZ() + -2), MobSpawnType.MOB_SUMMONED);
								if (entityToSpawn != null) {
								}
							}
							if (world instanceof ServerLevel _level) {
								Entity entityToSpawn = VlAbyssModEntities.FIRE_ERUPTION.get().spawn(_level, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ() + -3), MobSpawnType.MOB_SUMMONED);
								if (entityToSpawn != null) {
								}
							}
							if (world instanceof ServerLevel _level) {
								Entity entityToSpawn = VlAbyssModEntities.FIRE_ERUPTION.get().spawn(_level, BlockPos.containing(entity.getX() + 2, entity.getY(), entity.getZ() + -2), MobSpawnType.MOB_SUMMONED);
								if (entityToSpawn != null) {
								}
							}
							{
								final Vec3 _center = new Vec3((entity.getX()), (entity.getY() + 1), (entity.getZ()));
								for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(16 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
										.toList()) {
									if ((entityiterator instanceof FireEruptionEntity _datEntS ? _datEntS.getEntityData().get(FireEruptionEntity.DATA_invocador) : "").equals("non")) {
										if (entityiterator instanceof FireEruptionEntity _datEntSetS)
											_datEntSetS.getEntityData().set(FireEruptionEntity.DATA_invocador, (entity.getStringUUID()));
									}
									if ((entityiterator instanceof FireEruptionEntity _datEntI ? _datEntI.getEntityData().get(FireEruptionEntity.DATA_dano) : 0) == 0) {
										if (entityiterator instanceof FireEruptionEntity _datEntSetI)
											_datEntSetI.getEntityData().set(FireEruptionEntity.DATA_dano,
													(int) (6 * (entity instanceof LivingEntity _livingEntity50 && _livingEntity50.getAttributes().hasAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get())
															? _livingEntity50.getAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get()).getValue()
															: 0)
															* (entity instanceof LivingEntity _livingEntity51 && _livingEntity51.getAttributes().hasAttribute(VlAbyssModAttributes.FLAME_BONUS.get())
																	? _livingEntity51.getAttribute(VlAbyssModAttributes.FLAME_BONUS.get()).getValue()
																	: 0)));
									}
								}
							}
						});
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