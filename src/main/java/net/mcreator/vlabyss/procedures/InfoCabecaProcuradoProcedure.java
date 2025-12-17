package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.Screen;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class InfoCabecaProcuradoProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getItemStack(), event.getToolTip());
	}

	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		execute(null, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		if (itemstack.getItem() == Blocks.PLAYER_HEAD.asItem()) {
			if (itemstack.getOrCreateTag().getBoolean("procuradinho")) {
				if (Screen.hasShiftDown()) {
					tooltip.add(1, Component.literal(("\u00A78\u2014 A cabe\u00E7a desse procurado pertence especificadamente a: " + itemstack.getOrCreateTag().getString("procurado"))));
				} else {
					tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));
				}
			}
		}
	}
}