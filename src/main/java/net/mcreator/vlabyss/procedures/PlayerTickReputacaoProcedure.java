package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraft.world.entity.ai.gossip.GossipContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlayerTickReputacaoProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).reputacao == 10) {
			if (entity instanceof Player _player) {
				double reputationValue = 2.5;
				CompoundTag playerData = _player.getPersistentData();
				playerData.putDouble("villager_discount", reputationValue / 100.0);
				_player.addTag("has_villager_discount");
				if (_player.level() instanceof ServerLevel serverLevel) {
					serverLevel.getAllEntities().forEach(villagerEntity -> {
						if (villagerEntity instanceof Villager villager) {
							GossipContainer gossips = villager.getGossips();
							// Limpa TODA reputação anterior deste jogador
							gossips.remove(_player.getUUID(), GossipType.MAJOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MAJOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.TRADING);
							// Aplica nova reputação
							if (reputationValue > 0) {
								// Reputação positiva = desconto
								gossips.add(_player.getUUID(), GossipType.MAJOR_POSITIVE, (int) (reputationValue * 10));
								gossips.add(_player.getUUID(), GossipType.MINOR_POSITIVE, (int) (reputationValue * 5));
							} else if (reputationValue < 0) {
								// Reputação negativa = preços mais altos
								int negativeValue = (int) (Math.abs(reputationValue) * 10);
								gossips.add(_player.getUUID(), GossipType.MAJOR_NEGATIVE, negativeValue);
								gossips.add(_player.getUUID(), GossipType.MINOR_NEGATIVE, negativeValue / 2);
							}
						}
					});
				}
			}
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).reputacao > 7
				&& !((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).reputacao >= 10)) {
			if (entity instanceof Player _player) {
				double reputationValue = 2;
				CompoundTag playerData = _player.getPersistentData();
				playerData.putDouble("villager_discount", reputationValue / 100.0);
				_player.addTag("has_villager_discount");
				if (_player.level() instanceof ServerLevel serverLevel) {
					serverLevel.getAllEntities().forEach(villagerEntity -> {
						if (villagerEntity instanceof Villager villager) {
							GossipContainer gossips = villager.getGossips();
							// Limpa TODA reputação anterior deste jogador
							gossips.remove(_player.getUUID(), GossipType.MAJOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MAJOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.TRADING);
							// Aplica nova reputação
							if (reputationValue > 0) {
								// Reputação positiva = desconto
								gossips.add(_player.getUUID(), GossipType.MAJOR_POSITIVE, (int) (reputationValue * 10));
								gossips.add(_player.getUUID(), GossipType.MINOR_POSITIVE, (int) (reputationValue * 5));
							} else if (reputationValue < 0) {
								// Reputação negativa = preços mais altos
								int negativeValue = (int) (Math.abs(reputationValue) * 10);
								gossips.add(_player.getUUID(), GossipType.MAJOR_NEGATIVE, negativeValue);
								gossips.add(_player.getUUID(), GossipType.MINOR_NEGATIVE, negativeValue / 2);
							}
						}
					});
				}
			}
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).reputacao >= 5
				&& !((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).reputacao >= 7)) {
			if (entity instanceof Player _player) {
				double reputationValue = 1.5;
				CompoundTag playerData = _player.getPersistentData();
				playerData.putDouble("villager_discount", reputationValue / 100.0);
				_player.addTag("has_villager_discount");
				if (_player.level() instanceof ServerLevel serverLevel) {
					serverLevel.getAllEntities().forEach(villagerEntity -> {
						if (villagerEntity instanceof Villager villager) {
							GossipContainer gossips = villager.getGossips();
							// Limpa TODA reputação anterior deste jogador
							gossips.remove(_player.getUUID(), GossipType.MAJOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MAJOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.TRADING);
							// Aplica nova reputação
							if (reputationValue > 0) {
								// Reputação positiva = desconto
								gossips.add(_player.getUUID(), GossipType.MAJOR_POSITIVE, (int) (reputationValue * 10));
								gossips.add(_player.getUUID(), GossipType.MINOR_POSITIVE, (int) (reputationValue * 5));
							} else if (reputationValue < 0) {
								// Reputação negativa = preços mais altos
								int negativeValue = (int) (Math.abs(reputationValue) * 10);
								gossips.add(_player.getUUID(), GossipType.MAJOR_NEGATIVE, negativeValue);
								gossips.add(_player.getUUID(), GossipType.MINOR_NEGATIVE, negativeValue / 2);
							}
						}
					});
				}
			}
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).reputacao >= 3
				&& !((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).reputacao >= 5)) {
			if (entity instanceof Player _player) {
				double reputationValue = 1;
				CompoundTag playerData = _player.getPersistentData();
				playerData.putDouble("villager_discount", reputationValue / 100.0);
				_player.addTag("has_villager_discount");
				if (_player.level() instanceof ServerLevel serverLevel) {
					serverLevel.getAllEntities().forEach(villagerEntity -> {
						if (villagerEntity instanceof Villager villager) {
							GossipContainer gossips = villager.getGossips();
							// Limpa TODA reputação anterior deste jogador
							gossips.remove(_player.getUUID(), GossipType.MAJOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MAJOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.TRADING);
							// Aplica nova reputação
							if (reputationValue > 0) {
								// Reputação positiva = desconto
								gossips.add(_player.getUUID(), GossipType.MAJOR_POSITIVE, (int) (reputationValue * 10));
								gossips.add(_player.getUUID(), GossipType.MINOR_POSITIVE, (int) (reputationValue * 5));
							} else if (reputationValue < 0) {
								// Reputação negativa = preços mais altos
								int negativeValue = (int) (Math.abs(reputationValue) * 10);
								gossips.add(_player.getUUID(), GossipType.MAJOR_NEGATIVE, negativeValue);
								gossips.add(_player.getUUID(), GossipType.MINOR_NEGATIVE, negativeValue / 2);
							}
						}
					});
				}
			}
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).reputacao > 0
				&& !((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).reputacao >= 3)) {
			if (entity instanceof Player _player) {
				double reputationValue = 0.5;
				CompoundTag playerData = _player.getPersistentData();
				playerData.putDouble("villager_discount", reputationValue / 100.0);
				_player.addTag("has_villager_discount");
				if (_player.level() instanceof ServerLevel serverLevel) {
					serverLevel.getAllEntities().forEach(villagerEntity -> {
						if (villagerEntity instanceof Villager villager) {
							GossipContainer gossips = villager.getGossips();
							// Limpa TODA reputação anterior deste jogador
							gossips.remove(_player.getUUID(), GossipType.MAJOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MAJOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.TRADING);
							// Aplica nova reputação
							if (reputationValue > 0) {
								// Reputação positiva = desconto
								gossips.add(_player.getUUID(), GossipType.MAJOR_POSITIVE, (int) (reputationValue * 10));
								gossips.add(_player.getUUID(), GossipType.MINOR_POSITIVE, (int) (reputationValue * 5));
							} else if (reputationValue < 0) {
								// Reputação negativa = preços mais altos
								int negativeValue = (int) (Math.abs(reputationValue) * 10);
								gossips.add(_player.getUUID(), GossipType.MAJOR_NEGATIVE, negativeValue);
								gossips.add(_player.getUUID(), GossipType.MINOR_NEGATIVE, negativeValue / 2);
							}
						}
					});
				}
			}
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).reputacao == 0) {
			if (entity instanceof Player _player) {
				double reputationValue = 0;
				CompoundTag playerData = _player.getPersistentData();
				playerData.putDouble("villager_discount", reputationValue / 100.0);
				_player.addTag("has_villager_discount");
				if (_player.level() instanceof ServerLevel serverLevel) {
					serverLevel.getAllEntities().forEach(villagerEntity -> {
						if (villagerEntity instanceof Villager villager) {
							GossipContainer gossips = villager.getGossips();
							// Limpa TODA reputação anterior deste jogador
							gossips.remove(_player.getUUID(), GossipType.MAJOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MAJOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.TRADING);
							// Aplica nova reputação
							if (reputationValue > 0) {
								// Reputação positiva = desconto
								gossips.add(_player.getUUID(), GossipType.MAJOR_POSITIVE, (int) (reputationValue * 10));
								gossips.add(_player.getUUID(), GossipType.MINOR_POSITIVE, (int) (reputationValue * 5));
							} else if (reputationValue < 0) {
								// Reputação negativa = preços mais altos
								int negativeValue = (int) (Math.abs(reputationValue) * 10);
								gossips.add(_player.getUUID(), GossipType.MAJOR_NEGATIVE, negativeValue);
								gossips.add(_player.getUUID(), GossipType.MINOR_NEGATIVE, negativeValue / 2);
							}
						}
					});
				}
			}
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).reputacao == -1) {
			if (entity instanceof Player _player) {
				double reputationValue = (-2);
				CompoundTag playerData = _player.getPersistentData();
				playerData.putDouble("villager_discount", reputationValue / 100.0);
				_player.addTag("has_villager_discount");
				if (_player.level() instanceof ServerLevel serverLevel) {
					serverLevel.getAllEntities().forEach(villagerEntity -> {
						if (villagerEntity instanceof Villager villager) {
							GossipContainer gossips = villager.getGossips();
							// Limpa TODA reputação anterior deste jogador
							gossips.remove(_player.getUUID(), GossipType.MAJOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MAJOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.TRADING);
							// Aplica nova reputação
							if (reputationValue > 0) {
								// Reputação positiva = desconto
								gossips.add(_player.getUUID(), GossipType.MAJOR_POSITIVE, (int) (reputationValue * 10));
								gossips.add(_player.getUUID(), GossipType.MINOR_POSITIVE, (int) (reputationValue * 5));
							} else if (reputationValue < 0) {
								// Reputação negativa = preços mais altos
								int negativeValue = (int) (Math.abs(reputationValue) * 10);
								gossips.add(_player.getUUID(), GossipType.MAJOR_NEGATIVE, negativeValue);
								gossips.add(_player.getUUID(), GossipType.MINOR_NEGATIVE, negativeValue / 2);
							}
						}
					});
				}
			}
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).reputacao == -2) {
			if (entity instanceof Player _player) {
				double reputationValue = (-3);
				CompoundTag playerData = _player.getPersistentData();
				playerData.putDouble("villager_discount", reputationValue / 100.0);
				_player.addTag("has_villager_discount");
				if (_player.level() instanceof ServerLevel serverLevel) {
					serverLevel.getAllEntities().forEach(villagerEntity -> {
						if (villagerEntity instanceof Villager villager) {
							GossipContainer gossips = villager.getGossips();
							// Limpa TODA reputação anterior deste jogador
							gossips.remove(_player.getUUID(), GossipType.MAJOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MAJOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.TRADING);
							// Aplica nova reputação
							if (reputationValue > 0) {
								// Reputação positiva = desconto
								gossips.add(_player.getUUID(), GossipType.MAJOR_POSITIVE, (int) (reputationValue * 10));
								gossips.add(_player.getUUID(), GossipType.MINOR_POSITIVE, (int) (reputationValue * 5));
							} else if (reputationValue < 0) {
								// Reputação negativa = preços mais altos
								int negativeValue = (int) (Math.abs(reputationValue) * 10);
								gossips.add(_player.getUUID(), GossipType.MAJOR_NEGATIVE, negativeValue);
								gossips.add(_player.getUUID(), GossipType.MINOR_NEGATIVE, negativeValue / 2);
							}
						}
					});
				}
			}
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).reputacao == -3) {
			if (entity instanceof Player _player) {
				double reputationValue = (-4);
				CompoundTag playerData = _player.getPersistentData();
				playerData.putDouble("villager_discount", reputationValue / 100.0);
				_player.addTag("has_villager_discount");
				if (_player.level() instanceof ServerLevel serverLevel) {
					serverLevel.getAllEntities().forEach(villagerEntity -> {
						if (villagerEntity instanceof Villager villager) {
							GossipContainer gossips = villager.getGossips();
							// Limpa TODA reputação anterior deste jogador
							gossips.remove(_player.getUUID(), GossipType.MAJOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MAJOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.TRADING);
							// Aplica nova reputação
							if (reputationValue > 0) {
								// Reputação positiva = desconto
								gossips.add(_player.getUUID(), GossipType.MAJOR_POSITIVE, (int) (reputationValue * 10));
								gossips.add(_player.getUUID(), GossipType.MINOR_POSITIVE, (int) (reputationValue * 5));
							} else if (reputationValue < 0) {
								// Reputação negativa = preços mais altos
								int negativeValue = (int) (Math.abs(reputationValue) * 10);
								gossips.add(_player.getUUID(), GossipType.MAJOR_NEGATIVE, negativeValue);
								gossips.add(_player.getUUID(), GossipType.MINOR_NEGATIVE, negativeValue / 2);
							}
						}
					});
				}
			}
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).reputacao == -4) {
			if (entity instanceof Player _player) {
				double reputationValue = (-6);
				CompoundTag playerData = _player.getPersistentData();
				playerData.putDouble("villager_discount", reputationValue / 100.0);
				_player.addTag("has_villager_discount");
				if (_player.level() instanceof ServerLevel serverLevel) {
					serverLevel.getAllEntities().forEach(villagerEntity -> {
						if (villagerEntity instanceof Villager villager) {
							GossipContainer gossips = villager.getGossips();
							// Limpa TODA reputação anterior deste jogador
							gossips.remove(_player.getUUID(), GossipType.MAJOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MAJOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.TRADING);
							// Aplica nova reputação
							if (reputationValue > 0) {
								// Reputação positiva = desconto
								gossips.add(_player.getUUID(), GossipType.MAJOR_POSITIVE, (int) (reputationValue * 10));
								gossips.add(_player.getUUID(), GossipType.MINOR_POSITIVE, (int) (reputationValue * 5));
							} else if (reputationValue < 0) {
								// Reputação negativa = preços mais altos
								int negativeValue = (int) (Math.abs(reputationValue) * 10);
								gossips.add(_player.getUUID(), GossipType.MAJOR_NEGATIVE, negativeValue);
								gossips.add(_player.getUUID(), GossipType.MINOR_NEGATIVE, negativeValue / 2);
							}
						}
					});
				}
			}
		} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).reputacao == -5) {
			if (entity instanceof Player _player) {
				double reputationValue = (-8);
				CompoundTag playerData = _player.getPersistentData();
				playerData.putDouble("villager_discount", reputationValue / 100.0);
				_player.addTag("has_villager_discount");
				if (_player.level() instanceof ServerLevel serverLevel) {
					serverLevel.getAllEntities().forEach(villagerEntity -> {
						if (villagerEntity instanceof Villager villager) {
							GossipContainer gossips = villager.getGossips();
							// Limpa TODA reputação anterior deste jogador
							gossips.remove(_player.getUUID(), GossipType.MAJOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_POSITIVE);
							gossips.remove(_player.getUUID(), GossipType.MAJOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.MINOR_NEGATIVE);
							gossips.remove(_player.getUUID(), GossipType.TRADING);
							// Aplica nova reputação
							if (reputationValue > 0) {
								// Reputação positiva = desconto
								gossips.add(_player.getUUID(), GossipType.MAJOR_POSITIVE, (int) (reputationValue * 10));
								gossips.add(_player.getUUID(), GossipType.MINOR_POSITIVE, (int) (reputationValue * 5));
							} else if (reputationValue < 0) {
								// Reputação negativa = preços mais altos
								int negativeValue = (int) (Math.abs(reputationValue) * 10);
								gossips.add(_player.getUUID(), GossipType.MAJOR_NEGATIVE, negativeValue);
								gossips.add(_player.getUUID(), GossipType.MINOR_NEGATIVE, negativeValue / 2);
							}
						}
					});
				}
			}
		}
	}
}