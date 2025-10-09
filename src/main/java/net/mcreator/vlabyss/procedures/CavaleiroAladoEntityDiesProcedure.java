package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.vlabyss.entity.CavaleiroAladoEntity;

public class CavaleiroAladoEntityDiesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof CavaleiroAladoEntity _datEntL0 && _datEntL0.getEntityData().get(CavaleiroAladoEntity.DATA_invocadordefinido)) == true) {
			{
				Entity _ent = entity;
				if (!_ent.level().isClientSide() && _ent.getServer() != null) {
					_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
							_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent),
							("addvidas " + (entity instanceof CavaleiroAladoEntity _datEntS ? _datEntS.getEntityData().get(CavaleiroAladoEntity.DATA_invocador) : "") + " 2"));
				}
			}
			if (world instanceof net.minecraft.server.level.ServerLevel) {
				net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
				int particleCount = (int) 20;
				double centerX = x;
				double centerY = y;
				double centerZ = z;
				double particleSpeed = 0.4;
				net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.END_ROD;
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
}