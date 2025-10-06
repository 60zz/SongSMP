package net.mcreator.vlabyss.procedures;

import net.minecraft.world.item.ItemStack;

public class RunasPropertyValueProviderProcedure {
	public static double execute(ItemStack itemstack) {
		if (itemstack.getOrCreateTag().getBoolean("runaazul")) {
			return 1;
		}
		return 0;
	}
}