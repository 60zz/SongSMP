package net.mcreator.vlabyss.block.entity;

import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoBlockEntity;

import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.init.VlAbyssModBlockEntities;
import net.mcreator.vlabyss.block.PortaAbyssBlock;
import net.mcreator.vlabyss.block.PortaAbyssMultiblockHelper;

import javax.annotation.Nullable;

import java.util.stream.IntStream;

public class PortaAbyssTileEntity extends RandomizableContainerBlockEntity implements GeoBlockEntity, WorldlyContainer {
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
	private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

	// Variáveis para controle do multi-block
	private boolean multiblockAtivo = false;
	private boolean validandoEstrutura = false;
	private int ticksValidacao = 0;

	public PortaAbyssTileEntity(BlockPos pos, BlockState state) {
		super(VlAbyssModBlockEntities.PORTA_ABYSS.get(), pos, state);
	}

	// Método para tick personalizado da tile entity
	public static void tick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state, PortaAbyssTileEntity entity) {
		if (!level.isClientSide()) {
			entity.serverTick(level, pos, state);
		}
	}

	private void serverTick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state) {
		// Verifica periodicamente se a estrutura está válida
		ticksValidacao++;
		if (ticksValidacao >= 100) { // Verifica a cada 5 segundos (100 ticks)
			ticksValidacao = 0;
			validateMultiblockStructure(level, pos, state);
		}
	}

	private void validateMultiblockStructure(net.minecraft.world.level.Level level, BlockPos pos, BlockState state) {
		if (validandoEstrutura) return;
		validandoEstrutura = true;

		try {
			Direction facing = state.getValue(PortaAbyssBlock.FACING);
			boolean estruturaValida = PortaAbyssMultiblockHelper.validateMultiblock(level, pos, facing);
			boolean estaAtivo = getPersistentData().getBoolean("multiblock_ativo");

			if (estaAtivo && !estruturaValida) {
				// Estrutura foi danificada, desativa o multi-block
				getPersistentData().putBoolean("multiblock_ativo", false);
				multiblockAtivo = false;
				setChanged();
				
				// Tenta recriar se possível
				if (PortaAbyssMultiblockHelper.createMultiblock(level, pos, facing)) {
					getPersistentData().putBoolean("multiblock_ativo", true);
					multiblockAtivo = true;
					setChanged();
				}
			} else if (!estaAtivo && estruturaValida) {
				// Estrutura está válida mas não estava ativa
				getPersistentData().putBoolean("multiblock_ativo", true);
				multiblockAtivo = true;
				setChanged();
			}
		} finally {
			validandoEstrutura = false;
		}
	}

	private PlayState predicate(AnimationState event) {
		String animationprocedure = ("" + this.getBlockState().getValue(PortaAbyssBlock.ANIMATION));
		if (animationprocedure.equals("0")) {
			return event.setAndContinue(RawAnimation.begin().thenLoop(animationprocedure));
		}
		return PlayState.STOP;
	}

	private PlayState procedurePredicate(AnimationState event) {
		String animationprocedure = ("" + this.getBlockState().getValue(PortaAbyssBlock.ANIMATION));
		if (!animationprocedure.equals("0") && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
			event.getController().setAnimation(RawAnimation.begin().thenPlay(animationprocedure));
			if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
				if (this.getBlockState().getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp)
					level.setBlock(this.getBlockPos(), this.getBlockState().setValue(_integerProp, 0), 3);
				event.getController().forceAnimationReset();
			}
		} else if (animationprocedure.equals("0")) {
			return PlayState.STOP;
		}
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<PortaAbyssTileEntity>(this, "controller", 0, this::predicate));
		data.add(new AnimationController<PortaAbyssTileEntity>(this, "procedurecontroller", 0, this::procedurePredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);
		if (!this.tryLoadLootTable(compound))
			this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(compound, this.stacks);
		
		// Carrega dados do multi-block
		multiblockAtivo = compound.getBoolean("multiblock_ativo");
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);
		if (!this.trySaveLootTable(compound)) {
			ContainerHelper.saveAllItems(compound, this.stacks);
		}
		
		// Salva dados do multi-block
		compound.putBoolean("multiblock_ativo", multiblockAtivo);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag() {
		return this.saveWithFullMetadata();
	}

	@Override
	public int getContainerSize() {
		return stacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.stacks)
			if (!itemstack.isEmpty())
				return false;
		return true;
	}

	@Override
	public Component getDefaultName() {
		return Component.literal("porta_abyss");
	}

	@Override
	public int getMaxStackSize() {
		return 64;
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return ChestMenu.threeRows(id, inventory);
	}

	@Override
	public Component getDisplayName() {
		return Component.literal("Portal do Abismo");
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.stacks;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> stacks) {
		this.stacks = stacks;
	}

	@Override
	public boolean canPlaceItem(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return IntStream.range(0, this.getContainerSize()).toArray();
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
		return this.canPlaceItem(index, stack);
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return true;
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER)
			return handlers[facing.ordinal()].cast();
		return super.getCapability(capability, facing);
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
		for (LazyOptional<? extends IItemHandler> handler : handlers)
			handler.invalidate();
	}

	// Métodos públicos para controle do multi-block
	public boolean isMultiblockAtivo() {
		return multiblockAtivo;
	}

	public void setMultiblockAtivo(boolean ativo) {
		this.multiblockAtivo = ativo;
		getPersistentData().putBoolean("multiblock_ativo", ativo);
		setChanged();
	}
}