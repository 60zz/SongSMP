package net.mcreator.vlabyss.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.vlabyss.entity.MantraSoulEntity;
import net.mcreator.vlabyss.entity.CorteChamaRetoEntity;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.List;
import java.util.Comparator;

public class CorteChamaRetoPlayerCollidesWithThisEntityProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(6 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
			for (Entity entityiterator : _entfound) {
				if (!(entityiterator instanceof MantraSoulEntity) || !(entityiterator instanceof CorteChamaRetoEntity)) {
					if (!(entity.getPersistentData().getString("invocador")).equals(entityiterator.getStringUUID())) {
						entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))), 8);
					}
				}
			}
		}
		entity.setNoGravity(true);
		((java.util.function.Supplier<Boolean>) () -> {
			try {
				if (entity != null && entity instanceof net.minecraft.world.entity.Entity) {
					net.minecraft.world.entity.Entity targetEntity = (net.minecraft.world.entity.Entity) entity;
					targetEntity.noPhysics = true;
					return true;
				}
				return false;
			} catch (Exception e) {
				return false;
			}
		}).get();
		VlAbyssMod.queueServerWork(20, () -> {
			if (!entity.level().isClientSide())
				entity.discard();
		});
	}
}