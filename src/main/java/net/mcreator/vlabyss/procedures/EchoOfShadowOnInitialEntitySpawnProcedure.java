package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
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
import net.mcreator.vlabyss.entity.EchoOfShadowEntity;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.function.BiFunction;
import java.util.UUID;
import java.util.Comparator;

public class EchoOfShadowOnInitialEntitySpawnProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		entity.setInvulnerable(true);
		VlAbyssMod.queueServerWork(20, () -> {
			if (!entity.level().isClientSide())
				entity.discard();
			{
				final Vec3 _center = new Vec3(x, y, z);
				for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(9 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
					if (!(((new BiFunction<LevelAccessor, String, Entity>() {
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
					}).apply(world, (entity instanceof EchoOfShadowEntity _datEntS ? _datEntS.getEntityData().get(EchoOfShadowEntity.DATA_invocador) : ""))) == entityiterator)) {
						if (!entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("vl_abyss:invencivel")))) {
							entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))),
									Math.round((entity instanceof EchoOfShadowEntity _datEntI ? _datEntI.getEntityData().get(EchoOfShadowEntity.DATA_dano) : 0) / 2));
							if (world instanceof net.minecraft.server.level.ServerLevel) {
								net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
								int particleCount = (int) 50;
								double centerX = (entityiterator.getX());
								double centerY = (entityiterator.getY() + 1);
								double centerZ = (entityiterator.getZ());
								double particleSpeed = 0.6;
								net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.PORTAL;
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
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:echoofshadow")), SoundSource.MASTER, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:echoofshadow")), SoundSource.MASTER, 1, 1, false);
								}
							}
							if (world instanceof ServerLevel _level)
								_level.sendParticles((SimpleParticleType) (VlAbyssModParticleTypes.ECHO_SLASH_PARTICLE.get()), (entityiterator.getX()), (entityiterator.getY() + 1), (entityiterator.getZ()), 1, 0, 0, 0, 0);
						}
					}
				}
			}
		});
	}
}