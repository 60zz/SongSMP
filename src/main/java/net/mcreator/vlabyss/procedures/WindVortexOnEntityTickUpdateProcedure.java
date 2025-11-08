package net.mcreator.vlabyss.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;

import java.util.Comparator;

public class WindVortexOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double dx = 0;
		double dy = 0;
		double dz = 0;
		double distance = 0;
		{
			final Vec3 _center = new Vec3(x, y, z);
			for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(6 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
				if (!(entity.getPersistentData().getString("invocador")).equals(entityiterator.getStringUUID())) {
					dx = entity.getX() - entityiterator.getX();
					dy = entity.getY() - entityiterator.getY();
					dz = entity.getZ() - entityiterator.getZ();
					distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
					if (distance > 0) {
						entityiterator.setDeltaMovement(new Vec3(((dx / distance) * 0.1), (entityiterator.getY() + 0.1), ((dz / distance) * 0.1)));
						if (entityiterator.tickCount - (entityiterator instanceof LivingEntity _livEnt ? _livEnt.getLastHurtByMobTimestamp() : 0) > 10) {
							entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))), 1);
							if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.ENFRAQUECIDO.get(), 10, 0));
						}
					}
				}
			}
		}
	}
}