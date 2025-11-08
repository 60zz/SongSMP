package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModAttributes;
import net.mcreator.vlabyss.configuration.SsmpConfiguration;

import java.util.UUID;

public class CapuzEsquecidoBaubleIsUnequippedProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Capridel == true)) {
			entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:absorve_alma_damage")))),
					(float) (double) SsmpConfiguration.CAPUZESQUECIDOCONFIG2.get());
			if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
					.hasModifier((new AttributeModifier(UUID.fromString("d57f7e7e-8fcd-4615-9f35-5daa08a82cec"), "vida", ((double) SsmpConfiguration.CAPUZESQUECIDOCONFIG2.get()), AttributeModifier.Operation.ADDITION)))))
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
						.addTransientModifier((new AttributeModifier(UUID.fromString("d57f7e7e-8fcd-4615-9f35-5daa08a82cec"), "vida", ((double) SsmpConfiguration.CAPUZESQUECIDOCONFIG2.get()), AttributeModifier.Operation.ADDITION)));
		} else {
			if (!(((LivingEntity) entity).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
					.hasModifier((new AttributeModifier(UUID.fromString("cb783c75-31ff-4f23-a5fa-8aac9958d94b"), "maxint", ((double) SsmpConfiguration.CAPUZESQUECIDOCONFIG.get()), AttributeModifier.Operation.ADDITION)))))
				((LivingEntity) entity).getAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
						.addTransientModifier((new AttributeModifier(UUID.fromString("cb783c75-31ff-4f23-a5fa-8aac9958d94b"), "maxint", ((double) SsmpConfiguration.CAPUZESQUECIDOCONFIG.get()), AttributeModifier.Operation.ADDITION)));
		}
		entity.getPersistentData().putBoolean("capuzesquecido", true);
	}
}