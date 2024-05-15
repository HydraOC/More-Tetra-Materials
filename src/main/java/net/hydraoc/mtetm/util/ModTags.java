package net.hydraoc.mtetm.util;

import net.hydraoc.mtetm.MoreTetraMaterials;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_MITHRIL_TOOL
                = tag("needs_mithril_tool");

        public static final TagKey<Block> NEEDS_ADAMANTIUM_TOOL
                = tag("needs_adamantium_tool");


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(MoreTetraMaterials.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
}