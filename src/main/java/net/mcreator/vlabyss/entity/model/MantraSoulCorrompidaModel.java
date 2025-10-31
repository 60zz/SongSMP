package net.mcreator.vlabyss.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.vlabyss.entity.MantraSoulCorrompidaEntity;

public class MantraSoulCorrompidaModel extends GeoModel<MantraSoulCorrompidaEntity> {
	@Override
	public ResourceLocation getAnimationResource(MantraSoulCorrompidaEntity entity) {
		return new ResourceLocation("vl_abyss", "animations/alma.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(MantraSoulCorrompidaEntity entity) {
		return new ResourceLocation("vl_abyss", "geo/alma.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(MantraSoulCorrompidaEntity entity) {
		return new ResourceLocation("vl_abyss", "textures/entities/" + entity.getTexture() + ".png");
	}

}