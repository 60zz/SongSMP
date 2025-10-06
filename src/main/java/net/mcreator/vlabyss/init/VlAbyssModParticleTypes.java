/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.mcreator.vlabyss.VlAbyssMod;

public class VlAbyssModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, VlAbyssMod.MODID);
	public static final RegistryObject<SimpleParticleType> PARRY_DEU_CERTO = REGISTRY.register("parry_deu_certo", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> PARTICULAMORREU_1 = REGISTRY.register("particulamorreu_1", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> PARTICULAMORREU_2 = REGISTRY.register("particulamorreu_2", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> VENTO_PARTICULA_2 = REGISTRY.register("vento_particula_2", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> VENTO_PARTICULA_1 = REGISTRY.register("vento_particula_1", () -> new SimpleParticleType(false));
}