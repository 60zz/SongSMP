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
public class InfoEspadasElementaresProcedure {
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
		if (itemstack.getItem() == VlAbyssModItems.LIGHT_SWORD.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(1, Component.literal(
						"\u00A78\u2014 A arma elementar da \"Mantra da Luz\" n\u00E3o possui uma classe definida, ela possui a finalidade de aprimorar em 20% as habilidades do usu\u00E1rio com rela\u00E7\u00E3o a mantra da Luz, isso, apenas se a arma estiver em m\u00E3os"));
			} else {
				tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));
			}
		}
		if (itemstack.getItem() == VlAbyssModItems.SNOWSTORM_SWORD.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(1, Component.literal(
						"\u00A78\u2014 A arma elementar da \"Mantra da Nevasca\" n\u00E3o possui uma classe definida, ela possui a finalidade de aprimorar em 20% as habilidades do usu\u00E1rio com rela\u00E7\u00E3o a mantra da Nevasca, isso, apenas se a arma estiver em m\u00E3os"));
			} else {
				tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));
			}
		}
		if (itemstack.getItem() == VlAbyssModItems.WIND_SWORD.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(1, Component.literal(
						"\u00A78\u2014 A arma elementar da \"Mantra do Respiro\" n\u00E3o possui uma classe definida, ela possui a finalidade de aprimorar em 20% as habilidades do usu\u00E1rio com rela\u00E7\u00E3o a mantra do Respiro, isso, apenas se a arma estiver em m\u00E3os"));
			} else {
				tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));
			}
		}
		if (itemstack.getItem() == VlAbyssModItems.LIGHTNING_SWORD.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(1, Component.literal(
						"\u00A78\u2014 A arma elementar da \"Mantra da Tempestade\" n\u00E3o possui uma classe definida, ela possui a finalidade de aprimorar em 20% as habilidades do usu\u00E1rio com rela\u00E7\u00E3o a mantra da Tempestade, isso, apenas se a arma estiver em m\u00E3os"));
			} else {
				tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));
			}
		}
		if (itemstack.getItem() == VlAbyssModItems.FLAME_SWORD.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(1, Component.literal(
						"\u00A78\u2014 A arma elementar da \"Mantra da Chama\" n\u00E3o possui uma classe definida, ela possui a finalidade de aprimorar em 20% as habilidades do usu\u00E1rio com rela\u00E7\u00E3o a mantra da Chama, isso, apenas se a arma estiver em m\u00E3os"));
			} else {
				tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));
			}
		}
		if (itemstack.getItem() == VlAbyssModItems.DARKNESS_SWORD.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(1, Component.literal(
						"\u00A78\u2014 A arma elementar da \"Mantra da Escurid\u00E3o\" n\u00E3o possui uma classe definida, ela possui a finalidade de aprimorar em 20% as habilidades do usu\u00E1rio com rela\u00E7\u00E3o a mantra da Escurid\u00E3o, isso, apenas se a arma estiver em m\u00E3os"));
			} else {
				tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));
			}
		}
	}
}