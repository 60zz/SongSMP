package net.mcreator.vlabyss.procedures;

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

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CriticoNasCostasShadeBackstapProcedure {
	private static CriticalHitEvent _event;

	@SubscribeEvent
	public static void onPlayerCriticalHit(CriticalHitEvent event) {
		_event = event;
		execute(event, event.getEntity().level(), event.getTarget(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		execute(null, world, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		double anglediff = 0;
		if (sourceentity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(VlAbyssModMobEffects.SHADE_BACKSTAP.get())) {
			anglediff = Math.abs(entity.getYRot()) - Math.atan2(entity.getX() - sourceentity.getX(), entity.getZ() - sourceentity.getZ());
			anglediff = 360 - anglediff;
			if (anglediff >= 359 && !(anglediff >= 361)) {
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK), sourceentity),
						(float) ((sourceentity instanceof LivingEntity _attributeContext ? _attributeContext.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE) : 0.0D) * 2));
			}
		}
	}
}