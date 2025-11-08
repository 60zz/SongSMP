package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.Entity;

public class ClicouNosIconesProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(entity.getPersistentData().getDouble("menuhabilidades") >= 1)) {
			entity.getPersistentData().putDouble("menuhabilidades", 1);
		} else {
			entity.getPersistentData().remove("menuhabilidades");
		}
	}
}