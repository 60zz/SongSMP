package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModAttributes;

import java.util.UUID;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.BoolArgumentType;

public class NimrenClasseProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		try {
			for (Entity entityiterator : EntityArgument.getEntities(arguments, "entity")) {
				if (BoolArgumentType.getBool(arguments, "value") == true) {
					{
						boolean _setval = true;
						entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Nimren = _setval;
							capability.syncPlayerVariables(entityiterator);
						});
					}
					if (!(((LivingEntity) entityiterator).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
							.hasModifier((new AttributeModifier(UUID.fromString("50373661-9355-4c8a-bcc7-edb4c72e5280"), "nim'ren_c", 2, AttributeModifier.Operation.ADDITION)))))
						((LivingEntity) entityiterator).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
								.addPermanentModifier((new AttributeModifier(UUID.fromString("50373661-9355-4c8a-bcc7-edb4c72e5280"), "nim'ren_c", 2, AttributeModifier.Operation.ADDITION)));
					if (entityiterator instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7bVoc\u00EA agora est\u00E1 na classe \u00A73NIM'REN"), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7bVoc\u00EA colocou " + entityiterator.getDisplayName().getString() + " na classe \u00A73NIM'REN")), false);
				} else {
					{
						boolean _setval = false;
						entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Nimren = _setval;
							capability.syncPlayerVariables(entityiterator);
						});
					}
					if (entityiterator instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7bVoc\u00EA foi removido da classe \u00A73NIM'REN"), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7bVoc\u00EA removeu " + entityiterator.getDisplayName().getString() + " na classe \u00A73NIM'REN")), false);
					((LivingEntity) entityiterator).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
							.removePermanentModifier((new AttributeModifier(UUID.fromString("50373661-9355-4c8a-bcc7-edb4c72e5280"), "nim'ren_c", 2, AttributeModifier.Operation.ADDITION)).getId());
				}
			}
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
	}
}