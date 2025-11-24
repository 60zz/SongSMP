package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.BlockEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CancelaPortalNetherProcedureProcedure {
	@SubscribeEvent
	public static void onPortalCreated(BlockEvent.PortalSpawnEvent event) {
		execute(event);
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		if (event != null && event.isCancelable()) {
			event.setCanceled(true);
		}
	}
}