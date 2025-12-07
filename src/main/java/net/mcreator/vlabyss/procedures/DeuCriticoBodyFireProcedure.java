package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class DeuCriticoBodyFireProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(Entity entity, Entity sourceentity) {
		execute(null, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (sourceentity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(VlAbyssModMobEffects.BODY_FIRE.get())) {
			if (!(entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(VlAbyssModMobEffects.QUEIMADURA.get()))) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.QUEIMADURA.get(), 100, 0));
			}
		}
		if (entity instanceof LivingEntity _livEnt3 && _livEnt3.hasEffect(VlAbyssModMobEffects.BODY_FIRE.get())) {
			if (sourceentity instanceof LivingEntity _livEnt4 && _livEnt4.hasEffect(VlAbyssModMobEffects.BURN_ARMOR.get())) {
				if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.BURN_ARMOR.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.BURN_ARMOR.get()).getAmplifier() : 0) == 0) {
					if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.BURN_ARMOR.get(), 80, 1));
				} else if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.BURN_ARMOR.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.BURN_ARMOR.get()).getAmplifier() : 0) == 1) {
					if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.BURN_ARMOR.get(), 80, 2));
				} else if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.BURN_ARMOR.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.BURN_ARMOR.get()).getAmplifier() : 0) == 2) {
					if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.BURN_ARMOR.get(), 80, 3));
				} else if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.BURN_ARMOR.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.BURN_ARMOR.get()).getAmplifier() : 0) == 3) {
					if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.BURN_ARMOR.get(), 80, 4));
				} else if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.BURN_ARMOR.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.BURN_ARMOR.get()).getAmplifier() : 0) == 4) {
					if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.BURN_ARMOR.get(), 80, 5));
				}
			} else {
				if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.BURN_ARMOR.get(), 80, 0));
			}
		}
	}
}