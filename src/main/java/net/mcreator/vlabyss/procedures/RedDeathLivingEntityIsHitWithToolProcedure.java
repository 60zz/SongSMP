package net.mcreator.vlabyss.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

public class RedDeathLivingEntityIsHitWithToolProcedure {
	public static void execute(Entity sourceentity, ItemStack itemstack) {
		if (sourceentity == null)
			return;
		if ((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Assassino == true) {
			if (!(itemstack.getOrCreateTag().getDouble("sangue") >= 20)) {
				if (Math.random() > 0.9) {
					if (itemstack.getOrCreateTag().getDouble("sangue") >= 1) {
						itemstack.getOrCreateTag().putDouble("sangue", (itemstack.getOrCreateTag().getDouble("sangue") + 1));
					} else {
						itemstack.getOrCreateTag().putDouble("sangue", 1);
					}
				}
			}
		}
	}
}