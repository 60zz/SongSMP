package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.TagKey;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;

public class ReconjurationProjectileHitsLivingEntityProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity immediatesourceentity) {
		if (entity == null || immediatesourceentity == null)
			return;
		if (!entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("vl_abyss:invencivel")))) {
			if (!((immediatesourceentity instanceof TraceableEntity _traceableEntity ? _traceableEntity.getOwner() : null) == entity)) {
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))), (float) (new Object() {
					public double getValue() {
						if (immediatesourceentity instanceof AbstractArrow) {
							return ((AbstractArrow) immediatesourceentity).getBaseDamage();
						}
						if (immediatesourceentity instanceof Fireball) {
							return 6.0;
						}
						if (immediatesourceentity instanceof ThrownPotion) {
							return 0.0;
						}
						return 0.0;
					}
				}.getValue()));
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.STORM_RECONJURATION.get(), 200, 0));
				if ((immediatesourceentity instanceof TraceableEntity _traceableEntity ? _traceableEntity.getOwner() : null) instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.RECONJURAR.get(), 200, 0));
			}
		}
		if (world instanceof net.minecraft.server.level.ServerLevel) {
			net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
			int particleCount = (int) 40;
			double centerX = x;
			double centerY = y;
			double centerZ = z;
			double particleSpeed = 0.8;
			net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.POOF;
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
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:reconjurationimpact")), SoundSource.MASTER, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:reconjurationimpact")), SoundSource.MASTER, 1, 1, false);
			}
		}
	}
}