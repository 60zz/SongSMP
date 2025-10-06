package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.GameType;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.Minecraft;

import net.mcreator.vlabyss.network.VlAbyssModVariables;

public class PlanarOnKeyPressedProcedure {
public static void execute(
Entity entity ) {
if (
entity == null ) return ;
if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).Respiro>=1&&(entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).MantraRegistrada==true) {if (new Object(){
public boolean checkGamemode(Entity _ent){
if(_ent instanceof ServerPlayer _serverPlayer) {
return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL;
} else if(_ent.level().isClientSide() && _ent instanceof Player _player) {
return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
&& Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SURVIVAL;
}
return false;
}
}.checkGamemode(entity)&&!(entity instanceof Player _plrCldCheck1 && _plrCldCheck1.getCooldowns().isOnCooldown(Items.FEATHER))) {if (&&) {if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,400,1));if(entity instanceof Player _player)
_player.getCooldowns().addCooldown(Items.FEATHER, 60);}}}
}
}