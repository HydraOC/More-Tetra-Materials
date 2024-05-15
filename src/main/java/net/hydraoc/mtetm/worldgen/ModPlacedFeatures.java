package net.hydraoc.mtetm.worldgen;

import net.hydraoc.mtetm.MoreTetraMaterials;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> MITHRIL_ORE_PLACED_KEY = registerKey("mithril_ore_placed");
    public static final ResourceKey<PlacedFeature> ADAMANTIUM_ORE_PLACED_KEY = registerKey("adamantium_ore_placed");
    public static final ResourceKey<PlacedFeature> BLOOD_EMERALD_ORE_PLACED_KEY = registerKey("blood_emerald_ore_placed");
    public static final ResourceKey<PlacedFeature> PANDORIUM_ORE_PLACED_KEY = registerKey("pandorium_ore_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, MITHRIL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MITHRIL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(5,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(20))));
        register(context, ADAMANTIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ADAMANTIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(5,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(25))));
        register(context, BLOOD_EMERALD_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOOD_EMERALD_ORE_KEY),
                ModOrePlacement.commonOrePlacement(6,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(25))));
        register(context, PANDORIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PANDORIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(8,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(80))));
    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(MoreTetraMaterials.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}