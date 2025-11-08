package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

public class HabilidadePrimariaOnKeyPressedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Entity target = null;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).hab_selecionada == 1) {
			ChamaPrimeiraHabilidadeProcedure.execute(world, x, y, z, entity);
			RespiroPrimeiraHabilidadeProcedure.execute(world, x, y, z, entity);
			EscuridaoPrimeiraHabilidadeProcedure.execute(world, x, y, z, entity);
			LuzPrimeiraHabilidadeProcedure.execute(world, x, y, z, entity);
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).hab_selecionada == 2) {
			RespiroSegundaHabilidadeProcedure.execute(world, x, y, z, entity);
			LuzSegundaHabilidadeProcedure.execute(world, x, y, z, entity);
		}
	}
}