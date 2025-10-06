package net.mcreator.vlabyss.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
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
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

import net.mcreator.vlabyss.procedures.PortaAbyssOnBlockRightClickedProcedure;
import net.mcreator.vlabyss.init.VlAbyssModBlockEntities;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Collections;

public class PortaAbyssBlock extends BaseEntityBlock implements EntityBlock {
	public static final IntegerProperty ANIMATION = IntegerProperty.create("animation", 0, (int) 4);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public PortaAbyssBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(-1, 3600000).noOcclusion().pushReaction(PushReaction.BLOCK).isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return VlAbyssModBlockEntities.PORTA_ABYSS.get().create(blockPos, blockState);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	// Método para verificar se a porta está aberta via NBT
	private boolean isPortaAberta(BlockGetter world, BlockPos pos) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null) {
			return blockEntity.getPersistentData().getBoolean("aberto");
		}
		return false;
	}

	// Método para verificar se o multi-block está ativo
	private boolean isMultiblockActive(BlockGetter world, BlockPos pos) {
		if (world instanceof Level level) {
			return PortaAbyssMultiblockHelper.isMultiblockActive(level, pos);
		}
		return false;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		// Se o multi-block estiver ativo, usa forma reduzida do Master
		if (isMultiblockActive(world, pos)) {
			return switch (state.getValue(FACING)) {
				default -> box(-32, 0, 0, 48, 96, 16);     // Centro pequeno
				case NORTH -> box(-28, 0, 0, 48, 96, 16);
				case EAST -> box(0, 0, -28, 16, 96, 48);
				case WEST -> box(0, 0, -32, 16, 96, 44);
			};
		}
		
		// Forma original quando não é multi-block
		return switch (state.getValue(FACING)) {
			default -> box(-32, 0, 0, 48, 96, 16);
			case NORTH -> box(-28, 0, 0, 48, 96, 16);
			case EAST -> box(0, 0, -28, 16, 96, 48);
			case WEST -> box(0, 0, -32, 16, 96, 44);
		};
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		// Se a porta estiver aberta (NBT "aberto" = true), remove a colisão
		if (isPortaAberta(world, pos)) {
			return Shapes.empty(); // Sem colisão
		}

		// Se o multi-block estiver ativo, apenas o Master tem colisão reduzida
		if (isMultiblockActive(world, pos)) {
			return box(-32, 0, 0, 48, 96, 16); // Colisão mínima no centro
		}

		// Caso contrário, usa a forma normal para colisão
		return getShape(state, world, pos, context);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ANIMATION, FACING);
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

	@Override
	public BlockPathTypes getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
		return BlockPathTypes.BLOCKED;
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDrops(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this, 1));
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
		super.setPlacedBy(world, pos, state, entity, stack);
		
		// Quando o bloco é colocado, tenta criar o multi-block automaticamente
		if (!world.isClientSide()) {
			Direction facing = state.getValue(FACING);
			
			// Delay de 1 tick para garantir que o bloco foi completamente colocado
			world.scheduleTick(pos, this, 1);
		}
	}

	@Override
	public void tick(BlockState state, net.minecraft.server.level.ServerLevel world, BlockPos pos, net.minecraft.util.RandomSource random) {
		// Execução com delay para criar o multi-block
		Direction facing = state.getValue(FACING);
		boolean created = PortaAbyssMultiblockHelper.createMultiblock(world, pos, facing);
		
		if (created && world.getEntity(null) instanceof Player player) {
			// Mensagem opcional de sucesso
			// player.sendSystemMessage(Component.literal("§aPortal multi-block ativado!"));
		}
	}

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		
		// Verifica se o multi-block está válido antes de permitir interação
		if (!world.isClientSide()) {
			Direction facing = blockstate.getValue(FACING);
			
			if (!PortaAbyssMultiblockHelper.validateMultiblock(world, pos, facing)) {
				// Tenta recriar o multi-block
				boolean recreated = PortaAbyssMultiblockHelper.createMultiblock(world, pos, facing);
				if (!recreated) {
					entity.sendSystemMessage(Component.literal("§cEstrutura do portal incompleta! Certifique-se de que há espaço livre ao redor."));
					return InteractionResult.FAIL;
				}
			}
		}
		
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();
		PortaAbyssOnBlockRightClickedProcedure.execute(world, x, y, z, entity);
		return InteractionResult.SUCCESS;
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock())) {
			// Quando o Master é removido, destrói o multi-block
			if (!world.isClientSide()) {
				Direction facing = state.getValue(FACING);
				PortaAbyssMultiblockHelper.destroyMultiblock(world, pos, facing);
			}
		}
		super.onRemove(state, world, pos, newState, isMoving);
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity == null ? false : blockEntity.triggerEvent(eventID, eventParam);
	}
}