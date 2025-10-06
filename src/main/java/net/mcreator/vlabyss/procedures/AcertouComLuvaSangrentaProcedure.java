package net.mcreator.vlabyss.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.CriticalHitEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.init.VlAbyssModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AcertouComLuvaSangrentaProcedure {
	@SubscribeEvent
	public static void onPlayerCriticalHit(CriticalHitEvent event) {
		execute(event, event.getTarget(), event.getEntity());
	}

	public static void execute(Entity entity, Entity sourceentity) {
		execute(null, entity, sourceentity);
	}

private static void execute(
@Nullable Event event,
Entity entity,
Entity sourceentity ) {
if (
entity == null ||
sourceentity == null ) return ;
if (sourceentity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get(), lv).isPresent()
: false&&!(sourceentity instanceof Player _plrCldCheck1 && _plrCldCheck1.getCooldowns().isOnCooldown(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get()))) {if (!(sourceentity instanceof LivingEntity _livEnt2 && _livEnt2.hasEffect(MobEffects.DAMAGE_BOOST))) {if (>=15) {if (!((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).Arkanthi==true)) {if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SANGRANDO.get(),100,0));if(sourceentity instanceof Player _player)
_player.getCooldowns().addCooldown(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get(), 800);}else{if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SANGRANDO.get(),160,0));if(sourceentity instanceof Player _player)
_player.getCooldowns().addCooldown(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get(), 800);}}}else if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.DAMAGE_BOOST) ?
_livEnt.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() : 0)==1) {if (>=20) {if (!((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).Arkanthi==true)) {if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SANGRANDO.get(),100,0));if(sourceentity instanceof Player _player)
_player.getCooldowns().addCooldown(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get(), 800);}else{if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SANGRANDO.get(),160,0));if(sourceentity instanceof Player _player)
_player.getCooldowns().addCooldown(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get(), 800);}}}else if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.DAMAGE_BOOST) ?
_livEnt.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() : 0)==2) {if (>=25) {if (!((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).Arkanthi==true)) {if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SANGRANDO.get(),100,0));if(sourceentity instanceof Player _player)
_player.getCooldowns().addCooldown(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get(), 800);}else{if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SANGRANDO.get(),160,0));if(sourceentity instanceof Player _player)
_player.getCooldowns().addCooldown(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get(), 800);}}}else if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.DAMAGE_BOOST) ?
_livEnt.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() : 0)>=3) {if (>=30) {if (!((sourceentity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).Arkanthi==true)) {if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SANGRANDO.get(),100,0));if(sourceentity instanceof Player _player)
_player.getCooldowns().addCooldown(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get(), 800);}else{if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SANGRANDO.get(),160,0));if(sourceentity instanceof Player _player)
_player.getCooldowns().addCooldown(VlAbyssModItems.LUVAS_LUA_SANGRENTA.get(), 800);}}}}
}
}