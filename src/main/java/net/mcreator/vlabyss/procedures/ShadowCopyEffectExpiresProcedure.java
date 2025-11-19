package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

import java.util.Comparator;

public class ShadowCopyEffectExpiresProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 30, 0));
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:copy_tempo")), SoundSource.MASTER, 1, 1);
			} else {
				_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:copy_tempo")), SoundSource.MASTER, 1, 1, false);
			}
		}
		if (world instanceof net.minecraft.server.level.ServerLevel) {
			net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
			int particleCount = (int) 40;
			double centerX = (entity.getX());
			double centerY = (entity.getY());
			double centerZ = (entity.getZ());
			double particleSpeed = 0.6;
			net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.SQUID_INK;
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
		{
			try {
				net.minecraft.world.entity.Entity targetEntity = entity;
				double teleportX = ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posX);
				double teleportY = ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posY);
				double teleportZ = ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posZ);
				if (targetEntity != null) {
					if (targetEntity instanceof net.minecraft.server.level.ServerPlayer _player && !_player.level().isClientSide()) {
						_player.connection.teleport(teleportX, teleportY, teleportZ, _player.getYRot(), _player.getXRot());
					} else {
						targetEntity.teleportTo(teleportX, teleportY, teleportZ);
					}
				}
			} catch (Exception e) {
			}
		}
		if (world instanceof net.minecraft.server.level.ServerLevel) {
			net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
			int particleCount = (int) 40;
			double centerX = (entity.getX());
			double centerY = (entity.getY());
			double centerZ = (entity.getZ());
			double particleSpeed = 0.6;
			net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.SQUID_INK;
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
				_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:copy_tempo")), SoundSource.MASTER, 1, 1);
			} else {
				_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:copy_tempo")), SoundSource.MASTER, 1, 1, false);
			}
		}
		{
			final Vec3 _center = new Vec3(x, y, z);
			for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(5 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
				if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 40, 0));
			}
		}
	}
}