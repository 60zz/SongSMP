package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.TagKey;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.entity.CavaleiroAladoEntity;
import net.mcreator.vlabyss.configuration.SsmpConfiguration;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.Comparator;

public class CavaleiroAladoOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.getPersistentData().getDouble("manaregen") <= 14) {
			entity.getPersistentData().putDouble("manaregen", (entity.getPersistentData().getDouble("manaregen") + 1));
		}
		if (entity.getPersistentData().getDouble("manaregen") >= 15) {
			if (!(entity.getPersistentData().getDouble("mana_atual") == entity.getPersistentData().getDouble("mana_total"))) {
				if ((entity instanceof CavaleiroAladoEntity _datEntL6 && _datEntL6.getEntityData().get(CavaleiroAladoEntity.DATA_ataquevoador)) == false
						&& (entity instanceof CavaleiroAladoEntity _datEntL7 && _datEntL7.getEntityData().get(CavaleiroAladoEntity.DATA_ataquechao)) == false) {
					entity.getPersistentData().putDouble("mana_atual", (entity.getPersistentData().getDouble("mana_atual") + 2));
					entity.getPersistentData().putDouble("manaregen", 0);
				}
			}
		}
		if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
			if (Math.random() >= 0 && !(Math.random() >= 0.5)) {
				if (entity.getPersistentData().getDouble("mana_atual") >= 120) {
					if ((entity instanceof CavaleiroAladoEntity _datEntL14 && _datEntL14.getEntityData().get(CavaleiroAladoEntity.DATA_ataquevoador)) == false
							&& (entity instanceof CavaleiroAladoEntity _datEntL15 && _datEntL15.getEntityData().get(CavaleiroAladoEntity.DATA_ataquechao)) == false) {
						if (entity instanceof CavaleiroAladoEntity) {
							((CavaleiroAladoEntity) entity).setAnimation("animation.model.ataquevoador");
						}
						if (entity instanceof CavaleiroAladoEntity _datEntSetL)
							_datEntSetL.getEntityData().set(CavaleiroAladoEntity.DATA_ataquevoador, true);
						entity.setDeltaMovement(new Vec3(0, 5, 0));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 280, 254));
						VlAbyssMod.queueServerWork(305, () -> {
							{
								final Vec3 _center = new Vec3(x, y, z);
								for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(12 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
										.toList()) {
									if (!(entityiterator instanceof CavaleiroAladoEntity) || !entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("vl_abyss:invencivel")))) {
										entityiterator.hurt(
												new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:danoabismoboss"))), entity),
												(float) (double) SsmpConfiguration.WINGEDKNIGHTFLYINGAXEGROUNDATTACKDAMAGE.get());
									}
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:batida_cavaleiro_alado")), SoundSource.MASTER, 1, 1);
										} else {
											_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:batida_cavaleiro_alado")), SoundSource.MASTER, 1, 1, false);
										}
									}
									if (world instanceof net.minecraft.server.level.ServerLevel) {
										net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
										int particleCount = (int) 40;
										double centerX = x;
										double centerY = y;
										double centerZ = z;
										double particleSpeed = 0.8;
										net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.CAMPFIRE_SIGNAL_SMOKE;
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
								}
							}
						});
						VlAbyssMod.queueServerWork(320, () -> {
							if (entity instanceof CavaleiroAladoEntity) {
								((CavaleiroAladoEntity) entity).setAnimation("empty");
							}
							if (entity instanceof CavaleiroAladoEntity) {
								((CavaleiroAladoEntity) entity).setAnimation("animation.model.preso no ch\u00E3o");
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
							VlAbyssMod.queueServerWork(160, () -> {
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
								if (entity instanceof CavaleiroAladoEntity _datEntSetL)
									_datEntSetL.getEntityData().set(CavaleiroAladoEntity.DATA_ataquevoador, false);
							});
						});
						entity.getPersistentData().putDouble("mana_atual", (entity.getPersistentData().getDouble("mana_atual") - 120));
					}
				}
			} else if (Math.random() >= 0.5) {
				if (entity.getPersistentData().getDouble("mana_atual") >= 80) {
					if ((entity instanceof CavaleiroAladoEntity _datEntL39 && _datEntL39.getEntityData().get(CavaleiroAladoEntity.DATA_ataquevoador)) == false
							&& (entity instanceof CavaleiroAladoEntity _datEntL40 && _datEntL40.getEntityData().get(CavaleiroAladoEntity.DATA_ataquechao)) == false) {
						if (entity instanceof CavaleiroAladoEntity) {
							((CavaleiroAladoEntity) entity).setAnimation("animation.model.ataqueno ch\u00E3o");
						}
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 254));
						VlAbyssMod.queueServerWork(75, () -> {
							{
								final Vec3 _center = new Vec3(x, y, z);
								for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(10 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
										.toList()) {
									if (!(entityiterator instanceof CavaleiroAladoEntity) || !entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("vl_abyss:invencivel")))) {
										entityiterator.hurt(
												new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:danoabismoboss"))), entity),
												(float) (double) SsmpConfiguration.WINGEDKNIGHTGROUNDAXEATTACKDAMAGE.get());
									}
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:batida_cavaleiro_alado")), SoundSource.MASTER, 1, 1);
										} else {
											_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:batida_cavaleiro_alado")), SoundSource.MASTER, 1, 1, false);
										}
									}
									if (world instanceof net.minecraft.server.level.ServerLevel) {
										net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
										int particleCount = (int) 40;
										double centerX = x;
										double centerY = y;
										double centerZ = z;
										double particleSpeed = 0.8;
										net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.CAMPFIRE_SIGNAL_SMOKE;
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
								}
							}
							if (entity instanceof CavaleiroAladoEntity _datEntSetL)
								_datEntSetL.getEntityData().set(CavaleiroAladoEntity.DATA_ataquechao, false);
						});
						if (entity instanceof CavaleiroAladoEntity _datEntSetL)
							_datEntSetL.getEntityData().set(CavaleiroAladoEntity.DATA_ataquechao, true);
						entity.getPersistentData().putDouble("mana_atual", (entity.getPersistentData().getDouble("mana_atual") - 80));
					}
				}
			}
		}
	}
}