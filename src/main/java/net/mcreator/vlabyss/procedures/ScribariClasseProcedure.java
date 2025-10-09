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

public class ScribariClasseProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		try {
			for (Entity entityiterator : EntityArgument.getEntities(arguments, "entity")) {
				if (BoolArgumentType.getBool(arguments, "value") == true) {
					{
						boolean _setval = true;
						entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Scribari = _setval;
							capability.syncPlayerVariables(entityiterator);
						});
					}
					if (!(((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
							.hasModifier((new AttributeModifier(UUID.fromString("80ccceb1-5152-46dc-99c1-bb0c7b089b2d"), "scribari", 1, AttributeModifier.Operation.ADDITION)))))
						((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
								.addPermanentModifier((new AttributeModifier(UUID.fromString("80ccceb1-5152-46dc-99c1-bb0c7b089b2d"), "scribari", 1, AttributeModifier.Operation.ADDITION)));
					if (!(((LivingEntity) entityiterator).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
							.hasModifier((new AttributeModifier(UUID.fromString("328c1315-04c1-4e6b-a297-8ace8353b818"), "scribari2", 2, AttributeModifier.Operation.ADDITION)))))
						((LivingEntity) entityiterator).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
								.addPermanentModifier((new AttributeModifier(UUID.fromString("328c1315-04c1-4e6b-a297-8ace8353b818"), "scribari2", 2, AttributeModifier.Operation.ADDITION)));
					if (entityiterator instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7bVoc\u00EA agora est\u00E1 na classe \u00A73SCRIBARI"), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7bVoc\u00EA colocou " + entityiterator.getDisplayName().getString() + " na classe \u00A73SCRIBARI")), false);
				} else {
					{
						boolean _setval = false;
						entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Scribari = _setval;
							capability.syncPlayerVariables(entityiterator);
						});
					}
					if (entityiterator instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7bVoc\u00EA foi removido da classe \u00A73SCRIBARI"), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7bVoc\u00EA removeu " + entityiterator.getDisplayName().getString() + " na classe \u00A73SCRIBARI")), false);
					((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
							.removePermanentModifier((new AttributeModifier(UUID.fromString("80ccceb1-5152-46dc-99c1-bb0c7b089b2d"), "scribari", 1, AttributeModifier.Operation.ADDITION)).getId());
					((LivingEntity) entityiterator).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
							.removePermanentModifier((new AttributeModifier(UUID.fromString("328c1315-04c1-4e6b-a297-8ace8353b818"), "scribari2", 2, AttributeModifier.Operation.ADDITION)).getId());
				}
			}
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
	}
}