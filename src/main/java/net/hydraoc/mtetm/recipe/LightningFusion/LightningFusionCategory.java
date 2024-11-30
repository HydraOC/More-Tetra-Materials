package net.hydraoc.mtetm.recipe.LightningFusion;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.hydraoc.mtetm.MoreTetraMaterials;
import net.hydraoc.mtetm.recipe.Smashing.SmashingRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import se.mickelus.tetra.blocks.workbench.BasicWorkbenchBlock;

public class LightningFusionCategory implements IRecipeCategory<LightningFusionRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MoreTetraMaterials.MOD_ID, "lightning_fusion");
    public static final ResourceLocation TEXTURE = new ResourceLocation("jei", "textures/jei/gui/gui_vanilla.png");

    public static final RecipeType<LightningFusionRecipe> LIGHTNING_TYPE =
            new RecipeType<>(UID, LightningFusionRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public LightningFusionCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 220, 82, 34);
        this.icon = helper.createDrawableItemStack(new ItemStack(Blocks.LIGHTNING_ROD));
    }

    @Override
    public RecipeType<LightningFusionRecipe> getRecipeType() {
        return LIGHTNING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.mtetm.smashing_jei");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, LightningFusionRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 9).addIngredients(recipe.getPrimary());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 61, 9).addItemStack(recipe.getResultItem(null));
    }
}

