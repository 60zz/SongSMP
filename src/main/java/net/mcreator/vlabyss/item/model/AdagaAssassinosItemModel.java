package net.mcreator.vlabyss.item.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.vlabyss.item.AdagaAssassinosItem;

public class AdagaAssassinosItemModel extends GeoModel<AdagaAssassinosItem> {
	@Override
	public ResourceLocation getAnimationResource(AdagaAssassinosItem animatable) {
		return new ResourceLocation("vl_abyss", "animations/adaga_assassina.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(AdagaAssassinosItem animatable) {
		return new ResourceLocation("vl_abyss", "geo/adaga_assassina.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(AdagaAssassinosItem animatable) {
		return new ResourceLocation("vl_abyss", "textures/item/adagaassassinotxt.png");
	}
}
