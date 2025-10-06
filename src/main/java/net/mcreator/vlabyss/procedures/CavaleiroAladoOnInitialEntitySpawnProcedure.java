package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.configuration.SsmpConfiguration;

public class CavaleiroAladoOnInitialEntitySpawnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putDouble("manaregen", 0);
		entity.getPersistentData().putDouble("mana_total", ((double) SsmpConfiguration.TOTALMANTRAWINGEDKNIGHT.get()));
		entity.getPersistentData().putDouble("mana_atual", ((double) SsmpConfiguration.TOTALMANTRAWINGEDKNIGHT.get()));
	}
}