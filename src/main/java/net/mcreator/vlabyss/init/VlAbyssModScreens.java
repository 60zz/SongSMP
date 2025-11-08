/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.vlabyss.client.gui.RodaDeMantrasScreen;
import net.mcreator.vlabyss.client.gui.MetalForjaScreen;
import net.mcreator.vlabyss.client.gui.JScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VlAbyssModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(VlAbyssModMenus.J.get(), JScreen::new);
			MenuScreens.register(VlAbyssModMenus.METAL_FORJA.get(), MetalForjaScreen::new);
			MenuScreens.register(VlAbyssModMenus.RODA_DE_MANTRAS.get(), RodaDeMantrasScreen::new);
		});
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}