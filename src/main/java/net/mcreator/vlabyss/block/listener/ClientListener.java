package net.mcreator.vlabyss.block.listener;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.vlabyss.init.VlAbyssModBlockEntities;
import net.mcreator.vlabyss.block.renderer.PortaAbyssTileRenderer;
import net.mcreator.vlabyss.VlAbyssMod;

@Mod.EventBusSubscriber(modid = VlAbyssMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientListener {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(VlAbyssModBlockEntities.PORTA_ABYSS.get(), context -> new PortaAbyssTileRenderer());
	}
}