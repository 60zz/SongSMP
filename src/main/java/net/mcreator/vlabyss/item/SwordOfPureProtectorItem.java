package net.mcreator.vlabyss.item;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.Minecraft;

import net.mcreator.vlabyss.procedures.SwordOfPureProtectorToolInInventoryTickProcedure;
import net.mcreator.vlabyss.procedures.SwordOfPureProtectorToolInHandTickProcedure;
import net.mcreator.vlabyss.procedures.SwordOfPureProtectorHasItemGlowingEffectProcedure;

public class SwordOfPureProtectorItem extends SwordItem {
	public SwordOfPureProtectorItem() {
		super(new Tier() {
			public int getUses() {
				return 2031;
			}

			public float getSpeed() {
				return 4f;
			}

			public float getAttackDamageBonus() {
				return 14f;
			}

			public int getLevel() {
				return 4;
			}

			public int getEnchantmentValue() {
				return 15;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of();
			}
		}, 3, -2.6f, new Item.Properties());
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		if (selected)
			SwordOfPureProtectorToolInHandTickProcedure.execute(entity);
		SwordOfPureProtectorToolInInventoryTickProcedure.execute(entity, itemstack);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean isFoil(ItemStack itemstack) {
		Entity entity = Minecraft.getInstance().player;
		return SwordOfPureProtectorHasItemGlowingEffectProcedure.execute(entity, itemstack);
	}
}