package net.hydraoc.mtetm.screen;

import net.hydraoc.mtetm.block.ModBlocks;
import net.hydraoc.mtetm.block.ModBlocks;
import net.hydraoc.mtetm.block.entity.NetheriteFurnaceBlockEntity;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class NetheriteFurnaceMenu extends AbstractFurnaceMenu {
    public NetheriteFurnaceMenu(int p_39532_, Inventory p_39533_) {
        super(MenuType.FURNACE, RecipeType.SMELTING, RecipeBookType.FURNACE, p_39532_, p_39533_);
    }

    public NetheriteFurnaceMenu(int p_39535_, Inventory p_39536_, Container p_39537_, ContainerData p_39538_) {
        super(MenuType.FURNACE, RecipeType.SMELTING, RecipeBookType.FURNACE, p_39535_, p_39536_, p_39537_, p_39538_);
    }
}