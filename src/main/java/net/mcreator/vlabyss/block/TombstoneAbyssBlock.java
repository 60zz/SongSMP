package net.mcreator.vlabyss.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.procedures.TombstoneAbyssOnBlockRightClickedProcedure;
import net.mcreator.vlabyss.block.entity.TombstoneAbyssBlockEntity;

public class TombstoneAbyssBlock extends Block implements EntityBlock {
	public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 2);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public TombstoneAbyssBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(-1, 3600000).lightLevel(s -> (new Object() {
			public int getLightLevel() {
				if (s.getValue(BLOCKSTATE) == 1)
					return 0;
				if (s.getValue(BLOCKSTATE) == 2)
					return 0;
				return 0;
			}
		}.getLightLevel())).noOcclusion().isRedstoneConductor((bs, br, bp) -> false).instrument(NoteBlockInstrument.BASEDRUM));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		if (state.getValue(BLOCKSTATE) == 1) {
			return switch (state.getValue(FACING)) {
				default -> Shapes.or(box(4, 0, -2, 12, 2, 18), box(5, 1, 15, 11, 23, 17), box(7, 1, 0, 9, 22, 16), box(5, 22, 10, 11, 23, 15), box(5, 23, 10, 11, 24, 15), box(5, 23, 9, 11, 25, 10), box(5, 23, 5, 11, 25, 6), box(5, 23, 1, 11, 24, 5),
						box(5, 22, 1, 11, 23, 5), box(5, 1, -1, 11, 23, 1));
				case NORTH -> Shapes.or(box(4, 0, -2, 12, 2, 18), box(5, 1, -1, 11, 23, 1), box(7, 1, 0, 9, 22, 16), box(5, 22, 1, 11, 23, 6), box(5, 23, 1, 11, 24, 6), box(5, 23, 6, 11, 25, 7), box(5, 23, 10, 11, 25, 11), box(5, 23, 11, 11, 24, 15),
						box(5, 22, 11, 11, 23, 15), box(5, 1, 15, 11, 23, 17));
				case EAST -> Shapes.or(box(-2, 0, 4, 18, 2, 12), box(15, 1, 5, 17, 23, 11), box(0, 1, 7, 16, 22, 9), box(10, 22, 5, 15, 23, 11), box(10, 23, 5, 15, 24, 11), box(9, 23, 5, 10, 25, 11), box(5, 23, 5, 6, 25, 11),
						box(1, 23, 5, 5, 24, 11), box(1, 22, 5, 5, 23, 11), box(-1, 1, 5, 1, 23, 11));
				case WEST -> Shapes.or(box(-2, 0, 4, 18, 2, 12), box(-1, 1, 5, 1, 23, 11), box(0, 1, 7, 16, 22, 9), box(1, 22, 5, 6, 23, 11), box(1, 23, 5, 6, 24, 11), box(6, 23, 5, 7, 25, 11), box(10, 23, 5, 11, 25, 11), box(11, 23, 5, 15, 24, 11),
						box(11, 22, 5, 15, 23, 11), box(15, 1, 5, 17, 23, 11));
			};
		}
		if (state.getValue(BLOCKSTATE) == 2) {
			return switch (state.getValue(FACING)) {
				default -> Shapes.or(box(4, 0, -2, 12, 2, 18), box(5, 1, 15, 11, 23, 17), box(7, 1, 0, 9, 22, 16), box(5, 22, 10, 11, 23, 15), box(5, 23, 10, 11, 24, 15), box(5, 23, 9, 11, 25, 10), box(5, 23, 5, 11, 25, 6), box(5, 23, 1, 11, 24, 5),
						box(5, 22, 1, 11, 23, 5), box(5, 1, -1, 11, 23, 1));
				case NORTH -> Shapes.or(box(4, 0, -2, 12, 2, 18), box(5, 1, -1, 11, 23, 1), box(7, 1, 0, 9, 22, 16), box(5, 22, 1, 11, 23, 6), box(5, 23, 1, 11, 24, 6), box(5, 23, 6, 11, 25, 7), box(5, 23, 10, 11, 25, 11), box(5, 23, 11, 11, 24, 15),
						box(5, 22, 11, 11, 23, 15), box(5, 1, 15, 11, 23, 17));
				case EAST -> Shapes.or(box(-2, 0, 4, 18, 2, 12), box(15, 1, 5, 17, 23, 11), box(0, 1, 7, 16, 22, 9), box(10, 22, 5, 15, 23, 11), box(10, 23, 5, 15, 24, 11), box(9, 23, 5, 10, 25, 11), box(5, 23, 5, 6, 25, 11),
						box(1, 23, 5, 5, 24, 11), box(1, 22, 5, 5, 23, 11), box(-1, 1, 5, 1, 23, 11));
				case WEST -> Shapes.or(box(-2, 0, 4, 18, 2, 12), box(-1, 1, 5, 1, 23, 11), box(0, 1, 7, 16, 22, 9), box(1, 22, 5, 6, 23, 11), box(1, 23, 5, 6, 24, 11), box(6, 23, 5, 7, 25, 11), box(10, 23, 5, 11, 25, 11), box(11, 23, 5, 15, 24, 11),
						box(11, 22, 5, 15, 23, 11), box(15, 1, 5, 17, 23, 11));
			};
		}
		return switch (state.getValue(FACING)) {
			default -> Shapes.or(box(4, 0, -2, 12, 2, 18), box(5, 1, 15, 11, 23, 17), box(7, 1, 0, 9, 22, 16), box(5, 22, 10, 11, 23, 15), box(5, 23, 10, 11, 24, 15), box(5, 23, 9, 11, 25, 10), box(5, 23, 5, 11, 25, 6), box(5, 23, 1, 11, 24, 5),
					box(5, 22, 1, 11, 23, 5), box(5, 1, -1, 11, 23, 1));
			case NORTH -> Shapes.or(box(4, 0, -2, 12, 2, 18), box(5, 1, -1, 11, 23, 1), box(7, 1, 0, 9, 22, 16), box(5, 22, 1, 11, 23, 6), box(5, 23, 1, 11, 24, 6), box(5, 23, 6, 11, 25, 7), box(5, 23, 10, 11, 25, 11), box(5, 23, 11, 11, 24, 15),
					box(5, 22, 11, 11, 23, 15), box(5, 1, 15, 11, 23, 17));
			case EAST -> Shapes.or(box(-2, 0, 4, 18, 2, 12), box(15, 1, 5, 17, 23, 11), box(0, 1, 7, 16, 22, 9), box(10, 22, 5, 15, 23, 11), box(10, 23, 5, 15, 24, 11), box(9, 23, 5, 10, 25, 11), box(5, 23, 5, 6, 25, 11), box(1, 23, 5, 5, 24, 11),
					box(1, 22, 5, 5, 23, 11), box(-1, 1, 5, 1, 23, 11));
			case WEST -> Shapes.or(box(-2, 0, 4, 18, 2, 12), box(-1, 1, 5, 1, 23, 11), box(0, 1, 7, 16, 22, 9), box(1, 22, 5, 6, 23, 11), box(1, 23, 5, 6, 24, 11), box(6, 23, 5, 7, 25, 11), box(10, 23, 5, 11, 25, 11), box(11, 23, 5, 15, 24, 11),
					box(11, 22, 5, 15, 23, 11), box(15, 1, 5, 17, 23, 11));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, BLOCKSTATE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();
		TombstoneAbyssOnBlockRightClickedProcedure.execute(world, x, y, z, entity);
		return InteractionResult.SUCCESS;
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TombstoneAbyssBlockEntity(pos, state);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity == null ? false : blockEntity.triggerEvent(eventID, eventParam);
	}
}