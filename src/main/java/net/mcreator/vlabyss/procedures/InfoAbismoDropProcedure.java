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
public class InfoAbismoDropProcedure {
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
		if (itemstack.getItem() == VlAbyssModItems.ESSENCIA_ABISMO.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(1, Component.literal("\u00A78\u2014 Um fragmento do abismo... A sensa\u00E7\u00E3o de morte que esse fragmento traz consigo \u00E9 de arrepiar..."));
			} else {
				tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));
			}
		}
		if (itemstack.getItem() == VlAbyssModItems.ARTIFACTS_BUNDLE.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(1, Component.literal("\u00A78\u2014 Esse bundle traz consigo um artefato aleat\u00F3rio, no qual trar\u00E1 equipamentos portados anteriormente por exploradores da antiga era... "));
			} else {
				tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));
			}
		}
	}
}