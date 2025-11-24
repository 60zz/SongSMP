package net.mcreator.vlabyss.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.vlabyss.world.inventory.SegundaHabilidadeMantraMenu;
import net.mcreator.vlabyss.procedures.MostraOpcoesRespiroProcedure;
import net.mcreator.vlabyss.procedures.MostraOpcoesChamaProcedure;
import net.mcreator.vlabyss.network.SegundaHabilidadeMantraButtonMessage;
import net.mcreator.vlabyss.init.VlAbyssModScreens;
import net.mcreator.vlabyss.VlAbyssMod;

import com.mojang.blaze3d.systems.RenderSystem;

public class SegundaHabilidadeMantraScreen extends AbstractContainerScreen<SegundaHabilidadeMantraMenu> implements VlAbyssModScreens.ScreenAccessor {
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

	public SegundaHabilidadeMantraScreen(SegundaHabilidadeMantraMenu container, Inventory inventory, Component text) {
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
	}

	@Override
	public void init() {
		super.init();
		imagebutton_coracao = new ImageButton(this.leftPos + 69, this.topPos + 61, 32, 32, 0, 0, 32, ResourceLocation.parse("vl_abyss:textures/screens/atlas/imagebutton_coracao.png"), 32, 64, e -> {
			int x = SegundaHabilidadeMantraScreen.this.x;
			int y = SegundaHabilidadeMantraScreen.this.y;
			if (MostraOpcoesRespiroProcedure.execute(entity)) {
				VlAbyssMod.PACKET_HANDLER.sendToServer(new SegundaHabilidadeMantraButtonMessage(0, x, y, z));
				SegundaHabilidadeMantraButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_coracao);
		imagebutton_coracao1 = new ImageButton(this.leftPos + -21, this.topPos + 61, 32, 32, 0, 0, 32, ResourceLocation.parse("vl_abyss:textures/screens/atlas/imagebutton_coracao1.png"), 32, 64, e -> {
			int x = SegundaHabilidadeMantraScreen.this.x;
			int y = SegundaHabilidadeMantraScreen.this.y;
			if (MostraOpcoesRespiroProcedure.execute(entity)) {
				VlAbyssMod.PACKET_HANDLER.sendToServer(new SegundaHabilidadeMantraButtonMessage(1, x, y, z));
				SegundaHabilidadeMantraButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_coracao1);
		imagebutton_coracao2 = new ImageButton(this.leftPos + 159, this.topPos + 61, 32, 32, 0, 0, 32, ResourceLocation.parse("vl_abyss:textures/screens/atlas/imagebutton_coracao2.png"), 32, 64, e -> {
			int x = SegundaHabilidadeMantraScreen.this.x;
			int y = SegundaHabilidadeMantraScreen.this.y;
			if (MostraOpcoesRespiroProcedure.execute(entity)) {
				VlAbyssMod.PACKET_HANDLER.sendToServer(new SegundaHabilidadeMantraButtonMessage(2, x, y, z));
				SegundaHabilidadeMantraButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_coracao2);
		imagebutton_coracao3 = new ImageButton(this.leftPos + -21, this.topPos + 61, 32, 32, 0, 0, 32, ResourceLocation.parse("vl_abyss:textures/screens/atlas/imagebutton_coracao3.png"), 32, 64, e -> {
			int x = SegundaHabilidadeMantraScreen.this.x;
			int y = SegundaHabilidadeMantraScreen.this.y;
			if (MostraOpcoesChamaProcedure.execute(entity)) {
				VlAbyssMod.PACKET_HANDLER.sendToServer(new SegundaHabilidadeMantraButtonMessage(3, x, y, z));
				SegundaHabilidadeMantraButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_coracao3);
		imagebutton_coracao4 = new ImageButton(this.leftPos + 69, this.topPos + 61, 32, 32, 0, 0, 32, ResourceLocation.parse("vl_abyss:textures/screens/atlas/imagebutton_coracao4.png"), 32, 64, e -> {
			int x = SegundaHabilidadeMantraScreen.this.x;
			int y = SegundaHabilidadeMantraScreen.this.y;
			if (MostraOpcoesChamaProcedure.execute(entity)) {
				VlAbyssMod.PACKET_HANDLER.sendToServer(new SegundaHabilidadeMantraButtonMessage(4, x, y, z));
				SegundaHabilidadeMantraButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_coracao4);
		imagebutton_coracao5 = new ImageButton(this.leftPos + 159, this.topPos + 61, 32, 32, 0, 0, 32, ResourceLocation.parse("vl_abyss:textures/screens/atlas/imagebutton_coracao5.png"), 32, 64, e -> {
			int x = SegundaHabilidadeMantraScreen.this.x;
			int y = SegundaHabilidadeMantraScreen.this.y;
			if (MostraOpcoesChamaProcedure.execute(entity)) {
				VlAbyssMod.PACKET_HANDLER.sendToServer(new SegundaHabilidadeMantraButtonMessage(5, x, y, z));
				SegundaHabilidadeMantraButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_coracao5);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		this.imagebutton_coracao.visible = MostraOpcoesRespiroProcedure.execute(entity);
		this.imagebutton_coracao1.visible = MostraOpcoesRespiroProcedure.execute(entity);
		this.imagebutton_coracao2.visible = MostraOpcoesRespiroProcedure.execute(entity);
		this.imagebutton_coracao3.visible = MostraOpcoesChamaProcedure.execute(entity);
		this.imagebutton_coracao4.visible = MostraOpcoesChamaProcedure.execute(entity);
		this.imagebutton_coracao5.visible = MostraOpcoesChamaProcedure.execute(entity);
	}
}