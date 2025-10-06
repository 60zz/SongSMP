package net.mcreator.vlabyss.item;

import top.theillusivec4.curios.api.type.capability.ICurioItem;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class LuvasLuaSangrentaItem extends Item implements ICurioItem {
	public LuvasLuaSangrentaItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}
}