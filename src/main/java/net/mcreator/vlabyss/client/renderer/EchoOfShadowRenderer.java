package net.mcreator.vlabyss.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.vlabyss.entity.EchoOfShadowEntity;
import net.mcreator.vlabyss.client.model.Modelechoofshadows;

import com.mojang.blaze3d.vertex.PoseStack;

public class EchoOfShadowRenderer extends MobRenderer<EchoOfShadowEntity, Modelechoofshadows<EchoOfShadowEntity>> {
	public EchoOfShadowRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelechoofshadows<EchoOfShadowEntity>(context.bakeLayer(Modelechoofshadows.LAYER_LOCATION)), 0f);
	}

	@Override
	protected void scale(EchoOfShadowEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(3.5f, 3.5f, 3.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(EchoOfShadowEntity entity) {
		return ResourceLocation.parse("vl_abyss:textures/entities/bah.png");
	}

	@Override
	protected boolean isBodyVisible(EchoOfShadowEntity entity) {
		return false;
	}
}