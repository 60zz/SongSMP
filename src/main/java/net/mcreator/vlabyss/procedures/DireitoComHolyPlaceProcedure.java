package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.VlAbyssMod;

import javax.annotation.Nullable;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(value = {Dist.CLIENT})
public class DireitoComHolyPlaceProcedure {
	@SubscribeEvent
	public static void onRightClick(PlayerInteractEvent.RightClickEmpty event) {
		if (event.getHand() != InteractionHand.MAIN_HAND)
			return;
		VlAbyssMod.PACKET_HANDLER.sendToServer(new DireitoComHolyPlaceMessage());
		execute(event.getLevel(), event.getEntity());
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class DireitoComHolyPlaceMessage {
		public DireitoComHolyPlaceMessage() {
		}

		public DireitoComHolyPlaceMessage(FriendlyByteBuf buffer) {
		}

		public static void buffer(DireitoComHolyPlaceMessage message, FriendlyByteBuf buffer) {
		}

		public static void handler(DireitoComHolyPlaceMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getSender().level().hasChunkAt(context.getSender().blockPosition()))
					return;
				execute(context.getSender().level(), context.getSender());
			});
			context.setPacketHandled(true);
		}

		@SubscribeEvent
		public static void registerMessage(FMLCommonSetupEvent event) {
			VlAbyssMod.addNetworkMessage(DireitoComHolyPlaceMessage.class, DireitoComHolyPlaceMessage::buffer, DireitoComHolyPlaceMessage::new, DireitoComHolyPlaceMessage::handler);
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity.getPersistentData().getBoolean("holyplace")) {
			if (entity.isShiftKeyDown()) {
				if (world instanceof net.minecraft.server.level.ServerLevel) {
					net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
					int particleCount = (int) 40;
					double centerX = (entity.getX());
					double centerY = (entity.getY() + 1);
					double centerZ = (entity.getZ());
					double particleSpeed = 0.6;
					net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.END_ROD;
					double initialRadius = 4.0;
					for (int i = 0; i < particleCount; i++) {
						double u = Math.random();
						double v = Math.random();
						double theta = 2 * Math.PI * u;
						double phi = Math.acos(2 * v - 1);
						double startX = centerX + initialRadius * Math.sin(phi) * Math.cos(theta);
						double startY = centerY + initialRadius * Math.cos(phi);
						double startZ = centerZ + initialRadius * Math.sin(phi) * Math.sin(theta);
						double deltaX = centerX - startX;
						double deltaY = centerY - startY;
						double deltaZ = centerZ - startZ;
						double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
						double velocityX = (deltaX / distance) * particleSpeed;
						double velocityY = (deltaY / distance) * particleSpeed;
						double velocityZ = (deltaZ / distance) * particleSpeed;
						_level.sendParticles(particleType, startX, startY, startZ, 0, velocityX, velocityY, velocityZ, particleSpeed);
					}
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY() + 1, entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:lightteleport")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound((entity.getX()), (entity.getY() + 1), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:lightteleport")), SoundSource.MASTER, 1, 1, false);
					}
				}
				{
					try {
						net.minecraft.world.entity.Entity targetEntity = entity;
						double teleportX = ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posX);
						double teleportY = ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posY);
						double teleportZ = ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posZ);
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
				if (world instanceof net.minecraft.server.level.ServerLevel) {
					net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
					int particleCount = (int) 40;
					double centerX = (entity.getX());
					double centerY = (entity.getY() + 1);
					double centerZ = (entity.getZ());
					double particleSpeed = 0.6;
					net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.END_ROD;
					for (int i = 0; i < particleCount; i++) {
						double u = Math.random();
						double v = Math.random();
						double theta = 2 * Math.PI * u;
						double phi = Math.acos(2 * v - 1);
						double directionX = Math.sin(phi) * Math.cos(theta);
						double directionY = Math.cos(phi);
						double directionZ = Math.sin(phi) * Math.sin(theta);
						double velocityX = directionX * particleSpeed;
						double velocityY = directionY * particleSpeed;
						double velocityZ = directionZ * particleSpeed;
						_level.sendParticles(particleType, centerX, centerY, centerZ, 0, velocityX, velocityY, velocityZ, particleSpeed);
					}
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY() + 1, entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:lightteleport")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound((entity.getX()), (entity.getY() + 1), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:lightteleport")), SoundSource.MASTER, 1, 1, false);
					}
				}
				entity.getPersistentData().remove("holyplace");
			}
		}
	}
}