package net.hydraoc.mtetm.menus.slots;

import net.hydraoc.mtetm.item.ModItems;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class FuelSlot extends Slot {

    public FuelSlot(Container container, int index, int x, int y) {
        super(container, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return (stack.getItem() == ModItems.THUNDER_CRYSTAL.get());
    }

}
