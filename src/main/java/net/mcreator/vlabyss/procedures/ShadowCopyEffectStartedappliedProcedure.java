package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

public class ShadowCopyEffectStartedappliedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = x;
			entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.posX = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = y;
			entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.posY = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = z;
			entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.posZ = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:copy_ativa")), SoundSource.MASTER, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:copy_ativa")), SoundSource.MASTER, 1, 1, false);
			}
		}
		if (world instanceof net.minecraft.server.level.ServerLevel) {
			net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
			int particleCount = (int) 40;
			double centerX = x;
			double centerY = y;
			double centerZ = z;
			double particleSpeed = 0.7;
			net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.SQUID_INK;
			for (int i = 0; i < particleCount; i++) {
				double u = Math.random();
				double v = Math.random();
				double theta = 2 * Math.PI * u;
				double phi = Math.acos(2 * v - 1);
				double directionX = Math.sin(phi) * Math.cos(theta);
				double directionY = Math.cos(phi);
				double directionZ = Math.sin(phi) * Math.sin(theta);
				double velocityX = directionX * particleSpeed;
				double velocityY = directionY * particleSpeed;
				double velocityZ = directionZ * particleSpeed;
				_level.sendParticles(particleType, centerX, centerY, centerZ, 0, velocityX, velocityY, velocityZ, particleSpeed);
			}
		}
	}
}