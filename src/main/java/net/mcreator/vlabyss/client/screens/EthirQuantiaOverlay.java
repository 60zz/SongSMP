package net.mcreator.vlabyss.client.screens;

import org.checkerframework.checker.units.qual.h;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.Minecraft;

import net.mcreator.vlabyss.procedures.VisivelMantraVentoProcedure;
import net.mcreator.vlabyss.procedures.VisivelMantraRaioProcedure;
import net.mcreator.vlabyss.procedures.VisivelMantraLuzProcedure;
import net.mcreator.vlabyss.procedures.VisivelMantraGeloProcedure;
import net.mcreator.vlabyss.procedures.VisivelMantraFogoProcedure;
import net.mcreator.vlabyss.procedures.VisivelMantraEscuridaoProcedure;
import net.mcreator.vlabyss.procedures.VisivelBarraMantraProcedure;
import net.mcreator.vlabyss.network.VlAbyssModVariables;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class EthirQuantiaOverlay {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void eventHandler(RenderGuiEvent.Pre event) {
        int w = event.getWindow().getGuiScaledWidth();
        int h = event.getWindow().getGuiScaledHeight();
        Level world = null;
        double x = 0;
        double y = 0;
        double z = 0;
        Player entity = Minecraft.getInstance().player;
        if (entity != null) {
            world = entity.level();
            x = entity.getX();
            y = entity.getY();
            z = entity.getZ();
        }

        // Verifica se o player existe antes de continuar
        if (entity == null) return;

        // Obtém as variáveis do player
        VlAbyssModVariables.PlayerVariables playerVars = entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
            .orElse(new VlAbyssModVariables.PlayerVariables());

        // Valores de mana
        double currentMana = playerVars.Ethir;
        double maxMana = playerVars.MaxEthir;

        // Calcula a porcentagem de mana (0.0 a 1.0)
        double manaPercentage = maxMana > 0 ? Math.max(0.0, Math.min(1.0, currentMana / maxMana)) : 0.0;

        // Calcula a largura da parte preenchida
        int filledWidth = (int) (150 * manaPercentage);

        // Posição centralizada da barra (metade da largura da barra = 150/2 = 75)
        int barX = w / 2 - 75;
        int barY = h - h / 6 - 10; // Mais próximo da barra de vida/fome

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        if (true) {
            if (VisivelBarraMantraProcedure.execute(entity)) {
                // Renderiza a barra vazia (fundo)
                event.getGuiGraphics().blit(new ResourceLocation("vl_abyss:textures/screens/barra_vazia.png"), barX, barY, 0, 0, 150, 12, 150, 12);
            }

            // Renderiza apenas a parte preenchida da barra com a cor apropriada
            if (filledWidth > 0) {
                if (VisivelMantraEscuridaoProcedure.execute(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("vl_abyss:textures/screens/barra_cheia.png"), barX, barY, 0, 0, filledWidth, 12, 150, 12);
                }
                if (VisivelMantraFogoProcedure.execute(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("vl_abyss:textures/screens/laranja.png"), barX, barY, 0, 0, filledWidth, 12, 150, 12);
                }
                if (VisivelMantraVentoProcedure.execute(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("vl_abyss:textures/screens/verde_azulado.png"), barX, barY, 0, 0, filledWidth, 12, 150, 12);
                }
                if (VisivelMantraLuzProcedure.execute(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("vl_abyss:textures/screens/branco.png"), barX, barY, 0, 0, filledWidth, 12, 150, 12);
                }
                if (VisivelMantraRaioProcedure.execute(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("vl_abyss:textures/screens/azul.png"), barX, barY, 0, 0, filledWidth, 12, 150, 12);
                }
                if (VisivelMantraGeloProcedure.execute(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("vl_abyss:textures/screens/ciano.png"), barX, barY, 0, 0, filledWidth, 12, 150, 12);
                }
            }
        }
        RenderSystem.depthMask(true);
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1, 1, 1, 1);
    }
}