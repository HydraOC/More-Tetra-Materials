package net.hydraoc.mtetm.menus;


import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.hydraoc.mtetm.MoreTetraMaterials;
import net.hydraoc.mtetm.block.ModBlocks;
import net.hydraoc.mtetm.recipe.HellSmelting.HellSmeltingCategory;
import net.hydraoc.mtetm.recipe.HellSmelting.HellSmeltingRecipe;
import net.hydraoc.mtetm.recipe.Smashing.SmashingCategory;
import net.hydraoc.mtetm.recipe.Smashing.SmashingRecipe;
import net.hydraoc.mtetm.recipe.LightningFusion.LightningFusionRecipe;
import net.hydraoc.mtetm.recipe.LightningFusion.LightningFusionCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import net.minecraft.world.level.block.Blocks;
import se.mickelus.tetra.blocks.workbench.BasicWorkbenchBlock;


import java.util.List;

@JeiPlugin
public class MtetmJEIPlugin implements IModPlugin {
    public static final RecipeType<HellSmeltingRecipe> HELL_SMELTING = RecipeType.create(MoreTetraMaterials.MOD_ID, "hell_smelting", HellSmeltingRecipe.class);
    public static final RecipeType<SmashingRecipe> SMASHING = RecipeType.create(MoreTetraMaterials.MOD_ID, "smashing", SmashingRecipe.class);
    //public static final RecipeType<LightningFusionRecipe> LIGHTNING = RecipeType.create(MoreTetraMaterials.MOD_ID, "lightning_fusion", LightningFusionRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MoreTetraMaterials.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new HellSmeltingCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new SmashingCategory(registration.getJeiHelpers().getGuiHelper()));
        //registration.addRecipeCategories(new LightningFusionCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<HellSmeltingRecipe> hellSmeltingRecipes = recipeManager.getAllRecipesFor(HellSmeltingRecipe.Type.INSTANCE);
        registration.addRecipes(HellSmeltingCategory.HELL_SMELTING_TYPE, hellSmeltingRecipes);

        List<SmashingRecipe> smashingRecipes = recipeManager.getAllRecipesFor(SmashingRecipe.Type.INSTANCE);
        registration.addRecipes(SmashingCategory.SMASHING_TYPE, smashingRecipes);

        //List<LightningFusionRecipe> lightningRecipes = recipeManager.getAllRecipesFor(LightningFusionRecipe.Type.INSTANCE);
        //registration.addRecipes(LightningFusionCategory.LIGHTNING_TYPE, lightningRecipes);

    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.HELLFORGE.get()), RecipeTypes.BLASTING);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.HELLFORGE.get()), HELL_SMELTING);
        registry.addRecipeCatalyst(new ItemStack(BasicWorkbenchBlock.instance), SMASHING);
        //registry.addRecipeCatalyst(new ItemStack(Blocks.LIGHTNING_ROD), LIGHTNING);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(HellforgeScreen.class, 54, 29, 24, 17, HellSmeltingCategory.HELL_SMELTING_TYPE);
    }
}
