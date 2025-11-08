package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

public class TextoDoDanoProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		if (entity.getPersistentData().getDouble("menuhabilidades") == 1) {
			if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Chama >= 1) {
				if (!entity.getPersistentData().getBoolean("capuzesquecido")) {
					return "\u00A7a80% Mantra Damage";
				} else {
					return "\u00A7a100% Mantra Damage";
				}
			} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Escuridao >= 1) {
				return "\u00A7aNatural Damage";
			} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Luz >= 1) {
				return "\u00A7a6 Mantra Damage on Mobs";
			} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Nevasca >= 1) {
				return "\u00A7b85 de Mantra";
			} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Tempestade >= 1) {
				return "\u00A7b85 de Mantra";
			} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Respiro >= 1) {
				if (!entity.getPersistentData().getBoolean("capuzesquecido")) {
					return "\u00A7a8 Mantra Damage";
				} else {
					return "\u00A7a12 Mantra Damage";
				}
			}
		}
		return "Mantra Damage";
	}
}