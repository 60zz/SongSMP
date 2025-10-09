/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.vlabyss.VlAbyssMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VlAbyssModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VlAbyssMod.MODID);
	public static final RegistryObject<CreativeModeTab> VL_ABYSS = REGISTRY.register("vl_abyss",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.vl_abyss.vl_abyss")).icon(() -> new ItemStack(VlAbyssModItems.MAPA_ABYSS.get())).displayItems((parameters, tabData) -> {
				tabData.accept(VlAbyssModItems.MAPA_ABYSS.get());
				tabData.accept(VlAbyssModItems.CAPUZ_ESQUECIDO.get());
				tabData.accept(VlAbyssModItems.VOTO_SILENCIOSO.get());
				tabData.accept(VlAbyssModItems.AMULETO_VESPERIAN.get());
				tabData.accept(VlAbyssModItems.AMULETO_SANGUE.get());
				tabData.accept(VlAbyssModBlocks.ABYSSION_ORE.get().asItem());
				tabData.accept(VlAbyssModItems.RAW_ABYSSION.get());
				tabData.accept(VlAbyssModItems.ABYSSION_INGOT.get());
				tabData.accept(VlAbyssModItems.ABYSSION_NUGGET.get());
				tabData.accept(VlAbyssModBlocks.RAW_ABYSSION_BLOCK.get().asItem());
				tabData.accept(VlAbyssModBlocks.ABYSSION_INGOT_BLOCK.get().asItem());
				tabData.accept(VlAbyssModItems.LUVAS_IRONSING.get());
				tabData.accept(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get());
				tabData.accept(VlAbyssModItems.ANEL_ENFERRUJADO.get());
				tabData.accept(VlAbyssModItems.CINTO_DOS_ECOS.get());
				tabData.accept(VlAbyssModItems.FAIXA_ACOLITO.get());
				tabData.accept(VlAbyssModItems.BOTAS_VAZIO_ABYSSAL.get());
				tabData.accept(VlAbyssModItems.FRAGMENTO_PRIMORDIAL.get());
				tabData.accept(VlAbyssModItems.LIBERACAO_MANTRA.get());
				tabData.accept(VlAbyssModItems.DESBLOQUEIO_ESQUIVA.get());
				tabData.accept(VlAbyssModItems.POCAO_REJUVENESCIMENTO.get());
				tabData.accept(VlAbyssModItems.POCAO_RESISTENCIA_ETERNA.get());
				tabData.accept(VlAbyssModItems.POCAO_VIDA_EXTRA.get());
				tabData.accept(VlAbyssModItems.POCAO_RENASCIMENTO.get());
				tabData.accept(VlAbyssModItems.RUNAS.get());
				tabData.accept(VlAbyssModItems.CAVALEIRO_ALADO_SPAWN_EGG.get());
				tabData.accept(VlAbyssModItems.ESSENCIA_ABISMO.get());
				tabData.accept(VlAbyssModBlocks.TOMBSTONE_ABYSS.get().asItem());
				tabData.accept(VlAbyssModBlocks.METAL_FORGING.get().asItem());
			}).build());
	public static final RegistryObject<CreativeModeTab> VL_ABYSS_ARMAS = REGISTRY.register("vl_abyss_armas",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.vl_abyss.vl_abyss_armas")).icon(() -> new ItemStack(VlAbyssModItems.ADAGA_ASSASSINOS.get())).displayItems((parameters, tabData) -> {
				tabData.accept(VlAbyssModItems.ADAGA_ASSASSINOS.get());
			}).withTabsBefore(VL_ABYSS.getId()).build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			tabData.accept(VlAbyssModBlocks.PORTA_ABYSS.get().asItem());
		}
	}
}