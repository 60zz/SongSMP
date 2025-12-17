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
public class InfoPocoesProcedure {
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
		if (itemstack.getItem() == VlAbyssModItems.POCAO_REJUVENESCIMENTO.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(1, Component.literal(
						"\u00A78\u2014 A \"Po\u00E7\u00E3o do Rejuvenescimento\" \u00E9 uma rara po\u00E7\u00E3o que pode conceder ao usu\u00E1rio que o consumir, +1 vida extra. Sua utiliza\u00E7\u00E3o \u00E9 \u00FAnica, s\u00F3 poder\u00E1 ser consumida uma \u00FAnica vez!"));
			} else {
				tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));
			}
		}
		if (itemstack.getItem() == VlAbyssModItems.POCAO_RESISTENCIA_ETERNA.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(1, Component.literal(
						"\u00A78\u2014 A \"Po\u00E7\u00E3o da Resist\u00EAncia Eterna\" \u00E9 uma forte po\u00E7\u00E3o para aqueles que adoram uma grande aventura. Os que consomem dessa po\u00E7\u00E3o, recebem uma ben\u00E7\u00E3o de cora\u00E7\u00F5es extras, afim de tornar o usu\u00E1rio mais resist\u00EAncia a ataques f\u00EDsicos e m\u00E1gicos. Sua utiliza\u00E7\u00E3o \u00E9 \u00FAnica, s\u00F3 poder\u00E1 ser consumida uma \u00FAnica vez"));
			} else {
				tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));
			}
		}
		if (itemstack.getItem() == VlAbyssModItems.POCAO_VIDA_EXTRA.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(1, Component.literal(
						"\u00A78\u2014 A \"Po\u00E7\u00E3o de Vida Extra\" como diz em seu nome, \u00E9 uma po\u00E7\u00E3o que ir\u00E1 conceder ao usu\u00E1rio que a consumir, +2 vidas extras, afim de torn\u00E1-lo mais forte e mais resistente! Sua utiliza\u00E7\u00E3o \u00E9 \u00FAnica, s\u00F3 poder\u00E1 ser consumida uma \u00FAnica vez!"));
			} else {
				tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));
			}
		}
		if (itemstack.getItem() == VlAbyssModItems.POCAO_RENASCIMENTO.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(1, Component.literal(
						"\u00A78\u2014 A \"Po\u00E7\u00E3o do Renascimento\" \u00E9 dentre todas uma das po\u00E7\u00F5es mais valiosas que existem, ela persiste na habilidade de fazer a remo\u00E7\u00E3o da classe que aquela pessoa det\u00E9m, resetando-a completamente para que aquela pessoa possa deter de outra classe, caso seja do agrado! Sua utiliza\u00E7\u00E3o \u00E9 \u00FAnica, s\u00F3 poder\u00E1 ser consumida uma \u00FAnica vez"));
			} else {
				tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));
			}
		}
	}
}