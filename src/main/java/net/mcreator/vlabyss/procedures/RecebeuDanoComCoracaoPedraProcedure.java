package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.init.VlAbyssModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class RecebeuDanoComCoracaoPedraProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity());
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		double level = 0;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Tanque == true) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == VlAbyssModItems.STONE_HEART.get()) {
				if (!(entity instanceof Player _plrCldCheck2 && _plrCldCheck2.getCooldowns().isOnCooldown(VlAbyssModItems.STONE_HEART.get()))) {
					if (!(entity instanceof LivingEntity _livEnt3 && _livEnt3.hasEffect(VlAbyssModMobEffects.STONE_RESISTANCE.get()))) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.STONE_RESISTANCE.get(), 100, 0));
					} else {
						if (!((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.STONE_RESISTANCE.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.STONE_RESISTANCE.get()).getAmplifier() : 0) >= 14)) {
							level = (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.STONE_RESISTANCE.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.STONE_RESISTANCE.get()).getAmplifier() : 0) + 1;
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.STONE_RESISTANCE.get(), 100, (int) level));
						}
					}
				}
			}
		}
	}
}