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
	public static final RegistryObject<SimpleParticleType> FLAMING_SLASH = REGISTRY.register("flaming_slash", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> EXPLOSAO_VENTO = REGISTRY.register("explosao_vento", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> SLAM_CYCLONE = REGISTRY.register("slam_cyclone", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> WHITE_SHOCK = REGISTRY.register("white_shock", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> LIGHTNING_CLOAK_PARTICLE = REGISTRY.register("lightning_cloak_particle", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> SHOCK_SWAP_PARTICLE = REGISTRY.register("shock_swap_particle", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> ELETRIC_ORB_PARTICLE = REGISTRY.register("eletric_orb_particle", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> ECHO_SLASH_PARTICLE = REGISTRY.register("echo_slash_particle", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> BODY_FIRE_PARTICLE = REGISTRY.register("body_fire_particle", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> FIRE_ERUPTION_PARTICLE = REGISTRY.register("fire_eruption_particle", () -> new SimpleParticleType(false));
}