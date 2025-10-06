package net.mcreator.vlabyss.procedures;

import net.minecraft.world.item.ItemStack;

public class RunasPropertyValueProvider7Procedure {
	public static double execute(ItemStack itemstack) {
		if (itemstack.getOrCreateTag().getBoolean("runaciana")) {
			return 1;
		}
		return 0;
	}
}