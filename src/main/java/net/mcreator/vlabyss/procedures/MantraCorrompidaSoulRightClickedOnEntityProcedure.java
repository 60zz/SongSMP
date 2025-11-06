package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkDirection;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.minecraft.network.Connection;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModAttributes;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.List;
import java.util.Iterator;

public class MantraCorrompidaSoulRightClickedOnEntityProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (!entity.getPersistentData().getBoolean("absorvendo")) {
			entity.getPersistentData().putBoolean("absorvendo", true);
			if (world.isClientSide()) {
				SetupAnimationsProcedure.setAnimationClientside((Player) sourceentity, "coletaalma", false);
			}
			if (!world.isClientSide()) {
				if (sourceentity instanceof Player && world instanceof ServerLevel srvLvl_) {
					List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
					synchronized (connections) {
						Iterator<Connection> iterator = connections.iterator();
						while (iterator.hasNext()) {
							Connection connection = iterator.next();
							if (!connection.isConnecting() && connection.isConnected())
								VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("coletaalma"), sourceentity.getId(), false), connection, NetworkDirection.PLAY_TO_CLIENT);
						}
					}
				}
			}
			new Object() {
				void timedLoop(int timedloopiterator, int timedlooptotal, int ticks) {
					if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int) 2.5, 254));
					sourceentity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(x, y, z));
					final int tick2 = ticks;
					VlAbyssMod.queueServerWork(tick2, () -> {
						if (timedlooptotal > timedloopiterator + 1) {
							timedLoop(timedloopiterator + 1, timedlooptotal, tick2);
						}
					});
				}
			}.timedLoop(0, 24, (int) 2.5);
			VlAbyssMod.queueServerWork(60, () -> {
				if (Math.random() >= 0.7) {
					if (!entity.level().isClientSide())
						entity.discard();
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:almacoletada")), SoundSource.MASTER, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:almacoletada")), SoundSource.MASTER, 1, 1, false);
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:almacoletada2")), SoundSource.MASTER, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:almacoletada2")), SoundSource.MASTER, 1, 1, false);
						}
					}
					if (world instanceof net.minecraft.server.level.ServerLevel) {
						net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
						int particleCount = (int) 30;
						double centerX = x;
						double centerY = y;
						double centerZ = z;
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
					if ((sourceentity instanceof LivingEntity _livingEntity10 && _livingEntity10.getAttributes().hasAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
							? _livingEntity10.getAttribute(VlAbyssModAttributes.INTELIGENCIA.get()).getValue()
							: 0) == 0) {
						{
							double _setval = (sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).almas_corrompidas + 1;
							sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.almas_corrompidas = _setval;
								capability.syncPlayerVariables(sourceentity);
							});
						}
					} else if ((sourceentity instanceof LivingEntity _livingEntity11 && _livingEntity11.getAttributes().hasAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
							? _livingEntity11.getAttribute(VlAbyssModAttributes.INTELIGENCIA.get()).getValue()
							: 0) == 1) {
						{
							double _setval = (sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).almas_corrompidas + 2;
							sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.almas_corrompidas = _setval;
								capability.syncPlayerVariables(sourceentity);
							});
						}
					} else if ((sourceentity instanceof LivingEntity _livingEntity12 && _livingEntity12.getAttributes().hasAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
							? _livingEntity12.getAttribute(VlAbyssModAttributes.INTELIGENCIA.get()).getValue()
							: 0) == 2) {
						{
							double _setval = (sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).almas_corrompidas + 3;
							sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.almas_corrompidas = _setval;
								capability.syncPlayerVariables(sourceentity);
							});
						}
					} else if ((sourceentity instanceof LivingEntity _livingEntity13 && _livingEntity13.getAttributes().hasAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
							? _livingEntity13.getAttribute(VlAbyssModAttributes.INTELIGENCIA.get()).getValue()
							: 0) == 3) {
						{
							double _setval = (sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).almas_corrompidas + 4;
							sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.almas_corrompidas = _setval;
								capability.syncPlayerVariables(sourceentity);
							});
						}
					} else if ((sourceentity instanceof LivingEntity _livingEntity14 && _livingEntity14.getAttributes().hasAttribute(VlAbyssModAttributes.INTELIGENCIA.get())
							? _livingEntity14.getAttribute(VlAbyssModAttributes.INTELIGENCIA.get()).getValue()
							: 0) >= 4) {
						{
							double _setval = (sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).almas_corrompidas + 5;
							sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.almas_corrompidas = _setval;
								capability.syncPlayerVariables(sourceentity);
							});
						}
					}
				} else {
					if ((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Scribari == true) {
						{
							double _setval = (sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).insanidade + 0.5;
							sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.insanidade = _setval;
								capability.syncPlayerVariables(sourceentity);
							});
						}
					} else {
						{
							double _setval = (sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).insanidade + 1;
							sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.insanidade = _setval;
								capability.syncPlayerVariables(sourceentity);
							});
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:almanaocoletada")), SoundSource.MASTER, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:almanaocoletada")), SoundSource.MASTER, 1, 1, false);
						}
					}
					if (!entity.level().isClientSide())
						entity.discard();
					sourceentity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:absorve_alma_damage")))), 12);
					if (world instanceof net.minecraft.server.level.ServerLevel) {
						net.minecraft.server.level.ServerLevel _level = (net.minecraft.server.level.ServerLevel) world;
						int particleCount = (int) 30;
						double centerX = x;
						double centerY = y;
						double centerZ = z;
						double particleSpeed = 0.6;
						net.minecraft.core.particles.ParticleOptions particleType = net.minecraft.core.particles.ParticleTypes.PORTAL;
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
				}
			});
		}
	}
}