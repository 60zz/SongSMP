package net.mcreator.vlabyss.init;

import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.mcreator.vlabyss.configuration.SsmpConfiguration;
import net.mcreator.vlabyss.VlAbyssMod;

@Mod.EventBusSubscriber(modid = VlAbyssMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VlAbyssModConfigs {
	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SsmpConfiguration.SPEC, "ssmp-common.toml");
		});
	}
}