package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingHealEvent;

import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.Minecraft;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModMobEffects;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PoucaVidaSomProcedure {
	@SubscribeEvent
	public static void onEntityHealed(LivingHealEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player) {
			if (!(getEntityGameType(entity) == GameType.CREATIVE) || (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).habilidade3 == false) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) <= 9) {
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.INSANO.get(), 12000, 0));
					entity.getPersistentData().putBoolean("insanidade", true);
				} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) >= 10) {
					if (entity instanceof LivingEntity _entity)
						_entity.removeEffect(VlAbyssModMobEffects.INSANO.get());
					entity.getPersistentData().remove("insanidade");
				}
			} else {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(VlAbyssModMobEffects.INSANO.get());
				entity.getPersistentData().remove("insanidade");
			}
		}
	}

	private static GameType getEntityGameType(Entity entity) {
		if (entity instanceof ServerPlayer serverPlayer) {
			return serverPlayer.gameMode.getGameModeForPlayer();
		} else if (entity instanceof Player player && player.level().isClientSide()) {
			PlayerInfo playerInfo = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());
			if (playerInfo != null)
				return playerInfo.getGameMode();
		}
		return null;
	}
}