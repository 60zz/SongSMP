package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkDirection;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.Connection;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.init.VlAbyssModAttributes;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.List;
import java.util.Iterator;

public class EscuridaoSegundaHabilidadeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Escuridao >= 1
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).MantraRegistrada == true
				&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).habilidade1 == true) {
			if (!((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).mantra2_cooldown > 0)) {
				if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).opcao_mantra2 == 1) {
					if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 70) {
						{
							double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 70;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.Ethir = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = Math.round(50 / (entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get())
									? _livingEntity0.getAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get()).getValue()
									: 0));
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.mantra2_cooldown = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if (world.isClientSide()) {
							SetupAnimationsProcedure.setAnimationClientside((Player) entity, "shadevision", false);
						}
						if (!world.isClientSide()) {
							if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
								List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
								synchronized (connections) {
									Iterator<Connection> iterator = connections.iterator();
									while (iterator.hasNext()) {
										Connection connection = iterator.next();
										if (!connection.isConnecting() && connection.isConnected())
											VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("shadevision"), entity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
									}
								}
							}
						}
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SHADE_VISION.get(),
									(int) (500
											* (entity instanceof LivingEntity _livingEntity2 && _livingEntity2.getAttributes().hasAttribute(VlAbyssModAttributes.DARKNESS_BONUS.get())
													? _livingEntity2.getAttribute(VlAbyssModAttributes.DARKNESS_BONUS.get()).getValue()
													: 0)
											* (entity instanceof LivingEntity _livingEntity3 && _livingEntity3.getAttributes().hasAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get())
													? _livingEntity3.getAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get()).getValue()
													: 0)),
									0));
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:ativacaoshadevision")), SoundSource.MASTER, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:ativacaoshadevision")), SoundSource.MASTER, 1, 1, false);
							}
						}
					} else {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal("\u00A7cSem \"ETHIR\" o suficiente"), true);
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")), SoundSource.MASTER, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")), SoundSource.MASTER, 1, 1, false);
							}
						}
					}
				} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).opcao_mantra2 == 2) {
					if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 110) {
						{
							double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 110;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.Ethir = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = Math.round(60 / (entity instanceof LivingEntity _livingEntity8 && _livingEntity8.getAttributes().hasAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get())
									? _livingEntity8.getAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get()).getValue()
									: 0));
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.mantra2_cooldown = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.ECHO_OF_SHADOWS.get(),
									(int) (400
											* (entity instanceof LivingEntity _livingEntity9 && _livingEntity9.getAttributes().hasAttribute(VlAbyssModAttributes.DARKNESS_BONUS.get())
													? _livingEntity9.getAttribute(VlAbyssModAttributes.DARKNESS_BONUS.get()).getValue()
													: 0)
											* (entity instanceof LivingEntity _livingEntity10 && _livingEntity10.getAttributes().hasAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get())
													? _livingEntity10.getAttribute(VlAbyssModAttributes.BONUS_ADDITIONAL_DAMAGE.get()).getValue()
													: 0)),
									0));
					} else {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal("\u00A7cSem \"ETHIR\" o suficiente"), true);
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")), SoundSource.MASTER, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")), SoundSource.MASTER, 1, 1, false);
							}
						}
					}
				} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).opcao_mantra2 == 3) {
					if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir >= 110) {
						{
							double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Ethir - 110;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.Ethir = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = Math.round(60 / (entity instanceof LivingEntity _livingEntity14 && _livingEntity14.getAttributes().hasAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get())
									? _livingEntity14.getAttribute(VlAbyssModAttributes.ABILITY_COOLDOWN_REDUCTION.get()).getValue()
									: 0));
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.mantra2_cooldown = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SHADE_BACKSTAP.get(), 600, 0));
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, x, y, z, 15, 0.05, 1, 0.05, 0.1);
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 15, 0.05, 1, 0.05, 0.1);
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:shadebackstab")), SoundSource.MASTER, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:shadebackstab")), SoundSource.MASTER, 1, 1, false);
							}
						}
					} else {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal("\u00A7cSem \"ETHIR\" o suficiente"), true);
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")), SoundSource.MASTER, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")), SoundSource.MASTER, 1, 1, false);
							}
						}
					}
				}
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7cHabilidade em recarga"), true);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")), SoundSource.MASTER, 1, 1, false);
					}
				}
			}
		}
	}
}