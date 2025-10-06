package net.mcreator.vlabyss.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.LivingEntity;

import net.mcreator.vlabyss.procedures.PocaoRejuvenescimentoPlayerFinishesUsingItemProcedure;

public class PocaoRejuvenescimentoItem extends Item {
	public PocaoRejuvenescimentoItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).food((new FoodProperties.Builder()).nutrition(0).saturationMod(0f).alwaysEat().build()));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.DRINK;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		PocaoRejuvenescimentoPlayerFinishesUsingItemProcedure.execute(entity);
		return retval;
	}
}