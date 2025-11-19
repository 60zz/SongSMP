package net.mcreator.vlabyss.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import org.checkerframework.checker.units.qual.m;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModItems;
import net.mcreator.vlabyss.VlAbyssMod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MorreuComArtefatoPrimordialProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(VlAbyssModItems.FRAGMENTO_PRIMORDIAL.get(), lv).isPresent() : false) {
			if (!(entity instanceof Player _plrCldCheck1 && _plrCldCheck1.getCooldowns().isOnCooldown(VlAbyssModItems.FRAGMENTO_PRIMORDIAL.get()))) {
				if (entity instanceof LivingEntity _entity)
					_entity.setHealth(4);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("item.totem.use")), SoundSource.MASTER, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("item.totem.use")), SoundSource.MASTER, 1, 1, false);
					}
				}
				{
					Entity _entity = entity;
					if (_entity instanceof LivingEntity _livingEntity) {
						Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.max_health"));
						if (_attribute != null) {
							AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
							if (_attr != null) {
								String _modifierName = "vidaprimordial";
								boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
								if (!_hasModifier) {
									AttributeModifier _modifier = new AttributeModifier(_modifierName, (-5), AttributeModifier.Operation.ADDITION);
									_attr.addPermanentModifier(_modifier);
								}
							}
						}
					}
				}
				if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Lumivivo == true) {
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 1200, 3));
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 400, 4));
					VlAbyssMod.queueServerWork(1200, () -> {
						{
							Entity _entity = entity;
							if (_entity instanceof LivingEntity _livingEntity) {
								Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.max_health"));
								if (_attribute != null) {
									AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
									if (_attr != null) {
										_attr.getModifiers().forEach((_modifier) -> {
											if (_modifier.getName().equals("vidaprimordial")) {
												_attr.removeModifier(_modifier);
											}
										});
									}
								}
							}
						}
					});
				} else {
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 3600, 3));
					VlAbyssMod.queueServerWork(3600, () -> {
						{
							Entity _entity = entity;
							if (_entity instanceof LivingEntity _livingEntity) {
								Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.max_health"));
								if (_attribute != null) {
									AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
									if (_attr != null) {
										_attr.getModifiers().forEach((_modifier) -> {
											if (_modifier.getName().equals("vidaprimordial")) {
												_attr.removeModifier(_modifier);
											}
										});
									}
								}
							}
						}
					});
				}
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(VlAbyssModItems.FRAGMENTO_PRIMORDIAL.get(), 72000);
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
			}
		}
	}
}