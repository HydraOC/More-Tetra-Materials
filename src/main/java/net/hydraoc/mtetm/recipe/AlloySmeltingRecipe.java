package net.hydraoc.mtetm.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.hydraoc.mtetm.MoreTetraMaterials;
import net.hydraoc.mtetm.block.ModBlocks;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

public class AlloySmeltingRecipe implements Recipe<Container> {
    private final NonNullList<Ingredient> inputItems1;
    private final NonNullList<Ingredient> inputItems2;
    private final ItemStack result;
    private final ResourceLocation id;
    private final float experience;
    private final int cookingtime;

    public AlloySmeltingRecipe(NonNullList<Ingredient> inputItems1, NonNullList<Ingredient> inputItems2, ItemStack output, ResourceLocation id, float xp, int time) {
        this.inputItems1 = inputItems1;
        this.inputItems2 = inputItems2;
        this.result = output;
        this.id = id;
        this.experience = xp;
        this.cookingtime = time;
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }
        return (inputItems1.get(0).test(pContainer.getItem(0)) || inputItems2.get(0).test(pContainer.getItem(3)));
    }

    @Override
    public ItemStack assemble(Container pContainer, RegistryAccess pRegistryAccess) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return result.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public NonNullList<Ingredient> getIngredientA() {
        return inputItems1;
    }

    public NonNullList<Ingredient> getIngredientB() {
        return inputItems2;
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.ALLOYFORGE.get());
    }

    public int getCookingTime() {return this.cookingtime;}
    public float getExperience() {return this.experience;}

    public static class Type implements RecipeType<AlloySmeltingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "alloy_smelting";
    }

    public static class Serializer implements RecipeSerializer<AlloySmeltingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(MoreTetraMaterials.MOD_ID, "alloy_smelting");

        @Override
        public AlloySmeltingRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {

            JsonArray ingredientsA = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredientA");
            JsonArray ingredientsB = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredientB");
            NonNullList<Ingredient> inputsA = NonNullList.withSize(1, Ingredient.EMPTY);
            NonNullList<Ingredient> inputsB = NonNullList.withSize(1, Ingredient.EMPTY);

            for(int i = 0; i < inputsA.size(); i++) {
                inputsA.set(i, Ingredient.fromJson(ingredientsA.get(i)));
            }

            for(int i = 0; i < inputsB.size(); i++) {
                inputsB.set(i, Ingredient.fromJson(ingredientsB.get(i)));
            }

            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));
            float experience = GsonHelper.getAsFloat(pSerializedRecipe, "experience", 0.0F);
            int cooktime = GsonHelper.getAsInt(pSerializedRecipe, "cookingtime", 200);

            return new AlloySmeltingRecipe(inputsA, inputsB, output, pRecipeId, experience, cooktime);
        }

        @Override
        public @Nullable AlloySmeltingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputsA = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputsA.size(); i++) {
                inputsA.set(i, Ingredient.fromNetwork(pBuffer));
            }

            NonNullList<Ingredient> inputsB = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputsB.size(); i++) {
                inputsB.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack output = pBuffer.readItem();
            float experience = pBuffer.readFloat();
            int cooktime = pBuffer.readInt();
            return new AlloySmeltingRecipe(inputsA, inputsB, output, pRecipeId, experience, cooktime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, AlloySmeltingRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.inputItems1.size());

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeInt(pRecipe.inputItems2.size());

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItemStack(pRecipe.getResultItem(null),false);
            pBuffer.writeFloat(pRecipe.getExperience());
            pBuffer.writeInt(pRecipe.getCookingTime());
        }
    }
}