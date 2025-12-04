package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.CriticalHitEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.init.VlAbyssModEntities;
import net.mcreator.vlabyss.init.VlAbyssModAttributes;
import net.mcreator.vlabyss.entity.EchoOfShadowEntity;

import javax.annotation.Nullable;

import java.util.Comparator;

@Mod.EventBusSubscriber
public class DeuCriticoEchoOfShadowsProcedure {
	private static CriticalHitEvent _event;

	@SubscribeEvent
	public static void onPlayerCriticalHit(CriticalHitEvent event) {
		_event = event;
		execute(event, event.getEntity().level(), event.getTarget(), event.getEntity(), event.getDamageModifier());
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity, double damagemodifier) {
		execute(null, world, entity, sourceentity, damagemodifier);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity, double damagemodifier) {
		if (entity == null || sourceentity == null)
			return;
		if (!(entity instanceof EchoOfShadowEntity)) {
			if (sourceentity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(VlAbyssModMobEffects.ECHO_OF_SHADOWS.get())) {
				if (world instanceof net.minecraft.server.level.ServerLevel) {
					net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
					int particleCount = (int) 40;
					double centerX = (entity.getX());
					double centerY = (entity.getY());
					double centerZ = (entity.getZ());
					double particleSpeed = 0.7;
					net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.PORTAL;
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
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = VlAbyssModEntities.ECHO_OF_SHADOW.get().spawn(_level, BlockPos.containing(entity.getX(), entity.getY() - 3, entity.getZ()), MobSpawnType.MOB_SUMMONED);
					if (entityToSpawn != null) {
						entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
					}
				}
				{
					final Vec3 _center = new Vec3((entity.getX()), (entity.getY() - 3), (entity.getZ()));
					for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(4 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
						if (entityiterator instanceof EchoOfShadowEntity) {
							if (entityiterator instanceof EchoOfShadowEntity _datEntSetS)
								_datEntSetS.getEntityData().set(EchoOfShadowEntity.DATA_invocador, (sourceentity.getStringUUID()));
							if (entityiterator instanceof EchoOfShadowEntity _datEntSetI)
								_datEntSetI.getEntityData().set(EchoOfShadowEntity.DATA_dano, (int) Math.round((damagemodifier
										+ (sourceentity instanceof LivingEntity _livingEntity16 && _livingEntity16.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity16.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0))
										* (sourceentity instanceof LivingEntity _livingEntity17 && _livingEntity17.getAttributes().hasAttribute(VlAbyssModAttributes.DARKNESS_BONUS.get())
												? _livingEntity17.getAttribute(VlAbyssModAttributes.DARKNESS_BONUS.get()).getValue()
												: 0)
										* (sourceentity instanceof LivingEntity _livingEntity18 && _livingEntity18.getAttributes().hasAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get())
												? _livingEntity18.getAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get()).getValue()
												: 0)));
						}
					}
				}
			}
		} else {
			if (!entity.level().isClientSide())
				entity.discard();
		}
	}
}