package net.mcreator.vlabyss.block.renderer;

import software.bernie.geckolib.renderer.GeoBlockRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.vlabyss.block.model.PortaAbyssBlockModel;
import net.mcreator.vlabyss.block.entity.PortaAbyssTileEntity;

public class PortaAbyssTileRenderer extends GeoBlockRenderer<PortaAbyssTileEntity> {
	public PortaAbyssTileRenderer() {
		super(new PortaAbyssBlockModel());
	}

	public int getViewDistance() {
		return 256;
	}

	@Override
	public RenderType getRenderType(PortaAbyssTileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
