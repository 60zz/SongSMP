package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModAttributes;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class MorreuVoltaBuffsProcedure {
	@SubscribeEvent
	public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).Arkanthi == true) {
			if (!(((LivingEntity) entity).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get()).hasModifier((new AttributeModifier(UUID.fromString("ea023ef8-c573-4b68-bdd7-ac8a9c4ad53f"), "arkanthi_int", 1, AttributeModifier.Operation.ADDITION)))))
				((LivingEntity) entity).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
						.addPermanentModifier((new AttributeModifier(UUID.fromString("ea023ef8-c573-4b68-bdd7-ac8a9c4ad53f"), "arkanthi_int", 1, AttributeModifier.Operation.ADDITION)));
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).Vesperian == true) {
			if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
					.hasModifier((new AttributeModifier(UUID.fromString("c0911b41-9d4d-43a0-b0a2-3765cf0b2762"), "vesperian_alt", 2, AttributeModifier.Operation.ADDITION)))))
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
						.addPermanentModifier((new AttributeModifier(UUID.fromString("c0911b41-9d4d-43a0-b0a2-3765cf0b2762"), "vesperian_alt", 2, AttributeModifier.Operation.ADDITION)));
			if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_SPEED)
					.hasModifier((new AttributeModifier(UUID.fromString("33949770-356f-4cba-8e47-5c76bec75423"), "vesperian_alt2", 1, AttributeModifier.Operation.ADDITION)))))
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_SPEED)
						.addPermanentModifier((new AttributeModifier(UUID.fromString("33949770-356f-4cba-8e47-5c76bec75423"), "vesperian_alt2", 1, AttributeModifier.Operation.ADDITION)));
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).Nimren == true) {
			if (!(((LivingEntity) entity).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get()).hasModifier((new AttributeModifier(UUID.fromString("50373661-9355-4c8a-bcc7-edb4c72e5280"), "nim'ren_c", 2, AttributeModifier.Operation.ADDITION)))))
				((LivingEntity) entity).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
						.addPermanentModifier((new AttributeModifier(UUID.fromString("50373661-9355-4c8a-bcc7-edb4c72e5280"), "nim'ren_c", 2, AttributeModifier.Operation.ADDITION)));
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).Simikari == true) {
			if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
					.hasModifier((new AttributeModifier(UUID.fromString("910c870f-db83-4890-95f1-7f104542e03a"), "simikari", 1, AttributeModifier.Operation.ADDITION)))))
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
						.addPermanentModifier((new AttributeModifier(UUID.fromString("910c870f-db83-4890-95f1-7f104542e03a"), "simikari", 1, AttributeModifier.Operation.ADDITION)));
			if (!(((LivingEntity) entity).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get()).hasModifier((new AttributeModifier(UUID.fromString("67a14d34-7c64-44a5-b9b0-f18445bbb5c4"), "simikari2", 1, AttributeModifier.Operation.ADDITION)))))
				((LivingEntity) entity).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
						.addPermanentModifier((new AttributeModifier(UUID.fromString("67a14d34-7c64-44a5-b9b0-f18445bbb5c4"), "simikari2", 1, AttributeModifier.Operation.ADDITION)));
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).Scribari == true) {
			if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
					.hasModifier((new AttributeModifier(UUID.fromString("80ccceb1-5152-46dc-99c1-bb0c7b089b2d"), "scribari", 1, AttributeModifier.Operation.ADDITION)))))
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
						.addPermanentModifier((new AttributeModifier(UUID.fromString("80ccceb1-5152-46dc-99c1-bb0c7b089b2d"), "scribari", 1, AttributeModifier.Operation.ADDITION)));
			if (!(((LivingEntity) entity).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get()).hasModifier((new AttributeModifier(UUID.fromString("328c1315-04c1-4e6b-a297-8ace8353b818"), "scribari2", 2, AttributeModifier.Operation.ADDITION)))))
				((LivingEntity) entity).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
						.addPermanentModifier((new AttributeModifier(UUID.fromString("328c1315-04c1-4e6b-a297-8ace8353b818"), "scribari2", 2, AttributeModifier.Operation.ADDITION)));
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).Capridel == true) {
			if (!(((LivingEntity) entity).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get()).hasModifier((new AttributeModifier(UUID.fromString("6fc1aba1-2aec-48c5-84ad-c99b6ab6759e"), "capidrel", 3, AttributeModifier.Operation.ADDITION)))))
				((LivingEntity) entity).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get()).addPermanentModifier((new AttributeModifier(UUID.fromString("6fc1aba1-2aec-48c5-84ad-c99b6ab6759e"), "capidrel", 3, AttributeModifier.Operation.ADDITION)));
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).Lumivivo == true) {
			if (!(((LivingEntity) entity).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get()).hasModifier((new AttributeModifier(UUID.fromString("61fbe6c9-72e2-4647-8c8e-723c03bb58ab"), "lumivivo", 2, AttributeModifier.Operation.ADDITION)))))
				((LivingEntity) entity).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get()).addPermanentModifier((new AttributeModifier(UUID.fromString("61fbe6c9-72e2-4647-8c8e-723c03bb58ab"), "lumivivo", 2, AttributeModifier.Operation.ADDITION)));
		}
	}
}