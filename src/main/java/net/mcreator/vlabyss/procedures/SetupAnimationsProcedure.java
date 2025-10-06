package net.mcreator.vlabyss.procedures;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.vlabyss.VlAbyssMod;

import javax.annotation.Nullable;

import java.util.function.Supplier;

import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationFactory;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.IAnimation;

@Mod.EventBusSubscriber(modid = "vl_abyss", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SetupAnimationsProcedure {
	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(new ResourceLocation("vl_abyss", "player_animation"), 1000, SetupAnimationsProcedure::registerPlayerAnimations);
	}

	private static IAnimation registerPlayerAnimations(net.minecraft.client.player.AbstractClientPlayer player) {
		return new ModifierLayer<>();
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class VlAbyssModAnimationMessage {
		Component animation;
		int target;
		boolean override;

		public VlAbyssModAnimationMessage(Component animation, int target, boolean override) {
			this.animation = animation;
			this.target = target;
			this.override = override;
		}

		public VlAbyssModAnimationMessage(FriendlyByteBuf buffer) {
			this.animation = buffer.readComponent();
			this.target = buffer.readInt();
			this.override = buffer.readBoolean();
		}

		public static void buffer(VlAbyssModAnimationMessage message, FriendlyByteBuf buffer) {
			buffer.writeComponent(message.animation);
			buffer.writeInt(message.target);
			buffer.writeBoolean(message.override);
		}

		public static void handler(VlAbyssModAnimationMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				Level level = context.getSender().level();
				if (level.getEntity(message.target) != null) {
					Player player = (Player) level.getEntity(message.target);
					setAnimationClientside(player, message.animation.getString(), message.override);
				}
			});
			context.setPacketHandled(true);
		}

		@SubscribeEvent
		public static void registerMessage(FMLCommonSetupEvent event) {
			VlAbyssMod.addNetworkMessage(VlAbyssModAnimationMessage.class, VlAbyssModAnimationMessage::buffer, VlAbyssModAnimationMessage::new, VlAbyssModAnimationMessage::handler);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static void setAnimationClientside(Player player, String anim, boolean override) {
		if (player instanceof net.minecraft.client.player.AbstractClientPlayer player_) {
			var animation = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData(player_).get(new ResourceLocation("vl_abyss", "player_animation"));
			if (animation != null && override ? true : !animation.isActive()) {
				animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(new ResourceLocation("vl_abyss", anim))));
			}
		}
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
	}
}