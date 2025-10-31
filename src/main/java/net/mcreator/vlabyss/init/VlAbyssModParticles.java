/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.vlabyss.client.particle.VentoParticula2Particle;
import net.mcreator.vlabyss.client.particle.VentoParticula1Particle;
import net.mcreator.vlabyss.client.particle.Particulamorreu2Particle;
import net.mcreator.vlabyss.client.particle.Particulamorreu1Particle;
import net.mcreator.vlabyss.client.particle.ParryDeuCertoParticle;
import net.mcreator.vlabyss.client.particle.FlamingSlashParticle;

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
	}
}