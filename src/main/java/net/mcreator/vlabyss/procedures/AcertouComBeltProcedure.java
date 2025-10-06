package net.mcreator.vlabyss.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.CriticalHitEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.registries.Registries;

import net.mcreator.vlabyss.init.VlAbyssModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AcertouComBeltProcedure {
	@SubscribeEvent
	public static void onPlayerCriticalHit(CriticalHitEvent event) {
		execute(event, event.getEntity().level(), event.getTarget(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity, double originaldamagemodifier) {
		execute(null, world, entity, sourceentity, originaldamagemodifier);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity, double originaldamagemodifier) {
		if (entity == null || sourceentity == null)
			return;
		if (sourceentity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(VlAbyssModItems.CINTO_DOS_ECOS.get(), lv).isPresent() : false) {
			if (entity.getPersistentData().getBoolean("criticonormal")) {
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), (float) (originaldamagemodifier + originaldamagemodifier * 0.25));
			} else if (entity.getPersistentData().getBoolean("criticosong")) {
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), (float) (originaldamagemodifier + originaldamagemodifier * 0.3));
			}
		}
	}
}