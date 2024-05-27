package net.hydraoc.mtetm.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public abstract class AbstractCCR implements Recipe<Container> {
    protected ResourceLocation id  = null;
    private CookingBookCategory category = null;
    protected String group = null;
    protected Ingredient ingredient = null;
    protected ItemStack result = null;
    protected float experience = 0;
    protected int cookingTime = 0;

    public AbstractCCR(ResourceLocation p_249379_, String p_249518_, CookingBookCategory p_250891_, Ingredient p_251354_, ItemStack p_252185_, float p_252165_, int p_250256_) {
        this.category = p_250891_;
        this.id = p_249379_;
        this.group = p_249518_;
        this.ingredient = p_251354_;
        this.result = p_252185_;
        this.experience = p_252165_;
        this.cookingTime = p_250256_;
    }

    public AbstractCCR(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {
        this.ingredient = inputItems.get(0);
        this.result = output;
        this.id = id;
    }

    public boolean matches(Container p_43748_, Level p_43749_) {
        return this.ingredient.test(p_43748_.getItem(0));
    }

    public ItemStack assemble(Container p_43746_, RegistryAccess p_267063_) {
        return this.result.copy();
    }

    public boolean canCraftInDimensions(int p_43743_, int p_43744_) {
        return true;
    }

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> $$0 = NonNullList.create();
        $$0.add(this.ingredient);
        return $$0;
    }

    public float getExperience() {
        return this.experience;
    }

    public ItemStack getResultItem(RegistryAccess p_266851_) {
        return this.result;
    }

    public String getGroup() {
        return this.group;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public CookingBookCategory category() {
        return this.category;
    }

}
