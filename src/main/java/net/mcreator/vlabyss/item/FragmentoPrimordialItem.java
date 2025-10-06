package net.mcreator.vlabyss.item;

import top.theillusivec4.curios.api.type.capability.ICurioItem;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class FragmentoPrimordialItem extends Item implements ICurioItem {
	public FragmentoPrimordialItem() {
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC));
	}
}