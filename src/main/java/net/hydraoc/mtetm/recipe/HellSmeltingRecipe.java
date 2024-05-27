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
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

public class HellSmeltingRecipe extends AbstractCCR {
    private final NonNullList<Ingredient> inputItems = null;
    public HellSmeltingRecipe(ResourceLocation p_249728_, String p_251053_, CookingBookCategory p_249936_, Ingredient p_251550_, ItemStack p_251027_, float p_250843_, int p_249841_) {
        super(p_249728_, p_251053_, p_249936_, p_251550_, p_251027_, p_250843_, p_249841_);
    }

    public HellSmeltingRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {
        super(inputItems,output,id);
    }
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.HELLFORGE.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    public static RecipeType<? extends AbstractCCR> returnType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<HellSmeltingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "hell_smelting";
    }

    public static class Serializer implements RecipeSerializer<HellSmeltingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(MoreTetraMaterials.MOD_ID, "hell_smelting");
        public static final ResourceLocation ID2 = new ResourceLocation("blasting");

        @Override
        public HellSmeltingRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new HellSmeltingRecipe(inputs, output, pRecipeId);
        }

        @Override
        public @Nullable HellSmeltingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack output = pBuffer.readItem();
            return new HellSmeltingRecipe(inputs, output, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, HellSmeltingRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.inputItems.size());

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
        }
    }
}
