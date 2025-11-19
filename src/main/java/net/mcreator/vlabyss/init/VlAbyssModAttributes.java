/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.EntityType;

import net.mcreator.vlabyss.VlAbyssMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VlAbyssModAttributes {
	public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, VlAbyssMod.MODID);
	public static final RegistryObject<Attribute> INTELIGENCIA = REGISTRY.register("inteligencia", () -> new RangedAttribute("attribute.vl_abyss.inteligencia", 0, 0, 10).setSyncable(true));
	public static final RegistryObject<Attribute> BONUS_ADDITIONAL_DAMAGE = REGISTRY.register("bonus_additional_damage", () -> new RangedAttribute("attribute.vl_abyss.bonus_additional_damage", 1, 0, 100).setSyncable(true));
	public static final RegistryObject<Attribute> FLAME_BONUS = REGISTRY.register("flame_bonus", () -> new RangedAttribute("attribute.vl_abyss.flame_bonus", 1, 0, 10).setSyncable(true));
	public static final RegistryObject<Attribute> BLIZZARD_BONUS = REGISTRY.register("blizzard_bonus", () -> new RangedAttribute("attribute.vl_abyss.blizzard_bonus", 1, 0, 10).setSyncable(true));
	public static final RegistryObject<Attribute> DARKNESS_BONUS = REGISTRY.register("darkness_bonus", () -> new RangedAttribute("attribute.vl_abyss.darkness_bonus", 1, 0, 10).setSyncable(true));
	public static final RegistryObject<Attribute> LIGHT_BONUS = REGISTRY.register("light_bonus", () -> new RangedAttribute("attribute.vl_abyss.light_bonus", 1, 0, 10).setSyncable(true));
	public static final RegistryObject<Attribute> WIND_BONUS = REGISTRY.register("wind_bonus", () -> new RangedAttribute("attribute.vl_abyss.wind_bonus", 1, 0, 10).setSyncable(true));
	public static final RegistryObject<Attribute> THUNDERSTORM_BONUS = REGISTRY.register("thunderstorm_bonus", () -> new RangedAttribute("attribute.vl_abyss.thunderstorm_bonus", 1, 0, 10).setSyncable(true));
	public static final RegistryObject<Attribute> MANTRA_REGENERATION = REGISTRY.register("mantra_regeneration", () -> new RangedAttribute("attribute.vl_abyss.mantra_regeneration", 1, -100, 100).setSyncable(true));

	@SubscribeEvent
	public static void addAttributes(EntityAttributeModificationEvent event) {
		event.add(EntityType.PLAYER, INTELIGENCIA.get());
		event.add(EntityType.PLAYER, BONUS_ADDITIONAL_DAMAGE.get());
		event.add(EntityType.PLAYER, FLAME_BONUS.get());
		event.add(EntityType.PLAYER, BLIZZARD_BONUS.get());
		event.add(EntityType.PLAYER, DARKNESS_BONUS.get());
		event.add(EntityType.PLAYER, LIGHT_BONUS.get());
		event.add(EntityType.PLAYER, WIND_BONUS.get());
		event.add(EntityType.PLAYER, THUNDERSTORM_BONUS.get());
		event.add(EntityType.PLAYER, MANTRA_REGENERATION.get());
	}

	@Mod.EventBusSubscriber
	public static class PlayerAttributesSync {
		@SubscribeEvent
		public static void playerClone(PlayerEvent.Clone event) {
			Player oldPlayer = event.getOriginal();
			Player newPlayer = event.getEntity();
			newPlayer.getAttribute(INTELIGENCIA.get()).setBaseValue(oldPlayer.getAttribute(INTELIGENCIA.get()).getBaseValue());
			newPlayer.getAttribute(BONUS_ADDITIONAL_DAMAGE.get()).setBaseValue(oldPlayer.getAttribute(BONUS_ADDITIONAL_DAMAGE.get()).getBaseValue());
			newPlayer.getAttribute(FLAME_BONUS.get()).setBaseValue(oldPlayer.getAttribute(FLAME_BONUS.get()).getBaseValue());
			newPlayer.getAttribute(BLIZZARD_BONUS.get()).setBaseValue(oldPlayer.getAttribute(BLIZZARD_BONUS.get()).getBaseValue());
			newPlayer.getAttribute(DARKNESS_BONUS.get()).setBaseValue(oldPlayer.getAttribute(DARKNESS_BONUS.get()).getBaseValue());
			newPlayer.getAttribute(LIGHT_BONUS.get()).setBaseValue(oldPlayer.getAttribute(LIGHT_BONUS.get()).getBaseValue());
			newPlayer.getAttribute(WIND_BONUS.get()).setBaseValue(oldPlayer.getAttribute(WIND_BONUS.get()).getBaseValue());
			newPlayer.getAttribute(THUNDERSTORM_BONUS.get()).setBaseValue(oldPlayer.getAttribute(THUNDERSTORM_BONUS.get()).getBaseValue());
			newPlayer.getAttribute(MANTRA_REGENERATION.get()).setBaseValue(oldPlayer.getAttribute(MANTRA_REGENERATION.get()).getBaseValue());
		}
	}
}