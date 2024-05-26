package net.hydraoc.mtetm.menus;

import com.mojang.blaze3d.systems.RenderSystem;
import net.hydraoc.mtetm.MoreTetraMaterials;
import net.hydraoc.mtetm.block.entity.HellforgeBlockEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.BlastingRecipeBookComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.BlastFurnaceMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HellforgeScreen extends AbstractContainerScreen<HellforgeMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MoreTetraMaterials.MOD_ID, "textures/gui/hellforge_gui.png");

    public HellforgeScreen(HellforgeMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    int imageWidth = 172;
    int imageHeight = 174;

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    private void renderFuelBar(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isLit()) {
            int fuel = menu.getLitProgress();
            guiGraphics.blit(TEXTURE, x, y+50-fuel, 23, 50-fuel, 13, fuel);
        }
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        int progress = (this.menu).getBurnProgress();
        guiGraphics.blit(TEXTURE, x, y, 0, 166, progress, 16);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
