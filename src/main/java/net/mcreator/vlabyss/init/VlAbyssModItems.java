/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.item.ItemProperties;

import net.mcreator.vlabyss.procedures.RunasPropertyValueProviderProcedure;
import net.mcreator.vlabyss.procedures.RunasPropertyValueProvider7Procedure;
import net.mcreator.vlabyss.procedures.RunasPropertyValueProvider6Procedure;
import net.mcreator.vlabyss.procedures.RunasPropertyValueProvider5Procedure;
import net.mcreator.vlabyss.procedures.RunasPropertyValueProvider4Procedure;
import net.mcreator.vlabyss.procedures.RunasPropertyValueProvider3Procedure;
import net.mcreator.vlabyss.procedures.RunasPropertyValueProvider2Procedure;
import net.mcreator.vlabyss.item.VotoSilenciosoItem;
import net.mcreator.vlabyss.item.RunasItem;
import net.mcreator.vlabyss.item.RawAbyssionItem;
import net.mcreator.vlabyss.item.PocaoVidaExtraItem;
import net.mcreator.vlabyss.item.PocaoResistenciaEternaItem;
import net.mcreator.vlabyss.item.PocaoRenascimentoItem;
import net.mcreator.vlabyss.item.PocaoRejuvenescimentoItem;
import net.mcreator.vlabyss.item.MapaAbyssItem;
import net.mcreator.vlabyss.item.LuvasLuaSangrentaItem;
import net.mcreator.vlabyss.item.LuvasIronsingItem;
import net.mcreator.vlabyss.item.LiberacaoMantraItem;
import net.mcreator.vlabyss.item.FragmentoPrimordialItem;
import net.mcreator.vlabyss.item.FaixaAcolitoItem;
import net.mcreator.vlabyss.item.EssenciaAbismoItem;
import net.mcreator.vlabyss.item.DesbloqueioEsquivaItem;
import net.mcreator.vlabyss.item.CintoDosEcosItem;
import net.mcreator.vlabyss.item.CapuzEsquecidoItem;
import net.mcreator.vlabyss.item.BotasVazioAbyssalItem;
import net.mcreator.vlabyss.item.ArtifactsBundleItem;
import net.mcreator.vlabyss.item.AnelEnferrujadoItem;
import net.mcreator.vlabyss.item.AmuletoVesperianItem;
import net.mcreator.vlabyss.item.AmuletoSangueItem;
import net.mcreator.vlabyss.item.AdagaAssassinosItem;
import net.mcreator.vlabyss.item.AbyssionNuggetItem;
import net.mcreator.vlabyss.item.AbyssionIngotItem;
import net.mcreator.vlabyss.block.display.PortaAbyssDisplayItem;
import net.mcreator.vlabyss.VlAbyssMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VlAbyssModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, VlAbyssMod.MODID);
	public static final RegistryObject<Item> MAPA_ABYSS = REGISTRY.register("mapa_abyss", () -> new MapaAbyssItem());
	public static final RegistryObject<Item> VOTO_SILENCIOSO = REGISTRY.register("voto_silencioso", () -> new VotoSilenciosoItem());
	public static final RegistryObject<Item> AMULETO_VESPERIAN = REGISTRY.register("amuleto_vesperian", () -> new AmuletoVesperianItem());
	public static final RegistryObject<Item> AMULETO_SANGUE = REGISTRY.register("amuleto_sangue", () -> new AmuletoSangueItem());
	public static final RegistryObject<Item> ABYSSION_ORE = block(VlAbyssModBlocks.ABYSSION_ORE);
	public static final RegistryObject<Item> RAW_ABYSSION = REGISTRY.register("raw_abyssion", () -> new RawAbyssionItem());
	public static final RegistryObject<Item> ABYSSION_INGOT = REGISTRY.register("abyssion_ingot", () -> new AbyssionIngotItem());
	public static final RegistryObject<Item> ABYSSION_NUGGET = REGISTRY.register("abyssion_nugget", () -> new AbyssionNuggetItem());
	public static final RegistryObject<Item> RAW_ABYSSION_BLOCK = block(VlAbyssModBlocks.RAW_ABYSSION_BLOCK);
	public static final RegistryObject<Item> ABYSSION_INGOT_BLOCK = block(VlAbyssModBlocks.ABYSSION_INGOT_BLOCK);
	public static final RegistryObject<Item> LUVAS_IRONSING = REGISTRY.register("luvas_ironsing", () -> new LuvasIronsingItem());
	public static final RegistryObject<Item> LUVAS_LUA_SANGRENTA = REGISTRY.register("luvas_lua_sangrenta", () -> new LuvasLuaSangrentaItem());
	public static final RegistryObject<Item> ANEL_ENFERRUJADO = REGISTRY.register("anel_enferrujado", () -> new AnelEnferrujadoItem());
	public static final RegistryObject<Item> CINTO_DOS_ECOS = REGISTRY.register("cinto_dos_ecos", () -> new CintoDosEcosItem());
	public static final RegistryObject<Item> FAIXA_ACOLITO = REGISTRY.register("faixa_acolito", () -> new FaixaAcolitoItem());
	public static final RegistryObject<Item> BOTAS_VAZIO_ABYSSAL = REGISTRY.register("botas_vazio_abyssal", () -> new BotasVazioAbyssalItem());
	public static final RegistryObject<Item> FRAGMENTO_PRIMORDIAL = REGISTRY.register("fragmento_primordial", () -> new FragmentoPrimordialItem());
	public static final RegistryObject<Item> LIBERACAO_MANTRA = REGISTRY.register("liberacao_mantra", () -> new LiberacaoMantraItem());
	public static final RegistryObject<Item> DESBLOQUEIO_ESQUIVA = REGISTRY.register("desbloqueio_esquiva", () -> new DesbloqueioEsquivaItem());
	public static final RegistryObject<Item> ADAGA_ASSASSINOS = REGISTRY.register("adaga_assassinos", () -> new AdagaAssassinosItem());
	public static final RegistryObject<Item> POCAO_REJUVENESCIMENTO = REGISTRY.register("pocao_rejuvenescimento", () -> new PocaoRejuvenescimentoItem());
	public static final RegistryObject<Item> POCAO_RESISTENCIA_ETERNA = REGISTRY.register("pocao_resistencia_eterna", () -> new PocaoResistenciaEternaItem());
	public static final RegistryObject<Item> POCAO_VIDA_EXTRA = REGISTRY.register("pocao_vida_extra", () -> new PocaoVidaExtraItem());
	public static final RegistryObject<Item> POCAO_RENASCIMENTO = REGISTRY.register("pocao_renascimento", () -> new PocaoRenascimentoItem());
	public static final RegistryObject<Item> PORTA_ABYSS = REGISTRY.register(VlAbyssModBlocks.PORTA_ABYSS.getId().getPath(), () -> new PortaAbyssDisplayItem(VlAbyssModBlocks.PORTA_ABYSS.get(), new Item.Properties()));
	public static final RegistryObject<Item> RUNAS = REGISTRY.register("runas", () -> new RunasItem());
	public static final RegistryObject<Item> CAVALEIRO_ALADO_SPAWN_EGG = REGISTRY.register("cavaleiro_alado_spawn_egg", () -> new ForgeSpawnEggItem(VlAbyssModEntities.CAVALEIRO_ALADO, -16777216, -6710887, new Item.Properties()));
	public static final RegistryObject<Item> ESSENCIA_ABISMO = REGISTRY.register("essencia_abismo", () -> new EssenciaAbismoItem());
	public static final RegistryObject<Item> PORTA_ABYSS_SLAVE = block(VlAbyssModBlocks.PORTA_ABYSS_SLAVE);
	public static final RegistryObject<Item> TOMBSTONE_ABYSS = block(VlAbyssModBlocks.TOMBSTONE_ABYSS);
	public static final RegistryObject<Item> METAL_FORGING = block(VlAbyssModBlocks.METAL_FORGING);
	public static final RegistryObject<Item> CAPUZ_ESQUECIDO = REGISTRY.register("capuz_esquecido", () -> new CapuzEsquecidoItem());
	public static final RegistryObject<Item> ARTIFACTS_BUNDLE = REGISTRY.register("artifacts_bundle", () -> new ArtifactsBundleItem());

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}

	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ItemProperties.register(RUNAS.get(), new ResourceLocation("vl_abyss:runas_runaazul"), (itemStackToRender, clientWorld, entity, itemEntityId) -> (float) RunasPropertyValueProviderProcedure.execute(itemStackToRender));
			ItemProperties.register(RUNAS.get(), new ResourceLocation("vl_abyss:runas_runaroxa"), (itemStackToRender, clientWorld, entity, itemEntityId) -> (float) RunasPropertyValueProvider2Procedure.execute(itemStackToRender));
			ItemProperties.register(RUNAS.get(), new ResourceLocation("vl_abyss:runas_runavermelha"), (itemStackToRender, clientWorld, entity, itemEntityId) -> (float) RunasPropertyValueProvider3Procedure.execute(itemStackToRender));
			ItemProperties.register(RUNAS.get(), new ResourceLocation("vl_abyss:runas_runaverde"), (itemStackToRender, clientWorld, entity, itemEntityId) -> (float) RunasPropertyValueProvider4Procedure.execute(itemStackToRender));
			ItemProperties.register(RUNAS.get(), new ResourceLocation("vl_abyss:runas_runaamarela"), (itemStackToRender, clientWorld, entity, itemEntityId) -> (float) RunasPropertyValueProvider5Procedure.execute(itemStackToRender));
			ItemProperties.register(RUNAS.get(), new ResourceLocation("vl_abyss:runas_runapreta"), (itemStackToRender, clientWorld, entity, itemEntityId) -> (float) RunasPropertyValueProvider6Procedure.execute(itemStackToRender));
			ItemProperties.register(RUNAS.get(), new ResourceLocation("vl_abyss:runas_runaciana"), (itemStackToRender, clientWorld, entity, itemEntityId) -> (float) RunasPropertyValueProvider7Procedure.execute(itemStackToRender));
		});
	}
}