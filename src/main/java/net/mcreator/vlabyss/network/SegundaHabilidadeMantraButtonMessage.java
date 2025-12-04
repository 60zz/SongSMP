package net.mcreator.vlabyss.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.procedures.ClicouTerceiraOpcaoTempestadeProcedure;
import net.mcreator.vlabyss.procedures.ClicouTerceiraOpcaoRespiroProcedure;
import net.mcreator.vlabyss.procedures.ClicouTerceiraOpcaoEscuridaoProcedure;
import net.mcreator.vlabyss.procedures.ClicouTerceiraOpcaoChamaProcedure;
import net.mcreator.vlabyss.procedures.ClicouSegundaOpcaoTempestadeProcedure;
import net.mcreator.vlabyss.procedures.ClicouSegundaOpcaoRespiroProcedure;
import net.mcreator.vlabyss.procedures.ClicouSegundaOpcaoEscuridaoProcedure;
import net.mcreator.vlabyss.procedures.ClicouSegundaOpcaoChamaProcedure;
import net.mcreator.vlabyss.procedures.ClicouPrimeiraOpcaoTempestadeProcedure;
import net.mcreator.vlabyss.procedures.ClicouPrimeiraOpcaoRespiroProcedure;
import net.mcreator.vlabyss.procedures.ClicouPrimeiraOpcaoEscuridaoProcedure;
import net.mcreator.vlabyss.procedures.ClicouPrimeiraOpcaoChamaProcedure;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SegundaHabilidadeMantraButtonMessage {
	private final int buttonID, x, y, z;

	public SegundaHabilidadeMantraButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public SegundaHabilidadeMantraButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(SegundaHabilidadeMantraButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(SegundaHabilidadeMantraButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> handleButtonAction(context.getSender(), message.buttonID, message.x, message.y, message.z));
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			ClicouSegundaOpcaoRespiroProcedure.execute(entity);
		}
		if (buttonID == 1) {

			ClicouPrimeiraOpcaoRespiroProcedure.execute(entity);
		}
		if (buttonID == 2) {

			ClicouTerceiraOpcaoRespiroProcedure.execute(entity);
		}
		if (buttonID == 3) {

			ClicouPrimeiraOpcaoChamaProcedure.execute(entity);
		}
		if (buttonID == 4) {

			ClicouSegundaOpcaoChamaProcedure.execute(entity);
		}
		if (buttonID == 5) {

			ClicouTerceiraOpcaoChamaProcedure.execute(entity);
		}
		if (buttonID == 6) {

			ClicouPrimeiraOpcaoTempestadeProcedure.execute(entity);
		}
		if (buttonID == 7) {

			ClicouSegundaOpcaoTempestadeProcedure.execute(entity);
		}
		if (buttonID == 8) {

			ClicouTerceiraOpcaoTempestadeProcedure.execute(entity);
		}
		if (buttonID == 9) {

			ClicouPrimeiraOpcaoEscuridaoProcedure.execute(entity);
		}
		if (buttonID == 10) {

			ClicouSegundaOpcaoEscuridaoProcedure.execute(entity);
		}
		if (buttonID == 11) {

			ClicouTerceiraOpcaoEscuridaoProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		VlAbyssMod.addNetworkMessage(SegundaHabilidadeMantraButtonMessage.class, SegundaHabilidadeMantraButtonMessage::buffer, SegundaHabilidadeMantraButtonMessage::new, SegundaHabilidadeMantraButtonMessage::handler);
	}
}