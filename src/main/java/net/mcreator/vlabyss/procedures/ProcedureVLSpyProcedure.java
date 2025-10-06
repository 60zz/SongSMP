package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.BoolArgumentType;

public class ProcedureVLSpyProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		try {
			for (Entity entityiterator : EntityArgument.getEntities(arguments, "entity")) {
				if (BoolArgumentType.getBool(arguments, "value") == true) {
					{
						boolean _setval = true;
						entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.VL_Spy = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					{
						boolean _setval = false;
						entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.VL_Spy = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			}
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
	}
}