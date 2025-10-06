package net.mcreator.vlabyss.init;

import top.theillusivec4.curios.api.SlotTypeMessage;

import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.resources.ResourceLocation;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VlAbyssModCustomCuriosSlots {
	@SubscribeEvent
	public static void enqueueIMC(final InterModEnqueueEvent event) {
		InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("feet").icon(new ResourceLocation("curios:slot/empty_armor_slot_boots")).size(1).build());
		InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("soul").icon(new ResourceLocation("curios:slot/soulslot")).size(1).build());
		InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("hands").icon(new ResourceLocation("curios:slot/hand")).size(1).build());
	}
}