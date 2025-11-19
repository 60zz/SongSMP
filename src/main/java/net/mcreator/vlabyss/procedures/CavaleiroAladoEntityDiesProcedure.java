package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

import java.util.function.BiFunction;
import java.util.UUID;

public class CavaleiroAladoEntityDiesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.getPersistentData().getBoolean("invocadordefinido")) {
			if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).fase == 1) {
				{
					Entity _ent = entity;
					if (!_ent.level().isClientSide() && _ent.getServer() != null) {
						_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
								_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), ("addvidas " + entity.getPersistentData().getString("invocador2") + " 2"));
					}
				}
				{
					double _setval = 2;
					((new BiFunction<LevelAccessor, String, Entity>() {
						@Override
						public Entity apply(LevelAccessor levelAccessor, String uuid) {
							if (levelAccessor instanceof ServerLevel serverLevel) {
								try {
									return serverLevel.getEntity(UUID.fromString(uuid));
								} catch (Exception e) {
								}
							}
							return null;
						}
					}).apply(world, (entity.getPersistentData().getString("invocador")))).getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.fase = _setval;
						capability.syncPlayerVariables(((new BiFunction<LevelAccessor, String, Entity>() {
							@Override
							public Entity apply(LevelAccessor levelAccessor, String uuid) {
								if (levelAccessor instanceof ServerLevel serverLevel) {
									try {
										return serverLevel.getEntity(UUID.fromString(uuid));
									} catch (Exception e) {
									}
								}
								return null;
							}
						}).apply(world, (entity.getPersistentData().getString("invocador")))));
					});
				}
			} else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).fase >= 2) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:entidadegrito")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:entidadegrito")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
				if (((new BiFunction<LevelAccessor, String, Entity>() {
					@Override
					public Entity apply(LevelAccessor levelAccessor, String uuid) {
						if (levelAccessor instanceof ServerLevel serverLevel) {
							try {
								return serverLevel.getEntity(UUID.fromString(uuid));
							} catch (Exception e) {
							}
						}
						return null;
					}
				}).apply(world, (entity.getPersistentData().getString("invocador")))) instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 120, 0));
				if (((new BiFunction<LevelAccessor, String, Entity>() {
					@Override
					public Entity apply(LevelAccessor levelAccessor, String uuid) {
						if (levelAccessor instanceof ServerLevel serverLevel) {
							try {
								return serverLevel.getEntity(UUID.fromString(uuid));
							} catch (Exception e) {
							}
						}
						return null;
					}
				}).apply(world, (entity.getPersistentData().getString("invocador")))) instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 120, 3));
				if (((new BiFunction<LevelAccessor, String, Entity>() {
					@Override
					public Entity apply(LevelAccessor levelAccessor, String uuid) {
						if (levelAccessor instanceof ServerLevel serverLevel) {
							try {
								return serverLevel.getEntity(UUID.fromString(uuid));
							} catch (Exception e) {
							}
						}
						return null;
					}
				}).apply(world, (entity.getPersistentData().getString("invocador")))) instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 1));
			}
		}
	}
}