package net.mcreator.vlabyss.block.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.vlabyss.block.entity.PortaAbyssTileEntity;

public class PortaAbyssBlockModel extends GeoModel<PortaAbyssTileEntity> {
	@Override
	public ResourceLocation getAnimationResource(PortaAbyssTileEntity animatable) {
		return new ResourceLocation("vl_abyss", "animations/portaozika.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(PortaAbyssTileEntity animatable) {
		return new ResourceLocation("vl_abyss", "geo/portaozika.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(PortaAbyssTileEntity animatable) {
		return new ResourceLocation("vl_abyss", "textures/block/porta.png");
	}
}
