package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkDirection;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.Connection;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.client.player.AbstractClientPlayer;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModItems;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.List;
import java.util.Iterator;

import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.IAnimation;

public class DashTrasOnKeyPressedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).dash_reto == true) {
			if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).desligadash == true) {
			if (!entity.getPersistentData().getBoolean("hitado") && !((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).dash == true)) {
				if (!(entity instanceof Player _plrCldCheck1 && _plrCldCheck1.getCooldowns().isOnCooldown(VlAbyssModItems.DESBLOQUEIO_ESQUIVA.get()))) {
					{
						boolean _setval = true;
						entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.dash = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					VlAbyssMod.queueServerWork(4, () -> {
						{
							boolean _setval = false;
							entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.dash = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if (entity instanceof Player _player)
							_player.getCooldowns().addCooldown(VlAbyssModItems.DESBLOQUEIO_ESQUIVA.get(), 10);
					});
				}
			} else if (!(entity instanceof Player _plrCldCheck4 && _plrCldCheck4.getCooldowns().isOnCooldown(VlAbyssModItems.DESBLOQUEIO_ESQUIVA.get()))
					&& (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).dash == true) {
				{
					boolean _setval = false;
					entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.dash = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (world.isClientSide()) {
					if (entity instanceof AbstractClientPlayer player) {
						var animation = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData(player).get(new ResourceLocation("vl_abyss", "player_animation"));
						if (animation != null) {
							animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(new ResourceLocation("vl_abyss", "dashtras"))));
						}
					}
				}
				if (!world.isClientSide()) {
					if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
						List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
						synchronized (connections) {
							Iterator<Connection> iterator = connections.iterator();
							while (iterator.hasNext()) {
								Connection connection = iterator.next();
								if (!connection.isConnecting() && connection.isConnected())
									VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("dashtras"), entity.getId(), true), connection, NetworkDirection.PLAY_TO_CLIENT);
							}
						}
					}
				}
				
				// Movimento para trás - direção oposta ao olhar do jogador
				Vec3 lookDirection = entity.getLookAngle();
				
				if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == VlAbyssModItems.ADAGA_ASSASSINOS.get())) {
					// Dash padrão para trás - usa o lookAngle negativo para ir na direção oposta
					entity.setDeltaMovement(new Vec3(-lookDirection.x * 1.5, 0, -lookDirection.z * 1.5));
				} else {
					if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).Assassino == true) {
						// Dash de assassino para trás - força dobrada
						entity.setDeltaMovement(new Vec3(-lookDirection.x * 2.5, 0, -lookDirection.z * 2.5));
						
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 40, 9));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 2));
					} else {
						// Dash normal para trás com adaga
						entity.setDeltaMovement(new Vec3(-lookDirection.x * 1.5, 0, -lookDirection.z * 1.5));
					}
				}
				
				// Som do dash
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("vl_abyss:dash_som")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("vl_abyss:dash_som")), SoundSource.MASTER, 1, 1, false);
					}
				}
				
				// Calcular posição inicial e final para o rastro
				double startX = entity.getX();
				double startY = entity.getY();
				double startZ = entity.getZ();
				
				// Calcular onde o jogador vai parar baseado no movimento
				Vec3 movement = entity.getDeltaMovement();
				double endX = startX + (movement.x * 20); // Multiplicar para simular a distância total
				double endY = startY;
				double endZ = startZ + (movement.z * 20);
				
				// Partícula inicial mais visível
				world.addParticle(ParticleTypes.POOF, x, y + 0.1, z, 0, 0.1, 0);
				world.addParticle(ParticleTypes.CLOUD, x, y + 0.1, z, 0, 0.1, 0);
				
				// Criar rastro da posição atual até a posição final
				createTrajectoryTrail(world, startX, startY, startZ, endX, endY, endZ, 0);
				
				VlAbyssMod.queueServerWork(5, () -> {
					if (entity instanceof Player _player)
						_player.getCooldowns().addCooldown(VlAbyssModItems.DESBLOQUEIO_ESQUIVA.get(), 100);
				});
			}
			}
		}
	}
	
	// Método para criar rastro da posição inicial até a final
	private static void createTrajectoryTrail(LevelAccessor world, double startX, double startY, double startZ, 
											   double endX, double endY, double endZ, int step) {
		if (step >= 20) return; // Total de 20 passos no rastro
		
		// Calcular posição atual no rastro (interpolação linear)
		double progress = (double) step / 19.0; // 0.0 a 1.0
		double currentX = startX + (endX - startX) * progress;
		double currentY = startY + (endY - startY) * progress;
		double currentZ = startZ + (endZ - startZ) * progress;
		
		// Densidade de partículas maior no início e diminuindo
		int particleCount = Math.max(1, 4 - (step / 5));
		
		for (int i = 0; i < particleCount; i++) {
			// Partículas POOF ao longo do rastro
			world.addParticle(ParticleTypes.POOF,
				currentX + (Math.random() - 0.5) * 0.4,
				currentY + 0.1 + Math.random() * 0.2,
				currentZ + (Math.random() - 0.5) * 0.4,
				(Math.random() - 0.5) * 0.1,
				0.05 + Math.random() * 0.05,
				(Math.random() - 0.5) * 0.1);
		}
		
		// Partículas CLOUD a cada 2 passos
		if (step % 2 == 0) {
			world.addParticle(ParticleTypes.CLOUD,
				currentX + (Math.random() - 0.5) * 0.3,
				currentY + 0.05,
				currentZ + (Math.random() - 0.5) * 0.3,
				(Math.random() - 0.5) * 0.05,
				0.02,
				(Math.random() - 0.5) * 0.05);
		}
		
		// Partículas especiais nos primeiros passos
		if (step < 5) {
			world.addParticle(ParticleTypes.WHITE_ASH,
				currentX,
				currentY + 0.1,
				currentZ,
				0, 0.01, 0);
		}
		
		// Próximo passo do rastro (mais rápido no início, mais lento no fim)
		int delay = Math.max(1, step / 4);
		VlAbyssMod.queueServerWork(delay, () -> {
			createTrajectoryTrail(world, startX, startY, startZ, endX, endY, endZ, step + 1);
		});
	}
	
	// Método antigo mantido como backup (não usado)
	private static void createParticleTrail(LevelAccessor world, Entity entity, int tickCount) {
		if (tickCount >= 15) return;
		
		double playerX = entity.getX();
		double playerY = entity.getY();
		double playerZ = entity.getZ();
		
		for (int i = 0; i < 3; i++) {
			world.addParticle(ParticleTypes.POOF, 
				playerX + (Math.random() - 0.5) * 0.6, 
				playerY + 0.1 + Math.random() * 0.3, 
				playerZ + (Math.random() - 0.5) * 0.6, 
				(Math.random() - 0.5) * 0.2, 
				0.05 + Math.random() * 0.1, 
				(Math.random() - 0.5) * 0.2);
		}
		
		for (int i = 0; i < 2; i++) {
			world.addParticle(ParticleTypes.CLOUD, 
				playerX + (Math.random() - 0.5) * 0.4, 
				playerY + 0.05, 
				playerZ + (Math.random() - 0.5) * 0.4, 
				(Math.random() - 0.5) * 0.1, 
				0.02 + Math.random() * 0.03, 
				(Math.random() - 0.5) * 0.1);
		}
		
		if (tickCount % 2 == 0) {
			world.addParticle(ParticleTypes.WHITE_ASH, 
				playerX + (Math.random() - 0.5) * 0.3, 
				playerY + 0.1, 
				playerZ + (Math.random() - 0.5) * 0.3, 
				0, 0.01, 0);
		}
		
		if (tickCount < 5) {
			world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, 
				playerX, 
				playerY + 0.1, 
				playerZ, 
				(Math.random() - 0.5) * 0.1, 
				0.05, 
				(Math.random() - 0.5) * 0.1);
		}
		
		VlAbyssMod.queueServerWork(1, () -> {
			createParticleTrail(world, entity, tickCount + 1);
		});
	}
}