/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.mcreator.vlabyss.potion.ShadowCopyMobEffect;
import net.mcreator.vlabyss.potion.SangrandoMobEffect;
import net.mcreator.vlabyss.potion.ResistenciaQuebradaMobEffect;
import net.mcreator.vlabyss.potion.RegenMantraMobEffect;
import net.mcreator.vlabyss.potion.QuebraMobEffect;
import net.mcreator.vlabyss.potion.InstantRegenMantraMobEffect;
import net.mcreator.vlabyss.potion.InsanoMobEffect;
import net.mcreator.vlabyss.potion.CortaRegenMobEffect;
import net.mcreator.vlabyss.potion.CooldownRespiroMobEffect;
import net.mcreator.vlabyss.VlAbyssMod;

public class VlAbyssModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, VlAbyssMod.MODID);
	public static final RegistryObject<MobEffect> SANGRANDO = REGISTRY.register("sangrando", () -> new SangrandoMobEffect());
	public static final RegistryObject<MobEffect> CORTA_REGEN = REGISTRY.register("corta_regen", () -> new CortaRegenMobEffect());
	public static final RegistryObject<MobEffect> QUEBRA = REGISTRY.register("quebra", () -> new QuebraMobEffect());
	public static final RegistryObject<MobEffect> RESISTENCIA_QUEBRADA = REGISTRY.register("resistencia_quebrada", () -> new ResistenciaQuebradaMobEffect());
	public static final RegistryObject<MobEffect> COOLDOWN_RESPIRO = REGISTRY.register("cooldown_respiro", () -> new CooldownRespiroMobEffect());
	public static final RegistryObject<MobEffect> INSANO = REGISTRY.register("insano", () -> new InsanoMobEffect());
	public static final RegistryObject<MobEffect> SHADOW_COPY = REGISTRY.register("shadow_copy", () -> new ShadowCopyMobEffect());
	public static final RegistryObject<MobEffect> INSTANT_REGEN_MANTRA = REGISTRY.register("instant_regen_mantra", () -> new InstantRegenMantraMobEffect());
	public static final RegistryObject<MobEffect> REGEN_MANTRA = REGISTRY.register("regen_mantra", () -> new RegenMantraMobEffect());
}