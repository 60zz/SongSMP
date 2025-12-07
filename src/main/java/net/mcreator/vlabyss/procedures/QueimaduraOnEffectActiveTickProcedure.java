package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;

public class QueimaduraOnEffectActiveTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity.tickCount - (entity instanceof LivingEntity _livEnt ? _livEnt.getLastHurtByMobTimestamp() : 0) > 20) {
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.QUEIMADURA.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.QUEIMADURA.get()).getAmplifier() : 0) >= 1) {
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))),
						(entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.QUEIMADURA.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.QUEIMADURA.get()).getAmplifier() : 0) + 1);
			} else {
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))), 1);
			}
		}
	}
}