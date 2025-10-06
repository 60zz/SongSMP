package net.mcreator.vlabyss.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class AbyssionIngotItem extends Item {
	public AbyssionIngotItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.RARE));
	}
}