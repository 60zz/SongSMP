package net.mcreator.vlabyss.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.procedures.TanqueClasseGuiProcedure;
import net.mcreator.vlabyss.procedures.GuerreiroClasseGuiProcedure;
import net.mcreator.vlabyss.procedures.BerserkerClasseGuiProcedure;
import net.mcreator.vlabyss.procedures.AssassinoClasseGuiProcedure;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SeletorDeClassesGuiButtonMessage {
	private final int buttonID, x, y, z;

	public SeletorDeClassesGuiButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public SeletorDeClassesGuiButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(SeletorDeClassesGuiButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(SeletorDeClassesGuiButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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

			TanqueClasseGuiProcedure.execute(entity);
		}
		if (buttonID == 1) {

			AssassinoClasseGuiProcedure.execute(entity);
		}
		if (buttonID == 2) {

			GuerreiroClasseGuiProcedure.execute(entity);
		}
		if (buttonID == 3) {

			BerserkerClasseGuiProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		VlAbyssMod.addNetworkMessage(SeletorDeClassesGuiButtonMessage.class, SeletorDeClassesGuiButtonMessage::buffer, SeletorDeClassesGuiButtonMessage::new, SeletorDeClassesGuiButtonMessage::handler);
	}
}