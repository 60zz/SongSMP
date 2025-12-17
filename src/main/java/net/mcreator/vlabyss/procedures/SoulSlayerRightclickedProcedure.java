package net.mcreator.vlabyss.procedures;

import net.minecraft.world.item.ItemStack;

public class SoulSlayerRightclickedProcedure {
	public static void execute(ItemStack itemstack) {
		if (itemstack.getOrCreateTag().getDouble("almas") >= 1) {
			itemstack.getOrCreateTag().putBoolean("ataquealmas", true);
		}
	}
}