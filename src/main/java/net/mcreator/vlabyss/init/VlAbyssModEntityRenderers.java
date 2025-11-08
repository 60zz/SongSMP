/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

import net.mcreator.vlabyss.client.renderer.WindVortexRenderer;
import net.mcreator.vlabyss.client.renderer.MantraSoulRenderer;
import net.mcreator.vlabyss.client.renderer.MantraSoulCorrompidaRenderer;
import net.mcreator.vlabyss.client.renderer.CavaleiroAladoRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VlAbyssModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(VlAbyssModEntities.MANTRA_SOUL.get(), MantraSoulRenderer::new);
		event.registerEntityRenderer(VlAbyssModEntities.RESPIRO_PRIMARIA.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(VlAbyssModEntities.CAVALEIRO_ALADO.get(), CavaleiroAladoRenderer::new);
		event.registerEntityRenderer(VlAbyssModEntities.MANTRA_SOUL_CORROMPIDA.get(), MantraSoulCorrompidaRenderer::new);
		event.registerEntityRenderer(VlAbyssModEntities.WIND_VORTEX.get(), WindVortexRenderer::new);
		event.registerEntityRenderer(VlAbyssModEntities.COUNTER_SPELL.get(), ThrownItemRenderer::new);
	}
}