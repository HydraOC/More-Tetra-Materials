package net.hydraoc.mtetm.block.entity;

import net.hydraoc.mtetm.MoreTetraMaterials;
import net.hydraoc.mtetm.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MoreTetraMaterials.MOD_ID);

    public static final RegistryObject<BlockEntityType<NetheriteFurnaceBlockEntity>> NETHERITE_FURNACE_BE =
            BLOCK_ENTITIES.register("netherite_furnace_be", () ->
                    BlockEntityType.Builder.of(NetheriteFurnaceBlockEntity::new,
                            ModBlocks.NETHERITE_FURNACE.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}