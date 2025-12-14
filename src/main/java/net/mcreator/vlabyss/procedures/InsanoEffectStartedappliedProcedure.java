package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.Entity;

public class InsanoEffectStartedappliedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putBoolean("insanidade", true);
	}
}