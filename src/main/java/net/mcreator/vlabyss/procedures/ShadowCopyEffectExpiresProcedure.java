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
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import java.util.List;
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
		if (entity instanceof ServerPlayer _player) {
			BlockPos spawnPos = _player.getRespawnPosition();
			if (spawnPos != null) {
				ServerLevel spawnLevel = _player.server.getLevel(_player.getRespawnDimension());
				if (spawnLevel != null) {
					_player.teleportTo(spawnLevel, spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5, _player.getYRot(), _player.getXRot());
				}
			} else {
				ServerLevel overworld = _player.server.getLevel(Level.OVERWORLD);
				if (overworld != null) {
					BlockPos worldSpawn = overworld.getSharedSpawnPos();
					_player.teleportTo(overworld, worldSpawn.getX() + 0.5, worldSpawn.getY(), worldSpawn.getZ() + 0.5, _player.getYRot(), _player.getXRot());
				}
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
			double initialRadius = 4.0;
			for (int i = 0; i < particleCount; i++) {
				double u = Math.random();
				double v = Math.random();
				double theta = 2 * Math.PI * u;
				double phi = Math.acos(2 * v - 1);
				double startX = centerX + initialRadius * Math.sin(phi) * Math.cos(theta);
				double startY = centerY + initialRadius * Math.cos(phi);
				double startZ = centerZ + initialRadius * Math.sin(phi) * Math.sin(theta);
				double deltaX = centerX - startX;
				double deltaY = centerY - startY;
				double deltaZ = centerZ - startZ;
				double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
				double velocityX = (deltaX / distance) * particleSpeed;
				double velocityY = (deltaY / distance) * particleSpeed;
				double velocityZ = (deltaZ / distance) * particleSpeed;
				_level.sendParticles(particleType, startX, startY, startZ, 0, velocityX, velocityY, velocityZ, particleSpeed);
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
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(5 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
			for (Entity entityiterator : _entfound) {
				if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 40, 0));
			}
		}
	}
}