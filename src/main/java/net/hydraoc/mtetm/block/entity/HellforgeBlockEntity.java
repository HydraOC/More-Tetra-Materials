package net.hydraoc.mtetm.block.entity;

import net.hydraoc.mtetm.menus.HellforgeMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

public class HellforgeBlockEntity extends AbstractHellforgeBE {
    private final ItemStackHandler itemHandler = new ItemStackHandler(2);

    public HellforgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.HELLFORGE_BE.get(), pos, state, RecipeType.SMELTING);
    }

    protected Component getDefaultName() {
        return Component.translatable("container.hellforge");
    }

    protected AbstractContainerMenu createMenu(int p_59293_, Inventory inv) {
        return new HellforgeMenu(p_59293_, inv, this, this.dataAccess);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

}
