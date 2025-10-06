package net.mcreator.vlabyss.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;

import java.util.List;

@JeiPlugin
public class VlAbyssModJeiInformation implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation("vl_abyss:information");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addIngredientInfo(List.of(new ItemStack(VlAbyssModItems.MAPA_ABYSS.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.vl_abyss.jei_mapa"));
		registration.addIngredientInfo(List.of(new ItemStack(VlAbyssModBlocks.ABYSSION_ORE.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.vl_abyss.jei_abyssion"));
		registration.addIngredientInfo(List.of(new ItemStack(VlAbyssModItems.LIBERACAO_MANTRA.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.vl_abyss.jei_liberacao_mantra"));
		registration.addIngredientInfo(List.of(new ItemStack(VlAbyssModItems.DESBLOQUEIO_ESQUIVA.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.vl_abyss.informacao_desviar_1"));
		registration.addIngredientInfo(List.of(new ItemStack(VlAbyssModItems.RUNAS.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.vl_abyss.runas_info"));
	}
}