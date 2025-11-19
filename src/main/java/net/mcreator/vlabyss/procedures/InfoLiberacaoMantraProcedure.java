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

@Mod.EventBusSubscriber(value = {Dist.CLIENT})
public class InfoLiberacaoMantraProcedure {
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
		if (itemstack.getItem() == VlAbyssModItems.LIBERACAO_MANTRA.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(1, Component.literal(
						"\u00A78\u2014 A \"Libera\u00E7\u00E3o de Mantra\" \u00E9 um recurso que poucos tem o acesso... Ele ir\u00E1 permitir ao usu\u00E1rio de obter uma MANTRA. Existem diversas em nosso mundo, at\u00E9 mesmo varia\u00E7\u00F5es, cada uma com uma fun\u00E7\u00E3o \u00FAnica e especial, cabe a voc\u00EA escolher qual ir\u00E1 te agradar mais, uma vez que escolher, n\u00E3o poder\u00E1 trocar"));
			} else {
				tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));
			}
		}
	}
}