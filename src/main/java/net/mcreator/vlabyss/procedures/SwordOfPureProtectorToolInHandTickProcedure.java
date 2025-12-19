package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.init.VlAbyssModItems;

public class SwordOfPureProtectorToolInHandTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof Player _plrCldCheck0 && _plrCldCheck0.getCooldowns().isOnCooldown(VlAbyssModItems.SWORD_OF_PURE_PROTECTOR.get()))) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) <= 6) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.LAST_RESISTANCE.get(), 700, 0));
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(VlAbyssModItems.SWORD_OF_PURE_PROTECTOR.get(), 12000);
			}
		}
	}
}