package net.hydraoc.mtetm.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.hydraoc.mtetm.screen.NetheriteFurnaceMenu;
import net.hydraoc.mtetm.MoreTetraMaterials;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NetheriteFurnaceScreen extends AbstractFurnaceScreen<FurnaceMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/netherite_furnace_gui.png");

    public NetheriteFurnaceScreen(FurnaceMenu p_98776_, Inventory p_98777_, Component p_98778_) {
        super(p_98776_, new SmeltingRecipeBookComponent(), p_98777_, p_98778_, TEXTURE);
    }
}