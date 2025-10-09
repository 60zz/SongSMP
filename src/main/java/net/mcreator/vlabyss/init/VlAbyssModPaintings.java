/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.entity.decoration.PaintingVariant;

import net.mcreator.vlabyss.VlAbyssMod;

public class VlAbyssModPaintings {
	public static final DeferredRegister<PaintingVariant> REGISTRY = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, VlAbyssMod.MODID);
	public static final RegistryObject<PaintingVariant> ABYSS_PAINTING = REGISTRY.register("abyss_painting", () -> new PaintingVariant(32, 32));
}