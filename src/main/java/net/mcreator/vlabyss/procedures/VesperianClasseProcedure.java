package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

import java.util.UUID;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.BoolArgumentType;

public class VesperianClasseProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		try {
			for (Entity entityiterator : EntityArgument.getEntities(arguments, "entity")) {
				if (BoolArgumentType.getBool(arguments, "value") == true) {
					{
						boolean _setval = true;
						entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Vesperian = _setval;
							capability.syncPlayerVariables(entityiterator);
						});
					}
					if (!(((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
							.hasModifier((new AttributeModifier(UUID.fromString("c0911b41-9d4d-43a0-b0a2-3765cf0b2762"), "vesperian_alt", 2, AttributeModifier.Operation.ADDITION)))))
						((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
								.addPermanentModifier((new AttributeModifier(UUID.fromString("c0911b41-9d4d-43a0-b0a2-3765cf0b2762"), "vesperian_alt", 2, AttributeModifier.Operation.ADDITION)));
					if (!(((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_SPEED)
							.hasModifier((new AttributeModifier(UUID.fromString("33949770-356f-4cba-8e47-5c76bec75423"), "vesperian_alt2", 1, AttributeModifier.Operation.ADDITION)))))
						((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_SPEED)
								.addPermanentModifier((new AttributeModifier(UUID.fromString("33949770-356f-4cba-8e47-5c76bec75423"), "vesperian_alt2", 1, AttributeModifier.Operation.ADDITION)));
					if (entityiterator instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7bVoc\u00EA agora est\u00E1 na classe \u00A73VESPERIAN"), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7bVoc\u00EA colocou " + entityiterator.getDisplayName().getString() + " na classe \u00A73VESPERIAN")), false);
				} else {
					{
						boolean _setval = false;
						entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Vesperian = _setval;
							capability.syncPlayerVariables(entityiterator);
						});
					}
					if (entityiterator instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7bVoc\u00EA foi removido da classe \u00A73VESPERIAN"), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7bVoc\u00EA removeu " + entityiterator.getDisplayName().getString() + " na classe \u00A73VESPERIAN")), false);
					((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
							.removePermanentModifier((new AttributeModifier(UUID.fromString("c0911b41-9d4d-43a0-b0a2-3765cf0b2762"), "vesperian_alt", 2, AttributeModifier.Operation.ADDITION)).getId());
					((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_SPEED)
							.removePermanentModifier((new AttributeModifier(UUID.fromString("33949770-356f-4cba-8e47-5c76bec75423"), "vesperian_alt2", 1, AttributeModifier.Operation.ADDITION)).getId());
				}
			}
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
	}
}