package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.configuration.SsmpConfiguration;

import java.util.UUID;

public class BotasVazioAbyssalBaubleIsEquippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).Nimren == true) {
			if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED)
					.hasModifier((new AttributeModifier(UUID.fromString("fdf27a86-6220-4cb2-a7d3-a010ec47fa47"), "corre_abyssal", ((double) SsmpConfiguration.BOTASVAZIOABYSSALCONFIG2.get()), AttributeModifier.Operation.ADDITION)))))
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED)
						.addTransientModifier((new AttributeModifier(UUID.fromString("fdf27a86-6220-4cb2-a7d3-a010ec47fa47"), "corre_abyssal", ((double) SsmpConfiguration.BOTASVAZIOABYSSALCONFIG2.get()), AttributeModifier.Operation.ADDITION)));
		} else {
			if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED)
					.hasModifier((new AttributeModifier(UUID.fromString("39856a2f-007e-4cd0-8b90-1baa5e97790a"), "corre_abyssal2", ((double) SsmpConfiguration.BOTASVAZIOABYSSALCONFIG.get()), AttributeModifier.Operation.ADDITION)))))
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED)
						.addTransientModifier((new AttributeModifier(UUID.fromString("39856a2f-007e-4cd0-8b90-1baa5e97790a"), "corre_abyssal2", ((double) SsmpConfiguration.BOTASVAZIOABYSSALCONFIG.get()), AttributeModifier.Operation.ADDITION)));
		}
	}
}