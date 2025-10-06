package net.mcreator.vlabyss.block;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;

import net.mcreator.vlabyss.init.VlAbyssModBlocks;

public class PortaAbyssMultiblockHelper {
    
    // Dimensões do multi-block: 5 blocos de largura, 7 blocos de altura
    public static final int WIDTH = 5;
    public static final int HEIGHT = 7;
    public static final int MASTER_X_OFFSET = 2; // Master no centro (posição 2 de 0-4)
    public static final int MASTER_Y_OFFSET = 0; // Master na base

    /**
     * Verifica se a estrutura multi-block está completa e válida
     */
    public static boolean validateMultiblock(Level world, BlockPos masterPos, Direction facing) {
        BlockPos[][] positions = getMultiblockPositions(masterPos, facing);
        
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                BlockPos checkPos = positions[x][y];
                BlockState state = world.getBlockState(checkPos);
                
                // Posição do Master
                if (x == MASTER_X_OFFSET && y == MASTER_Y_OFFSET) {
                    if (!state.is(VlAbyssModBlocks.PORTA_ABYSS.get())) {
                        return false;
                    }
                } else {
                    // Posições dos Slaves
                    if (!state.is(VlAbyssModBlocks.PORTA_ABYSS_SLAVE.get())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Cria a estrutura multi-block completa
     */
    public static boolean createMultiblock(Level world, BlockPos masterPos, Direction facing) {
        if (validateMultiblock(world, masterPos, facing)) {
            return true; // Já existe
        }

        // Verifica se há espaço livre
        if (!hasSpaceForMultiblock(world, masterPos, facing)) {
            return false;
        }

        BlockPos[][] positions = getMultiblockPositions(masterPos, facing);
        
        // Coloca os blocos Slave
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                // Pula a posição do Master
                if (x == MASTER_X_OFFSET && y == MASTER_Y_OFFSET) {
                    continue;
                }
                
                BlockPos slavePos = positions[x][y];
                BlockState slaveState = VlAbyssModBlocks.PORTA_ABYSS_SLAVE.get().defaultBlockState()
                    .setValue(PortaAbyssSlaveBlock.FACING, facing);
                
                world.setBlock(slavePos, slaveState, 3);
            }
        }

        // Marca o Master como "multi-block ativo"
        BlockEntity masterEntity = world.getBlockEntity(masterPos);
        if (masterEntity != null) {
            masterEntity.getPersistentData().putBoolean("multiblock_ativo", true);
            masterEntity.setChanged();
        }

        return true;
    }

    /**
     * Destroi a estrutura multi-block completa
     */
    public static void destroyMultiblock(Level world, BlockPos masterPos, Direction facing) {
        BlockPos[][] positions = getMultiblockPositions(masterPos, facing);
        
        // Remove todos os blocos Slave
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                // Pula a posição do Master
                if (x == MASTER_X_OFFSET && y == MASTER_Y_OFFSET) {
                    continue;
                }
                
                BlockPos slavePos = positions[x][y];
                if (world.getBlockState(slavePos).is(VlAbyssModBlocks.PORTA_ABYSS_SLAVE.get())) {
                    world.setBlock(slavePos, Blocks.AIR.defaultBlockState(), 3);
                }
            }
        }

        // Marca o Master como "multi-block inativo"
        BlockEntity masterEntity = world.getBlockEntity(masterPos);
        if (masterEntity != null) {
            masterEntity.getPersistentData().putBoolean("multiblock_ativo", false);
            masterEntity.setChanged();
        }
    }

    /**
     * Verifica se há espaço livre para criar o multi-block
     */
    private static boolean hasSpaceForMultiblock(Level world, BlockPos masterPos, Direction facing) {
        BlockPos[][] positions = getMultiblockPositions(masterPos, facing);
        
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                // Pula a posição do Master (já ocupada)
                if (x == MASTER_X_OFFSET && y == MASTER_Y_OFFSET) {
                    continue;
                }
                
                BlockPos checkPos = positions[x][y];
                BlockState state = world.getBlockState(checkPos);
                
                // Verifica se a posição está livre (ar ou blocos substituíveis)
                if (!state.isAir() && !state.canBeReplaced()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Calcula todas as posições do multi-block baseado na posição do Master e orientação
     */
    public static BlockPos[][] getMultiblockPositions(BlockPos masterPos, Direction facing) {
        BlockPos[][] positions = new BlockPos[WIDTH][HEIGHT];
        
        // Calcula offsets baseados na direção
        int[] xOffset = getXOffsets(facing);
        int[] zOffset = getZOffsets(facing);
        
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                int localX = x - MASTER_X_OFFSET; // Centraliza no Master
                positions[x][y] = masterPos.offset(
                    localX * xOffset[0] + 0 * zOffset[0], // X
                    y,                                     // Y
                    localX * xOffset[1] + 0 * zOffset[1]  // Z
                );
            }
        }
        
        return positions;
    }

    /**
     * Encontra a posição do Master baseado na posição de um Slave
     */
    public static BlockPos getMasterPosition(Level world, BlockPos slavePos, Direction facing) {
        // Testa todas as possíveis posições de Master em um raio
        for (int testX = -MASTER_X_OFFSET; testX <= WIDTH - MASTER_X_OFFSET - 1; testX++) {
            for (int testY = 0; testY < HEIGHT; testY++) {
                int[] xOffset = getXOffsets(facing);
                int[] zOffset = getZOffsets(facing);
                
                BlockPos testMasterPos = slavePos.offset(
                    -testX * xOffset[0],
                    -testY,
                    -testX * xOffset[1]
                );
                
                // Verifica se essa posição é realmente um Master
                if (world.getBlockState(testMasterPos).is(VlAbyssModBlocks.PORTA_ABYSS.get())) {
                    // Verifica se este Slave faz parte do multi-block deste Master
                    if (isSlavePartOfMultiblock(slavePos, testMasterPos, facing)) {
                        return testMasterPos;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Verifica se um Slave específico faz parte do multi-block de um Master
     */
    private static boolean isSlavePartOfMultiblock(BlockPos slavePos, BlockPos masterPos, Direction facing) {
        BlockPos[][] positions = getMultiblockPositions(masterPos, facing);
        
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (positions[x][y].equals(slavePos)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Retorna os offsets de X baseado na direção que o portal está virado
     */
    private static int[] getXOffsets(Direction facing) {
        return switch (facing) {
            case NORTH -> new int[]{1, 0};  // X+ = direita, Z = constante
            case SOUTH -> new int[]{-1, 0}; // X- = direita, Z = constante  
            case EAST -> new int[]{0, 1};   // Z+ = direita, X = constante
            case WEST -> new int[]{0, -1};  // Z- = direita, X = constante
            default -> new int[]{1, 0};
        };
    }

    /**
     * Retorna os offsets de Z baseado na direção que o portal está virado
     */
    private static int[] getZOffsets(Direction facing) {
        return switch (facing) {
            case NORTH -> new int[]{0, -1}; // Profundidade para Norte
            case SOUTH -> new int[]{0, 1};  // Profundidade para Sul
            case EAST -> new int[]{1, 0};   // Profundidade para Leste  
            case WEST -> new int[]{-1, 0};  // Profundidade para Oeste
            default -> new int[]{0, -1};
        };
    }

    /**
     * Verifica se o multi-block está ativo
     */
    public static boolean isMultiblockActive(Level world, BlockPos masterPos) {
        BlockEntity masterEntity = world.getBlockEntity(masterPos);
        if (masterEntity != null) {
            return masterEntity.getPersistentData().getBoolean("multiblock_ativo");
        }
        return false;
    }
}