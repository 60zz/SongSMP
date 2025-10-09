package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;

public class CoracaoScreenDisplayOverlayIngameProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		// SISTEMA DE FADE CORRETO - COMEÇANDO DO ZERO
		boolean shouldFade = true;
		float alpha;
		if (shouldFade) {
			// Fazer fade pulsante (0.3 a 1.0)
			long time = System.currentTimeMillis();
			double cycle = (time % 2500) / 2500.0; // 2.5 segundos por ciclo
			alpha = (float) (Math.sin(cycle * Math.PI * 2.0) * 0.35 + 0.65);
		} else {
			// Sem fade - alpha normal
			alpha = 1.0f;
		}
		// Aplicar apenas via RenderSystem (método mais seguro)
		com.mojang.blaze3d.systems.RenderSystem.enableBlend();
		com.mojang.blaze3d.systems.RenderSystem.defaultBlendFunc();
		com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, alpha);
		if (entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(VlAbyssModMobEffects.INSANO.get())) {
			return true;
		}
		return false;
	}
}