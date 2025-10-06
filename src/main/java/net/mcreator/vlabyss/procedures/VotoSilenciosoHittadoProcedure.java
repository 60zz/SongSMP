package net.mcreator.vlabyss.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.vlabyss.init.VlAbyssModItems;
import net.mcreator.vlabyss.configuration.SsmpConfiguration;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class VotoSilenciosoHittadoProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingHurtEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity());
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

private static void execute(
@Nullable Event event,
Entity entity ) {
if (
entity == null ) return ;
if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(VlAbyssModItems.VOTO_SILENCIOSO.get(), lv).isPresent()
: false) {if (event instanceof LivingHurtEvent _hurt) {
_hurt.setAmount((float)(/ (double) SsmpConfiguration.COLARVOTOSILENCIOSOCONFIG.get()));
}}
}
}