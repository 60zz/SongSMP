/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.vlabyss.client.gui.SeletorDeClassesGuiScreen;
import net.mcreator.vlabyss.client.gui.SegundaHabilidadeMantraScreen;
import net.mcreator.vlabyss.client.gui.JScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VlAbyssModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(VlAbyssModMenus.J.get(), JScreen::new);
			MenuScreens.register(VlAbyssModMenus.SELETOR_DE_CLASSES_GUI.get(), SeletorDeClassesGuiScreen::new);
			MenuScreens.register(VlAbyssModMenus.SEGUNDA_HABILIDADE_MANTRA.get(), SegundaHabilidadeMantraScreen::new);
		});
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}