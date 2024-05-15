package net.hydraoc.mtetm.datagen;

import net.hydraoc.mtetm.MoreTetraMaterials;
import net.hydraoc.mtetm.block.ModBlocks;
import net.hydraoc.mtetm.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MoreTetraMaterials.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //Mithril Tag addition
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.MITHRIL_ORE.get());
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(ModBlocks.MITHRIL_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.DEEPSLATE_MITHRIL_ORE.get());
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(ModBlocks.DEEPSLATE_MITHRIL_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.RAW_MITHRIL_BLOCK.get());
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(ModBlocks.RAW_MITHRIL_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.MITHRIL_BLOCK.get());
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(ModBlocks.MITHRIL_BLOCK.get());

        //Adamantium and Blood Emerald Tag addition
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ADAMANTIUM_ORE.get());
        this.tag(ModTags.Blocks.NEEDS_MITHRIL_TOOL).add(ModBlocks.ADAMANTIUM_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.RAW_ADAMANTIUM_BLOCK.get());
        this.tag(ModTags.Blocks.NEEDS_MITHRIL_TOOL).add(ModBlocks.RAW_ADAMANTIUM_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ADAMANTIUM_BLOCK.get());
        this.tag(ModTags.Blocks.NEEDS_MITHRIL_TOOL).add(ModBlocks.ADAMANTIUM_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.BLOOD_EMERALD_ORE.get());
        this.tag(ModTags.Blocks.NEEDS_MITHRIL_TOOL).add(ModBlocks.BLOOD_EMERALD_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.BLOOD_EMERALD_BLOCK.get());
        this.tag(ModTags.Blocks.NEEDS_MITHRIL_TOOL).add(ModBlocks.BLOOD_EMERALD_BLOCK.get());

        //Uru and Ender Adamantium tag addition
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.PANDORIUM_ORE.get());
        this.tag(ModTags.Blocks.NEEDS_ADAMANTIUM_TOOL).add(ModBlocks.PANDORIUM_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.RAW_PANDORIUM_BLOCK.get());
        this.tag(ModTags.Blocks.NEEDS_ADAMANTIUM_TOOL).add(ModBlocks.RAW_PANDORIUM_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.PANDORIUM_BLOCK.get());
        this.tag(ModTags.Blocks.NEEDS_ADAMANTIUM_TOOL).add(ModBlocks.PANDORIUM_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ENDERFUSED_ADAMNTIUM_BLOCK.get());
        this.tag(ModTags.Blocks.NEEDS_ADAMANTIUM_TOOL).add(ModBlocks.ENDERFUSED_ADAMNTIUM_BLOCK.get());
    }
}
