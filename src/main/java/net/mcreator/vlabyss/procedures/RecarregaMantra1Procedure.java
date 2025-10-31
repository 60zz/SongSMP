package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class RecarregaMantra1Procedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).mantra1_cooldown >= 1) {
			if (entity.getPersistentData().getDouble("mantra1cooldown_timer") <= 19) {
				entity.getPersistentData().putDouble("mantra1cooldown_timer", (entity.getPersistentData().getDouble("mantra1cooldown_timer") + 1));
			}
			if (entity.getPersistentData().getDouble("mantra1cooldown_timer") >= 20) {
				{
					double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).mantra1_cooldown - 1;
					entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.mantra1_cooldown = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				entity.getPersistentData().putDouble("mantra1cooldown_timer", 0);
			}
		}
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).mantra2_cooldown >= 1) {
			if (entity.getPersistentData().getDouble("mantra2cooldown_timer") <= 19) {
				entity.getPersistentData().putDouble("mantra2cooldown_timer", (entity.getPersistentData().getDouble("mantra2cooldown_timer") + 1));
			}
			if (entity.getPersistentData().getDouble("mantra2cooldown_timer") >= 20) {
				{
					double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).mantra1_cooldown - 1;
					entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.mantra1_cooldown = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				entity.getPersistentData().putDouble("mantra2cooldown_timer", 0);
			}
		}
	}
}