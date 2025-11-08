package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModAttributes;
import net.mcreator.vlabyss.configuration.SsmpConfiguration;

import java.util.UUID;

public class CapuzEsquecidoBaubleIsEquippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Capridel == true)) {
			((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).removeModifier(UUID.fromString("d57f7e7e-8fcd-4615-9f35-5daa08a82cec"));
		} else {
			((LivingEntity) entity).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
					.removeModifier((new AttributeModifier(UUID.fromString("cb783c75-31ff-4f23-a5fa-8aac9958d94b"), "maxint", ((double) SsmpConfiguration.CAPUZESQUECIDOCONFIG.get()), AttributeModifier.Operation.ADDITION)));
		}
		entity.getPersistentData().remove("capuzesquecido");
	}
}