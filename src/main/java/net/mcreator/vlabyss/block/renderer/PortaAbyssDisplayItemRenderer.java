package net.mcreator.vlabyss.block.renderer;

import software.bernie.geckolib.renderer.GeoItemRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.vlabyss.block.model.PortaAbyssDisplayModel;
import net.mcreator.vlabyss.block.display.PortaAbyssDisplayItem;

public class PortaAbyssDisplayItemRenderer extends GeoItemRenderer<PortaAbyssDisplayItem> {
	public PortaAbyssDisplayItemRenderer() {
		super(new PortaAbyssDisplayModel());
	}

	@Override
	public RenderType getRenderType(PortaAbyssDisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
