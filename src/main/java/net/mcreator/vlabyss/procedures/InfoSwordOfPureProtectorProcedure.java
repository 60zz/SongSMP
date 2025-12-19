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
public class InfoSwordOfPureProtectorProcedure {
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
		if (itemstack.getItem() == VlAbyssModItems.SWORD_OF_PURE_PROTECTOR.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(1, Component.literal(
						"\u00A78\u2014 A arma \"Espada do Puro Protetor\" n\u00E3o pertence a nenhuma classe, ela \u00E9 classificada como arma \"independente\", a sua habilidade \u00E9 passiva, quando o portador estiver pr\u00F3ximo da morte, a habilidade \"\u00DAltima Resist\u00EAncia\" \u00E9 ativa, deixando o portador sem tomar nenhum tipo de dano e aumentando consideravelmente seu dano"));
			} else {
				tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));
			}
		}
	}
}