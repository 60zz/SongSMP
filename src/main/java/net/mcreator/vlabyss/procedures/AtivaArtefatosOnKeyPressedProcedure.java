package net.mcreator.vlabyss.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModItems;

public class AtivaArtefatosOnKeyPressedProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity lv
				? CuriosApi.getCuriosHelper().findEquippedCurio(VlAbyssModItems.VOTO_SILENCIOSO.get(), lv).isPresent()
				: false && !(entity instanceof Player _plrCldCheck1 && _plrCldCheck1.getCooldowns().isOnCooldown(VlAbyssModItems.VOTO_SILENCIOSO.get()))) {
			if (!(world instanceof Level _lvl2 && _lvl2.isDay())) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 2400, 4));
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(VlAbyssModItems.VOTO_SILENCIOSO.get(), 3600);
			}
		}
		if (entity instanceof LivingEntity lv
				? CuriosApi.getCuriosHelper().findEquippedCurio(VlAbyssModItems.CINTO_DOS_ECOS.get(), lv).isPresent()
				: false && !(entity instanceof Player _plrCldCheck6 && _plrCldCheck6.getCooldowns().isOnCooldown(VlAbyssModItems.CINTO_DOS_ECOS.get()))) {
			if (!((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).Songs == true)) {
				entity.getPersistentData().putBoolean("criticonormal", true);
			} else {
				entity.getPersistentData().putBoolean("criticosong", true);
			}
			if (entity instanceof Player _player)
				_player.getCooldowns().addCooldown(VlAbyssModItems.CINTO_DOS_ECOS.get(), 6000);
		}
		if (entity instanceof LivingEntity lv
				? CuriosApi.getCuriosHelper().findEquippedCurio(VlAbyssModItems.LUVAS_IRONSING.get(), lv).isPresent()
				: false && !(entity instanceof Player _plrCldCheck11 && _plrCldCheck11.getCooldowns().isOnCooldown(VlAbyssModItems.LUVAS_IRONSING.get()))) {
			if (!((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VlAbyssModVariables.PlayerVariables())).Scribari == true)) {
				entity.getPersistentData().putBoolean("criticairons_s", true);
			} else {
				entity.getPersistentData().putBoolean("criticairons", true);
			}
			if (entity instanceof Player _player)
				_player.getCooldowns().addCooldown(VlAbyssModItems.LUVAS_IRONSING.get(), 400);
		}
	}
}