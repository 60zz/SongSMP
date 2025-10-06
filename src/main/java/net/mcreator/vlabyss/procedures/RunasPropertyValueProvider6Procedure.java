package net.mcreator.vlabyss.procedures;

import net.minecraft.world.item.ItemStack;

public class RunasPropertyValueProvider6Procedure {
	public static double execute(ItemStack itemstack) {
		if (itemstack.getOrCreateTag().getBoolean("runapreta")) {
			return 1;
		}
		return 0;
	}
}