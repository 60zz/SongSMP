/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.mcreator.vlabyss.block.entity.TombstoneAbyssBlockEntity;
import net.mcreator.vlabyss.block.entity.PortaAbyssTileEntity;
import net.mcreator.vlabyss.block.entity.PortaAbyssSlaveBlockEntity;
import net.mcreator.vlabyss.block.entity.MetalForgingBlockEntity;
import net.mcreator.vlabyss.VlAbyssMod;

public class VlAbyssModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, VlAbyssMod.MODID);
	public static final RegistryObject<BlockEntityType<PortaAbyssTileEntity>> PORTA_ABYSS = REGISTRY.register("porta_abyss", () -> BlockEntityType.Builder.of(PortaAbyssTileEntity::new, VlAbyssModBlocks.PORTA_ABYSS.get()).build(null));
	public static final RegistryObject<BlockEntityType<?>> PORTA_ABYSS_SLAVE = register("porta_abyss_slave", VlAbyssModBlocks.PORTA_ABYSS_SLAVE, PortaAbyssSlaveBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> TOMBSTONE_ABYSS = register("tombstone_abyss", VlAbyssModBlocks.TOMBSTONE_ABYSS, TombstoneAbyssBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> METAL_FORGING = register("metal_forging", VlAbyssModBlocks.METAL_FORGING, MetalForgingBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}