package net.mcreator.vlabyss.procedures;

import net.minecraft.world.item.ItemStack;

public class PurpleScytheHasItemGlowingEffectProcedure {
	public static boolean execute(ItemStack itemstack) {
		if (itemstack.isEnchanted()) {
			return true;
		} else if (itemstack.getOrCreateTag().getDouble("contador") == 10) {
			return true;
		}
		return false;
	}
}