package net.mcreator.vlabyss.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.procedures.SpearOfImperatorToolInInventoryTickProcedure;
import net.mcreator.vlabyss.procedures.SpearOfImperatorToolInHandTickProcedure;
import net.mcreator.vlabyss.procedures.SpearOfImperatorLivingEntityIsHitWithToolProcedure;

public class SpearOfImperatorItem extends SwordItem {
	public SpearOfImperatorItem() {
		super(new Tier() {
			public int getUses() {
				return 935;
			}

			public float getSpeed() {
				return 4f;
			}

			public float getAttackDamageBonus() {
				return 11f;
			}

			public int getLevel() {
				return 3;
			}

			public int getEnchantmentValue() {
				return 10;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of();
			}
		}, 3, -2.9f, new Item.Properties());
	}

	@Override
	public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
		SpearOfImperatorLivingEntityIsHitWithToolProcedure.execute(sourceentity);
		return retval;
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		if (selected)
			SpearOfImperatorToolInHandTickProcedure.execute(entity);
		SpearOfImperatorToolInInventoryTickProcedure.execute(entity);
	}
}