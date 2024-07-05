package net.hydraoc.mtetm.menus;

import com.mojang.blaze3d.systems.RenderSystem;
import net.hydraoc.mtetm.MoreTetraMaterials;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HellforgeScreen extends AbstractContainerScreen<HellforgeMenu> {
    //Stores the texture location
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MoreTetraMaterials.MOD_ID, "textures/gui/hellforge_gui.png");

    public HellforgeScreen(HellforgeMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    //new image width and height variable declaration
    int imageWidth = 171;
    int imageHeight = 173;

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    //Main renderBg method for the class, contains calls to the rest of the rendering methods that require custom logic
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - 176) / 2;
        int y = (height - 166) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        renderFuelBar(guiGraphics,x+146,y+50);
        renderProgressArrow(guiGraphics,x+54,y+29);
        renderColorBars(guiGraphics,x,y);
    }

    //Renders the fuel bar
    private void renderFuelBar(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isLit()) {
            int fuel = menu.getLitProgress();
            guiGraphics.blit(TEXTURE, x, y-fuel, 172, 50-fuel, 13, fuel);
        }
    }

    //Renders the crafting progress arrow
    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        int progress = (this.menu).getBurnProgress();
        guiGraphics.blit(TEXTURE, x, y, 0, 174, progress, 16);
    }

    //Renders the color bars for the menu
    private void renderColorBars(GuiGraphics guiGraphics, int x , int y){
        if(menu.isLit()) {
            guiGraphics.blit(TEXTURE, x + 148, y + 7, 171, 7, 1, 3);
            guiGraphics.blit(TEXTURE, x + 135, y + 37, 171, 11, 5, 1);
            if((this.menu).getSlot(0).hasItem() || (this.menu).getBurnProgress() > 0) {
                guiGraphics.blit(TEXTURE, x + 114, y + 34, 177, 5, 17, 7);
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
