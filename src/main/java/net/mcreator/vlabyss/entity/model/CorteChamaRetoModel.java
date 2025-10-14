package net.mcreator.vlabyss.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.vlabyss.entity.CorteChamaRetoEntity;

public class CorteChamaRetoModel extends GeoModel<CorteChamaRetoEntity> {
	@Override
	public ResourceLocation getAnimationResource(CorteChamaRetoEntity entity) {
		return new ResourceLocation("vl_abyss", "animations/corte_reto.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(CorteChamaRetoEntity entity) {
		return new ResourceLocation("vl_abyss", "geo/corte_reto.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(CorteChamaRetoEntity entity) {
		return new ResourceLocation("vl_abyss", "textures/entities/" + entity.getTexture() + ".png");
	}

}