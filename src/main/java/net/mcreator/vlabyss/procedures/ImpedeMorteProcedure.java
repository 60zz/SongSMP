package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ImpedeMorteProcedure {
	private static LivingDamageEvent _event;

	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent event) {
		_event = event;
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(VlAbyssModMobEffects.LAST_RESISTANCE.get())) {
			if (world instanceof net.minecraft.server.level.ServerLevel) {
				net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
				int particleCount = (int) 40;
				double centerX = x;
				double centerY = y;
				double centerZ = z;
				double particleSpeed = 0.6;
				net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.PORTAL;
				double initialRadius = 4.0;
				for (int i = 0; i < particleCount; i++) {
					double u = Math.random();
					double v = Math.random();
					double theta = 2 * Math.PI * u;
					double phi = Math.acos(2 * v - 1);
					double startX = centerX + initialRadius * Math.sin(phi) * Math.cos(theta);
					double startY = centerY + initialRadius * Math.cos(phi);
					double startZ = centerZ + initialRadius * Math.sin(phi) * Math.sin(theta);
					double deltaX = centerX - startX;
					double deltaY = centerY - startY;
					double deltaZ = centerZ - startZ;
					double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
					double velocityX = (deltaX / distance) * particleSpeed;
					double velocityY = (deltaY / distance) * particleSpeed;
					double velocityZ = (deltaZ / distance) * particleSpeed;
					_level.sendParticles(particleType, startX, startY, startZ, 0, velocityX, velocityY, velocityZ, particleSpeed);
				}
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("entity.dragon_fireball.explode")), SoundSource.MASTER, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("entity.dragon_fireball.explode")), SoundSource.MASTER, 1, 1, false);
				}
			}
			if (event != null && event.isCancelable()) {
				event.setCanceled(true);
			}
		}
	}
}