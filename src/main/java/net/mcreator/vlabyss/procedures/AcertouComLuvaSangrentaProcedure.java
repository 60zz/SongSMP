package net.mcreator.vlabyss.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.CriticalHitEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.init.VlAbyssModItems;
import net.mcreator.vlabyss.VlAbyssMod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AcertouComLuvaSangrentaProcedure {
	@SubscribeEvent
	public static void onPlayerCriticalHit(CriticalHitEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity sourceentity) {
		execute(null, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity sourceentity) {
		if (sourceentity == null)
			return;
		if (sourceentity instanceof LivingEntity lv
				? CuriosApi.getCuriosHelper().findEquippedCurio(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get(), lv).isPresent()
				: false && !(sourceentity instanceof Player _plrCldCheck1 && _plrCldCheck1.getCooldowns().isOnCooldown(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get()))) {
			VlAbyssMod.LOGGER.info("");
		}
	}
}