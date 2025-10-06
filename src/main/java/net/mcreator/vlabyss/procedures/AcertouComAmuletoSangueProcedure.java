package net.mcreator.vlabyss.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.CriticalHitEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.init.VlAbyssModItems;
import net.mcreator.vlabyss.configuration.SsmpConfiguration;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AcertouComAmuletoSangueProcedure {
	@SubscribeEvent
	public static void onPlayerCriticalHit(CriticalHitEvent event) {
		execute(event, event.getTarget(), event.getEntity());
	}

	public static void execute(Entity entity, Entity sourceentity) {
		execute(null, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (sourceentity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(VlAbyssModItems.AMULETO_SANGUE.get(), lv).isPresent() : false) {
			if (!(sourceentity instanceof Player _plrCldCheck1 && _plrCldCheck1.getCooldowns().isOnCooldown(VlAbyssModItems.AMULETO_SANGUE.get()))) {
				if (entity instanceof LivingEntity _livEnt2 && _livEnt2.hasEffect(VlAbyssModMobEffects.SANGRANDO.get())) {
					if ((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).Arkanthi == true) {
						if (sourceentity instanceof LivingEntity _entity)
							_entity.setHealth((float) ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) + (double) SsmpConfiguration.AMULETOSANGUECONFIG.get()));
					} else {
						if (sourceentity instanceof LivingEntity _entity)
							_entity.setHealth((float) ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) + (double) SsmpConfiguration.AMULETOSANGUECONFIG2.get()));
					}
					if (entity instanceof LivingEntity _entity)
						_entity.removeEffect(VlAbyssModMobEffects.SANGRANDO.get());
					if (sourceentity instanceof Player _player)
						_player.getCooldowns().addCooldown(VlAbyssModItems.AMULETO_SANGUE.get(), 100);
				}
			}
		}
	}
}