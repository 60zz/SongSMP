package net.mcreator.vlabyss.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.vlabyss.entity.CloneEntity;
import net.mcreator.vlabyss.client.model.Modelstivi;

public class CloneRenderer extends MobRenderer<CloneEntity, Modelstivi<CloneEntity>> {
	public CloneRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelstivi<CloneEntity>(context.bakeLayer(Modelstivi.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(CloneEntity entity) {
		return ResourceLocation.parse("vl_abyss:textures/entities/clone.png");
	}
}