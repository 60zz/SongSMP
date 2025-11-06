package net.mcreator.vlabyss.procedures;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.client.Minecraft;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModKeyMappings;
import net.mcreator.vlabyss.VlAbyssMod;

import javax.annotation.Nullable;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(value = {Dist.CLIENT})
public class SelecionaMantraRapidoOnKeyPressedProcedure {
	@SubscribeEvent
	public static void onRightClick(PlayerInteractEvent.RightClickEmpty event) {
		if (event.getHand() != InteractionHand.MAIN_HAND)
			return;
		VlAbyssMod.PACKET_HANDLER.sendToServer(new SelecionaMantraRapidoOnKeyPressedMessage());
		execute(event.getEntity());
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class SelecionaMantraRapidoOnKeyPressedMessage {
		public SelecionaMantraRapidoOnKeyPressedMessage() {
		}

		public SelecionaMantraRapidoOnKeyPressedMessage(FriendlyByteBuf buffer) {
		}

		public static void buffer(SelecionaMantraRapidoOnKeyPressedMessage message, FriendlyByteBuf buffer) {
		}

		public static void handler(SelecionaMantraRapidoOnKeyPressedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getSender().level().hasChunkAt(context.getSender().blockPosition()))
					return;
				execute(context.getSender());
			});
			context.setPacketHandled(true);
		}

		@SubscribeEvent
		public static void registerMessage(FMLCommonSetupEvent event) {
			VlAbyssMod.addNetworkMessage(SelecionaMantraRapidoOnKeyPressedMessage.class, SelecionaMantraRapidoOnKeyPressedMessage::buffer, SelecionaMantraRapidoOnKeyPressedMessage::new, SelecionaMantraRapidoOnKeyPressedMessage::handler);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (!Minecraft.getInstance().options.keyShift.isDown()) {
			if (VlAbyssModKeyMappings.SELECIONA_MANTRA_RAPIDO.isDown()) {
				if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).hab_selecionada == 6) {
					{
						double _setval = 1;
						entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.hab_selecionada = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).hab_selecionada <= 5) {
					{
						double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).hab_selecionada + 1;
						entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.hab_selecionada = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			}
		} else {
			if (VlAbyssModKeyMappings.SELECIONA_MANTRA_RAPIDO.isDown()) {
				if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).hab_selecionada == 1) {
					{
						double _setval = 6;
						entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.hab_selecionada = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).hab_selecionada >= 2) {
					{
						double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).hab_selecionada - 1;
						entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.hab_selecionada = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			}
		}
	}
}