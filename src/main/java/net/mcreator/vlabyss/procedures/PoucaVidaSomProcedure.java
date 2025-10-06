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
import net.minecraft.client.Minecraft;

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
			if (!(new Object() {
				public boolean checkGamemode(Entity _ent) {
					if (_ent instanceof ServerPlayer _serverPlayer) {
						return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
					} else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
						return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
								&& Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
					}
					return false;
				}
			}.checkGamemode(entity))) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) <= 9) {
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.INSANO.get(), 12000, 0));
					entity.getPersistentData().putBoolean("insanidade", true);
				} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) >= 10) {
					if (entity instanceof LivingEntity _entity)
						_entity.removeEffect(VlAbyssModMobEffects.INSANO.get());
				}
			} else {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(VlAbyssModMobEffects.INSANO.get());
			}
		}
	}
}