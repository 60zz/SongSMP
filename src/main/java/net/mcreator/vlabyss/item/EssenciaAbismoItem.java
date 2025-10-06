package net.mcreator.vlabyss.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class EssenciaAbismoItem extends Item {
	public EssenciaAbismoItem() {
		super(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.EPIC));
	}
}