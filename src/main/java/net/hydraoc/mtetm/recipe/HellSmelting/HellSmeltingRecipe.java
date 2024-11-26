package net.hydraoc.mtetm.recipe.HellSmelting;

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
import org.jetbrains.annotations.Nullable;

public class HellSmeltingRecipe implements Recipe<Container> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack result;
    private final ResourceLocation id;
    private final float experience;
    private final int cookingtime;

    public HellSmeltingRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id, float xp, int time) {
        this.inputItems = inputItems;
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

        return inputItems.get(0).test(pContainer.getItem(0));
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

    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.HELLFORGE.get());
    }

    public int getCookingTime() {return this.cookingtime;}
    public float getExperience() {return this.experience;}

    public static class Type implements RecipeType<HellSmeltingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "helL_smelting";
    }

    public static class Serializer implements RecipeSerializer<HellSmeltingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(MoreTetraMaterials.MOD_ID, "hell_smelting");

        @Override
        public HellSmeltingRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredient");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));
            float experience = GsonHelper.getAsFloat(pSerializedRecipe, "experience", 0.0F);
            int cooktime = GsonHelper.getAsInt(pSerializedRecipe, "cookingtime", 200);

            return new HellSmeltingRecipe(inputs, output, pRecipeId, experience, cooktime);
        }

        @Override
        public @Nullable HellSmeltingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack output = pBuffer.readItem();
            float experience = pBuffer.readFloat();
            int cooktime = pBuffer.readInt();
            return new HellSmeltingRecipe(inputs, output, pRecipeId, experience, cooktime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, HellSmeltingRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.inputItems.size());

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItemStack(pRecipe.getResultItem(null),false);
            pBuffer.writeFloat(pRecipe.getExperience());
            pBuffer.writeInt(pRecipe.getCookingTime());
        }
    }
}