package net.mcreator.vlabyss.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.vlabyss.entity.MantraSoulEntity;

public class MantraSoulModel extends GeoModel<MantraSoulEntity> {
	@Override
	public ResourceLocation getAnimationResource(MantraSoulEntity entity) {
		return new ResourceLocation("vl_abyss", "animations/alma.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(MantraSoulEntity entity) {
		return new ResourceLocation("vl_abyss", "geo/alma.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(MantraSoulEntity entity) {
		return new ResourceLocation("vl_abyss", "textures/entities/" + entity.getTexture() + ".png");
	}

}