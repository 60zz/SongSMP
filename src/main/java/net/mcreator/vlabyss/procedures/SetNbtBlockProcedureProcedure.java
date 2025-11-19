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
import com.mojang.brigadier.arguments.BoolArgumentType;

public class SetNbtBlockProcedureProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
		if (!world.isClientSide()) {
			BlockPos _bp = new BlockPos(commandParameterBlockPos(arguments, "blockentity").getX(), commandParameterBlockPos(arguments, "blockentity").getY(), commandParameterBlockPos(arguments, "blockentity").getZ());
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null)
				_blockEntity.getPersistentData().putBoolean((commandParameterMessage(arguments, "nbt")), (BoolArgumentType.getBool(arguments, "value")));
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
	}

	private static BlockPos commandParameterBlockPos(CommandContext<CommandSourceStack> arguments, String parameter) {
		try {
			return BlockPosArgument.getLoadedBlockPos(arguments, parameter);
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
			return new BlockPos(0, 0, 0);
		}
	}

	private static String commandParameterMessage(CommandContext<CommandSourceStack> arguments, String parameter) {
		try {
			return MessageArgument.getMessage(arguments, parameter).getString();
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
			return "";
		}
	}
}