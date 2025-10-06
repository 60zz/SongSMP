package net.mcreator.vlabyss.item;

import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.SlotContext;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.mcreator.vlabyss.procedures.AmuletoVesperianWhileBaubleIsEquippedTickProcedure;

public class AmuletoVesperianItem extends Item implements ICurioItem {
	public AmuletoVesperianItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}

	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		AmuletoVesperianWhileBaubleIsEquippedTickProcedure.execute(slotContext.entity());
	}
}