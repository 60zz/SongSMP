package net.mcreator.vlabyss.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.init.VlAbyssModItems;

public class MapaabyssalDisplayOverlayIngameProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == VlAbyssModItems.MAPA_ABYSS.get()) {
			return true;
		}
		return false;
	}
}