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

    public static final RegistryObject<BlockEntityType<HellforgeBlockEntity>> HELLFORGE_BE =
            BLOCK_ENTITIES.register("hellforge_be", () ->
                    BlockEntityType.Builder.of(HellforgeBlockEntity::new,
                            ModBlocks.HELLFORGE.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}