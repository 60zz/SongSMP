package net.mcreator.vlabyss.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.CriticalHitEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.init.VlAbyssModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class LuvasLuaSangrentaCriticoProcedure {
	private static CriticalHitEvent _event;

	@SubscribeEvent
	public static void onPlayerCriticalHit(CriticalHitEvent event) {
		_event = event;
		execute(event, event.getTarget(), event.getEntity(), event.getDamageModifier());
	}

	public static void execute(Entity entity, Entity sourceentity, double damagemodifier) {
		execute(null, entity, sourceentity, damagemodifier);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity sourceentity, double damagemodifier) {
		if (entity == null || sourceentity == null)
			return;
		if (sourceentity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get(), lv).isPresent() : false) {
			if (!(sourceentity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(MobEffects.DAMAGE_BOOST))) {
				if (((sourceentity instanceof LivingEntity _livingEntity2 && _livingEntity2.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity2.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0) + damagemodifier) - 1 >= 17) {
					if (!((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Arkanthi == true)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SANGRANDO.get(), 100, 0));
					} else {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SANGRANDO.get(), 160, 0));
					}
					if (sourceentity instanceof Player _player)
						_player.getCooldowns().addCooldown(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get(), 800);
				}
			} else {
				if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.DAMAGE_BOOST) ? _livEnt.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() : 0) == 1) {
					if (((sourceentity instanceof LivingEntity _livingEntity7 && _livingEntity7.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity7.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0) + damagemodifier)
							- 1 >= 22) {
						if (!((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Arkanthi == true)) {
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SANGRANDO.get(), 100, 0));
						} else {
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SANGRANDO.get(), 160, 0));
						}
						if (sourceentity instanceof Player _player)
							_player.getCooldowns().addCooldown(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get(), 800);
					}
				} else if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.DAMAGE_BOOST) ? _livEnt.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() : 0) == 2) {
					if (((sourceentity instanceof LivingEntity _livingEntity12 && _livingEntity12.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity12.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0) + damagemodifier)
							- 1 >= 26) {
						if (!((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Arkanthi == true)) {
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SANGRANDO.get(), 100, 0));
						} else {
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SANGRANDO.get(), 160, 0));
						}
						if (sourceentity instanceof Player _player)
							_player.getCooldowns().addCooldown(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get(), 800);
					}
				} else if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.DAMAGE_BOOST) ? _livEnt.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() : 0) >= 3) {
					if (((sourceentity instanceof LivingEntity _livingEntity17 && _livingEntity17.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity17.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0) + damagemodifier)
							- 1 >= 30) {
						if (!((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Arkanthi == true)) {
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SANGRANDO.get(), 100, 0));
						} else {
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SANGRANDO.get(), 160, 0));
						}
						if (sourceentity instanceof Player _player)
							_player.getCooldowns().addCooldown(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get(), 800);
					}
				}
			}
		}
	}
}