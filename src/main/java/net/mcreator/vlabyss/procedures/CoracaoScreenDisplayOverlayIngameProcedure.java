package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;

public class CoracaoScreenDisplayOverlayIngameProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(VlAbyssModMobEffects.INSANO.get())) {
			return true;
		}
		return false;
	}
}