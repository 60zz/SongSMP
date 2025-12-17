package net.mcreator.vlabyss.procedures;

import net.minecraft.world.item.ItemStack;

public class SoulSlayerHasItemGlowingEffectProcedure {
	public static boolean execute(ItemStack itemstack) {
		if (itemstack.getOrCreateTag().getBoolean("ataquealmas")) {
			return true;
		} else if (itemstack.isEnchanted()) {
			return true;
		}
		return false;
	}
}