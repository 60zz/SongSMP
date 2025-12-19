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
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElseGet(PlayerVariables::new)).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElseGet(PlayerVariables::new)).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElseGet(PlayerVariables::new)).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElseGet(PlayerVariables::new));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElseGet(PlayerVariables::new));
			clone.almas = original.almas;
			clone.almas_corrompidas = original.almas_corrompidas;
			clone.Arkanthi = original.Arkanthi;
			clone.Assassino = original.Assassino;
			clone.Berserker = original.Berserker;
			clone.Capridel = original.Capridel;
			clone.Chama = original.Chama;
			clone.dash = original.dash;
			clone.dash_reto = original.dash_reto;
			clone.desligadash = original.desligadash;
			clone.Escuridao = original.Escuridao;
			clone.Ethir = original.Ethir;
			clone.fase = original.fase;
			clone.Guerreiro = original.Guerreiro;
			clone.habilidade1 = original.habilidade1;
			clone.habilidade2 = original.habilidade2;
			clone.habilidade3 = original.habilidade3;
			clone.habilidade4 = original.habilidade4;
			clone.habilidade5 = original.habilidade5;
			clone.Lumivivo = original.Lumivivo;
			clone.Luz = original.Luz;
			clone.ManaRegenTimer = original.ManaRegenTimer;
			clone.mantra1_cooldown = original.mantra1_cooldown;
			clone.mantra2_cooldown = original.mantra2_cooldown;
			clone.mantra3_cooldown = original.mantra3_cooldown;
			clone.mantra4_cooldown = original.mantra4_cooldown;
			clone.mantra5_cooldown = original.mantra5_cooldown;
			clone.MantraRegistrada = original.MantraRegistrada;
			clone.mapa = original.mapa;
			clone.MaxEthir = original.MaxEthir;
			clone.Nevasca = original.Nevasca;
			clone.Nimren = original.Nimren;
			clone.pocaoextravida = original.pocaoextravida;
			clone.pocaoreju = original.pocaoreju;
			clone.pocaorenas = original.pocaorenas;
			clone.pocaoresis = original.pocaoresis;
			clone.reputacao = original.reputacao;
			clone.Respiro = original.Respiro;
			clone.Scribari = original.Scribari;
			clone.Simikari = original.Simikari;
			clone.Songs = original.Songs;
			clone.Tanque = original.Tanque;
			clone.Tempestade = original.Tempestade;
			clone.Valmiriano = original.Valmiriano;
			clone.Vesperian = original.Vesperian;
			clone.vidas = original.vidas;
			clone.VL_Spy = original.VL_Spy;
			clone.hab_selecionada = original.hab_selecionada;
			clone.posX = original.posX;
			clone.posY = original.posY;
			clone.posZ = original.posZ;
			clone.opcao_mantra2 = original.opcao_mantra2;
			clone.opcao_mantra3 = original.opcao_mantra3;
			clone.lctrlativo = original.lctrlativo;
			clone.procurado = original.procurado;
			clone.song_escuridao = original.song_escuridao;
			clone.song_respiro = original.song_respiro;
			clone.song_nevasca = original.song_nevasca;
			clone.song_chama = original.song_chama;
			clone.song_luz = original.song_luz;
			clone.song_tempestade = original.song_tempestade;
			clone.song_esquecimento = original.song_esquecimento;
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
		public double almas = 0;
		public double almas_corrompidas = 0.0;
		public boolean Arkanthi = false;
		public boolean Assassino = false;
		public boolean Berserker = false;
		public boolean Capridel = false;
		public double Chama = 0.0;
		public boolean dash = false;
		public boolean dash_reto = false;
		public boolean desligadash = false;
		public double Escuridao = 0.0;
		public double Ethir = 100.0;
		public double fase = 1.0;
		public boolean Guerreiro = false;
		public boolean habilidade1 = false;
		public boolean habilidade2 = false;
		public boolean habilidade3 = false;
		public boolean habilidade4 = false;
		public boolean habilidade5 = false;
		public boolean Lumivivo = false;
		public double Luz = 0.0;
		public double ManaRegenTimer = 0.0;
		public double mantra1_cooldown = 0.0;
		public double mantra2_cooldown = 0.0;
		public double mantra3_cooldown = 0.0;
		public double mantra4_cooldown = 0.0;
		public double mantra5_cooldown = 0.0;
		public boolean MantraRegistrada = false;
		public boolean mapa = false;
		public double MaxEthir = 100.0;
		public double Nevasca = 0.0;
		public boolean Nimren = false;
		public boolean pocaoextravida = false;
		public boolean pocaoreju = false;
		public boolean pocaorenas = false;
		public boolean pocaoresis = false;
		public double reputacao = 0.0;
		public double Respiro = 0.0;
		public boolean Scribari = false;
		public boolean Simikari = false;
		public boolean Songs = false;
		public boolean Tanque = false;
		public double Tempestade = 0.0;
		public boolean Valmiriano = false;
		public boolean Vesperian = false;
		public double vidas = 5.0;
		public boolean VL_Spy = false;
		public double hab_selecionada = 1.0;
		public double posX = 0;
		public double posY = 0;
		public double posZ = 0;
		public double opcao_mantra2 = 0;
		public double opcao_mantra3 = 0;
		public boolean lctrlativo = false;
		public boolean procurado = false;
		public boolean song_escuridao = false;
		public boolean song_respiro = false;
		public boolean song_nevasca = false;
		public boolean song_chama = false;
		public boolean song_luz = false;
		public boolean song_tempestade = false;
		public boolean song_esquecimento = false;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				VlAbyssMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putDouble("almas", almas);
			nbt.putDouble("almas_corrompidas", almas_corrompidas);
			nbt.putBoolean("Arkanthi", Arkanthi);
			nbt.putBoolean("Assassino", Assassino);
			nbt.putBoolean("Berserker", Berserker);
			nbt.putBoolean("Capridel", Capridel);
			nbt.putDouble("Chama", Chama);
			nbt.putBoolean("dash", dash);
			nbt.putBoolean("dash_reto", dash_reto);
			nbt.putBoolean("desligadash", desligadash);
			nbt.putDouble("Escuridao", Escuridao);
			nbt.putDouble("Ethir", Ethir);
			nbt.putDouble("fase", fase);
			nbt.putBoolean("Guerreiro", Guerreiro);
			nbt.putBoolean("habilidade1", habilidade1);
			nbt.putBoolean("habilidade2", habilidade2);
			nbt.putBoolean("habilidade3", habilidade3);
			nbt.putBoolean("habilidade4", habilidade4);
			nbt.putBoolean("habilidade5", habilidade5);
			nbt.putBoolean("Lumivivo", Lumivivo);
			nbt.putDouble("Luz", Luz);
			nbt.putDouble("ManaRegenTimer", ManaRegenTimer);
			nbt.putDouble("mantra1_cooldown", mantra1_cooldown);
			nbt.putDouble("mantra2_cooldown", mantra2_cooldown);
			nbt.putDouble("mantra3_cooldown", mantra3_cooldown);
			nbt.putDouble("mantra4_cooldown", mantra4_cooldown);
			nbt.putDouble("mantra5_cooldown", mantra5_cooldown);
			nbt.putBoolean("MantraRegistrada", MantraRegistrada);
			nbt.putBoolean("mapa", mapa);
			nbt.putDouble("MaxEthir", MaxEthir);
			nbt.putDouble("Nevasca", Nevasca);
			nbt.putBoolean("Nimren", Nimren);
			nbt.putBoolean("pocaoextravida", pocaoextravida);
			nbt.putBoolean("pocaoreju", pocaoreju);
			nbt.putBoolean("pocaorenas", pocaorenas);
			nbt.putBoolean("pocaoresis", pocaoresis);
			nbt.putDouble("reputacao", reputacao);
			nbt.putDouble("Respiro", Respiro);
			nbt.putBoolean("Scribari", Scribari);
			nbt.putBoolean("Simikari", Simikari);
			nbt.putBoolean("Songs", Songs);
			nbt.putBoolean("Tanque", Tanque);
			nbt.putDouble("Tempestade", Tempestade);
			nbt.putBoolean("Valmiriano", Valmiriano);
			nbt.putBoolean("Vesperian", Vesperian);
			nbt.putDouble("vidas", vidas);
			nbt.putBoolean("VL_Spy", VL_Spy);
			nbt.putDouble("hab_selecionada", hab_selecionada);
			nbt.putDouble("posX", posX);
			nbt.putDouble("posY", posY);
			nbt.putDouble("posZ", posZ);
			nbt.putDouble("opcao_mantra2", opcao_mantra2);
			nbt.putDouble("opcao_mantra3", opcao_mantra3);
			nbt.putBoolean("lctrlativo", lctrlativo);
			nbt.putBoolean("procurado", procurado);
			nbt.putBoolean("song_escuridao", song_escuridao);
			nbt.putBoolean("song_respiro", song_respiro);
			nbt.putBoolean("song_nevasca", song_nevasca);
			nbt.putBoolean("song_chama", song_chama);
			nbt.putBoolean("song_luz", song_luz);
			nbt.putBoolean("song_tempestade", song_tempestade);
			nbt.putBoolean("song_esquecimento", song_esquecimento);
			return nbt;
		}

		public void readNBT(Tag tag) {
			CompoundTag nbt = (CompoundTag) tag;
			almas = nbt.getDouble("almas");
			almas_corrompidas = nbt.getDouble("almas_corrompidas");
			Arkanthi = nbt.getBoolean("Arkanthi");
			Assassino = nbt.getBoolean("Assassino");
			Berserker = nbt.getBoolean("Berserker");
			Capridel = nbt.getBoolean("Capridel");
			Chama = nbt.getDouble("Chama");
			dash = nbt.getBoolean("dash");
			dash_reto = nbt.getBoolean("dash_reto");
			desligadash = nbt.getBoolean("desligadash");
			Escuridao = nbt.getDouble("Escuridao");
			Ethir = nbt.getDouble("Ethir");
			fase = nbt.getDouble("fase");
			Guerreiro = nbt.getBoolean("Guerreiro");
			habilidade1 = nbt.getBoolean("habilidade1");
			habilidade2 = nbt.getBoolean("habilidade2");
			habilidade3 = nbt.getBoolean("habilidade3");
			habilidade4 = nbt.getBoolean("habilidade4");
			habilidade5 = nbt.getBoolean("habilidade5");
			Lumivivo = nbt.getBoolean("Lumivivo");
			Luz = nbt.getDouble("Luz");
			ManaRegenTimer = nbt.getDouble("ManaRegenTimer");
			mantra1_cooldown = nbt.getDouble("mantra1_cooldown");
			mantra2_cooldown = nbt.getDouble("mantra2_cooldown");
			mantra3_cooldown = nbt.getDouble("mantra3_cooldown");
			mantra4_cooldown = nbt.getDouble("mantra4_cooldown");
			mantra5_cooldown = nbt.getDouble("mantra5_cooldown");
			MantraRegistrada = nbt.getBoolean("MantraRegistrada");
			mapa = nbt.getBoolean("mapa");
			MaxEthir = nbt.getDouble("MaxEthir");
			Nevasca = nbt.getDouble("Nevasca");
			Nimren = nbt.getBoolean("Nimren");
			pocaoextravida = nbt.getBoolean("pocaoextravida");
			pocaoreju = nbt.getBoolean("pocaoreju");
			pocaorenas = nbt.getBoolean("pocaorenas");
			pocaoresis = nbt.getBoolean("pocaoresis");
			reputacao = nbt.getDouble("reputacao");
			Respiro = nbt.getDouble("Respiro");
			Scribari = nbt.getBoolean("Scribari");
			Simikari = nbt.getBoolean("Simikari");
			Songs = nbt.getBoolean("Songs");
			Tanque = nbt.getBoolean("Tanque");
			Tempestade = nbt.getDouble("Tempestade");
			Valmiriano = nbt.getBoolean("Valmiriano");
			Vesperian = nbt.getBoolean("Vesperian");
			vidas = nbt.getDouble("vidas");
			VL_Spy = nbt.getBoolean("VL_Spy");
			hab_selecionada = nbt.getDouble("hab_selecionada");
			posX = nbt.getDouble("posX");
			posY = nbt.getDouble("posY");
			posZ = nbt.getDouble("posZ");
			opcao_mantra2 = nbt.getDouble("opcao_mantra2");
			opcao_mantra3 = nbt.getDouble("opcao_mantra3");
			lctrlativo = nbt.getBoolean("lctrlativo");
			procurado = nbt.getBoolean("procurado");
			song_escuridao = nbt.getBoolean("song_escuridao");
			song_respiro = nbt.getBoolean("song_respiro");
			song_nevasca = nbt.getBoolean("song_nevasca");
			song_chama = nbt.getBoolean("song_chama");
			song_luz = nbt.getBoolean("song_luz");
			song_tempestade = nbt.getBoolean("song_tempestade");
			song_esquecimento = nbt.getBoolean("song_esquecimento");
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
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElseGet(PlayerVariables::new));
					variables.almas = message.data.almas;
					variables.almas_corrompidas = message.data.almas_corrompidas;
					variables.Arkanthi = message.data.Arkanthi;
					variables.Assassino = message.data.Assassino;
					variables.Berserker = message.data.Berserker;
					variables.Capridel = message.data.Capridel;
					variables.Chama = message.data.Chama;
					variables.dash = message.data.dash;
					variables.dash_reto = message.data.dash_reto;
					variables.desligadash = message.data.desligadash;
					variables.Escuridao = message.data.Escuridao;
					variables.Ethir = message.data.Ethir;
					variables.fase = message.data.fase;
					variables.Guerreiro = message.data.Guerreiro;
					variables.habilidade1 = message.data.habilidade1;
					variables.habilidade2 = message.data.habilidade2;
					variables.habilidade3 = message.data.habilidade3;
					variables.habilidade4 = message.data.habilidade4;
					variables.habilidade5 = message.data.habilidade5;
					variables.Lumivivo = message.data.Lumivivo;
					variables.Luz = message.data.Luz;
					variables.ManaRegenTimer = message.data.ManaRegenTimer;
					variables.mantra1_cooldown = message.data.mantra1_cooldown;
					variables.mantra2_cooldown = message.data.mantra2_cooldown;
					variables.mantra3_cooldown = message.data.mantra3_cooldown;
					variables.mantra4_cooldown = message.data.mantra4_cooldown;
					variables.mantra5_cooldown = message.data.mantra5_cooldown;
					variables.MantraRegistrada = message.data.MantraRegistrada;
					variables.mapa = message.data.mapa;
					variables.MaxEthir = message.data.MaxEthir;
					variables.Nevasca = message.data.Nevasca;
					variables.Nimren = message.data.Nimren;
					variables.pocaoextravida = message.data.pocaoextravida;
					variables.pocaoreju = message.data.pocaoreju;
					variables.pocaorenas = message.data.pocaorenas;
					variables.pocaoresis = message.data.pocaoresis;
					variables.reputacao = message.data.reputacao;
					variables.Respiro = message.data.Respiro;
					variables.Scribari = message.data.Scribari;
					variables.Simikari = message.data.Simikari;
					variables.Songs = message.data.Songs;
					variables.Tanque = message.data.Tanque;
					variables.Tempestade = message.data.Tempestade;
					variables.Valmiriano = message.data.Valmiriano;
					variables.Vesperian = message.data.Vesperian;
					variables.vidas = message.data.vidas;
					variables.VL_Spy = message.data.VL_Spy;
					variables.hab_selecionada = message.data.hab_selecionada;
					variables.posX = message.data.posX;
					variables.posY = message.data.posY;
					variables.posZ = message.data.posZ;
					variables.opcao_mantra2 = message.data.opcao_mantra2;
					variables.opcao_mantra3 = message.data.opcao_mantra3;
					variables.lctrlativo = message.data.lctrlativo;
					variables.procurado = message.data.procurado;
					variables.song_escuridao = message.data.song_escuridao;
					variables.song_respiro = message.data.song_respiro;
					variables.song_nevasca = message.data.song_nevasca;
					variables.song_chama = message.data.song_chama;
					variables.song_luz = message.data.song_luz;
					variables.song_tempestade = message.data.song_tempestade;
					variables.song_esquecimento = message.data.song_esquecimento;
				}
			});
			context.setPacketHandled(true);
		}
	}
}