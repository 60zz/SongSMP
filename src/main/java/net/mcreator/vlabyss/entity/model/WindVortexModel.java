package net.mcreator.vlabyss.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.vlabyss.entity.WindVortexEntity;

public class WindVortexModel extends GeoModel<WindVortexEntity> {
	@Override
	public ResourceLocation getAnimationResource(WindVortexEntity entity) {
		return new ResourceLocation("vl_abyss", "animations/vortex.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(WindVortexEntity entity) {
		return new ResourceLocation("vl_abyss", "geo/vortex.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(WindVortexEntity entity) {
		return new ResourceLocation("vl_abyss", "textures/entities/" + entity.getTexture() + ".png");
	}

}