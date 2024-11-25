package net.hydraoc.mtetm.worldgen;

import net.hydraoc.mtetm.MoreTetraMaterials;
import net.hydraoc.mtetm.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> MITHRIL_ORE_KEY = registerKey("mithril_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ADAMANTIUM_ORE_KEY = registerKey("adamantium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOOD_EMERALD_ORE_KEY = registerKey("blood_emerald_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PANDORIUM_ORE_KEY = registerKey("pandorium_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_GEODE_BLOCK_KEY = registerKey("nether_geode_block");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplacables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldSapphireOres = List.of(OreConfiguration.target(stoneReplaceable,
                        ModBlocks.MITHRIL_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_MITHRIL_ORE.get().defaultBlockState()));

        register(context, MITHRIL_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSapphireOres, 3));


        register(context, ADAMANTIUM_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplacables,
                ModBlocks.ADAMANTIUM_ORE.get().defaultBlockState(), 1));

        register(context, BLOOD_EMERALD_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplacables,
                ModBlocks.BLOOD_EMERALD_ORE.get().defaultBlockState(), 2));

        register(context, NETHER_GEODE_BLOCK_KEY, Feature.ORE, new OreConfiguration(netherrackReplacables,
                ModBlocks.NETHER_GEODE_BLOCK.get().defaultBlockState(), 8));


        register(context, PANDORIUM_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables,
                ModBlocks.PANDORIUM_ORE.get().defaultBlockState(), 3));
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MoreTetraMaterials.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}