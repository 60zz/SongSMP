package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.configuration.SsmpConfiguration;

public class CapuzEsquecidoBaubleIsUnequippedProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).Capridel == true)) {
			entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:mantra_damage")))),
					(float) (double) SsmpConfiguration.CAPUZESQUECIDOCONFIG2.get());
		} else {
		}
	}
}