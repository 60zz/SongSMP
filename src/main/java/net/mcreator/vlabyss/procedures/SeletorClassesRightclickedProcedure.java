package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.game.ClientboundUpdateAdvancementsPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.vlabyss.world.inventory.SeletorDeClassesGuiMenu;
import net.mcreator.vlabyss.network.VlAbyssModVariables;

import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import io.netty.buffer.Unpooled;

public class SeletorClassesRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Assassino == false
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Berserker == false
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Guerreiro == false
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Tanque == false) {
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("SeletorDeClassesGui");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new SeletorDeClassesGuiMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		} else {
			{
				Entity _entity = entity;
				if (_entity instanceof ServerPlayer _player) {
					ItemStack _icon = new ItemStack(ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse("vl_abyss:abyss_banner_pattern")));
					Component _title = Component.literal("title");
					Component _description = Component.literal("Voc\u00EA j\u00E1 det\u00E9m de uma classe!");
					ResourceLocation _advId = ResourceLocation.tryParse("custom:toast_" + System.currentTimeMillis());
					DisplayInfo _display = new DisplayInfo(_icon, _description, _title, null, FrameType.TASK, true, true, false);
					Advancement.Builder _builder = Advancement.Builder.advancement().display(_display).addCriterion("trigger", new ImpossibleTrigger.TriggerInstance());
					Advancement _adv = _builder.build(_advId);
					AdvancementProgress _progress = new AdvancementProgress();
					_progress.update(_adv.getCriteria(), _adv.getRequirements());
					_progress.grantProgress("trigger");
					Map<ResourceLocation, AdvancementProgress> _progressMap = new HashMap<>();
					_progressMap.put(_advId, _progress);
					_player.connection.send(new ClientboundUpdateAdvancementsPacket(false, List.of(_adv), Set.of(), _progressMap));
					new Thread(() -> {
						try {
							Thread.sleep((long) (40 * 50));
							_player.connection.send(new ClientboundUpdateAdvancementsPacket(false, List.of(), Set.of(_advId), Map.of()));
						} catch (Exception e) {
						}
					}).start();
				}
			}
		}
	}
}