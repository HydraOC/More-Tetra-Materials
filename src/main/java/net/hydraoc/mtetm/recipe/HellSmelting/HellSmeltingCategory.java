package net.hydraoc.mtetm.recipe.HellSmelting;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.hydraoc.mtetm.MoreTetraMaterials;
import net.hydraoc.mtetm.block.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import se.mickelus.tetra.items.cell.ThermalCellItem;

public class HellSmeltingCategory implements IRecipeCategory<HellSmeltingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MoreTetraMaterials.MOD_ID, "hell_smelting");
    public static final ResourceLocation TEXTURE = new ResourceLocation(MoreTetraMaterials.MOD_ID,
            "textures/gui/hellforge_jei.png");

    public static final RecipeType<HellSmeltingRecipe> HELL_SMELTING_TYPE =
            new RecipeType<>(UID, HellSmeltingRecipe.class);

    private final IDrawable background;
    private final IDrawableAnimated arrow;
    private final IDrawable icon;
    private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;

    public HellSmeltingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 25, 0, 146, 75);;
        this.arrow = helper.drawableBuilder(TEXTURE, 0, 174, 24, 17)
                .buildAnimated(400, IDrawableAnimated.StartDirection.LEFT, false);
        this.icon = helper.createDrawableItemStack(new ItemStack(ModBlocks.HELLFORGE.get()));
        this.cachedArrows = CacheBuilder.newBuilder().maximumSize(25L).build(new CacheLoader<Integer, IDrawableAnimated>() {
            public IDrawableAnimated load(Integer cookTime) {
                return helper.drawableBuilder(TEXTURE, 0, 174, 24, 17).buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
            }
        });
    }

    public void draw(HellSmeltingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IDrawableAnimated arrow = this.getArrow(recipe);
        arrow.draw(guiGraphics, 29, 29);
        this.drawExperience(recipe, guiGraphics, 0);
        this.drawCookTime(recipe, guiGraphics, 10);
    }

    @Override
    public RecipeType<HellSmeltingRecipe> getRecipeType() {
        return HELL_SMELTING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.mtetm.hell_smelting_jei");
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
    public void setRecipe(IRecipeLayoutBuilder builder, HellSmeltingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 31-25, 30).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 91-25, 30).addItemStack(recipe.getResultItem(null));
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 144-25, 56).addItemStack(new ItemStack(ThermalCellItem.instance.get()));
    }

    protected IDrawableAnimated getArrow(HellSmeltingRecipe recipe) {
        int cookTime = recipe.getCookingTime();
        if (cookTime <= 0) {
            cookTime = 200;
        }

        return (IDrawableAnimated)this.cachedArrows.getUnchecked(cookTime);
    }

    protected void drawExperience(HellSmeltingRecipe recipe, GuiGraphics guiGraphics, int y) {
        float experience = recipe.getExperience();
        if (experience > 0.0F) {
            Component experienceString = Component.translatable("gui.jei.category.smelting.experience", new Object[]{experience});
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(experienceString);
            guiGraphics.drawString(fontRenderer, experienceString, this.getWidth() - stringWidth-35, y, -8355712, false);
        }

    }

    protected void drawCookTime(HellSmeltingRecipe recipe, GuiGraphics guiGraphics, int y) {
        int cookTime = recipe.getCookingTime();
        if (cookTime > 0) {
            int cookTimeSeconds = cookTime / 20;
            Component timeString = Component.translatable("gui.jei.category.smelting.time.seconds", new Object[]{cookTimeSeconds});
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(timeString);
            guiGraphics.drawString(fontRenderer, timeString, this.getWidth() - stringWidth-35, y, -8355712, false);
        }

    }
}
