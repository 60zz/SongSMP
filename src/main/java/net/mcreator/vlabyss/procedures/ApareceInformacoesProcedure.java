package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.Entity;

public class ApareceInformacoesProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if (entity.getPersistentData().getDouble("menuhabilidades") >= 1) {
			return true;
		}
		return false;
	}
}