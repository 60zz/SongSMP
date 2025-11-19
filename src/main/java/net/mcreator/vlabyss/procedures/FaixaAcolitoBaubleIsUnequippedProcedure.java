package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

public class FaixaAcolitoBaubleIsUnequippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			Entity _entity = entity;
			if (_entity instanceof LivingEntity _livingEntity) {
				Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("vl_abyss:inteligencia"));
				if (_attribute != null) {
					AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
					if (_attr != null) {
						_attr.getModifiers().forEach((_modifier) -> {
							if (_modifier.getName().equals("intacolito")) {
								_attr.removeModifier(_modifier);
							}
						});
					}
				}
			}
		}
		if (!((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Valmiriano == true)) {
			{
				Entity _ent = entity;
				if (!_ent.level().isClientSide() && _ent.getServer() != null) {
					_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
							_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "curios add hand @s 1");
				}
			}
		}
	}
}