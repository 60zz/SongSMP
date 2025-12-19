package net.mcreator.vlabyss.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class RedDeathRightclickedProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double precisa = 0;
		double sangueatual = 0;
		double sanguenecessario = 0;
		if (itemstack.getOrCreateTag().getDouble("sangue") >= 1) {
			if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) == (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1))) {
				precisa = (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) - (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1);
				sangueatual = itemstack.getOrCreateTag().getDouble("sangue");
				if (!(sangueatual > precisa)) {
					if (entity instanceof LivingEntity _entity)
						_entity.setHealth((float) ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) + sangueatual));
					itemstack.getOrCreateTag().putDouble("sangue", 0);
				} else {
					sanguenecessario = sangueatual - precisa;
					itemstack.getOrCreateTag().putDouble("sangue", (sangueatual - precisa));
					if (entity instanceof LivingEntity _entity)
						_entity.setHealth(entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1);
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 1200);
			}
		}
	}
}