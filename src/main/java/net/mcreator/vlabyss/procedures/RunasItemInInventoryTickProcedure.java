package net.mcreator.vlabyss.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

public class RunasItemInInventoryTickProcedure {
	public static void execute(ItemStack itemstack) {
		if (itemstack.getOrCreateTag().getBoolean("runavermelha")) {
			itemstack.setHoverName(Component.literal("\u00A7r\u00A7eRuna da Chama"));
		} else if (itemstack.getOrCreateTag().getBoolean("runaroxa")) {
			itemstack.setHoverName(Component.literal("\u00A7r\u00A7eRuna da Escurid\u00E3o"));
		} else if (itemstack.getOrCreateTag().getBoolean("runaazul")) {
			itemstack.setHoverName(Component.literal("\u00A7r\u00A7eRuna da Tempestade"));
		} else if (itemstack.getOrCreateTag().getBoolean("runaverde")) {
			itemstack.setHoverName(Component.literal("\u00A7r\u00A7eRuna da Respira\u00E7\u00E3o"));
		} else if (itemstack.getOrCreateTag().getBoolean("runaamarela")) {
			itemstack.setHoverName(Component.literal("\u00A7r\u00A7eRuna da Luz"));
		} else if (itemstack.getOrCreateTag().getBoolean("runapreta")) {
			itemstack.setHoverName(Component.literal("\u00A7r\u00A7eRuna do Abismo"));
		} else if (itemstack.getOrCreateTag().getBoolean("runaciana")) {
			itemstack.setHoverName(Component.literal("\u00A7r\u00A7eRuna da Nevasca"));
		}
	}
}