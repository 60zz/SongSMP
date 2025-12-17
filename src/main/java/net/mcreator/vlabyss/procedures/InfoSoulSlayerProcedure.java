package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.Screen;

import net.mcreator.vlabyss.init.VlAbyssModItems;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class InfoSoulSlayerProcedure {
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
		if (itemstack.getItem() == VlAbyssModItems.SOUL_SLAYER.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add((int) 1.1, Component.literal(("\u00A78 \uD83D\uDC7B Souls: " + itemstack.getOrCreateTag().getDouble("almas"))));
				tooltip.add((int) 1.2, Component.literal(""));
				tooltip.add((int) 1.3, Component.literal(
						"\u00A78\u2014 A arma \"Abatedora de Almas\" pertence a classe dos \"Assassinos\", ela tem a finalidade de acumular almas mortas dentro da arma, e utiliz\u00E1-las para aumentar como multiplicador na hora de desferir algum tipo de dano no oponente"));
			} else {
				tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));
			}
		}
	}
}