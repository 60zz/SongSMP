/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.vlabyss.client.model.Modelechoofshadows;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class VlAbyssModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelechoofshadows.LAYER_LOCATION, Modelechoofshadows::createBodyLayer);
	}
}