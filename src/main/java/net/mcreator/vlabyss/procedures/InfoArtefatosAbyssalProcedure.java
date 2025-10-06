package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.Screen;

import net.mcreator.vlabyss.init.VlAbyssModItems;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class InfoArtefatosAbyssalProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getItemStack(), event.getToolTip());
	}

	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		execute(null, itemstack, tooltip);
	}

private static void execute(
@Nullable Event event,
ItemStack itemstack,
List<Component> tooltip ) {
if (
tooltip == null ) return ;
if (itemstack.getItem() == VlAbyssModItems.CAPUZ_ESQUECIDO.get()) {if (Screen.hasShiftDown()) {tooltip.add(1, Component.literal("\u00A78\u2014 O artefato \"Capuz do Esquecido\" ir\u00E1 reduzir 1 ponto de m\u00E1xima m\u00E1xima, em troca, aumentar\u00E1 a concentra\u00E7\u00E3o de mantra do usu\u00E1rio"));}else{tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));}}if (itemstack.getItem() == VlAbyssModItems.VOTO_SILENCIOSO.get()) {if (Screen.hasShiftDown()) {tooltip.add(1, Component.literal("\u00A78\u2014 O artefato \"Colar do Voto Silencioso\" ir\u00E1 deixar o usu\u00E1rio invis\u00EDvel depois de matar uma criatura, tamb\u00E9m reduzir\u00E1 uma % do dano recebido"));tooltip.add(2, Component.literal(""));tooltip.add(3, Component.literal(("\u00A78Tecla de Ativa\u00E7\u00E3o: "+)));}else{tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));}}if (itemstack.getItem() == VlAbyssModItems.AMULETO_VESPERIAN.get()) {if (Screen.hasShiftDown()) {tooltip.add(1, Component.literal("\u00A78\u2014 O artefato \"Amuleto de Vesperian\" aumentar\u00E1 a regenera\u00E7\u00E3o do usu\u00E1rio dentro da \u00E1gua, por\u00E9m reduzir\u00E1 sua velocidade em terra firme"));}else{tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));}}if (itemstack.getItem() == VlAbyssModItems.AMULETO_SANGUE.get()) {if (Screen.hasShiftDown()) {tooltip.add(1, Component.literal("\u00A78\u2014 O artefato \"Amuleto de Sangue\" ir\u00E1 remover o sangramento do usu\u00E1rio que acertar, consumindo esse sangue e retornando em vida para quem o porta"));}else{tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));}}if (itemstack.getItem() == VlAbyssModItems.LUVAS_IRONSING.get()) {if (Screen.hasShiftDown()) {tooltip.add(1, Component.literal("\u00A78\u2014 As Luvas de Ironsing permitem um ataque carregado quando ativas, causando atordoamento no usu\u00E1rio que for acertado. Possui um tempo de recarga de 20 segundos"));tooltip.add(2, Component.literal(""));tooltip.add(3, Component.literal(("\u00A78Tecla de Ativa\u00E7\u00E3o: "+)));}else{tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));}}if (itemstack.getItem() == VlAbyssModItems.LUVAS_LUA_SANGRENTA.get()) {if (Screen.hasShiftDown()) {tooltip.add(1, Component.literal("\u00A78\u2014 Ao acertar com uma for\u00E7a absurda a \"Luva da Lua Sangrenta\", ir\u00E1 fazer o usu\u00E1rio que foi acertado sangrar durante alguns segundos, possui tempo de recarga de 40 segundos."));}else{tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));}}if (itemstack.getItem() == VlAbyssModItems.ANEL_ENFERRUJADO.get()) {if (Screen.hasShiftDown()) {tooltip.add(1, Component.literal("\u00A78\u2014 O \"Anel Enferrujado\" quando utilizado para atacar algu\u00E9m, ir\u00E1 fazer com que o usu\u00E1rio que foi acertado ter sua regenera\u00E7\u00E3o cortada durante 3 segundos. Possui 10% de chance de acerto, al\u00E9m de quem o porta perder 1 Slot de Ring"));}else{tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));}}if (itemstack.getItem() == VlAbyssModItems.CINTO_DOS_ECOS.get()) {if (Screen.hasShiftDown()) {tooltip.add(1, Component.literal("\u00A78\u2014 O \"Cinto dos Ecos\" possui uma habilidade ativa, ao ativar sua habilidade, seu pr\u00F3ximo ataque ir\u00E1 causar 25% a mais de dano do que o comum, possui um tempo de recarga de 5 minutos"));tooltip.add(2, Component.literal(""));tooltip.add(3, Component.literal(("\u00A78Tecla de Ativa\u00E7\u00E3o: "+)));}else{tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));}}if (itemstack.getItem() == VlAbyssModItems.FAIXA_ACOLITO.get()) {if (Screen.hasShiftDown()) {tooltip.add(1, Component.literal("\u00A78\u2014 A \"Faixa de Ac\u00F3lito\" diferente das demais habilidades, ir\u00E1 fazer com que o usu\u00E1rio que o porta receber uma intelig\u00EAncia maior, por\u00E9m, reduzir\u00E1 um slot de Hand do portador"));}else{tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));}}if (itemstack.getItem() == VlAbyssModItems.BOTAS_VAZIO_ABYSSAL.get()) {if (Screen.hasShiftDown()) {tooltip.add(1, Component.literal("\u00A78\u2014 As \"Botas do Vazio\" ir\u00E1 fazer com que o usu\u00E1rio que o veste ter uma velocidade aumentada de corrida, ele poder\u00E1 correr mais r\u00E1pido do que o comum, tornando-se \u00FAtil para situa\u00E7\u00F5es complicadas"));}else{tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));}}if (itemstack.getItem() == VlAbyssModItems.FRAGMENTO_PRIMORDIAL.get()) {if (Screen.hasShiftDown()) {tooltip.add(1, Component.literal("\u00A78\u2014 O raro \"Fragmento de Primordial\" pertence a uma rara esp\u00E9cie de artefatos de linhagem desconhecida. Sua finalidade se resume em permitir a \"auto ressurrei\u00E7\u00E3o\" do usu\u00E1rio, tendo 1 hora de tempo de uso, al\u00E9m de ficar com uma vida reduzida e uma grande fome insaci\u00E1vel "));}else{tooltip.add(1, Component.literal("\u00A78Segure SHIFT para mais informa\u00E7\u00F5es"));}}
}
}