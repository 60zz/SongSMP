package net.mcreator.vlabyss.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.procedures.PortaAbyssOnBlockRightClickedProcedure;
import net.mcreator.vlabyss.init.VlAbyssModBlocks;

import java.util.List;
import java.util.Collections;

public class PortaAbyssSlaveBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public PortaAbyssSlaveBlock() {
        super(BlockBehaviour.Properties.of()
            .sound(SoundType.METAL)
            .strength(-1, 3600000)
            .noOcclusion()
            .pushReaction(PushReaction.BLOCK)
            .isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE; // Bloco invisível
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    // Encontra o bloco Master associado a este Slave
    private BlockPos findMasterBlock(Level world, BlockPos slavePos, Direction facing) {
        // Calcula a posição relativa do Master baseada na orientação
        return PortaAbyssMultiblockHelper.getMasterPosition(world, slavePos, facing);
    }

    // Verifica se a porta está aberta através do Master
    private boolean isPortaAberta(Level world, BlockPos slavePos, Direction facing) {
        BlockPos masterPos = findMasterBlock(world, slavePos, facing);
        if (masterPos == null) return false;
        
        BlockEntity masterEntity = world.getBlockEntity(masterPos);
        if (masterEntity != null) {
            return masterEntity.getPersistentData().getBoolean("aberto");
        }
        return false;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        // Forma completa para rendering (invisível mesmo assim)
        return Shapes.block();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        if (world instanceof Level level) {
            Direction facing = state.getValue(FACING);
            // Se a porta estiver aberta, remove a colisão
            if (isPortaAberta(level, pos, facing)) {
                return Shapes.empty();
            }
        }
        // Caso contrário, bloqueia passagem
        return Shapes.block();
    }

    @Override
    public BlockPathTypes getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
        return BlockPathTypes.BLOCKED;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        // Slaves não dropam nada - apenas o Master dropa
        return Collections.emptyList();
    }

    @Override
    public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        // Redireciona interação para o Master
        Direction facing = blockstate.getValue(FACING);
        BlockPos masterPos = findMasterBlock(world, pos, facing);
        
        if (masterPos != null && world.getBlockState(masterPos).getBlock() == VlAbyssModBlocks.PORTA_ABYSS.get()) {
            // Executa a procedure no Master
            PortaAbyssOnBlockRightClickedProcedure.execute(world, masterPos.getX(), masterPos.getY(), masterPos.getZ(), entity);
            return InteractionResult.SUCCESS;
        }
        
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            // Quando um Slave é removido, destrói o multi-block
            Direction facing = state.getValue(FACING);
            BlockPos masterPos = findMasterBlock(world, pos, facing);
            
            if (masterPos != null) {
                PortaAbyssMultiblockHelper.destroyMultiblock(world, masterPos, facing);
            }
        }
        super.onRemove(state, world, pos, newState, isMoving);
    }
}