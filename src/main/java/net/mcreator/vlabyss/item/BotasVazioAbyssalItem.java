package net.mcreator.vlabyss.item;

import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.SlotContext;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.mcreator.vlabyss.procedures.BotasVazioAbyssalBaubleIsUnequippedProcedure;
import net.mcreator.vlabyss.procedures.BotasVazioAbyssalBaubleIsEquippedProcedure;

public class BotasVazioAbyssalItem extends Item implements ICurioItem {
	public BotasVazioAbyssalItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}

	@Override
	public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
		BotasVazioAbyssalBaubleIsEquippedProcedure.execute(slotContext.entity());
	}

	@Override
	public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
		BotasVazioAbyssalBaubleIsUnequippedProcedure.execute(slotContext.entity());
	}
}