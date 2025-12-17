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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.minecraft.network.Connection;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.CameraType;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModParticleTypes;
import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.init.VlAbyssModAttributes;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.List;
import java.util.Iterator;
import java.util.Comparator;

public class TempestadeSegundaHabilidadeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double dx = 0;
		double dy = 0;
		double dz = 0;
		Entity target = null;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Tempestade >= 1
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).MantraRegistrada == true
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).habilidade2 == true) {
			if (!((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).mantra2_cooldown > 0)) {
				if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).opcao_mantra2 == 1) {
					if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 110) {
						{
							double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 110;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.Ethir = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = Math.round(100 / (entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get())
									? _livingEntity0.getAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get()).getValue()
									: 0));
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.mantra2_cooldown = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if (!world.getLevelData().isRaining()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:dischargespark")), SoundSource.MASTER, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:dischargespark")), SoundSource.MASTER, 1, 1, false);
								}
							}
							new Object() {
								void timedLoop(int timedloopiterator, int timedlooptotal, int ticks) {
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (VlAbyssModParticleTypes.LIGHTNING_CLOAK_PARTICLE.get()), x, y, z, 15, 0.5, 0.5, 0.5, 1);
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (VlAbyssModParticleTypes.WHITE_SHOCK.get()), x, y, z, 8, 0.5, 0.5, 0.5, 1);
									final int tick2 = ticks;
									VlAbyssMod.queueServerWork(tick2, () -> {
										if (timedlooptotal > timedloopiterator + 1) {
											timedLoop(timedloopiterator + 1, timedlooptotal, tick2);
										}
									});
								}
							}.timedLoop(0, 4, 10);
							new Object() {
								void timedLoop(int timedloopiterator, int timedlooptotal, int ticks) {
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (VlAbyssModParticleTypes.LIGHTNING_CLOAK_PARTICLE.get()), x, y, z, 15, 0.5, 0.5, 0.5, 1);
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (VlAbyssModParticleTypes.WHITE_SHOCK.get()), x, y, z, 8, 0.5, 0.5, 0.5, 1);
									final int tick2 = ticks;
									VlAbyssMod.queueServerWork(tick2, () -> {
										if (timedlooptotal > timedloopiterator + 1) {
											timedLoop(timedloopiterator + 1, timedlooptotal, tick2);
										}
									});
								}
							}.timedLoop(0, 2, 15);
							{
								final Vec3 _center = new Vec3(x, y, z);
								for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(8 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
										.toList()) {
									if (!(entityiterator == entity)) {
										entityiterator.hurt(
												new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))),
												(float) (6
														* (entity instanceof LivingEntity _livingEntity10 && _livingEntity10.getAttributes().hasAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get())
																? _livingEntity10.getAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get()).getValue()
																: 0)
														* (entity instanceof LivingEntity _livingEntity11 && _livingEntity11.getAttributes().hasAttribute(VlAbyssModAttributes.THUNDERSTORM_BONUS.get())
																? _livingEntity11.getAttribute(VlAbyssModAttributes.THUNDERSTORM_BONUS.get()).getValue()
																: 0)));
										if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
											_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.PARALISADO.get(), 240, 0));
									}
								}
							}
						} else {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:dischargespark")), SoundSource.MASTER, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:dischargespark")), SoundSource.MASTER, 1, 1, false);
								}
							}
							new Object() {
								void timedLoop(int timedloopiterator, int timedlooptotal, int ticks) {
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (VlAbyssModParticleTypes.LIGHTNING_CLOAK_PARTICLE.get()), x, y, z, 25, 0.5, 0.5, 0.5, 1);
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (VlAbyssModParticleTypes.WHITE_SHOCK.get()), x, y, z, 20, 0.5, 0.5, 0.5, 1);
									final int tick2 = ticks;
									VlAbyssMod.queueServerWork(tick2, () -> {
										if (timedlooptotal > timedloopiterator + 1) {
											timedLoop(timedloopiterator + 1, timedlooptotal, tick2);
										}
									});
								}
							}.timedLoop(0, 4, 10);
							new Object() {
								void timedLoop(int timedloopiterator, int timedlooptotal, int ticks) {
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (VlAbyssModParticleTypes.LIGHTNING_CLOAK_PARTICLE.get()), x, y, z, 15, 0.5, 0.5, 0.5, 1);
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (VlAbyssModParticleTypes.WHITE_SHOCK.get()), x, y, z, 8, 0.5, 0.5, 0.5, 1);
									final int tick2 = ticks;
									VlAbyssMod.queueServerWork(tick2, () -> {
										if (timedlooptotal > timedloopiterator + 1) {
											timedLoop(timedloopiterator + 1, timedlooptotal, tick2);
										}
									});
								}
							}.timedLoop(0, 2, 15);
							{
								final Vec3 _center = new Vec3(x, y, z);
								for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(16 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
										.toList()) {
									if (!(entityiterator == entity)) {
										entityiterator.hurt(
												new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))),
												(float) (10
														* (entity instanceof LivingEntity _livingEntity24 && _livingEntity24.getAttributes().hasAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get())
																? _livingEntity24.getAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get()).getValue()
																: 0)
														* (entity instanceof LivingEntity _livingEntity25 && _livingEntity25.getAttributes().hasAttribute(VlAbyssModAttributes.THUNDERSTORM_BONUS.get())
																? _livingEntity25.getAttribute(VlAbyssModAttributes.THUNDERSTORM_BONUS.get()).getValue()
																: 0)));
										if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
											_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.PARALISADO.get(), 240, 1));
									}
								}
							}
						}
						if (world.isClientSide()) {
							SetupAnimationsProcedure.setAnimationClientside((Player) entity, "dischargepower", false);
						}
						if (!world.isClientSide()) {
							if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
								List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
								synchronized (connections) {
									Iterator<Connection> iterator = connections.iterator();
									while (iterator.hasNext()) {
										Connection connection = iterator.next();
										if (!connection.isConnecting() && connection.isConnected())
											VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("dischargepower"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
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
				} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).opcao_mantra2 == 2) {
					if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 90) {
						dx = entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(10)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX();
						dy = entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(10)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY();
						dz = entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(10)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ();
						if (!entity.isShiftKeyDown()) {
							{
								double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 90;
								entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.Ethir = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							{
								double _setval = Math.round(70 / (entity instanceof LivingEntity _livingEntity38 && _livingEntity38.getAttributes().hasAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get())
										? _livingEntity38.getAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get()).getValue()
										: 0));
								entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.mantra2_cooldown = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sparkswap")), SoundSource.MASTER, 1, 1);
								} else {
									_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sparkswap")), SoundSource.MASTER, 1, 1, false);
								}
							}
							world.addParticle((SimpleParticleType) (VlAbyssModParticleTypes.SHOCK_SWAP_PARTICLE.get()), (entity.getX()), (entity.getY() + 1), (entity.getZ()), 0, 0, 0);
							{
								try {
									net.minecraft.world.entity.Entity targetEntity = entity;
									double teleportX = dx;
									double teleportY = (dy + 1);
									double teleportZ = dz;
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
							world.addParticle((SimpleParticleType) (VlAbyssModParticleTypes.SHOCK_SWAP_PARTICLE.get()), dx, (dy + 1), dz, 0, 0, 0);
							{
								final Vec3 _center = new Vec3(dx, dy, dz);
								for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(9 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
										.toList()) {
									if (!(entity == entityiterator)) {
										entityiterator.hurt(
												new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))),
												(float) (8
														* (entity instanceof LivingEntity _livingEntity50 && _livingEntity50.getAttributes().hasAttribute(VlAbyssModAttributes.THUNDERSTORM_BONUS.get())
																? _livingEntity50.getAttribute(VlAbyssModAttributes.THUNDERSTORM_BONUS.get()).getValue()
																: 0)
														* (entity instanceof LivingEntity _livingEntity51 && _livingEntity51.getAttributes().hasAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get())
																? _livingEntity51.getAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get()).getValue()
																: 0)));
									}
								}
							}
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sparkswap")), SoundSource.MASTER, 1, 1);
								} else {
									_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sparkswap")), SoundSource.MASTER, 1, 1, false);
								}
							}
						} else {
							target = findEntityInWorldRange(world, LivingEntity.class, dx, dy, dz, 10);
							if (!(target == null)) {
								{
									double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 90;
									entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.Ethir = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								{
									double _setval = 70 / (entity instanceof LivingEntity _livingEntity61 && _livingEntity61.getAttributes().hasAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get())
											? _livingEntity61.getAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get()).getValue()
											: 0);
									entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.mantra2_cooldown = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(target.getX(), target.getY(), target.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sparkswap")), SoundSource.MASTER, 1, 1);
									} else {
										_level.playLocalSound((target.getX()), (target.getY()), (target.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sparkswap")), SoundSource.MASTER, 1, 1, false);
									}
								}
								world.addParticle((SimpleParticleType) (VlAbyssModParticleTypes.SHOCK_SWAP_PARTICLE.get()), (target.getX()), (target.getY() + 1), (target.getZ()), 0, 0, 0);
								{
									try {
										net.minecraft.world.entity.Entity targetEntity = target;
										double teleportX = (entity.getX());
										double teleportY = (entity.getY() + 1);
										double teleportZ = (entity.getZ());
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
								{
									try {
										net.minecraft.world.entity.Entity targetEntity = entity;
										double teleportX = dx;
										double teleportY = (dy + 1);
										double teleportZ = dz;
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
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sparkswap")), SoundSource.MASTER, 1, 1);
									} else {
										_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sparkswap")), SoundSource.MASTER, 1, 1, false);
									}
								}
								world.addParticle((SimpleParticleType) (VlAbyssModParticleTypes.SHOCK_SWAP_PARTICLE.get()), (entity.getX()), (entity.getY() + 1), (entity.getZ()), 0, 0, 0);
							} else {
								if (entity instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal("\u00A7cCriatura n\u00E3o est\u00E1 pr\u00F3xima do raio de alcance"), true);
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
							_player.displayClientMessage(Component.literal("\u00A7cSem \"ETHIR\" o suficiente"), true);
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")), SoundSource.MASTER, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")), SoundSource.MASTER, 1, 1, false);
							}
						}
					}
				} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).opcao_mantra2 == 3) {
					if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 115) {
						{
							double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 115;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.Ethir = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = Math.round(120 / (entity instanceof LivingEntity _livingEntity87 && _livingEntity87.getAttributes().hasAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get())
									? _livingEntity87.getAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get()).getValue()
									: 0));
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.mantra2_cooldown = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.ELETRIC_TOUCH.get(), 240, 0));
						if (world.isClientSide()) {
							SetupAnimationsProcedure.setAnimationClientside((Player) entity, "eletrictouch", false);
						}
						if (!world.isClientSide()) {
							if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
								List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
								synchronized (connections) {
									Iterator<Connection> iterator = connections.iterator();
									while (iterator.hasNext()) {
										Connection connection = iterator.next();
										if (!connection.isConnecting() && connection.isConnected())
											VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("eletrictouch"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
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