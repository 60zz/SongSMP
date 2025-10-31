package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

public class RegenMantraOnEffectActiveTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getPersistentData().getDouble("manaregen_timer") <= 4) {
			entity.getPersistentData().putDouble("manaregen_timer", (entity.getPersistentData().getDouble("manaregen_timer") + 1));
		}
		if (entity.getPersistentData().getDouble("manaregen_timer") >= 5) {
			if (!((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).Ethir == (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new VlAbyssModVariables.PlayerVariables())).MaxEthir)) {
				{
					double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).Ethir + 1;
					entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.Ethir = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				entity.getPersistentData().putDouble("manaregen_timer", 0);
			} else {
				entity.getPersistentData().remove("manaregen_timer");
			}
		}
	}
}