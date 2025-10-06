package net.mcreator.vlabyss.item;

import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.SlotContext;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.mcreator.vlabyss.procedures.CapuzEsquecidoBaubleIsUnequippedProcedure;
import net.mcreator.vlabyss.procedures.CapuzEsquecidoBaubleIsEquippedProcedure;

public class CapuzEsquecidoItem extends Item implements ICurioItem {
	public CapuzEsquecidoItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}

	@Override
	public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
		CapuzEsquecidoBaubleIsUnequippedProcedure.execute(slotContext.entity().level(), slotContext.entity());
	}

	@Override
	public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
		CapuzEsquecidoBaubleIsEquippedProcedure.execute(slotContext.entity());
	}
}