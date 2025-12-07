package net.mcreator.vlabyss.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.vlabyss.entity.FireEruptionEntity;
import net.mcreator.vlabyss.client.model.Modelfireeruptionentity;

public class FireEruptionRenderer extends MobRenderer<FireEruptionEntity, Modelfireeruptionentity<FireEruptionEntity>> {
	public FireEruptionRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelfireeruptionentity<FireEruptionEntity>(context.bakeLayer(Modelfireeruptionentity.LAYER_LOCATION)), 0f);
	}

	@Override
	public ResourceLocation getTextureLocation(FireEruptionEntity entity) {
		return ResourceLocation.parse("vl_abyss:textures/entities/bah.png");
	}

	@Override
	protected boolean isBodyVisible(FireEruptionEntity entity) {
		return false;
	}
}