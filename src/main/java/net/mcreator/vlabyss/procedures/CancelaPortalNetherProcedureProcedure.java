package net.ncreator.vlarchives.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;


@Mod.EventBusSubscriber
public class CancelaPortalNetherProcedureProcedure {
	@SubscribeEvent
	public static void onBlockRightClick(PlayerInteractEvent.RightClickBlock event) {
		BlockState state = event.getLevel().getBlockState(event.getPos());
		// Verifica se o jogador está usando um isqueiro
		if (event.getItemStack().getItem() == net.minecraft.world.item.Items.FLINT_AND_STEEL) {
			// Verifica se o bloco clicado é obsidiana
			if (state.getBlock() == Blocks.OBSIDIAN) {
				// Permite que o isqueiro funcione normalmente
			}
		}
	}

	@SubscribeEvent
	public static void onPortalSpawn(BlockEvent.PortalSpawnEvent event) {
		// Cancela a criação do portal do Nether
		event.setCanceled(true);
	}
}
