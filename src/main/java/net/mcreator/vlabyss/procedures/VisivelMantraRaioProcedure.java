package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

public class VisivelMantraRaioProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).MantraRegistrada == true
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).Tempestade >= 1) {
			return true;
		}
		return false;
	}
}