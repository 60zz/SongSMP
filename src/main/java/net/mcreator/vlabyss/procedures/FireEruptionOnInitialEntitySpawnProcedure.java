package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.TagKey;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.init.VlAbyssModParticleTypes;
import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.entity.FireEruptionEntity;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.function.BiFunction;
import java.util.UUID;
import java.util.Comparator;

public class FireEruptionOnInitialEntitySpawnProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (VlAbyssModParticleTypes.FIRE_ERUPTION_PARTICLE.get()), x, (y + 2), z, 1, 0, 0, 0, 0);
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (VlAbyssModParticleTypes.BODY_FIRE_PARTICLE.get()), x, (y + 1), z, 30, 0.2, 0.1, 0.2, 0.1);
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:fireeruptionmantra")), SoundSource.MASTER, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:fireeruptionmantra")), SoundSource.MASTER, 1, 1, false);
			}
		}
		VlAbyssMod.queueServerWork(10, () -> {
			if (!entity.level().isClientSide())
				entity.discard();
			{
				final Vec3 _center = new Vec3(x, y, z);
				for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(4 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
					if (!(entityiterator == ((new BiFunction<LevelAccessor, String, Entity>() {
						@Override
						public Entity apply(LevelAccessor levelAccessor, String uuid) {
							if (levelAccessor instanceof ServerLevel serverLevel) {
								try {
									return serverLevel.getEntity(UUID.fromString(uuid));
								} catch (Exception e) {
								}
							}
							return null;
						}
					}).apply(world, (entity instanceof FireEruptionEntity _datEntS ? _datEntS.getEntityData().get(FireEruptionEntity.DATA_invocador) : ""))))) {
						if (!entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("vl_abyss:invencivel")))) {
							entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))),
									(float) (entity instanceof FireEruptionEntity _datEntI ? _datEntI.getEntityData().get(FireEruptionEntity.DATA_dano) : 0));
							if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEIMADURA.get(), 100, 1));
						}
					}
				}
			}
		});
	}
}