/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.mcreator.vlabyss.potion.StormReconjurationMobEffect;
import net.mcreator.vlabyss.potion.ShadowCopyMobEffect;
import net.mcreator.vlabyss.potion.SangrandoMobEffect;
import net.mcreator.vlabyss.potion.ResistenciaQuebradaMobEffect;
import net.mcreator.vlabyss.potion.RegenMantraMobEffect;
import net.mcreator.vlabyss.potion.ReconjurarMobEffect;
import net.mcreator.vlabyss.potion.QuebraMobEffect;
import net.mcreator.vlabyss.potion.ParalisadoMobEffect;
import net.mcreator.vlabyss.potion.MedoMobEffect;
import net.mcreator.vlabyss.potion.LightningCloakMobEffect;
import net.mcreator.vlabyss.potion.LastHopeMobEffect;
import net.mcreator.vlabyss.potion.InstantRegenMantraMobEffect;
import net.mcreator.vlabyss.potion.InsanoMobEffect;
import net.mcreator.vlabyss.potion.InsanidadeMobEffect;
import net.mcreator.vlabyss.potion.FlamingCutMobEffect;
import net.mcreator.vlabyss.potion.EssenceMantraMobEffect;
import net.mcreator.vlabyss.potion.EnfraquecidoMobEffect;
import net.mcreator.vlabyss.potion.ElixirMantraMobEffect;
import net.mcreator.vlabyss.potion.EletricTouchMobEffect;
import net.mcreator.vlabyss.potion.CycloneStormMobEffect;
import net.mcreator.vlabyss.potion.CortaRegenMobEffect;
import net.mcreator.vlabyss.potion.BurnSoulMobEffect;
import net.mcreator.vlabyss.VlAbyssMod;

public class VlAbyssModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, VlAbyssMod.MODID);
	public static final RegistryObject<MobEffect> SANGRANDO = REGISTRY.register("sangrando", () -> new SangrandoMobEffect());
	public static final RegistryObject<MobEffect> CORTA_REGEN = REGISTRY.register("corta_regen", () -> new CortaRegenMobEffect());
	public static final RegistryObject<MobEffect> QUEBRA = REGISTRY.register("quebra", () -> new QuebraMobEffect());
	public static final RegistryObject<MobEffect> RESISTENCIA_QUEBRADA = REGISTRY.register("resistencia_quebrada", () -> new ResistenciaQuebradaMobEffect());
	public static final RegistryObject<MobEffect> INSANO = REGISTRY.register("insano", () -> new InsanoMobEffect());
	public static final RegistryObject<MobEffect> SHADOW_COPY = REGISTRY.register("shadow_copy", () -> new ShadowCopyMobEffect());
	public static final RegistryObject<MobEffect> INSTANT_REGEN_MANTRA = REGISTRY.register("instant_regen_mantra", () -> new InstantRegenMantraMobEffect());
	public static final RegistryObject<MobEffect> REGEN_MANTRA = REGISTRY.register("regen_mantra", () -> new RegenMantraMobEffect());
	public static final RegistryObject<MobEffect> FLAMING_CUT = REGISTRY.register("flaming_cut", () -> new FlamingCutMobEffect());
	public static final RegistryObject<MobEffect> BURN_SOUL = REGISTRY.register("burn_soul", () -> new BurnSoulMobEffect());
	public static final RegistryObject<MobEffect> ENFRAQUECIDO = REGISTRY.register("enfraquecido", () -> new EnfraquecidoMobEffect());
	public static final RegistryObject<MobEffect> LAST_HOPE = REGISTRY.register("last_hope", () -> new LastHopeMobEffect());
	public static final RegistryObject<MobEffect> ELIXIR_MANTRA = REGISTRY.register("elixir_mantra", () -> new ElixirMantraMobEffect());
	public static final RegistryObject<MobEffect> ESSENCE_MANTRA = REGISTRY.register("essence_mantra", () -> new EssenceMantraMobEffect());
	public static final RegistryObject<MobEffect> MEDO = REGISTRY.register("medo", () -> new MedoMobEffect());
	public static final RegistryObject<MobEffect> INSANIDADE = REGISTRY.register("insanidade", () -> new InsanidadeMobEffect());
	public static final RegistryObject<MobEffect> STORM_RECONJURATION = REGISTRY.register("storm_reconjuration", () -> new StormReconjurationMobEffect());
	public static final RegistryObject<MobEffect> RECONJURAR = REGISTRY.register("reconjurar", () -> new ReconjurarMobEffect());
	public static final RegistryObject<MobEffect> CYCLONE_STORM = REGISTRY.register("cyclone_storm", () -> new CycloneStormMobEffect());
	public static final RegistryObject<MobEffect> LIGHTNING_CLOAK = REGISTRY.register("lightning_cloak", () -> new LightningCloakMobEffect());
	public static final RegistryObject<MobEffect> PARALISADO = REGISTRY.register("paralisado", () -> new ParalisadoMobEffect());
	public static final RegistryObject<MobEffect> ELETRIC_TOUCH = REGISTRY.register("eletric_touch", () -> new EletricTouchMobEffect());
}