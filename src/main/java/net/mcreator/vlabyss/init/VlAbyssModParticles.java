/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.vlabyss.client.particle.WhiteShockParticle;
import net.mcreator.vlabyss.client.particle.VentoParticula2Particle;
import net.mcreator.vlabyss.client.particle.VentoParticula1Particle;
import net.mcreator.vlabyss.client.particle.SlamCycloneParticle;
import net.mcreator.vlabyss.client.particle.ShockSwapParticleParticle;
import net.mcreator.vlabyss.client.particle.Particulamorreu2Particle;
import net.mcreator.vlabyss.client.particle.Particulamorreu1Particle;
import net.mcreator.vlabyss.client.particle.ParryDeuCertoParticle;
import net.mcreator.vlabyss.client.particle.LightningCloakParticleParticle;
import net.mcreator.vlabyss.client.particle.FlamingSlashParticle;
import net.mcreator.vlabyss.client.particle.ExplosaoVentoParticle;
import net.mcreator.vlabyss.client.particle.EletricOrbParticleParticle;
import net.mcreator.vlabyss.client.particle.EchoSlashParticleParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VlAbyssModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(VlAbyssModParticleTypes.PARRY_DEU_CERTO.get(), ParryDeuCertoParticle::provider);
		event.registerSpriteSet(VlAbyssModParticleTypes.PARTICULAMORREU_1.get(), Particulamorreu1Particle::provider);
		event.registerSpriteSet(VlAbyssModParticleTypes.PARTICULAMORREU_2.get(), Particulamorreu2Particle::provider);
		event.registerSpriteSet(VlAbyssModParticleTypes.VENTO_PARTICULA_2.get(), VentoParticula2Particle::provider);
		event.registerSpriteSet(VlAbyssModParticleTypes.VENTO_PARTICULA_1.get(), VentoParticula1Particle::provider);
		event.registerSpriteSet(VlAbyssModParticleTypes.FLAMING_SLASH.get(), FlamingSlashParticle::provider);
		event.registerSpriteSet(VlAbyssModParticleTypes.EXPLOSAO_VENTO.get(), ExplosaoVentoParticle::provider);
		event.registerSpriteSet(VlAbyssModParticleTypes.SLAM_CYCLONE.get(), SlamCycloneParticle::provider);
		event.registerSpriteSet(VlAbyssModParticleTypes.WHITE_SHOCK.get(), WhiteShockParticle::provider);
		event.registerSpriteSet(VlAbyssModParticleTypes.LIGHTNING_CLOAK_PARTICLE.get(), LightningCloakParticleParticle::provider);
		event.registerSpriteSet(VlAbyssModParticleTypes.SHOCK_SWAP_PARTICLE.get(), ShockSwapParticleParticle::provider);
		event.registerSpriteSet(VlAbyssModParticleTypes.ELETRIC_ORB_PARTICLE.get(), EletricOrbParticleParticle::provider);
		event.registerSpriteSet(VlAbyssModParticleTypes.ECHO_SLASH_PARTICLE.get(), EchoSlashParticleParticle::provider);
	}
}