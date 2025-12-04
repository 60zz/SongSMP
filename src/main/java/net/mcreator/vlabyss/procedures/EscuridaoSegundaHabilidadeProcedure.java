package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkDirection;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.minecraft.network.Connection;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.CameraType;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.init.VlAbyssModAttributes;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.List;
import java.util.Iterator;
import java.util.Comparator;

public class EscuridaoSegundaHabilidadeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Escuridao >= 1
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).MantraRegistrada == true
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).habilidade1 == true) {
			if (!((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).mantra2_cooldown > 0)) {
				if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).opcao_mantra2 == 1) {
					if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 70) {
						{
							double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 70;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.Ethir = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = Math.round(50 / (entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get())
									? _livingEntity0.getAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get()).getValue()
									: 0));
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.mantra2_cooldown = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if (world.isClientSide()) {
							SetupAnimationsProcedure.setAnimationClientside((Player) entity, "shadevision", false);
						}
						if (!world.isClientSide()) {
							if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
								List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
								synchronized (connections) {
									Iterator<Connection> iterator = connections.iterator();
									while (iterator.hasNext()) {
										Connection connection = iterator.next();
										if (!connection.isConnecting() && connection.isConnected())
											VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("shadevision"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
									}
								}
							}
						}
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SHADE_VISION.get(),
									(int) (500
											* (entity instanceof LivingEntity _livingEntity2 && _livingEntity2.getAttributes().hasAttribute(VlAbyssModAttributes.DARKNESS_BONUS.get())
													? _livingEntity2.getAttribute(VlAbyssModAttributes.DARKNESS_BONUS.get()).getValue()
													: 0)
											* (entity instanceof LivingEntity _livingEntity3 && _livingEntity3.getAttributes().hasAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get())
													? _livingEntity3.getAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get()).getValue()
													: 0)),
									0));
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:ativacaoshadevision")), SoundSource.MASTER, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:ativacaoshadevision")), SoundSource.MASTER, 1, 1, false);
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
					if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 110) {
						{
							double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 110;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.Ethir = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = Math.round(60 / (entity instanceof LivingEntity _livingEntity8 && _livingEntity8.getAttributes().hasAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get())
									? _livingEntity8.getAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get()).getValue()
									: 0));
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.mantra2_cooldown = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.ECHO_OF_SHADOWS.get(),
									(int) (400
											* (entity instanceof LivingEntity _livingEntity9 && _livingEntity9.getAttributes().hasAttribute(VlAbyssModAttributes.DARKNESS_BONUS.get())
													? _livingEntity9.getAttribute(VlAbyssModAttributes.DARKNESS_BONUS.get()).getValue()
													: 0)
											* (entity instanceof LivingEntity _livingEntity10 && _livingEntity10.getAttributes().hasAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get())
													? _livingEntity10.getAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get()).getValue()
													: 0)),
									0));
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
					if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 95) {
						{
							double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 95;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.Ethir = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = Math.round(50 / (entity instanceof LivingEntity _livingEntity14 && _livingEntity14.getAttributes().hasAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get())
									? _livingEntity14.getAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get()).getValue()
									: 0));
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.mantra2_cooldown = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							final Vec3 _center = new Vec3(x, y, z);
							for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(9 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
								if (!(entityiterator == entity)) {
									entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))),
											4);
									{
										double _setval = 40;
										entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.Ethir = _setval;
											capability.syncPlayerVariables(entityiterator);
										});
									}
									if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
										_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 120, 0));
									if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
										_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 120, 0));
									if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
										_entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 120, 0));
								}
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 1));
								if (entity instanceof LivingEntity _entity)
									_entity.setAbsorptionAmount(6);
								{
									double _setval = 55;
									entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.Ethir = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
							}
						}
						if (world instanceof net.minecraft.server.level.ServerLevel) {
							net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
							int particleCount = (int) 80;
							double centerX = x;
							double centerY = y;
							double centerZ = z;
							double particleSpeed = 0.5;
							net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.SQUID_INK;
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
						if (world.isClientSide()) {
							SetupAnimationsProcedure.setAnimationClientside((Player) entity, "shadevision", false);
						}
						if (!world.isClientSide()) {
							if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
								List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
								synchronized (connections) {
									Iterator<Connection> iterator = connections.iterator();
									while (iterator.hasNext()) {
										Connection connection = iterator.next();
										if (!connection.isConnecting() && connection.isConnected())
											VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("shadevision"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
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
}