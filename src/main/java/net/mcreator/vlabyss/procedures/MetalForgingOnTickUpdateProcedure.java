package net.mcreator.vlabyss.procedures;

import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import java.util.concurrent.atomic.AtomicInteger;

public class MetalForgingOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((new Object() {
			public String getValue() {
				BlockEntity _ent = world.getBlockEntity(new BlockPos((int) x, (int) y, (int) z));
				if (_ent != null) {
					CompoundTag _nbt = _ent.saveWithFullMetadata();
					if (_nbt.contains("Items") && _nbt.get("Items") instanceof ListTag) {
						ListTag _listTag = _nbt.getList("Items", 10);
						if (_listTag.size() > 0) {
							CompoundTag _compound = _listTag.getCompound(0);
							if (_compound.contains("id")) {
								return _compound.getString("id");
							}
						}
					}
				}
				return "";
			}
		}.getValue()).equals("vl_abyss:raw_abyssion") && new Object() {
			public double getValue() {
				BlockEntity _ent = world.getBlockEntity(new BlockPos((int) x, (int) y, (int) z));
				if (_ent != null) {
					CompoundTag _nbt = _ent.saveWithFullMetadata();
					if (_nbt.contains("Items") && _nbt.get("Items") instanceof ListTag) {
						ListTag _listTag = _nbt.getList("Items", 10);
						if (_listTag.size() > 0) {
							CompoundTag _compound = _listTag.getCompound(0);
							if (_compound.contains("Slot")) {
								return _compound.getByte("Slot");
							}
						}
					}
				}
				return -1.0;
			}
		}.getValue() == 2) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putDouble("max_item", 80);
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else {
			if (!world.isClientSide()) {
				BlockPos _blockPos = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_blockPos);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().remove("max_item");
					if (world instanceof Level _level) {
						BlockState _blockState = _level.getBlockState(_blockPos);
						_level.sendBlockUpdated(_blockPos, _blockState, _blockState, 3);
					}
				}
			}
		}
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "tempo") <= 4) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putDouble("tempo", (new Object() {
						public double getValue(LevelAccessor world, BlockPos pos, String tag) {
							BlockEntity blockEntity = world.getBlockEntity(pos);
							if (blockEntity != null)
								return blockEntity.getPersistentData().getDouble(tag);
							return -1;
						}
					}.getValue(world, BlockPos.containing(x, y, z), "tempo") + 1));
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "tempo") >= 5) {
			if (!(new Object() {
				public double getValue(LevelAccessor world, BlockPos pos, String tag) {
					BlockEntity blockEntity = world.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getPersistentData().getDouble(tag);
					return -1;
				}
			}.getValue(world, BlockPos.containing(x, y, z), "temp_atual") == new Object() {
				public double getValue(LevelAccessor world, BlockPos pos, String tag) {
					BlockEntity blockEntity = world.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getPersistentData().getDouble(tag);
					return -1;
				}
			}.getValue(world, BlockPos.containing(x, y, z), "max_item"))) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putDouble("temp_atual", (new Object() {
							public double getValue(LevelAccessor world, BlockPos pos, String tag) {
								BlockEntity blockEntity = world.getBlockEntity(pos);
								if (blockEntity != null)
									return blockEntity.getPersistentData().getDouble(tag);
								return -1;
							}
						}.getValue(world, BlockPos.containing(x, y, z), "temp_atual") + 1));
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putDouble("tempo", 0);
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			{
				BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
				int _amount = 20;
				if (_ent != null)
					_ent.getCapability(ForgeCapabilities.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
			}
		}
		if (new Object() {
			public double getValue() {
				BlockEntity _ent = world.getBlockEntity(new BlockPos((int) x, (int) y, (int) z));
				if (_ent != null) {
					CompoundTag _nbt = _ent.saveWithFullMetadata();
					if (_nbt.contains("Items") && _nbt.get("Items") instanceof ListTag) {
						ListTag _listTag = _nbt.getList("Items", 10);
						if (_listTag.size() > 0) {
							CompoundTag _compound = _listTag.getCompound(0);
							if (_compound.contains("Slot")) {
								return _compound.getByte("Slot");
							}
						}
					}
				}
				return -1.0;
			}
		}.getValue() == 0) {
			if ((new Object() {
				public String getValue() {
					BlockEntity _ent = world.getBlockEntity(new BlockPos((int) x, (int) y, (int) z));
					if (_ent != null) {
						CompoundTag _nbt = _ent.saveWithFullMetadata();
						if (_nbt.contains("Items") && _nbt.get("Items") instanceof ListTag) {
							ListTag _listTag = _nbt.getList("Items", 10);
							if (_listTag.size() > 0) {
								CompoundTag _compound = _listTag.getCompound(0);
								if (_compound.contains("id")) {
									return _compound.getString("id");
								}
							}
						}
					}
					return "";
				}
			}.getValue()).equals("minecraft:coal") || (new Object() {
				public String getValue() {
					BlockEntity _ent = world.getBlockEntity(new BlockPos((int) x, (int) y, (int) z));
					if (_ent != null) {
						CompoundTag _nbt = _ent.saveWithFullMetadata();
						if (_nbt.contains("Items") && _nbt.get("Items") instanceof ListTag) {
							ListTag _listTag = _nbt.getList("Items", 10);
							if (_listTag.size() > 0) {
								CompoundTag _compound = _listTag.getCompound(0);
								if (_compound.contains("id")) {
									return _compound.getString("id");
								}
							}
						}
					}
					return "";
				}
			}.getValue()).equals("minecraft:charcoal") || (new Object() {
				public String getValue() {
					BlockEntity _ent = world.getBlockEntity(new BlockPos((int) x, (int) y, (int) z));
					if (_ent != null) {
						CompoundTag _nbt = _ent.saveWithFullMetadata();
						if (_nbt.contains("Items") && _nbt.get("Items") instanceof ListTag) {
							ListTag _listTag = _nbt.getList("Items", 10);
							if (_listTag.size() > 0) {
								CompoundTag _compound = _listTag.getCompound(0);
								if (_compound.contains("id")) {
									return _compound.getString("id");
								}
							}
						}
					}
					return "";
				}
			}.getValue()).equals("minecraft:magma_block")) {
				if (new Object() {
					public int getEnergyStored(LevelAccessor level, BlockPos pos) {
						AtomicInteger _retval = new AtomicInteger(0);
						BlockEntity _ent = level.getBlockEntity(pos);
						if (_ent != null)
							_ent.getCapability(ForgeCapabilities.ENERGY, null).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
						return _retval.get();
					}
				}.getEnergyStored(world, BlockPos.containing(x, y, z)) <= 19920) {
					{
						BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
						int _amount = 80;
						if (_ent != null)
							_ent.getCapability(ForgeCapabilities.ENERGY, null).ifPresent(capability -> capability.receiveEnergy(_amount, false));
					}
					{
						BlockEntity _ent = world.getBlockEntity(new BlockPos((int) x, (int) y, (int) z));
						if (_ent != null) {
							CompoundTag _nbt = _ent.saveWithFullMetadata();
							if (_nbt.contains("Items") && _nbt.get("Items") instanceof ListTag) {
								ListTag _listTag = _nbt.getList("Items", 10);
								if (_listTag.size() > 0) {
									CompoundTag _compound = _listTag.getCompound(0);
									_compound.putDouble("Count", ((new Object() {
										public double getValue() {
											BlockEntity _ent = world.getBlockEntity(new BlockPos((int) x, (int) y, (int) z));
											if (_ent != null) {
												CompoundTag _nbt = _ent.saveWithFullMetadata();
												if (_nbt.contains("Items") && _nbt.get("Items") instanceof ListTag) {
													ListTag _listTag = _nbt.getList("Items", 10);
													if (_listTag.size() > 0) {
														CompoundTag _compound = _listTag.getCompound(0);
														if (_compound.contains("Count")) {
															return _compound.getByte("Count");
														}
													}
												}
											}
											return -1.0;
										}
									}.getValue()) - 1));
									_ent.load(_nbt);
									_ent.setChanged();
								}
							}
						}
					}
				}
			} else if ((new Object() {
				public String getValue() {
					BlockEntity _ent = world.getBlockEntity(new BlockPos((int) x, (int) y, (int) z));
					if (_ent != null) {
						CompoundTag _nbt = _ent.saveWithFullMetadata();
						if (_nbt.contains("Items") && _nbt.get("Items") instanceof ListTag) {
							ListTag _listTag = _nbt.getList("Items", 10);
							if (_listTag.size() > 0) {
								CompoundTag _compound = _listTag.getCompound(0);
								if (_compound.contains("id")) {
									return _compound.getString("id");
								}
							}
						}
					}
					return "";
				}
			}.getValue()).equals("minecraft:coal_block")) {
				if (new Object() {
					public int getEnergyStored(LevelAccessor level, BlockPos pos) {
						AtomicInteger _retval = new AtomicInteger(0);
						BlockEntity _ent = level.getBlockEntity(pos);
						if (_ent != null)
							_ent.getCapability(ForgeCapabilities.ENERGY, null).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
						return _retval.get();
					}
				}.getEnergyStored(world, BlockPos.containing(x, y, z)) <= 19870) {
					{
						BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
						int _amount = 130;
						if (_ent != null)
							_ent.getCapability(ForgeCapabilities.ENERGY, null).ifPresent(capability -> capability.receiveEnergy(_amount, false));
					}
					{
						BlockEntity _ent = world.getBlockEntity(new BlockPos((int) x, (int) y, (int) z));
						if (_ent != null) {
							CompoundTag _nbt = _ent.saveWithFullMetadata();
							if (_nbt.contains("Items") && _nbt.get("Items") instanceof ListTag) {
								ListTag _listTag = _nbt.getList("Items", 10);
								if (_listTag.size() > 0) {
									CompoundTag _compound = _listTag.getCompound(0);
									_compound.putDouble("Count", ((new Object() {
										public double getValue() {
											BlockEntity _ent = world.getBlockEntity(new BlockPos((int) x, (int) y, (int) z));
											if (_ent != null) {
												CompoundTag _nbt = _ent.saveWithFullMetadata();
												if (_nbt.contains("Items") && _nbt.get("Items") instanceof ListTag) {
													ListTag _listTag = _nbt.getList("Items", 10);
													if (_listTag.size() > 0) {
														CompoundTag _compound = _listTag.getCompound(0);
														if (_compound.contains("Count")) {
															return _compound.getByte("Count");
														}
													}
												}
											}
											return -1.0;
										}
									}.getValue()) - 1));
									_ent.load(_nbt);
									_ent.setChanged();
								}
							}
						}
					}
				}
			} else if ((new Object() {
				public String getValue() {
					BlockEntity _ent = world.getBlockEntity(new BlockPos((int) x, (int) y, (int) z));
					if (_ent != null) {
						CompoundTag _nbt = _ent.saveWithFullMetadata();
						if (_nbt.contains("Items") && _nbt.get("Items") instanceof ListTag) {
							ListTag _listTag = _nbt.getList("Items", 10);
							if (_listTag.size() > 0) {
								CompoundTag _compound = _listTag.getCompound(0);
								if (_compound.contains("id")) {
									return _compound.getString("id");
								}
							}
						}
					}
					return "";
				}
			}.getValue()).equals("minecraft:lava_bucket")) {
				if (new Object() {
					public int getEnergyStored(LevelAccessor level, BlockPos pos) {
						AtomicInteger _retval = new AtomicInteger(0);
						BlockEntity _ent = level.getBlockEntity(pos);
						if (_ent != null)
							_ent.getCapability(ForgeCapabilities.ENERGY, null).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
						return _retval.get();
					}
				}.getEnergyStored(world, BlockPos.containing(x, y, z)) <= 19800) {
					{
						BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
						int _amount = 200;
						if (_ent != null)
							_ent.getCapability(ForgeCapabilities.ENERGY, null).ifPresent(capability -> capability.receiveEnergy(_amount, false));
					}
					{
						BlockEntity _ent = world.getBlockEntity(new BlockPos((int) x, (int) y, (int) z));
						if (_ent != null) {
							CompoundTag _nbt = _ent.saveWithFullMetadata();
							if (_nbt.contains("Items") && _nbt.get("Items") instanceof ListTag) {
								ListTag _listTag = _nbt.getList("Items", 10);
								if (_listTag.size() > 0) {
									CompoundTag _compound = _listTag.getCompound(0);
									_compound.putString("id", "minecraft:bucket");
									_ent.load(_nbt);
									_ent.setChanged();
								}
							}
						}
					}
				}
			}
		}
	}
}