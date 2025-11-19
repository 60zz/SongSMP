package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.vlabyss.VlAbyssMod;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class VLMPBcastProcedureProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments) {
		try {
			for (Entity entityiterator : EntityArgument.getEntities(arguments, "entity")) {
				if (entityiterator instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A7f\u00A7lSSMP \u00A7r\u00A78>> \u00A77" + commandParameterMessage(arguments, "message"))), false);
			}
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		VlAbyssMod.LOGGER.info("\u00A7f\u00A7lSSMP \u00A7r\u00A78>> \u00A77" + commandParameterMessage(arguments, "message"));
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