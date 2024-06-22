package net.hydraoc.mtetm.recipe;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.hydraoc.mtetm.MoreTetraMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import se.mickelus.tetra.blocks.workbench.BasicWorkbenchBlock;

public class SmashingCategory implements IRecipeCategory<SmashingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MoreTetraMaterials.MOD_ID, "smashing");
    public static final ResourceLocation TEXTURE = new ResourceLocation("jei", "textures/jei/gui/gui_vanilla.png");

    public static final RecipeType<SmashingRecipe> SMASHING_TYPE =
            new RecipeType<>(UID, SmashingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public SmashingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 220, 82, 34);
        this.icon = helper.createDrawableItemStack(new ItemStack(BasicWorkbenchBlock.instance));
    }

    @Override
    public RecipeType<SmashingRecipe> getRecipeType() {
        return SMASHING_TYPE;
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
    public void setRecipe(IRecipeLayoutBuilder builder, SmashingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 9).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 61, 9).addItemStack(recipe.getResultItem(null));
    }
}

