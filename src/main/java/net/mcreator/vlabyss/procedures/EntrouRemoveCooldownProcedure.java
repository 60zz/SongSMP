package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class EntrouRemoveCooldownProcedure {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity.getPersistentData().getBoolean("cooldown")) {
			entity.getPersistentData().remove("cooldown");
		}
		if (entity.getPersistentData().getBoolean("perry")) {
			entity.getPersistentData().remove("perry");
		}
		if (entity.getPersistentData().getBoolean("hitado")) {
			entity.getPersistentData().remove("hitado");
		}
	}
}