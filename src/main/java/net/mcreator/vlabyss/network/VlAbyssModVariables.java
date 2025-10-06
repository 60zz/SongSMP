package net.mcreator.vlabyss.network;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import net.mcreator.vlabyss.VlAbyssMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VlAbyssModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		VlAbyssMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			clone.mapa = original.mapa;
			clone.almas = original.almas;
			clone.Valmiriano = original.Valmiriano;
			clone.Arkanthi = original.Arkanthi;
			clone.Vesperian = original.Vesperian;
			clone.Nimren = original.Nimren;
			clone.Simikari = original.Simikari;
			clone.Scribari = original.Scribari;
			clone.Capridel = original.Capridel;
			clone.Lumivivo = original.Lumivivo;
			clone.VL_Spy = original.VL_Spy;
			clone.insanidade = original.insanidade;
			clone.vidas = original.vidas;
			clone.dash = original.dash;
			clone.Songs = original.Songs;
			clone.Chama = original.Chama;
			clone.Respiro = original.Respiro;
			clone.Nevasca = original.Nevasca;
			clone.Tempestade = original.Tempestade;
			clone.Escuridao = original.Escuridao;
			clone.Luz = original.Luz;
			clone.MantraRegistrada = original.MantraRegistrada;
			clone.dash_reto = original.dash_reto;
			clone.Ethir = original.Ethir;
			clone.Assassino = original.Assassino;
			clone.Guerreiro = original.Guerreiro;
			clone.Berserker = original.Berserker;
			clone.Tanque = original.Tanque;
			clone.pocaoreju = original.pocaoreju;
			clone.pocaoresis = original.pocaoresis;
			clone.pocaoextravida = original.pocaoextravida;
			clone.pocaorenas = original.pocaorenas;
			clone.reputacao = original.reputacao;
			clone.MaxEthir = original.MaxEthir;
			clone.ManaRegenTimer = original.ManaRegenTimer;
			clone.fase = original.fase;
			if (!event.isWasDeath()) {
			}
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(ResourceLocation.fromNamespaceAndPath("vl_abyss", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
		public boolean mapa = false;
		public double almas = 0;
		public boolean Valmiriano = false;
		public boolean Arkanthi = false;
		public boolean Vesperian = false;
		public boolean Nimren = false;
		public boolean Simikari = false;
		public boolean Scribari = false;
		public boolean Capridel = false;
		public boolean Lumivivo = false;
		public boolean VL_Spy = false;
		public double insanidade = 0;
		public double vidas = 5.0;
		public boolean dash = false;
		public boolean Songs = false;
		public double Chama = 0;
		public double Respiro = 0;
		public double Nevasca = 0;
		public double Tempestade = 0;
		public double Escuridao = 0;
		public double Luz = 0;
		public boolean MantraRegistrada = false;
		public boolean dash_reto = false;
		public double Ethir = 100.0;
		public boolean Assassino = false;
		public boolean Guerreiro = false;
		public boolean Berserker = false;
		public boolean Tanque = false;
		public boolean pocaoreju = false;
		public boolean pocaoresis = false;
		public boolean pocaoextravida = false;
		public boolean pocaorenas = false;
		public double reputacao = 0;
		public double MaxEthir = 100.0;
		public double ManaRegenTimer = 0;
		public double fase = 1.0;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				VlAbyssMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putBoolean("mapa", mapa);
			nbt.putDouble("almas", almas);
			nbt.putBoolean("Valmiriano", Valmiriano);
			nbt.putBoolean("Arkanthi", Arkanthi);
			nbt.putBoolean("Vesperian", Vesperian);
			nbt.putBoolean("Nimren", Nimren);
			nbt.putBoolean("Simikari", Simikari);
			nbt.putBoolean("Scribari", Scribari);
			nbt.putBoolean("Capridel", Capridel);
			nbt.putBoolean("Lumivivo", Lumivivo);
			nbt.putBoolean("VL_Spy", VL_Spy);
			nbt.putDouble("insanidade", insanidade);
			nbt.putDouble("vidas", vidas);
			nbt.putBoolean("dash", dash);
			nbt.putBoolean("Songs", Songs);
			nbt.putDouble("Chama", Chama);
			nbt.putDouble("Respiro", Respiro);
			nbt.putDouble("Nevasca", Nevasca);
			nbt.putDouble("Tempestade", Tempestade);
			nbt.putDouble("Escuridao", Escuridao);
			nbt.putDouble("Luz", Luz);
			nbt.putBoolean("MantraRegistrada", MantraRegistrada);
			nbt.putBoolean("dash_reto", dash_reto);
			nbt.putDouble("Ethir", Ethir);
			nbt.putBoolean("Assassino", Assassino);
			nbt.putBoolean("Guerreiro", Guerreiro);
			nbt.putBoolean("Berserker", Berserker);
			nbt.putBoolean("Tanque", Tanque);
			nbt.putBoolean("pocaoreju", pocaoreju);
			nbt.putBoolean("pocaoresis", pocaoresis);
			nbt.putBoolean("pocaoextravida", pocaoextravida);
			nbt.putBoolean("pocaorenas", pocaorenas);
			nbt.putDouble("reputacao", reputacao);
			nbt.putDouble("MaxEthir", MaxEthir);
			nbt.putDouble("ManaRegenTimer", ManaRegenTimer);
			nbt.putDouble("fase", fase);
			return nbt;
		}

		public void readNBT(Tag tag) {
			CompoundTag nbt = (CompoundTag) tag;
			mapa = nbt.getBoolean("mapa");
			almas = nbt.getDouble("almas");
			Valmiriano = nbt.getBoolean("Valmiriano");
			Arkanthi = nbt.getBoolean("Arkanthi");
			Vesperian = nbt.getBoolean("Vesperian");
			Nimren = nbt.getBoolean("Nimren");
			Simikari = nbt.getBoolean("Simikari");
			Scribari = nbt.getBoolean("Scribari");
			Capridel = nbt.getBoolean("Capridel");
			Lumivivo = nbt.getBoolean("Lumivivo");
			VL_Spy = nbt.getBoolean("VL_Spy");
			insanidade = nbt.getDouble("insanidade");
			vidas = nbt.getDouble("vidas");
			dash = nbt.getBoolean("dash");
			Songs = nbt.getBoolean("Songs");
			Chama = nbt.getDouble("Chama");
			Respiro = nbt.getDouble("Respiro");
			Nevasca = nbt.getDouble("Nevasca");
			Tempestade = nbt.getDouble("Tempestade");
			Escuridao = nbt.getDouble("Escuridao");
			Luz = nbt.getDouble("Luz");
			MantraRegistrada = nbt.getBoolean("MantraRegistrada");
			dash_reto = nbt.getBoolean("dash_reto");
			Ethir = nbt.getDouble("Ethir");
			Assassino = nbt.getBoolean("Assassino");
			Guerreiro = nbt.getBoolean("Guerreiro");
			Berserker = nbt.getBoolean("Berserker");
			Tanque = nbt.getBoolean("Tanque");
			pocaoreju = nbt.getBoolean("pocaoreju");
			pocaoresis = nbt.getBoolean("pocaoresis");
			pocaoextravida = nbt.getBoolean("pocaoextravida");
			pocaorenas = nbt.getBoolean("pocaorenas");
			reputacao = nbt.getDouble("reputacao");
			MaxEthir = nbt.getDouble("MaxEthir");
			ManaRegenTimer = nbt.getDouble("ManaRegenTimer");
			fase = nbt.getDouble("fase");
		}
	}

	public static class PlayerVariablesSyncMessage {
		private final PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
					variables.mapa = message.data.mapa;
					variables.almas = message.data.almas;
					variables.Valmiriano = message.data.Valmiriano;
					variables.Arkanthi = message.data.Arkanthi;
					variables.Vesperian = message.data.Vesperian;
					variables.Nimren = message.data.Nimren;
					variables.Simikari = message.data.Simikari;
					variables.Scribari = message.data.Scribari;
					variables.Capridel = message.data.Capridel;
					variables.Lumivivo = message.data.Lumivivo;
					variables.VL_Spy = message.data.VL_Spy;
					variables.insanidade = message.data.insanidade;
					variables.vidas = message.data.vidas;
					variables.dash = message.data.dash;
					variables.Songs = message.data.Songs;
					variables.Chama = message.data.Chama;
					variables.Respiro = message.data.Respiro;
					variables.Nevasca = message.data.Nevasca;
					variables.Tempestade = message.data.Tempestade;
					variables.Escuridao = message.data.Escuridao;
					variables.Luz = message.data.Luz;
					variables.MantraRegistrada = message.data.MantraRegistrada;
					variables.dash_reto = message.data.dash_reto;
					variables.Ethir = message.data.Ethir;
					variables.Assassino = message.data.Assassino;
					variables.Guerreiro = message.data.Guerreiro;
					variables.Berserker = message.data.Berserker;
					variables.Tanque = message.data.Tanque;
					variables.pocaoreju = message.data.pocaoreju;
					variables.pocaoresis = message.data.pocaoresis;
					variables.pocaoextravida = message.data.pocaoextravida;
					variables.pocaorenas = message.data.pocaorenas;
					variables.reputacao = message.data.reputacao;
					variables.MaxEthir = message.data.MaxEthir;
					variables.ManaRegenTimer = message.data.ManaRegenTimer;
					variables.fase = message.data.fase;
				}
			});
			context.setPacketHandled(true);
		}
	}
}