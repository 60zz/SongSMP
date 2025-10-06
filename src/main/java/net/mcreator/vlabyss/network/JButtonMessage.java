package net.mcreator.vlabyss.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.procedures.TempestadeBotaoClicouProcedure;
import net.mcreator.vlabyss.procedures.RespiroBotaoClicouProcedure;
import net.mcreator.vlabyss.procedures.NevascaBotaoClicouProcedure;
import net.mcreator.vlabyss.procedures.LuzBotaoClicouProcedure;
import net.mcreator.vlabyss.procedures.EscuridaoBotaoClicouProcedure;
import net.mcreator.vlabyss.procedures.ChamaBotaoClicouProcedure;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class JButtonMessage {
	private final int buttonID, x, y, z;

	public JButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public JButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(JButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(JButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			LuzBotaoClicouProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			TempestadeBotaoClicouProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			NevascaBotaoClicouProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 3) {

			RespiroBotaoClicouProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 4) {

			ChamaBotaoClicouProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 5) {

			EscuridaoBotaoClicouProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		VlAbyssMod.addNetworkMessage(JButtonMessage.class, JButtonMessage::buffer, JButtonMessage::new, JButtonMessage::handler);
	}
}