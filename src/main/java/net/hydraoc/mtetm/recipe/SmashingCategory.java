package net.hydraoc.mtetm.recipe;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.hydraoc.mtetm.MoreTetraMaterials;
import net.hydraoc.mtetm.block.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class SmashingCategory implements IRecipeCategory<SmashingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MoreTetraMaterials.MOD_ID, "smashing");
    public static final ResourceLocation TEXTURE = new ResourceLocation(MoreTetraMaterials.MOD_ID,
            "textures/gui/smashing_jei.png");

    public static final RecipeType<SmashingRecipe> SMASHING_TYPE =
            new RecipeType<>(UID, SmashingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public SmashingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 25, 0, 146, 75);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.HELLFORGE.get()));
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
        builder.addSlot(RecipeIngredientRole.INPUT, 31 - 25, 30).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 91 - 25, 30).addItemStack(recipe.getResultItem(null));
    }
}

