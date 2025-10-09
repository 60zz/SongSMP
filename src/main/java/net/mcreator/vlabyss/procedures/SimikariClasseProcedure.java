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

public class SimikariClasseProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		try {
			for (Entity entityiterator : EntityArgument.getEntities(arguments, "entity")) {
				if (BoolArgumentType.getBool(arguments, "value") == true) {
					{
						boolean _setval = true;
						entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Simikari = _setval;
							capability.syncPlayerVariables(entityiterator);
						});
					}
					if (!(((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
							.hasModifier((new AttributeModifier(UUID.fromString("910c870f-db83-4890-95f1-7f104542e03a"), "simikari", 1, AttributeModifier.Operation.ADDITION)))))
						((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
								.addPermanentModifier((new AttributeModifier(UUID.fromString("910c870f-db83-4890-95f1-7f104542e03a"), "simikari", 1, AttributeModifier.Operation.ADDITION)));
					if (!(((LivingEntity) entityiterator).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
							.hasModifier((new AttributeModifier(UUID.fromString("67a14d34-7c64-44a5-b9b0-f18445bbb5c4"), "simikari2", 1, AttributeModifier.Operation.ADDITION)))))
						((LivingEntity) entityiterator).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
								.addPermanentModifier((new AttributeModifier(UUID.fromString("67a14d34-7c64-44a5-b9b0-f18445bbb5c4"), "simikari2", 1, AttributeModifier.Operation.ADDITION)));
					if (entityiterator instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7bVoc\u00EA agora est\u00E1 na classe \u00A73SIMIKARI"), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7bVoc\u00EA colocou " + entityiterator.getDisplayName().getString() + " na classe \u00A73SIMIKARI")), false);
				} else {
					{
						boolean _setval = false;
						entityiterator.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Simikari = _setval;
							capability.syncPlayerVariables(entityiterator);
						});
					}
					if (entityiterator instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7bVoc\u00EA foi removido da classe \u00A73SIMIKARI"), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7bVoc\u00EA removeu " + entityiterator.getDisplayName().getString() + " na classe \u00A73SIMIKARI")), false);
					((LivingEntity) entityiterator).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
							.removePermanentModifier((new AttributeModifier(UUID.fromString("910c870f-db83-4890-95f1-7f104542e03a"), "simikari", 1, AttributeModifier.Operation.ADDITION)).getId());
					((LivingEntity) entityiterator).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
							.removePermanentModifier((new AttributeModifier(UUID.fromString("67a14d34-7c64-44a5-b9b0-f18445bbb5c4"), "simikari2", 1, AttributeModifier.Operation.ADDITION)).getId());
				}
			}
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
	}
}