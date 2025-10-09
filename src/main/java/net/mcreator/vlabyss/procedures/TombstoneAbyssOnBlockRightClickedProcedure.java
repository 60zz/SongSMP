package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModEntities;
import net.mcreator.vlabyss.VlAbyssMod;

public class TombstoneAbyssOnBlockRightClickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!(new Object() {
			public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getBoolean(tag);
				return false;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "recarregando"))) {
			if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).fase == 1) {
				{
					int _value = 2;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				if (world instanceof net.minecraft.server.level.ServerLevel) {
					net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
					int particleCount = (int) 40;
					double centerX = x;
					double centerY = y;
					double centerZ = z;
					double particleSpeed = 0.8;
					net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.GLOW_SQUID_INK;
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
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putBoolean("recarregando", true);
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = VlAbyssModEntities.CAVALEIRO_ALADO.get().spawn(_level, BlockPos.containing(x, y + 2, z), MobSpawnType.MOB_SUMMONED);
					if (entityToSpawn != null) {
					}
				}
				VlAbyssMod.queueServerWork(12000, () -> {
					if (!world.isClientSide()) {
						BlockPos _blockPos = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_blockPos);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().remove("recarregando");
							if (world instanceof Level _level) {
								BlockState _blockState = _level.getBlockState(_blockPos);
								_level.sendBlockUpdated(_blockPos, _blockState, _blockState, 3);
							}
						}
					}
					{
						int _value = 1;
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
							world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
					}
				});
			} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).fase == 2) {
				{
					int _value = 2;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				if (world instanceof net.minecraft.server.level.ServerLevel) {
					net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
					int particleCount = (int) 40;
					double centerX = x;
					double centerY = y;
					double centerZ = z;
					double particleSpeed = 0.8;
					net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.GLOW_SQUID_INK;
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
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putBoolean("recarregando", true);
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				VlAbyssMod.queueServerWork(12000, () -> {
					if (!world.isClientSide()) {
						BlockPos _blockPos = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_blockPos);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().remove("recarregando");
							if (world instanceof Level _level) {
								BlockState _blockState = _level.getBlockState(_blockPos);
								_level.sendBlockUpdated(_blockPos, _blockState, _blockState, 3);
							}
						}
					}
					{
						int _value = 1;
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
							world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
					}
				});
			} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).fase == 3) {
				{
					int _value = 2;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				if (world instanceof net.minecraft.server.level.ServerLevel) {
					net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
					int particleCount = (int) 40;
					double centerX = x;
					double centerY = y;
					double centerZ = z;
					double particleSpeed = 0.8;
					net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.GLOW_SQUID_INK;
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
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putBoolean("recarregando", true);
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				VlAbyssMod.queueServerWork(12000, () -> {
					if (!world.isClientSide()) {
						BlockPos _blockPos = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_blockPos);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().remove("recarregando");
							if (world instanceof Level _level) {
								BlockState _blockState = _level.getBlockState(_blockPos);
								_level.sendBlockUpdated(_blockPos, _blockState, _blockState, 3);
							}
						}
					}
					{
						int _value = 1;
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
							world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
					}
				});
			} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).fase >= 4) {
				{
					int _value = 2;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				if (world instanceof net.minecraft.server.level.ServerLevel) {
					net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
					int particleCount = (int) 40;
					double centerX = x;
					double centerY = y;
					double centerZ = z;
					double particleSpeed = 0.8;
					net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.GLOW_SQUID_INK;
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
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putBoolean("recarregando", true);
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				VlAbyssMod.queueServerWork(12000, () -> {
					if (!world.isClientSide()) {
						BlockPos _blockPos = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_blockPos);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().remove("recarregando");
							if (world instanceof Level _level) {
								BlockState _blockState = _level.getBlockState(_blockPos);
								_level.sendBlockUpdated(_blockPos, _blockState, _blockState, 3);
							}
						}
					}
					{
						int _value = 1;
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
							world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
					}
				});
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:invocacao_iniciada")), SoundSource.MASTER, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:invocacao_iniciada")), SoundSource.MASTER, 1, 1, false);
				}
			}
		} else {
			VlAbyssMod.queueServerWork(12000, () -> {
				if (new Object() {
					public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
						BlockEntity blockEntity = world.getBlockEntity(pos);
						if (blockEntity != null)
							return blockEntity.getPersistentData().getBoolean(tag);
						return false;
					}
				}.getValue(world, BlockPos.containing(x, y, z), "recarregando")) {
					if (!world.isClientSide()) {
						BlockPos _blockPos = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_blockPos);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().remove("recarregando");
							if (world instanceof Level _level) {
								BlockState _blockState = _level.getBlockState(_blockPos);
								_level.sendBlockUpdated(_blockPos, _blockState, _blockState, 3);
							}
						}
					}
					{
						int _value = 1;
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
							world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
					}
				}
			});
		}
	}
}