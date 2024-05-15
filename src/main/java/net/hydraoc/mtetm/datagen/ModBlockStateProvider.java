package net.hydraoc.mtetm.datagen;

import net.hydraoc.mtetm.MoreTetraMaterials;
import net.hydraoc.mtetm.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MoreTetraMaterials.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.MITHRIL_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_MITHRIL_ORE);
        blockWithItem(ModBlocks.RAW_MITHRIL_BLOCK);
        blockWithItem(ModBlocks.MITHRIL_BLOCK);

        blockWithItem(ModBlocks.ADAMANTIUM_ORE);
        blockWithItem(ModBlocks.RAW_ADAMANTIUM_BLOCK);
        blockWithItem(ModBlocks.ADAMANTIUM_BLOCK);
        blockWithItem(ModBlocks.BLOOD_EMERALD_ORE);
        blockWithItem(ModBlocks.BLOOD_EMERALD_BLOCK);
        blockWithItem(ModBlocks.ENDERFUSED_ADAMNTIUM_BLOCK);

        blockWithItem(ModBlocks.PANDORIUM_ORE);
        blockWithItem(ModBlocks.RAW_PANDORIUM_BLOCK);
        blockWithItem(ModBlocks.PANDORIUM_BLOCK);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

}

