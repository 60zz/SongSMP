/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.vlabyss.network.PlanarMessage;
import net.mcreator.vlabyss.network.ParryMessage;
import net.mcreator.vlabyss.network.HabilidadeSecundariaMessage;
import net.mcreator.vlabyss.network.HabilidadePrimariaMessage;
import net.mcreator.vlabyss.network.DashTrasMessage;
import net.mcreator.vlabyss.network.DashFrenteMessage;
import net.mcreator.vlabyss.network.AtivaArtefatosMessage;
import net.mcreator.vlabyss.VlAbyssMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class VlAbyssModKeyMappings {
	public static final KeyMapping PARRY = new KeyMapping("key.vl_abyss.parry", GLFW.GLFW_KEY_F, "key.categories.geral") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				VlAbyssMod.PACKET_HANDLER.sendToServer(new ParryMessage(0, 0));
				ParryMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping ATIVA_ARTEFATOS = new KeyMapping("key.vl_abyss.ativa_artefatos", GLFW.GLFW_KEY_X, "key.categories.artefatos") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				VlAbyssMod.PACKET_HANDLER.sendToServer(new AtivaArtefatosMessage(0, 0));
				AtivaArtefatosMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping DASH_TRAS = new KeyMapping("key.vl_abyss.dash_tras", GLFW.GLFW_KEY_S, "key.categories.geral") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				VlAbyssMod.PACKET_HANDLER.sendToServer(new DashTrasMessage(0, 0));
				DashTrasMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping PLANAR = new KeyMapping("key.vl_abyss.planar", GLFW.GLFW_KEY_SPACE, "key.categories.geral") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				VlAbyssMod.PACKET_HANDLER.sendToServer(new PlanarMessage(0, 0));
				PlanarMessage.pressAction(Minecraft.getInstance().player, 0, 0);
				PLANAR_LASTPRESS = System.currentTimeMillis();
			} else if (isDownOld != isDown && !isDown) {
				int dt = (int) (System.currentTimeMillis() - PLANAR_LASTPRESS);
				VlAbyssMod.PACKET_HANDLER.sendToServer(new PlanarMessage(1, dt));
				PlanarMessage.pressAction(Minecraft.getInstance().player, 1, dt);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping HABILIDADE_PRIMARIA = new KeyMapping("key.vl_abyss.habilidade_primaria", GLFW.GLFW_KEY_X, "key.categories.mantra") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				VlAbyssMod.PACKET_HANDLER.sendToServer(new HabilidadePrimariaMessage(0, 0));
				HabilidadePrimariaMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping DASH_FRENTE = new KeyMapping("key.vl_abyss.dash_frente", GLFW.GLFW_KEY_W, "key.categories.geral") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				VlAbyssMod.PACKET_HANDLER.sendToServer(new DashFrenteMessage(0, 0));
				DashFrenteMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping HABILIDADE_SECUNDARIA = new KeyMapping("key.vl_abyss.habilidade_secundaria", GLFW.GLFW_KEY_Z, "key.categories.mantra") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				VlAbyssMod.PACKET_HANDLER.sendToServer(new HabilidadeSecundariaMessage(0, 0));
				HabilidadeSecundariaMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	private static long PLANAR_LASTPRESS = 0;

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(PARRY);
		event.register(ATIVA_ARTEFATOS);
		event.register(DASH_TRAS);
		event.register(PLANAR);
		event.register(HABILIDADE_PRIMARIA);
		event.register(DASH_FRENTE);
		event.register(HABILIDADE_SECUNDARIA);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				PARRY.consumeClick();
				ATIVA_ARTEFATOS.consumeClick();
				DASH_TRAS.consumeClick();
				PLANAR.consumeClick();
				HABILIDADE_PRIMARIA.consumeClick();
				DASH_FRENTE.consumeClick();
				HABILIDADE_SECUNDARIA.consumeClick();
			}
		}
	}
}