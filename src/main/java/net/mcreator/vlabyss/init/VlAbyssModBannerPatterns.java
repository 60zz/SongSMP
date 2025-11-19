/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.core.registries.Registries;

import net.mcreator.vlabyss.VlAbyssMod;

public class VlAbyssModBannerPatterns {
	public static final DeferredRegister<BannerPattern> REGISTRY = DeferredRegister.create(Registries.BANNER_PATTERN, VlAbyssMod.MODID);
	public static final RegistryObject<BannerPattern> ABYSS = REGISTRY.register("abyss", () -> new BannerPattern("vl_abyss:abyss"));
	public static final RegistryObject<BannerPattern> DEPTHS = REGISTRY.register("depths", () -> new BannerPattern("vl_abyss:depths"));
	public static final RegistryObject<BannerPattern> WILDERNES = REGISTRY.register("wildernes", () -> new BannerPattern("vl_abyss:wildernes"));
	public static final RegistryObject<BannerPattern> NYVATH = REGISTRY.register("nyvath", () -> new BannerPattern("vl_abyss:nyvath"));
	public static final RegistryObject<BannerPattern> NOCTHARIS = REGISTRY.register("noctharis", () -> new BannerPattern("vl_abyss:noctharis"));
	public static final RegistryObject<BannerPattern> VELMORRA = REGISTRY.register("velmorra", () -> new BannerPattern("vl_abyss:velmorra"));
}