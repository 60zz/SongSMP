package net.mcreator.vlabyss.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.registries.Registries;

import net.mcreator.vlabyss.init.VlAbyssModItems;

public class SoulSlayerLivingEntityIsHitWithToolProcedure {
	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity, ItemStack itemstack) {
		if (entity == null || sourceentity == null)
			return;
		double danosomado = 0;
		if (itemstack.getOrCreateTag().getBoolean("ataquealmas")) {
			danosomado = itemstack.getOrCreateTag().getDouble("almas");
			danosomado = Math.round(danosomado / 25);
			if (!(danosomado < 1)) {
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK), sourceentity),
						(float) ((sourceentity instanceof LivingEntity _attributeContext ? _attributeContext.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE) : 0.0D) * danosomado));
				itemstack.getOrCreateTag().putDouble("almas", 0);
				itemstack.getOrCreateTag().remove("ataquealmas");
				if (sourceentity instanceof Player _player)
					_player.getCooldowns().addCooldown(VlAbyssModItems.SOUL_SLAYER.get(), 600);
			} else {
				itemstack.getOrCreateTag().putDouble("almas", 0);
				itemstack.getOrCreateTag().remove("ataquealmas");
				if (sourceentity instanceof Player _player)
					_player.getCooldowns().addCooldown(VlAbyssModItems.SOUL_SLAYER.get(), 300);
			}
		}
	}
}