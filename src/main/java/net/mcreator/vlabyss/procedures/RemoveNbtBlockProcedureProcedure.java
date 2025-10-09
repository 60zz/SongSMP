package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class RemoveNbtBlockProcedureProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
		if (!world.isClientSide()) {
			BlockPos _blockPos = BlockPos.containing(new Object() {
				public double getX() {
					try {
						return BlockPosArgument.getLoadedBlockPos(arguments, "blockentity").getX();
					} catch (CommandSyntaxException e) {
						e.printStackTrace();
						return 0;
					}
				}
			}.getX(), new Object() {
				public double getY() {
					try {
						return BlockPosArgument.getLoadedBlockPos(arguments, "blockentity").getY();
					} catch (CommandSyntaxException e) {
						e.printStackTrace();
						return 0;
					}
				}
			}.getY(), new Object() {
				public double getZ() {
					try {
						return BlockPosArgument.getLoadedBlockPos(arguments, "blockentity").getZ();
					} catch (CommandSyntaxException e) {
						e.printStackTrace();
						return 0;
					}
				}
			}.getZ());
			BlockEntity _blockEntity = world.getBlockEntity(_blockPos);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().remove((new Object() {
					public String getMessage() {
						try {
							return MessageArgument.getMessage(arguments, "nbt").getString();
						} catch (CommandSyntaxException ignored) {
							return "";
						}
					}
				}).getMessage());
				if (world instanceof Level _level) {
					BlockState _blockState = _level.getBlockState(_blockPos);
					_level.sendBlockUpdated(_blockPos, _blockState, _blockState, 3);
				}
			}
		}
	}
}