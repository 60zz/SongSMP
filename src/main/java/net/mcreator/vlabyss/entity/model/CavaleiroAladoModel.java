package net.mcreator.vlabyss.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.vlabyss.entity.CavaleiroAladoEntity;

public class CavaleiroAladoModel extends GeoModel<CavaleiroAladoEntity> {
	@Override
	public ResourceLocation getAnimationResource(CavaleiroAladoEntity entity) {
		return new ResourceLocation("vl_abyss", "animations/cavaleiro_alado.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(CavaleiroAladoEntity entity) {
		return new ResourceLocation("vl_abyss", "geo/cavaleiro_alado.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(CavaleiroAladoEntity entity) {
		return new ResourceLocation("vl_abyss", "textures/entities/" + entity.getTexture() + ".png");
	}

}