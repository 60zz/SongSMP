/*
*	MCreator note: This file will be REGENERATED on each build.
*/
package net.mcreator.vlabyss.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.common.BasicItemListing;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VlAbyssModTrades {
	@SubscribeEvent
	public static void registerTrades(VillagerTradesEvent event) {
		if (event.getType() == VlAbyssModVillagerProfessions.AVENTUREIRO_PROFESSION.get()) {
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.PAPER), new ItemStack(Items.DIAMOND, 24), new ItemStack(VlAbyssModItems.MAPA_ABYSS.get()), 10, 5, 0.05f));
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(VlAbyssModItems.ABYSSION_INGOT.get(), 8), new ItemStack(Items.NETHERITE_INGOT, 24), new ItemStack(VlAbyssModItems.SELETOR_CLASSES.get()), 5, 30, 0.05f));
		}
		if (event.getType() == VlAbyssModVillagerProfessions.MESTRE_MANTRA_PROFESSION.get()) {
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.NETHER_STAR, 8), new ItemStack(VlAbyssModItems.ABYSSION_INGOT.get(), 12), new ItemStack(VlAbyssModItems.LIBERACAO_MANTRA.get()), 3, 5, 0.05f));
		}
	}
}