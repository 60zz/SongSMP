package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.vlabyss.entity.CavaleiroAladoEntity;
import net.mcreator.vlabyss.configuration.SsmpConfiguration;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CavaleiroAladoAtacouProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		execute(null, world, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (sourceentity instanceof CavaleiroAladoEntity) {
			entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:danoabismoboss")))),
					(float) (double) SsmpConfiguration.WINGEDKNIGHTTOTALDAMAGE.get());
		} else if (entity instanceof Player) {
			if ((sourceentity instanceof CavaleiroAladoEntity _datEntL5 && _datEntL5.getEntityData().get(CavaleiroAladoEntity.DATA_invocadordefinido)) == false) {
				if (sourceentity instanceof CavaleiroAladoEntity _datEntSetS)
					_datEntSetS.getEntityData().set(CavaleiroAladoEntity.DATA_invocador, (entity.getDisplayName().getString()));
				if (sourceentity instanceof CavaleiroAladoEntity _datEntSetL)
					_datEntSetL.getEntityData().set(CavaleiroAladoEntity.DATA_invocadordefinido, true);
			}
		}
	}
}