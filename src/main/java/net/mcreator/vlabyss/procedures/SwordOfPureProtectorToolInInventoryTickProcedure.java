package net.mcreator.vlabyss.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;

public class SwordOfPureProtectorToolInInventoryTickProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == itemstack.getItem())) {
			if (entity instanceof LivingEntity _livEnt3 && _livEnt3.hasEffect(VlAbyssModMobEffects.LAST_RESISTANCE.get())) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(VlAbyssModMobEffects.LAST_RESISTANCE.get());
			}
		}
	}
}