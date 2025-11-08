package net.mcreator.vlabyss.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
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

import java.util.UUID;

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
				if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
						.hasModifier((new AttributeModifier(UUID.fromString("d91e77f4-2bb8-4dc6-b7ae-e9d794b5adba"), "vidaprimordial", (-5), AttributeModifier.Operation.ADDITION)))))
					((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
							.addTransientModifier((new AttributeModifier(UUID.fromString("d91e77f4-2bb8-4dc6-b7ae-e9d794b5adba"), "vidaprimordial", (-5), AttributeModifier.Operation.ADDITION)));
				if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Lumivivo == true) {
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 1200, 3));
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 400, 4));
					VlAbyssMod.queueServerWork(1200, () -> {
						((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
								.removeModifier((new AttributeModifier(UUID.fromString("d91e77f4-2bb8-4dc6-b7ae-e9d794b5adba"), "vidaprimordial", (-5), AttributeModifier.Operation.ADDITION)));
					});
				} else {
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 3600, 3));
					VlAbyssMod.queueServerWork(3600, () -> {
						((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
								.removeModifier((new AttributeModifier(UUID.fromString("d91e77f4-2bb8-4dc6-b7ae-e9d794b5adba"), "vidaprimordial", (-5), AttributeModifier.Operation.ADDITION)));
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