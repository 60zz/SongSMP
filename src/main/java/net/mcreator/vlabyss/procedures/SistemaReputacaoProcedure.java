package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class SistemaReputacaoProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(Entity entity, Entity sourceentity) {
		execute(null, entity, sourceentity);
	}

private static void execute(
@Nullable Event event,
Entity entity,
Entity sourceentity ) {
if (
entity == null ||
sourceentity == null ) return ;
if () {if (!((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).reputacao==-5)) {{
double _setval = (sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).reputacao-1;
sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
capability.reputacao = _setval;
capability.syncPlayerVariables(sourceentity);
});
}
}}else if (entity instanceof Player&&(entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).reputacao<0) {if (!((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).reputacao==10)) {{
double _setval = (sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).reputacao+2;
sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
capability.reputacao = _setval;
capability.syncPlayerVariables(sourceentity);
});
}
}else if ((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).reputacao==9) {{
double _setval = (sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).reputacao+1;
sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
capability.reputacao = _setval;
capability.syncPlayerVariables(sourceentity);
});
}
}}else if (entity instanceof Player&&(entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).reputacao>=0) {if (!((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).reputacao==-5)) {{
double _setval = (sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).reputacao-2;
sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
capability.reputacao = _setval;
capability.syncPlayerVariables(sourceentity);
});
}
}else if ((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).reputacao==-4) {{
double _setval = (sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).reputacao-(-1);
sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
capability.reputacao = _setval;
capability.syncPlayerVariables(sourceentity);
});
}
}}else if () {if (Math.random()>=0.8) {if (!((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).reputacao==10)) {{
double _setval = (sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).reputacao+1;
sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
capability.reputacao = _setval;
capability.syncPlayerVariables(sourceentity);
});
}
}}}
}
}