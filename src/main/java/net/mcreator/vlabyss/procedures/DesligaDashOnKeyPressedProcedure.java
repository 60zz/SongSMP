package net.mcreator.vlabyss.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

public class DesligaDashOnKeyPressedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (Minecraft.getInstance().options.keyShift.isDown()) {
			if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).desligadash == true) {
				{
					boolean _setval = false;
					entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.desligadash = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7b[ SYSTEM ] Dash Desligado"), false);
			} else {
				{
					boolean _setval = true;
					entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.desligadash = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7b[ SYSTEM ] Dash Ligado"), false);
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7b[ SYSTEM ] Para desligar o dash, voc\u00EA precisa segurar SHIFT"), false);
		}
	}
}