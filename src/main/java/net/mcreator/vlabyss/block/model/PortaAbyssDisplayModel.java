package net.mcreator.vlabyss.block.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.vlabyss.block.display.PortaAbyssDisplayItem;

public class PortaAbyssDisplayModel extends GeoModel<PortaAbyssDisplayItem> {
	@Override
	public ResourceLocation getAnimationResource(PortaAbyssDisplayItem animatable) {
		return new ResourceLocation("vl_abyss", "animations/portaozika.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(PortaAbyssDisplayItem animatable) {
		return new ResourceLocation("vl_abyss", "geo/portaozika.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(PortaAbyssDisplayItem entity) {
		return new ResourceLocation("vl_abyss", "textures/block/porta.png");
	}
}
