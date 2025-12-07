package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.vlabyss.VlAbyssMod;

public class CloneOnInitialEntitySpawnProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		VlAbyssMod.queueServerWork(Mth.nextInt(RandomSource.create(), 100, 200), () -> {
			if (entity.isAlive()) {
				if (!entity.level().isClientSide())
					entity.discard();
				if (world instanceof Level _level && !_level.isClientSide())
					_level.explode(entity, new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))), null, x, y, z,
							3, false, Level.ExplosionInteraction.BLOCK);
			}
		});
	}
}