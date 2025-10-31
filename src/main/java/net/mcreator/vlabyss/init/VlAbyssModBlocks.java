/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.vlabyss.block.TombstoneAbyssBlock;
import net.mcreator.vlabyss.block.RawAbyssionBlockBlock;
import net.mcreator.vlabyss.block.PortaAbyssSlaveBlock;
import net.mcreator.vlabyss.block.PortaAbyssBlock;
import net.mcreator.vlabyss.block.MetalForgingBlock;
import net.mcreator.vlabyss.block.AbyssionOreBlock;
import net.mcreator.vlabyss.block.AbyssionIngotBlockBlock;
import net.mcreator.vlabyss.VlAbyssMod;

public class VlAbyssModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, VlAbyssMod.MODID);
	public static final RegistryObject<Block> ABYSSION_ORE = REGISTRY.register("abyssion_ore", AbyssionOreBlock::new);
	public static final RegistryObject<Block> RAW_ABYSSION_BLOCK = REGISTRY.register("raw_abyssion_block", RawAbyssionBlockBlock::new);
	public static final RegistryObject<Block> ABYSSION_INGOT_BLOCK = REGISTRY.register("abyssion_ingot_block", AbyssionIngotBlockBlock::new);
	public static final RegistryObject<Block> PORTA_ABYSS = REGISTRY.register("porta_abyss", PortaAbyssBlock::new);
	public static final RegistryObject<Block> PORTA_ABYSS_SLAVE = REGISTRY.register("porta_abyss_slave", PortaAbyssSlaveBlock::new);
	public static final RegistryObject<Block> TOMBSTONE_ABYSS = REGISTRY.register("tombstone_abyss", TombstoneAbyssBlock::new);
	public static final RegistryObject<Block> METAL_FORGING = REGISTRY.register("metal_forging", MetalForgingBlock::new);
	// Start of user code block custom blocks
	// End of user code block custom blocks
}