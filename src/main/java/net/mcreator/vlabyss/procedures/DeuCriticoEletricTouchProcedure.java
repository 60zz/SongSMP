package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.CriticalHitEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.Direction;

import net.mcreator.vlabyss.init.VlAbyssModParticleTypes;
import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.init.VlAbyssModAttributes;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class DeuCriticoEletricTouchProcedure {
	private static CriticalHitEvent _event;

	@SubscribeEvent
	public static void onPlayerCriticalHit(CriticalHitEvent event) {
		_event = event;
		execute(event, event.getEntity().level(), event.getTarget(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		execute(null, world, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (sourceentity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(VlAbyssModMobEffects.ELETRIC_TOUCH.get())) {
			world.addParticle((SimpleParticleType) (VlAbyssModParticleTypes.ELETRIC_ORB_PARTICLE.get()), (entity.getX()), (entity.getY() + 1), (entity.getZ()), 0, 0, 0);
			if (sourceentity instanceof LivingEntity _entity)
				_entity.removeEffect(VlAbyssModMobEffects.ELETRIC_TOUCH.get());
			if ((sourceentity.getDirection()) == Direction.NORTH) {
				entity.setDeltaMovement(new Vec3(0, 0.3, (entity.getLookAngle().z + -4)));
			} else if ((sourceentity.getDirection()) == Direction.SOUTH) {
				entity.setDeltaMovement(new Vec3(0, 0.3, (entity.getLookAngle().z + 4)));
			} else if ((sourceentity.getDirection()) == Direction.WEST) {
				entity.setDeltaMovement(new Vec3((entity.getLookAngle().x + -4), 0.3, 0));
			} else if ((sourceentity.getDirection()) == Direction.EAST) {
				entity.setDeltaMovement(new Vec3((entity.getLookAngle().x + 4), 0.3, 0));
			}
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.PARALISADO.get(),
						(int) (160
								* (sourceentity instanceof LivingEntity _livingEntity22 && _livingEntity22.getAttributes().hasAttribute(VlAbyssModAttributes.THUNDERSTORM_BONUS.get())
										? _livingEntity22.getAttribute(VlAbyssModAttributes.THUNDERSTORM_BONUS.get()).getValue()
										: 0)
								* (sourceentity instanceof LivingEntity _livingEntity23 && _livingEntity23.getAttributes().hasAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get())
										? _livingEntity23.getAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get()).getValue()
										: 0)),
						1));
			world.addParticle(ParticleTypes.EXPLOSION_EMITTER, (sourceentity.getX()), (sourceentity.getY()), (sourceentity.getZ()), 0, 0, 0);
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (VlAbyssModParticleTypes.LIGHTNING_CLOAK_PARTICLE.get()), (sourceentity.getX()), (sourceentity.getY()), (sourceentity.getZ()), 25, 0.25, 0.25, 0.25, 1);
		}
	}
}