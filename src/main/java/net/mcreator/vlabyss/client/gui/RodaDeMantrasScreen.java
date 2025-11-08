package net.mcreator.vlabyss.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.vlabyss.world.inventory.RodaDeMantrasMenu;
import net.mcreator.vlabyss.procedures.TextoDosNomesProcedure;
import net.mcreator.vlabyss.procedures.TextoDoDanoProcedure;
import net.mcreator.vlabyss.procedures.TextoDoCustoProcedure;
import net.mcreator.vlabyss.procedures.NivelHabilidade1Procedure;
import net.mcreator.vlabyss.procedures.ApareceInformacoesProcedure;
import net.mcreator.vlabyss.network.RodaDeMantrasButtonMessage;
import net.mcreator.vlabyss.init.VlAbyssModScreens;
import net.mcreator.vlabyss.VlAbyssMod;

import com.mojang.blaze3d.systems.RenderSystem;

public class RodaDeMantrasScreen extends AbstractContainerScreen<RodaDeMantrasMenu> implements VlAbyssModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	ImageButton imagebutton_coracao;
	ImageButton imagebutton_coracao1;
	ImageButton imagebutton_coracao2;
	ImageButton imagebutton_coracao3;
	ImageButton imagebutton_coracao4;
	ImageButton imagebutton_coracao5;

	public RodaDeMantrasScreen(RodaDeMantrasMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		if (ApareceInformacoesProcedure.execute(entity))
			guiGraphics.drawString(this.font, NivelHabilidade1Procedure.execute(entity), 21, -23, -1, false);
		if (ApareceInformacoesProcedure.execute(entity))
			guiGraphics.drawString(this.font, TextoDosNomesProcedure.execute(entity), 54, -36, -1, false);
		if (ApareceInformacoesProcedure.execute(entity))
			guiGraphics.drawString(this.font, TextoDoCustoProcedure.execute(entity), 21, -13, -12829636, false);
		if (ApareceInformacoesProcedure.execute(entity))
			guiGraphics.drawString(this.font, TextoDoDanoProcedure.execute(entity), 97, -23, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_coracao = new ImageButton(this.leftPos + 70, this.topPos + 10, 32, 32, 0, 0, 32, ResourceLocation.parse("vl_abyss:textures/screens/atlas/imagebutton_coracao.png"), 32, 64, e -> {
			int x = RodaDeMantrasScreen.this.x;
			int y = RodaDeMantrasScreen.this.y;
			if (true) {
				VlAbyssMod.PACKET_HANDLER.sendToServer(new RodaDeMantrasButtonMessage(0, x, y, z));
				RodaDeMantrasButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_coracao);
		imagebutton_coracao1 = new ImageButton(this.leftPos + 6, this.topPos + 31, 32, 32, 0, 0, 32, ResourceLocation.parse("vl_abyss:textures/screens/atlas/imagebutton_coracao1.png"), 32, 64, e -> {
		});
		this.addRenderableWidget(imagebutton_coracao1);
		imagebutton_coracao2 = new ImageButton(this.leftPos + 141, this.topPos + 31, 32, 32, 0, 0, 32, ResourceLocation.parse("vl_abyss:textures/screens/atlas/imagebutton_coracao2.png"), 32, 64, e -> {
		});
		this.addRenderableWidget(imagebutton_coracao2);
		imagebutton_coracao3 = new ImageButton(this.leftPos + 6, this.topPos + 93, 32, 32, 0, 0, 32, ResourceLocation.parse("vl_abyss:textures/screens/atlas/imagebutton_coracao3.png"), 32, 64, e -> {
		});
		this.addRenderableWidget(imagebutton_coracao3);
		imagebutton_coracao4 = new ImageButton(this.leftPos + 141, this.topPos + 93, 32, 32, 0, 0, 32, ResourceLocation.parse("vl_abyss:textures/screens/atlas/imagebutton_coracao4.png"), 32, 64, e -> {
		});
		this.addRenderableWidget(imagebutton_coracao4);
		imagebutton_coracao5 = new ImageButton(this.leftPos + 70, this.topPos + 115, 32, 32, 0, 0, 32, ResourceLocation.parse("vl_abyss:textures/screens/atlas/imagebutton_coracao5.png"), 32, 64, e -> {
		});
		this.addRenderableWidget(imagebutton_coracao5);
	}
}