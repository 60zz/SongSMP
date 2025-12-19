package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.registries.Registries;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.VlAbyssMod;

public class AstralAxeLivingEntityIsHitWithToolProcedure {
	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if ((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Guerreiro == true
				|| (sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Tanque == true
				|| (sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Berserker == true) {
			if (Math.random() >= 0.75) {
				VlAbyssMod.queueServerWork(Mth.nextInt(RandomSource.create(), 10, 30), () -> {
					entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK), sourceentity),
							Math.round((sourceentity instanceof LivingEntity _attributeContext ? _attributeContext.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE) : 0.0D) * Math.random()));
					if (world instanceof net.minecraft.server.level.ServerLevel) {
						net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
						int particleCount = (int) 40;
						double centerX = (entity.getX());
						double centerY = (entity.getY() + 1);
						double centerZ = (entity.getZ());
						double particleSpeed = 0.6;
						net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.PORTAL;
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
				});
			}
		}
	}
}