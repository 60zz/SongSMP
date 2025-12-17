package net.mcreator.vlabyss.procedures;

import org.checkerframework.checker.units.qual.m;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

public class BerserkerClasseGuiProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Assassino == false
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Berserker == false
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Guerreiro == false
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Tanque == false) {
			{
				boolean _setval = true;
				entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Berserker = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.attack_damage"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "berserker";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, 4, AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.armor"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "berserker1";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, (-2), AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.movement_speed"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "berserker2";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, (-0.02), AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
			if (world instanceof net.minecraft.server.level.ServerLevel) {
				net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
				int particleCount = (int) 40;
				double centerX = x;
				double centerY = y;
				double centerZ = z;
				double particleSpeed = 0.6;
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
			if (entity instanceof Player _player)
				_player.closeContainer();
		}
	}
}