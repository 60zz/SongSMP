package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.init.VlAbyssModParticleTypes;
import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.VlAbyssMod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class TomouHitParryProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		execute(null, world, x, y, z, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (entity.getPersistentData().getBoolean("perry") == true) {
			if (!entity.getPersistentData().getBoolean("hitado")) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:swords")))
						|| (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:swords")))
						|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))
						|| (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))) {
					if (entity.getPersistentData().getBoolean("perry") == true) {
						if (!(sourceentity instanceof LivingEntity _livEnt5 && _livEnt5.hasEffect(VlAbyssModMobEffects.RESISTENCIA_QUEBRADA.get()))) {
							if (!(sourceentity instanceof LivingEntity _livEnt6 && _livEnt6.hasEffect(VlAbyssModMobEffects.QUEBRA.get()))) {
								if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEBRA.get(), 600, 0));
								sourceentity.getPersistentData().putBoolean("quebra1", true);
								VlAbyssMod.queueServerWork(600, () -> {
									sourceentity.getPersistentData().remove("quebra1");
								});
							} else if (sourceentity.getPersistentData().getBoolean("quebra1")) {
								if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEBRA.get(), 1200, 1));
								sourceentity.getPersistentData().putBoolean("quebra2", true);
								sourceentity.getPersistentData().remove("quebra1");
								VlAbyssMod.queueServerWork(1200, () -> {
									sourceentity.getPersistentData().remove("quebra2");
								});
							} else if (sourceentity.getPersistentData().getBoolean("quebra2")) {
								if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEBRA.get(), 2400, 2));
								sourceentity.getPersistentData().putBoolean("quebra3", true);
								sourceentity.getPersistentData().remove("quebra2");
								VlAbyssMod.queueServerWork(2400, () -> {
									sourceentity.getPersistentData().remove("quebra3");
								});
							} else if (sourceentity.getPersistentData().getBoolean("quebra3")) {
								sourceentity.getPersistentData().putBoolean("quebra4", true);
								if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEBRA.get(), 3600, 3));
								sourceentity.getPersistentData().remove("quebra3");
								VlAbyssMod.queueServerWork(3600, () -> {
									sourceentity.getPersistentData().remove("quebra4");
								});
							} else if (sourceentity.getPersistentData().getBoolean("quebra4")) {
								sourceentity.getPersistentData().remove("quebra4");
								if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.RESISTENCIA_QUEBRADA.get(), 200, 0));
								if (sourceentity instanceof LivingEntity _entity)
									_entity.removeEffect(VlAbyssModMobEffects.QUEBRA.get());
							}
						}
						{
							ItemStack _ist = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
							if (_ist.hurt(520, RandomSource.create(), null)) {
								_ist.shrink(1);
								_ist.setDamageValue(0);
							}
						}
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (VlAbyssModParticleTypes.PARRY_DEU_CERTO.get()), x, y, z, 15, 0.5, 0.5, 0.5, 0.1);
						entity.getPersistentData().remove("perry");
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:parry")), SoundSource.PLAYERS, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:parry")), SoundSource.PLAYERS, 1, 1, false);
							}
						}
						entity.getPersistentData().putBoolean("cooldown", true);
						VlAbyssMod.queueServerWork(30, () -> {
							entity.getPersistentData().remove("cooldown");
						});
						if (event != null && event.isCancelable()) {
							event.setCanceled(true);
						}
					}
				} else {
					if (entity.getPersistentData().getBoolean("perry") == true) {
						if (!(sourceentity instanceof LivingEntity _livEnt42 && _livEnt42.hasEffect(VlAbyssModMobEffects.RESISTENCIA_QUEBRADA.get()))) {
							if (!(sourceentity instanceof LivingEntity _livEnt43 && _livEnt43.hasEffect(VlAbyssModMobEffects.QUEBRA.get()))) {
								if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEBRA.get(), 600, 0));
								sourceentity.getPersistentData().putBoolean("quebra1", true);
								VlAbyssMod.queueServerWork(600, () -> {
									sourceentity.getPersistentData().remove("quebra1");
								});
							} else if (sourceentity.getPersistentData().getBoolean("quebra1")) {
								if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEBRA.get(), 1200, 1));
								sourceentity.getPersistentData().putBoolean("quebra2", true);
								sourceentity.getPersistentData().remove("quebra1");
								VlAbyssMod.queueServerWork(1200, () -> {
									sourceentity.getPersistentData().remove("quebra2");
								});
							} else if (sourceentity.getPersistentData().getBoolean("quebra2")) {
								if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEBRA.get(), 2400, 2));
								sourceentity.getPersistentData().putBoolean("quebra3", true);
								sourceentity.getPersistentData().remove("quebra2");
								VlAbyssMod.queueServerWork(2400, () -> {
									sourceentity.getPersistentData().remove("quebra3");
								});
							} else if (sourceentity.getPersistentData().getBoolean("quebra3")) {
								sourceentity.getPersistentData().putBoolean("quebra4", true);
								if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEBRA.get(), 3600, 3));
								sourceentity.getPersistentData().remove("quebra3");
								VlAbyssMod.queueServerWork(3600, () -> {
									sourceentity.getPersistentData().remove("quebra4");
								});
							} else if (sourceentity.getPersistentData().getBoolean("quebra4")) {
								sourceentity.getPersistentData().remove("quebra4");
								if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.RESISTENCIA_QUEBRADA.get(), 200, 0));
								if (sourceentity instanceof LivingEntity _entity)
									_entity.removeEffect(VlAbyssModMobEffects.QUEBRA.get());
							}
						}
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (VlAbyssModParticleTypes.PARRY_DEU_CERTO.get()), x, y, z, 15, 0.5, 0.5, 0.5, 0.1);
						entity.getPersistentData().remove("perry");
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:parry_hands")), SoundSource.PLAYERS, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:parry_hands")), SoundSource.PLAYERS, 1, 1, false);
							}
						}
						entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK)),
								(float) ((sourceentity instanceof LivingEntity _attributeContext ? _attributeContext.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE) : 0.0D)
										- (sourceentity instanceof LivingEntity _attributeContext ? _attributeContext.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE) : 0.0D) / 1.5));
						entity.getPersistentData().putBoolean("cooldown", true);
						VlAbyssMod.queueServerWork(30, () -> {
							entity.getPersistentData().remove("cooldown");
						});
						if (event != null && event.isCancelable()) {
							event.setCanceled(true);
						}
					}
				}
			}
		} else {
			if (!entity.getPersistentData().getBoolean("hitado")) {
				entity.getPersistentData().putBoolean("hitado", true);
				VlAbyssMod.queueServerWork(30, () -> {
					entity.getPersistentData().remove("hitado");
				});
			}
		}
	}
}