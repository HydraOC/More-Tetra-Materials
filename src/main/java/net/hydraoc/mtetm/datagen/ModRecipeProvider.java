package net.hydraoc.mtetm.datagen;

import net.hydraoc.mtetm.MoreTetraMaterials;
import net.hydraoc.mtetm.block.ModBlocks;
import net.hydraoc.mtetm.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private static final List<ItemLike> MITHRIL_SMELTABLES = List.of(ModItems.RAW_MITHRIL.get(),
            ModBlocks.MITHRIL_ORE.get(), ModBlocks.DEEPSLATE_MITHRIL_ORE.get(), ModItems.MITHRIL_DUST.get());

    private static final List<ItemLike> ADAMANTIUM_SMELTABLES = List.of(ModItems.RAW_ADAMANTIUM.get(),
            ModBlocks.ADAMANTIUM_ORE.get(), ModItems.ADAMANTIUM_DUST.get());

    private static final List<ItemLike> PANDORIUM_SMELTABLES = List.of(ModItems.RAW_PANDORIUM.get(),
            ModBlocks.PANDORIUM_ORE.get(), ModItems.PANDORIUM_DUST.get());

    private static final List<ItemLike> ENDER_ADAMANTIUM_SMELTABLES = List.of(ModItems.ENDERFUSED_ADAMANTIUM_DUST.get());
    public ModRecipeProvider(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        oreSmelting(consumer, MITHRIL_SMELTABLES, RecipeCategory.MISC, ModItems.MITHRIL_INGOT.get(), 0.25f, 200, "mithril");
        oreBlasting(consumer, MITHRIL_SMELTABLES, RecipeCategory.MISC, ModItems.MITHRIL_INGOT.get(), 0.25f, 100, "mithril");

        oreSmelting(consumer, ADAMANTIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ADAMANTIUM_INGOT.get(), 0.25f, 200, "adamantium");
        oreBlasting(consumer, ADAMANTIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ADAMANTIUM_INGOT.get(), 0.25f, 100, "adamantium");

        oreSmelting(consumer, PANDORIUM_SMELTABLES, RecipeCategory.MISC, ModItems.PANDORIUM_INGOT.get(), 0.25f, 200, "uru");
        oreBlasting(consumer, PANDORIUM_SMELTABLES, RecipeCategory.MISC, ModItems.PANDORIUM_INGOT.get(), 0.25f, 100, "uru");

        oreSmelting(consumer, ENDER_ADAMANTIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ENDERFUSED_ADAMANTIUM_INGOT.get(), 0.25f, 200, "ender_adamantium");
        oreBlasting(consumer, ENDER_ADAMANTIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ENDERFUSED_ADAMANTIUM_INGOT.get(), 0.25f, 100, "ender_adamantium");

        oreBlockCrafting(ModItems.MITHRIL_INGOT, ModBlocks.MITHRIL_BLOCK, consumer);
        oreBlockSplitting(ModBlocks.MITHRIL_BLOCK, ModItems.MITHRIL_INGOT, consumer);
        oreBlockCrafting(ModItems.RAW_MITHRIL, ModBlocks.RAW_MITHRIL_BLOCK, consumer);
        oreBlockSplitting(ModBlocks.RAW_MITHRIL_BLOCK, ModItems.RAW_MITHRIL, consumer);

        oreBlockCrafting(ModItems.ADAMANTIUM_INGOT, ModBlocks.ADAMANTIUM_BLOCK, consumer);
        oreBlockSplitting(ModBlocks.ADAMANTIUM_BLOCK, ModItems.ADAMANTIUM_INGOT, consumer);
        oreBlockCrafting(ModItems.RAW_ADAMANTIUM, ModBlocks.RAW_ADAMANTIUM_BLOCK, consumer);
        oreBlockSplitting(ModBlocks.RAW_ADAMANTIUM_BLOCK, ModItems.RAW_ADAMANTIUM, consumer);

        oreBlockCrafting(ModItems.PANDORIUM_INGOT, ModBlocks.PANDORIUM_BLOCK, consumer);
        oreBlockSplitting(ModBlocks.PANDORIUM_BLOCK, ModItems.PANDORIUM_INGOT, consumer);
        oreBlockCrafting(ModItems.RAW_PANDORIUM, ModBlocks.RAW_PANDORIUM_BLOCK, consumer);
        oreBlockSplitting(ModBlocks.RAW_PANDORIUM_BLOCK, ModItems.RAW_PANDORIUM, consumer);

        oreBlockCrafting(ModItems.ENDERFUSED_ADAMANTIUM_INGOT, ModBlocks.ENDERFUSED_ADAMNTIUM_BLOCK, consumer);
        oreBlockSplitting(ModBlocks.ENDERFUSED_ADAMNTIUM_BLOCK, ModItems.ENDERFUSED_ADAMANTIUM_INGOT, consumer);
        oreBlockCrafting(ModItems.BLOOD_EMERALD, ModBlocks.BLOOD_EMERALD_BLOCK, consumer);
        oreBlockSplitting(ModBlocks.BLOOD_EMERALD_BLOCK, ModItems.BLOOD_EMERALD, consumer);

    }

    protected static void oreBlockCrafting(RegistryObject<Item> inputItem, RegistryObject<Block> outputBlock, Consumer<FinishedRecipe> consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, outputBlock.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S',inputItem.get())
                .unlockedBy(getHasName(inputItem.get()), has(inputItem.get()))
                .save(consumer);
    }

    protected static void oreBlockSplitting(RegistryObject<Block> inputBlock, RegistryObject<Item> outputItem, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, outputItem.get(), 9)
                .requires(inputBlock.get())
                .unlockedBy(getHasName(inputBlock.get()), has((inputBlock.get())))
                .save(consumer);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> p_250654_, List<ItemLike> p_250172_, RecipeCategory p_250588_, ItemLike p_251868_, float p_250789_, int p_252144_, String p_251687_) {
        oreCooking(p_250654_, RecipeSerializer.SMELTING_RECIPE, p_250172_, p_250588_, p_251868_, p_250789_, p_252144_, p_251687_, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> p_248775_, List<ItemLike> p_251504_, RecipeCategory p_248846_, ItemLike p_249735_, float p_248783_, int p_250303_, String p_251984_) {
        oreCooking(p_248775_, RecipeSerializer.BLASTING_RECIPE, p_251504_, p_248846_, p_249735_, p_248783_, p_250303_, p_251984_, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> p_250791_, RecipeSerializer<? extends AbstractCookingRecipe> p_251817_, List<ItemLike> p_249619_, RecipeCategory p_251154_, ItemLike p_250066_, float p_251871_, int p_251316_, String p_251450_, String p_249236_) {
        Iterator var9 = p_249619_.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), p_251154_, p_250066_, p_251871_, p_251316_, p_251817_).group(p_251450_).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(p_250791_, MoreTetraMaterials.MOD_ID +":" +  getItemName(p_250066_) + p_249236_ + "_" + getItemName(itemlike));
        }

    }
}
