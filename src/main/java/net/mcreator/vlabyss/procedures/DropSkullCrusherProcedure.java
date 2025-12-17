package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.item.ItemTossEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.vlabyss.init.VlAbyssModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class DropSkullCrusherProcedure {
	@SubscribeEvent
	public static void onGemDropped(ItemTossEvent event) {
		execute(event, event.getPlayer(), event.getEntity().getItem());
	}

	public static void execute(Entity entity, ItemStack itemstack) {
		execute(null, entity, itemstack);
	}

	private static void execute(@Nullable Event event, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double vidaatual = 0;
		if (itemstack.getItem() == VlAbyssModItems.SKULL_CRUSHER.get()) {
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.knockback_resistance"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							_attr.getModifiers().forEach((_modifier) -> {
								if (_modifier.getName().equals("knockbackresis1")) {
									_attr.removeModifier(_modifier);
								}
							});
						}
					}
				}
			}
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.knockback_resistance"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							_attr.getModifiers().forEach((_modifier) -> {
								if (_modifier.getName().equals("knockbackresis2")) {
									_attr.removeModifier(_modifier);
								}
							});
						}
					}
				}
			}
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.knockback_resistance"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							_attr.getModifiers().forEach((_modifier) -> {
								if (_modifier.getName().equals("knockbackresis3")) {
									_attr.removeModifier(_modifier);
								}
							});
						}
					}
				}
			}
		}
	}
}