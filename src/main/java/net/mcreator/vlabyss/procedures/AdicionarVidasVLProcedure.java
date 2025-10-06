package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class AdicionarVidasVLProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		try {
			for (Entity entityiterator : EntityArgument.getEntities(arguments, "entity")) {
				if (!((entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).vidas + DoubleArgumentType.getDouble(arguments, "value") > 10)) {
					{
						double _setval = (entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).vidas + DoubleArgumentType.getDouble(arguments, "value");
						entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.vidas = _setval;
							capability.syncPlayerVariables(entityiterator);
						});
					}
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7bVoc\u00EA adicionou \u00A73" + DoubleArgumentType.getDouble(arguments, "value") + " \u00A7bvida(s) extra(s) para: " + entityiterator.getDisplayName().getString())),
								false);
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7bN\u00E3o pode ultrapassar o limite \u00A73M\u00C1XIMO \u00A7bde 10 vidas"), false);
				}
			}
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
	}
}