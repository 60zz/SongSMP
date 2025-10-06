package net.mcreator.vlabyss.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.CriticalHitEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.init.VlAbyssModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AcertouComAnelEnferrujadoProcedure {
	@SubscribeEvent
	public static void onPlayerCriticalHit(CriticalHitEvent event) {
		execute(event, event.getTarget(), event.getEntity());
	}

	public static void execute(Entity entity, Entity sourceentity) {
		execute(null, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (sourceentity instanceof LivingEntity lv
				? CuriosApi.getCuriosHelper().findEquippedCurio(VlAbyssModItems.ANEL_ENFERRUJADO.get(), lv).isPresent()
				: false && !(sourceentity instanceof Player _plrCldCheck1 && _plrCldCheck1.getCooldowns().isOnCooldown(VlAbyssModItems.ANEL_ENFERRUJADO.get()))) {
			if (Math.random() >= 0.85) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.CORTA_REGEN.get(), 60, 0));
				if (sourceentity instanceof Player _player)
					_player.getCooldowns().addCooldown(VlAbyssModItems.ANEL_ENFERRUJADO.get(), 400);
			}
		}
	}
}