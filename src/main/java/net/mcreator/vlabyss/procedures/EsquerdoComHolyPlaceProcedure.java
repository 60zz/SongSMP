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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.VlAbyssMod;

import javax.annotation.Nullable;

import java.util.function.Supplier;
import java.util.Comparator;

@Mod.EventBusSubscriber(value = {Dist.CLIENT})
public class EsquerdoComHolyPlaceProcedure {
	@SubscribeEvent
	public static void onLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
		VlAbyssMod.PACKET_HANDLER.sendToServer(new EsquerdoComHolyPlaceMessage());
		execute(event.getLevel(), event.getEntity());
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class EsquerdoComHolyPlaceMessage {
		public EsquerdoComHolyPlaceMessage() {
		}

		public EsquerdoComHolyPlaceMessage(FriendlyByteBuf buffer) {
		}

		public static void buffer(EsquerdoComHolyPlaceMessage message, FriendlyByteBuf buffer) {
		}

		public static void handler(EsquerdoComHolyPlaceMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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
			VlAbyssMod.addNetworkMessage(EsquerdoComHolyPlaceMessage.class, EsquerdoComHolyPlaceMessage::buffer, EsquerdoComHolyPlaceMessage::new, EsquerdoComHolyPlaceMessage::handler);
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
				{
					final Vec3 _center = new Vec3(((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posX),
							((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posY),
							((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posZ));
					for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(8 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
						if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.HOLY_PURGE.get(), 240, 0));
					}
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null,
								BlockPos.containing((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posX,
										(entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posY + 1,
										(entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posZ),
								ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:purgesound")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posX),
								((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posY + 1),
								((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posZ),
								ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:purgesound")), SoundSource.MASTER, 1, 1, false);
					}
				}
				if (world instanceof net.minecraft.server.level.ServerLevel) {
					net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
					int particleCount = (int) 40;
					double centerX = ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posX);
					double centerY = ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posY + 1);
					double centerZ = ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).posZ);
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
				entity.getPersistentData().remove("holyplace");
			}
		}
	}
}