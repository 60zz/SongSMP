package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.entity.WindVortexEntity;
import net.mcreator.vlabyss.entity.RespiroPrimariaEntity;
import net.mcreator.vlabyss.entity.ReconjurationEntity;
import net.mcreator.vlabyss.entity.MantraSoulEntity;
import net.mcreator.vlabyss.entity.MantraSoulCorrompidaEntity;
import net.mcreator.vlabyss.entity.FireEruptionEntity;
import net.mcreator.vlabyss.entity.EchoOfShadowEntity;
import net.mcreator.vlabyss.entity.CloneEntity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class EntityTickDespawnProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof RespiroPrimariaEntity) {
			if (new Object() {
				public int getTicksExisted(Entity entity) {
					if (entity == null)
						return 0;
					return entity.tickCount;
				}
			}.getTicksExisted(entity) >= 40) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
		if (entity instanceof WindVortexEntity) {
			if (new Object() {
				public int getTicksExisted(Entity entity) {
					if (entity == null)
						return 0;
					return entity.tickCount;
				}
			}.getTicksExisted(entity) >= 300) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
		if (entity instanceof ReconjurationEntity) {
			if (new Object() {
				public int getTicksExisted(Entity entity) {
					if (entity == null)
						return 0;
					return entity.tickCount;
				}
			}.getTicksExisted(entity) >= 40) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
		if (entity instanceof ReconjurationEntity) {
			if (new Object() {
				public int getTicksExisted(Entity entity) {
					if (entity == null)
						return 0;
					return entity.tickCount;
				}
			}.getTicksExisted(entity) >= 40) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
		if (entity instanceof FireEruptionEntity) {
			if (new Object() {
				public int getTicksExisted(Entity entity) {
					if (entity == null)
						return 0;
					return entity.tickCount;
				}
			}.getTicksExisted(entity) >= 10) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
		if (entity instanceof CloneEntity) {
			if (new Object() {
				public int getTicksExisted(Entity entity) {
					if (entity == null)
						return 0;
					return entity.tickCount;
				}
			}.getTicksExisted(entity) >= 150) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
		if (entity instanceof EchoOfShadowEntity) {
			if (new Object() {
				public int getTicksExisted(Entity entity) {
					if (entity == null)
						return 0;
					return entity.tickCount;
				}
			}.getTicksExisted(entity) >= 20) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
		if (entity instanceof MantraSoulCorrompidaEntity) {
			if (new Object() {
				public int getTicksExisted(Entity entity) {
					if (entity == null)
						return 0;
					return entity.tickCount;
				}
			}.getTicksExisted(entity) >= 600) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
		if (entity instanceof MantraSoulEntity) {
			if (new Object() {
				public int getTicksExisted(Entity entity) {
					if (entity == null)
						return 0;
					return entity.tickCount;
				}
			}.getTicksExisted(entity) >= 600) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
	}
}