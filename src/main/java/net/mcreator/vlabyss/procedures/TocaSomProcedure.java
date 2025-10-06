package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class TocaSomProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null || !(world instanceof Level _level))
			return;

		CompoundTag entityData = entity.getPersistentData();
		boolean isInsane = entityData.getBoolean("insanidade");
		double health = entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1;

		// Variáveis para controlar o timing dos sons (em ticks)
		int relogioTimer = entityData.getInt("relogioTimer");
		int coracaoTimer = entityData.getInt("coracaoTimer");
		int medoTimer = entityData.getInt("medoTimer");

		if (isInsane && health > 0) {
			if (health <= 9 && health > 5) {
				// Estado de insanidade moderada (6-9 HP)
				playLoopedSound(_level, x, y, z, "vl_abyss:insanidade_relogio", entityData, "relogioTimer", relogioTimer, 150); // Loop a cada 5 segundos
				playLoopedSound(_level, x, y, z, "vl_abyss:coracao_lento", entityData, "coracaoTimer", coracaoTimer, 160); // Loop a cada 4 segundos
				playLoopedSound(_level, x, y, z, "vl_abyss:insanidade_som_medo", entityData, "medoTimer", medoTimer, 600); // Loop a cada 6 segundos
			} else if (health <= 5) {
				// Estado de insanidade severa (1-5 HP)
				playLoopedSound(_level, x, y, z, "vl_abyss:insanidade_som_medo", entityData, "medoTimer", medoTimer, 600); // Loop mais rápido
				playLoopedSound(_level, x, y, z, "vl_abyss:insanidade_relogio", entityData, "relogioTimer", relogioTimer, 150); // Loop mais rápido
				playLoopedSound(_level, x, y, z, "vl_abyss:coracao_rapido", entityData, "coracaoTimer", coracaoTimer, 160); // Loop bem mais rápido
			}
		} else {
			// Reset timers quando não está insano
			entityData.putInt("relogioTimer", 0);
			entityData.putInt("coracaoTimer", 0);
			entityData.putInt("medoTimer", 0);
		}
	}

	private static void playLoopedSound(Level level, double x, double y, double z, String soundName, 
	                                   CompoundTag entityData, String timerKey, int currentTimer, int interval) {
		if (currentTimer <= 0) {
			// Tocar o som
			if (!level.isClientSide()) {
				level.playSound(null, BlockPos.containing(x, y, z), 
				              ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(soundName)), 
				              SoundSource.MASTER, 0.5f, 1.0f);
			} else {
				level.playLocalSound(x, y, z, 
				                   ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(soundName)), 
				                   SoundSource.MASTER, 0.5f, 1.0f, false);
			}
			// Reset o timer
			entityData.putInt(timerKey, interval);
		} else {
			// Diminuir o timer
			entityData.putInt(timerKey, currentTimer - 1);
		}
	}
}