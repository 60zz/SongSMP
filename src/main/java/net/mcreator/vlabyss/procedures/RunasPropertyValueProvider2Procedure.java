package net.mcreator.vlabyss.procedures;

import net.minecraft.world.item.ItemStack;

public class RunasPropertyValueProvider2Procedure {
	public static double execute(ItemStack itemstack) {
		if (itemstack.getOrCreateTag().getBoolean("runaroxa")) {
			return 1;
		}
		return 0;
	}
}