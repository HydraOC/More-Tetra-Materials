package net.hydraoc.mtetm.item;

import net.hydraoc.mtetm.MoreTetraMaterials;
import net.hydraoc.mtetm.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import se.mickelus.tetra.TetraRegistries;

import java.util.List;

public class ModToolTiers {

    public static final Tier MITHRIL = new ForgeTier(5, 2000, 9f, 3f, 24,
                    ModTags.Blocks.NEEDS_MITHRIL_TOOL, () -> Ingredient.of(ModItems.MITHRIL_INGOT.get()));

    public static final Tier ADAMANTIUM = new ForgeTier(6, 3000, 10f, 4f, 24,
                    ModTags.Blocks.NEEDS_ADAMANTIUM_TOOL, () -> Ingredient.of(ModItems.ADAMANTIUM_INGOT.get()));

    public static void init(){
        TierSortingRegistry.registerTier(ModToolTiers.MITHRIL, new ResourceLocation(MoreTetraMaterials.MOD_ID, "mithril"), List.of(TetraRegistries.forgeHammerTier), List.of());
        TierSortingRegistry.registerTier(ModToolTiers.ADAMANTIUM, new ResourceLocation(MoreTetraMaterials.MOD_ID, "adamantium"), List.of(ModToolTiers.MITHRIL), List.of());
    }
}