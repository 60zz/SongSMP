package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ProcuradoMorreuProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(Entity entity, Entity sourceentity) {
		execute(null, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		ItemStack head = ItemStack.EMPTY;
		if (sourceentity instanceof Player) {
			if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).procurado == true) {
				head = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:player_head"))).copy();
				head.getOrCreateTag().putString("SkullOwner", (new Object() {
					public String getRealName(Entity entity) {
						if (entity == null)
							return "";
						if (entity instanceof net.minecraft.world.entity.player.Player _player)
							return _player.getGameProfile().getName();
						return entity.getType().getDescription().getString();
					}
				}.getRealName(entity)));
				head.getOrCreateTag().putString("procurado", (new Object() {
					public String getRealName(Entity entity) {
						if (entity == null)
							return "";
						if (entity instanceof net.minecraft.world.entity.player.Player _player)
							return _player.getGameProfile().getName();
						return entity.getType().getDescription().getString();
					}
				}.getRealName(entity)));
				head.getOrCreateTag().putBoolean("procuradinho", true);
				entity.spawnAtLocation(head);
				{
					boolean _setval = false;
					entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.procurado = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).vidas - 1;
					entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.vidas = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}