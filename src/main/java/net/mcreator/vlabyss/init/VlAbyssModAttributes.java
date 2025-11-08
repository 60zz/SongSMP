/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;

import net.mcreator.vlabyss.VlAbyssMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VlAbyssModAttributes {
	public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, VlAbyssMod.MODID);
	public static final RegistryObject<Attribute> INTELIGENCIA = REGISTRY.register("inteligencia", () -> (new RangedAttribute("attribute." + VlAbyssMod.MODID + ".inteligencia", 0, 0, 10)).setSyncable(true));
	public static final RegistryObject<Attribute> BONUSADDITIONALDAMAGE = REGISTRY.register("bonus_additional_damage", () -> (new RangedAttribute("attribute." + VlAbyssMod.MODID + ".bonus_additional_damage", 1, 0, 100)).setSyncable(true));

	@SubscribeEvent
	public static void addAttributes(EntityAttributeModificationEvent event) {
	}
}