package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.Entity;

public class InsanoEffectExpiresProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().remove("insanidade");
	}
}