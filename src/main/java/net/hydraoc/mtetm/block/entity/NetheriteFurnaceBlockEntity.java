package net.hydraoc.mtetm.block.entity;

import net.hydraoc.mtetm.item.ModItems;
import net.hydraoc.mtetm.screen.NetheriteFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NetheriteFurnaceBlockEntity extends AbstractFurnaceBlockEntity {
    public NetheriteFurnaceBlockEntity(BlockPos p_155545_, BlockState p_155546_) {
        super(BlockEntityType.FURNACE, p_155545_, p_155546_, RecipeType.SMELTING);
    }

    protected Component getDefaultName() {
        return Component.translatable("container.netherite_furnace");
    }

    protected AbstractContainerMenu createMenu(int p_59293_, Inventory p_59294_) {
        return new NetheriteFurnaceMenu(p_59293_, p_59294_, this, this.dataAccess);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.mtetm.netherite_furnace");
    }

}