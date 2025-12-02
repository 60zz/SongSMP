package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.VlAbyssMod;

import javax.annotation.Nullable;

import java.util.function.Supplier;
import java.util.Comparator;

@Mod.EventBusSubscriber(value = {Dist.CLIENT})
public class RightClickWithReconjurarProcedure {
	@SubscribeEvent
	public static void onRightClick(PlayerInteractEvent.RightClickEmpty event) {
		if (event.getHand() != InteractionHand.MAIN_HAND)
			return;
		VlAbyssMod.PACKET_HANDLER.sendToServer(new RightClickWithReconjurarMessage());
		execute(event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getEntity());
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RightClickWithReconjurarMessage {
		public RightClickWithReconjurarMessage() {
		}

		public RightClickWithReconjurarMessage(FriendlyByteBuf buffer) {
		}

		public static void buffer(RightClickWithReconjurarMessage message, FriendlyByteBuf buffer) {
		}

		public static void handler(RightClickWithReconjurarMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getSender().level().hasChunkAt(context.getSender().blockPosition()))
					return;
				execute(context.getSender().level(), context.getSender().getX(), context.getSender().getY(), context.getSender().getZ(), context.getSender());
			});
			context.setPacketHandled(true);
		}

		@SubscribeEvent
		public static void registerMessage(FMLCommonSetupEvent event) {
			VlAbyssMod.addNetworkMessage(RightClickWithReconjurarMessage.class, RightClickWithReconjurarMessage::buffer, RightClickWithReconjurarMessage::new, RightClickWithReconjurarMessage::handler);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(VlAbyssModMobEffects.RECONJURAR.get())) {
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(VlAbyssModMobEffects.RECONJURAR.get()) ? _livEnt.getEffect(VlAbyssModMobEffects.RECONJURAR.get()).getAmplifier() : 0) == 0) {
				{
					final Vec3 _center = new Vec3(x, y, z);
					for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(20 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
						if (entityiterator instanceof LivingEntity _livEnt2 && _livEnt2.hasEffect(VlAbyssModMobEffects.STORM_RECONJURATION.get())) {
							{
								try {
									net.minecraft.world.entity.Entity targetEntity = entity;
									double teleportX = (entityiterator.getX() + Math.sin(entityiterator.getYRot()) * 1.5);
									double teleportY = (entityiterator.getY());
									double teleportZ = (entityiterator.getZ() - Math.cos(entityiterator.getYRot()) * 1.5);
									if (targetEntity != null) {
										if (targetEntity instanceof net.minecraft.server.level.ServerPlayer _player && !_player.level().isClientSide()) {
											_player.connection.teleport(teleportX, teleportY, teleportZ, _player.getYRot(), _player.getXRot());
										} else {
											targetEntity.teleportTo(teleportX, teleportY, teleportZ);
										}
									}
								} catch (Exception e) {
								}
							}
							if (entityiterator instanceof LivingEntity _entity)
								_entity.removeEffect(VlAbyssModMobEffects.STORM_RECONJURATION.get());
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 2));
							if (entity instanceof LivingEntity _entity)
								_entity.removeEffect(VlAbyssModMobEffects.RECONJURAR.get());
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.RECONJURAR.get(), 100, 1));
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:dash_som")), SoundSource.MASTER, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:dash_som")), SoundSource.MASTER, 1, 1, false);
								}
							}
						}
					}
				}
			}
		}
	}
}