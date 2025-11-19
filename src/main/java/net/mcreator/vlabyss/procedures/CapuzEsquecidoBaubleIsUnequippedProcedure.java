package net.mcreator.vlabyss.procedures;

import org.checkerframework.checker.units.qual.m;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.configuration.SsmpConfiguration;

public class CapuzEsquecidoBaubleIsUnequippedProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElseGet(VlAbyssModVariables.PlayerVariables::new)).Capridel == true)) {
			entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:absorve_alma_damage")))),
					(float) (double) SsmpConfiguration.CAPUZESQUECIDOCONFIG2.get());
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.max_health"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "vida";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, ((double) SsmpConfiguration.CAPUZESQUECIDOCONFIG2.get()), AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
		} else {
			{
				Entity _entity = entity;
				if (_entity instanceof LivingEntity _livingEntity) {
					Attribute _attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("vl_abyss:inteligencia"));
					if (_attribute != null) {
						AttributeInstance _attr = _livingEntity.getAttribute(_attribute);
						if (_attr != null) {
							String _modifierName = "maxint";
							boolean _hasModifier = _attr.getModifiers().stream().anyMatch(m -> m.getName().equals(_modifierName));
							if (!_hasModifier) {
								AttributeModifier _modifier = new AttributeModifier(_modifierName, ((double) SsmpConfiguration.CAPUZESQUECIDOCONFIG.get()), AttributeModifier.Operation.ADDITION);
								_attr.addPermanentModifier(_modifier);
							}
						}
					}
				}
			}
		}
		entity.getPersistentData().putBoolean("capuzesquecido", true);
	}
}