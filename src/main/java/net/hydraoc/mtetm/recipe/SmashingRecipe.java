package net.hydraoc.mtetm.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.hydraoc.mtetm.MoreTetraMaterials;
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

public class SmashingRecipe implements Recipe<Container> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack result;
    private final ResourceLocation id;

    public SmashingRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {
        this.inputItems = inputItems;
        this.result = output;
        this.id = id;
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
        return SmashingRecipe.Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return SmashingRecipe.Type.INSTANCE;
    }

    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    public static class Type implements RecipeType<SmashingRecipe> {
        public static final SmashingRecipe.Type INSTANCE = new SmashingRecipe.Type();
        public static final String ID = "smashing";
    }

    public static class Serializer implements RecipeSerializer<SmashingRecipe> {
        public static final SmashingRecipe.Serializer INSTANCE = new SmashingRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(MoreTetraMaterials.MOD_ID, "smashing");

        @Override
        public SmashingRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredient");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));

            return new SmashingRecipe(inputs, output, pRecipeId);
        }

        @Override
        public @Nullable SmashingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack output = pBuffer.readItem();
            return new SmashingRecipe(inputs, output, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, SmashingRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.inputItems.size());

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItemStack(pRecipe.getResultItem(null),false);
        }
    }
}
