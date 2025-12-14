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
public class InfoAxeOfAngelProcedure {
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
		if (itemstack.getItem() == VlAbyssModItems.AXE_OF_ANGEL.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(1, Component.literal(
						"\u00A78\u2014 A arma \"Machado do Anjo Ca\u00EDdo\" \u00E9 uma arma muito rara, encontrada apenas quando o monstruoso Anjo Ca\u00EDdo \u00E9 derrotado, com essa arma, voc\u00EA ter\u00E1 a for\u00E7a monstruosa que ele tinha, desferindo golpes que ir\u00E3o causar danos a todos numa \u00E1rea consider\u00E1vel... A arma s\u00F3 pode ser portada por Guerreiros e Berserkers"));
			} else {
				tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));
			}
		}
	}
}