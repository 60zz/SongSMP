package net.mcreator.vlabyss.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

@Mod.EventBusSubscriber
public class ItemBorderProcedureProcedure {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onTooltipColor(RenderTooltipEvent.Color event) {
        execute(event.getItemStack(), event);
    }

    public static void execute(ItemStack itemstack, RenderTooltipEvent.Color event) {
        
        if (itemstack == null || itemstack.isEmpty()) {
            return;
        }


        // Verificar tags de cores
        int borderColor = getBorderColorFromTags(itemstack);
        
        if (borderColor != -1) {
            
            // Aplicar a cor da borda
            event.setBackgroundStart(0xF0100010);
            event.setBackgroundEnd(0xF0100010);
            
            int finalColor = (borderColor & 0xFFFFFF) | 0xFF000000;
            event.setBorderStart(finalColor);
            event.setBorderEnd(finalColor);
            
        }
    }

    // Método vazio para compatibilidade com MCreator (se necessário)
    public static void execute() {
        // Este método pode ficar vazio, o evento é automático
    }

    private static int getBorderColorFromTags(ItemStack stack) {
        
        // Vermelho
        if (hasItemTag(stack, "red")) {
            return 0xFF0000;
        }
        
        // Vermelho Escuro
        if (hasItemTag(stack, "dark_red")) {
            return 0x660000;
        }
        
        // Verde
        if (hasItemTag(stack, "green")) {
            return 0x00FF00;
        }
        
        // Azul
        if (hasItemTag(stack, "blue")) {
            return 0x0000FF;
        }
        
        // Roxo
        if (hasItemTag(stack, "purple")) {
            return 0x9932CC;
        }
        
        // Roxo Escuro
        if (hasItemTag(stack, "dark_purple")) {
            return 0x4B0082;
        }
        
        // Roxo Claro
        if (hasItemTag(stack, "light_purple")) {
            return 0x8D2992;
        }
        
        // Dourado
        if (hasItemTag(stack, "gold")) {
            return 0xFFD700;
        }
        
        // Laranja
        if (hasItemTag(stack, "orange")) {
            return 0xFF8C00;
        }
        
        // Rosa
        if (hasItemTag(stack, "pink")) {
            return 0xFF69B4;
        }
        
        // Ciano
        if (hasItemTag(stack, "cyan")) {
            return 0x00FFFF;
        }
        
        // Amarelo
        if (hasItemTag(stack, "yellow")) {
            return 0xFFFF00;
        }
        
        // Branco
        if (hasItemTag(stack, "white")) {
            return 0xFFFFFF;
        }
        
        // Preto
        if (hasItemTag(stack, "black")) {
            return 0x000000;
        }
        
        return -1; // Nenhuma cor encontrada
    }
    
    private static boolean hasItemTag(ItemStack stack, String tagName) {
        try {
            // Tentar vl_abyss primeiro
            ResourceLocation tagLocation = new ResourceLocation("vl_abyss", tagName);
            TagKey<Item> tag = ItemTags.create(tagLocation);
            if (stack.is(tag)) {
                return true;
            }
            
            // Tentar minecraft
            tagLocation = new ResourceLocation("minecraft", tagName);
            tag = ItemTags.create(tagLocation);
            if (stack.is(tag)) {
                return true;
            }
            
            // Tentar forge
            tagLocation = new ResourceLocation("forge", tagName);
            tag = ItemTags.create(tagLocation);
            if (stack.is(tag)) {
                return true;
            }
            
        } catch (Exception e) {
        }
        
        return false;
    }
}