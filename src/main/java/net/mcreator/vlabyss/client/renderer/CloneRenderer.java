package net.mcreator.vlabyss.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.vlabyss.entity.CloneEntity;
import net.mcreator.vlabyss.client.model.Modelstivi;

import com.mojang.blaze3d.vertex.PoseStack;

public class CloneRenderer extends MobRenderer<CloneEntity, Modelstivi<CloneEntity>> {
	public CloneRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelstivi<CloneEntity>(context.bakeLayer(Modelstivi.LAYER_LOCATION)), 0.5f);
	}

	@Override
	protected void scale(CloneEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(entity.getScale(), entity.getScale(), entity.getScale());
	}

	@Override
	public ResourceLocation getTextureLocation(CloneEntity entity) {
		return ResourceLocation.parse("vl_abyss:textures/entities/clone.png");
	}
}