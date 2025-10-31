/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.vlabyss.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.entity.RespiroPrimariaEntity;
import net.mcreator.vlabyss.entity.MantraSoulEntity;
import net.mcreator.vlabyss.entity.MantraSoulCorrompidaEntity;
import net.mcreator.vlabyss.entity.CavaleiroAladoEntity;
import net.mcreator.vlabyss.VlAbyssMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VlAbyssModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, VlAbyssMod.MODID);
	public static final RegistryObject<EntityType<MantraSoulEntity>> MANTRA_SOUL = register("mantra_soul", EntityType.Builder.<MantraSoulEntity>of(MantraSoulEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(1)
			.setUpdateInterval(3).setCustomClientFactory(MantraSoulEntity::new).fireImmune().sized(0.2f, 0.5f));
	public static final RegistryObject<EntityType<RespiroPrimariaEntity>> RESPIRO_PRIMARIA = register("respiro_primaria", EntityType.Builder.<RespiroPrimariaEntity>of(RespiroPrimariaEntity::new, MobCategory.MISC)
			.setCustomClientFactory(RespiroPrimariaEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<CavaleiroAladoEntity>> CAVALEIRO_ALADO = register("cavaleiro_alado", EntityType.Builder.<CavaleiroAladoEntity>of(CavaleiroAladoEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CavaleiroAladoEntity::new).fireImmune().sized(1f, 3f));
	public static final RegistryObject<EntityType<MantraSoulCorrompidaEntity>> MANTRA_SOUL_CORROMPIDA = register("mantra_soul_corrompida", EntityType.Builder.<MantraSoulCorrompidaEntity>of(MantraSoulCorrompidaEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(1).setUpdateInterval(3).setCustomClientFactory(MantraSoulCorrompidaEntity::new).fireImmune().sized(0.2f, 0.5f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			MantraSoulEntity.init();
			CavaleiroAladoEntity.init();
			MantraSoulCorrompidaEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(MANTRA_SOUL.get(), MantraSoulEntity.createAttributes().build());
		event.put(CAVALEIRO_ALADO.get(), CavaleiroAladoEntity.createAttributes().build());
		event.put(MANTRA_SOUL_CORROMPIDA.get(), MantraSoulCorrompidaEntity.createAttributes().build());
	}
}