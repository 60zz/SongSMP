package net.mcreator.vlabyss.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.TagKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.init.VlAbyssModParticleTypes;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.Comparator;

public class RespiroPrimariaWhileProjectileFlyingTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity immediatesourceentity) {
		if (immediatesourceentity == null)
			return;
		{
			final Vec3 _center = new Vec3(x, y, z);
			for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(5 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
				if (!entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("vl_abyss:invencivel")))) {
					if (!((immediatesourceentity instanceof TraceableEntity _traceableEntity ? _traceableEntity.getOwner() : null) == entityiterator)) {
						entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))),
								(float) (new Object() {
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
					}
				}
			}
		}
		world.addParticle((SimpleParticleType) (VlAbyssModParticleTypes.VENTO_PARTICULA_1.get()), x, y, z, 0, 0, 0);
		world.addParticle((SimpleParticleType) (VlAbyssModParticleTypes.VENTO_PARTICULA_2.get()), (x + Mth.nextDouble(RandomSource.create(), -1, 1)), (y - Mth.nextDouble(RandomSource.create(), 0.1, 1)),
				(z + Mth.nextDouble(RandomSource.create(), -1, 1)), 0, 0, 0);
		if (Math.random() >= 0.9) {
			if (world instanceof ServerLevel _level) {
				LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
				entityToSpawn.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(x, y, z)));
				entityToSpawn.setVisualOnly(true);
				_level.addFreshEntity(entityToSpawn);
			}
		}
		immediatesourceentity.setNoGravity(true);
		((java.util.function.Supplier<Boolean>) () -> {
			try {
				if (immediatesourceentity != null && immediatesourceentity instanceof net.minecraft.world.entity.Entity) {
					net.minecraft.world.entity.Entity targetEntity = (net.minecraft.world.entity.Entity) immediatesourceentity;
					targetEntity.noPhysics = true;
					return true;
				}
				return false;
			} catch (Exception e) {
				return false;
			}
		}).get();
		VlAbyssMod.queueServerWork(40, () -> {
			if (!immediatesourceentity.level().isClientSide())
				immediatesourceentity.discard();
		});
	}
}