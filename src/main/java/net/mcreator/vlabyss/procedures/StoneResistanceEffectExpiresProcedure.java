package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.init.VlAbyssModItems;

public class StoneResistanceEffectExpiresProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.STONE_RESISTANCE.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.STONE_RESISTANCE.get()).getDuration() : 0) <= 1) {
			if (entity instanceof Player _player)
				_player.getCooldowns().addCooldown(VlAbyssModItems.STONE_HEART.get(), 1800);
		}
	}
}