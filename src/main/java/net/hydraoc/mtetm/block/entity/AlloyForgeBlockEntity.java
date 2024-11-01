package net.hydraoc.mtetm.block.entity;

import net.hydraoc.mtetm.menus.AlloyForgeMenu;
import net.hydraoc.mtetm.menus.HellforgeMenu;
import net.hydraoc.mtetm.recipe.AlloySmeltingRecipe;
import net.hydraoc.mtetm.recipe.HellSmeltingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

public class AlloyForgeBlockEntity extends AbstractAlloyForgeBE {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4);

    public AlloyForgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLOYFORGE_BE.get(), pos, state, AlloySmeltingRecipe.Type.INSTANCE, RecipeType.BLASTING);
    }

    protected Component getDefaultName() {
        return Component.translatable("container.alloy_forge");
    }

    protected AlloyForgeMenu createMenu(int p_59293_, Inventory inv) {
        return new AlloyForgeMenu(p_59293_, inv, this, this.dataAccess);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

}
