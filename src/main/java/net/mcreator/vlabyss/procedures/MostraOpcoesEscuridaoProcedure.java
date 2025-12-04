package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

public class MostraOpcoesEscuridaoProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Escuridao >= 1) {
			return true;
		}
		return false;
	}
}