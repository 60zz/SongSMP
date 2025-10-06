package net.mcreator.vlabyss.procedures;

import net.minecraft.world.item.ItemStack;

public class RunasPropertyValueProvider3Procedure {
	public static double execute(ItemStack itemstack) {
		if (itemstack.getOrCreateTag().getBoolean("runavermelha")) {
			return 1;
		}
		return 0;
	}
}