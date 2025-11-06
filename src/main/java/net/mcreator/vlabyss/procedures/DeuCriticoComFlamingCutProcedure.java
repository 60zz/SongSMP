package net.mcreator.vlabyss.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.CriticalHitEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
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

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModParticleTypes;
import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.init.VlAbyssModItems;
import net.mcreator.vlabyss.VlAbyssMod;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Iterator;

@Mod.EventBusSubscriber
public class DeuCriticoComFlamingCutProcedure {
	@SubscribeEvent
	public static void onPlayerCriticalHit(CriticalHitEvent event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getTarget(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		execute(null, world, x, y, z, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (sourceentity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(VlAbyssModMobEffects.FLAMING_CUT.get())) {
			if ((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).hab1_nivel == 1) {
				if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.FLAMING_CUT.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.FLAMING_CUT.get()).getAmplifier() : 0) == 0) {
					if (world instanceof net.minecraft.server.level.ServerLevel) {
						net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
						int particleCount = (int) 20;
						double centerX = (entity.getX());
						double centerY = (entity.getY() + 1);
						double centerZ = (entity.getZ());
						double particleSpeed = 0.6;
						net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.FLAME;
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
					if (world instanceof net.minecraft.server.level.ServerLevel) {
						net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
						int particleCount = (int) 20;
						double centerX = (entity.getX());
						double centerY = (entity.getY() + 1);
						double centerZ = (entity.getZ());
						double particleSpeed = 0.4;
						net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.SMALL_FLAME;
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
						SetupAnimationsProcedure.setAnimationClientside((Player) sourceentity, "mantragolpesoco", false);
					}
					if (!world.isClientSide()) {
						if (sourceentity instanceof Player && world instanceof ServerLevel srvLvl_) {
							List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
							synchronized (connections) {
								Iterator<Connection> iterator = connections.iterator();
								while (iterator.hasNext()) {
									Connection connection = iterator.next();
									if (!connection.isConnecting() && connection.isConnected())
										VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("mantragolpesoco"), sourceentity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
								}
							}
						}
					}
					if (sourceentity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(VlAbyssModItems.CAPUZ_ESQUECIDO.get(), lv).isPresent() : false) {
						entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))),
								(float) ((sourceentity instanceof LivingEntity _attributeContext ? _attributeContext.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE) : 0.0D) * 1.2));
					} else {
						entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))),
								(float) (sourceentity instanceof LivingEntity _attributeContext ? _attributeContext.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE) : 0.0D));
					}
					entity.setSecondsOnFire(8);
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.BURN_SOUL.get(), 60, 0));
					if (sourceentity instanceof LivingEntity _entity)
						_entity.removeEffect(VlAbyssModMobEffects.FLAMING_CUT.get());
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:chama_soco")), SoundSource.MASTER, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:chama_soco")), SoundSource.MASTER, 1, 1, false);
						}
					}
				} else if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.FLAMING_CUT.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.FLAMING_CUT.get()).getAmplifier() : 0) >= 1) {
					world.addParticle((SimpleParticleType) (VlAbyssModParticleTypes.FLAMING_SLASH.get()), (entity.getX()), (entity.getY() + 1), (entity.getZ()), 0, 0, 0);
					if (sourceentity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(VlAbyssModItems.CAPUZ_ESQUECIDO.get(), lv).isPresent() : false) {
						entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))),
								(float) (sourceentity instanceof LivingEntity _attributeContext ? _attributeContext.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE) : 0.0D));
					} else {
						entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))),
								(float) ((sourceentity instanceof LivingEntity _attributeContext ? _attributeContext.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE) : 0.0D)
										- (sourceentity instanceof LivingEntity _attributeContext ? _attributeContext.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE) : 0.0D) / 1.2));
					}
					entity.setSecondsOnFire(8);
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.BURN_SOUL.get(), 100, 0));
					if (sourceentity instanceof LivingEntity _entity)
						_entity.removeEffect(VlAbyssModMobEffects.FLAMING_CUT.get());
					if (world.isClientSide()) {
						SetupAnimationsProcedure.setAnimationClientside((Player) sourceentity, "mantragolpeespada", false);
					}
					if (!world.isClientSide()) {
						if (sourceentity instanceof Player && world instanceof ServerLevel srvLvl_) {
							List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
							synchronized (connections) {
								Iterator<Connection> iterator = connections.iterator();
								while (iterator.hasNext()) {
									Connection connection = iterator.next();
									if (!connection.isConnecting() && connection.isConnected())
										VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("mantragolpeespada"), sourceentity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
								}
							}
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:chama_blade")), SoundSource.MASTER, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:chama_blade")), SoundSource.MASTER, 1, 1, false);
						}
					}
				}
			} else if ((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).hab1_nivel >= 2) {
				if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.FLAMING_CUT.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.FLAMING_CUT.get()).getAmplifier() : 0) == 0) {
					if (world instanceof net.minecraft.server.level.ServerLevel) {
						net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
						int particleCount = (int) 20;
						double centerX = (entity.getX());
						double centerY = (entity.getY() + 1);
						double centerZ = (entity.getZ());
						double particleSpeed = 0.6;
						net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.FLAME;
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
					if (world instanceof net.minecraft.server.level.ServerLevel) {
						net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
						int particleCount = (int) 20;
						double centerX = (entity.getX());
						double centerY = (entity.getY() + 1);
						double centerZ = (entity.getZ());
						double particleSpeed = 0.4;
						net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.SMALL_FLAME;
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
						SetupAnimationsProcedure.setAnimationClientside((Player) sourceentity, "mantragolpesoco", false);
					}
					if (!world.isClientSide()) {
						if (sourceentity instanceof Player && world instanceof ServerLevel srvLvl_) {
							List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
							synchronized (connections) {
								Iterator<Connection> iterator = connections.iterator();
								while (iterator.hasNext()) {
									Connection connection = iterator.next();
									if (!connection.isConnecting() && connection.isConnected())
										VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("mantragolpesoco"), sourceentity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
								}
							}
						}
					}
					if (sourceentity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(VlAbyssModItems.CAPUZ_ESQUECIDO.get(), lv).isPresent() : false) {
						entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))),
								(float) ((sourceentity instanceof LivingEntity _attributeContext ? _attributeContext.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE) : 0.0D) * 1.4));
					} else {
						entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))),
								(float) ((sourceentity instanceof LivingEntity _attributeContext ? _attributeContext.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE) : 0.0D) * 1.2));
					}
					entity.setSecondsOnFire(8);
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.BURN_SOUL.get(), 60, 0));
					if (sourceentity instanceof LivingEntity _entity)
						_entity.removeEffect(VlAbyssModMobEffects.FLAMING_CUT.get());
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:chama_soco")), SoundSource.MASTER, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:chama_soco")), SoundSource.MASTER, 1, 1, false);
						}
					}
				} else if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.FLAMING_CUT.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.FLAMING_CUT.get()).getAmplifier() : 0) >= 1) {
					world.addParticle((SimpleParticleType) (VlAbyssModParticleTypes.FLAMING_SLASH.get()), (entity.getX()), (entity.getY() + 1), (entity.getZ()), 0, 0, 0);
					if (sourceentity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(VlAbyssModItems.CAPUZ_ESQUECIDO.get(), lv).isPresent() : false) {
						entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))),
								(float) ((sourceentity instanceof LivingEntity _attributeContext ? _attributeContext.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE) : 0.0D) * 1.2));
					} else {
						entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))),
								(float) (sourceentity instanceof LivingEntity _attributeContext ? _attributeContext.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE) : 0.0D));
					}
					entity.setSecondsOnFire(8);
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.BURN_SOUL.get(), 100, 0));
					if (sourceentity instanceof LivingEntity _entity)
						_entity.removeEffect(VlAbyssModMobEffects.FLAMING_CUT.get());
					if (world.isClientSide()) {
						SetupAnimationsProcedure.setAnimationClientside((Player) sourceentity, "mantragolpeespada", false);
					}
					if (!world.isClientSide()) {
						if (sourceentity instanceof Player && world instanceof ServerLevel srvLvl_) {
							List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
							synchronized (connections) {
								Iterator<Connection> iterator = connections.iterator();
								while (iterator.hasNext()) {
									Connection connection = iterator.next();
									if (!connection.isConnecting() && connection.isConnected())
										VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("mantragolpeespada"), sourceentity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
								}
							}
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:chama_blade")), SoundSource.MASTER, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:chama_blade")), SoundSource.MASTER, 1, 1, false);
						}
					}
				}
			}
		}
	}
}