package net.hydraoc.mtetm.recipe.LightningFusion;

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

public class LightningFusionRecipe implements Recipe<Container> {
    private static NonNullList<Ingredient> primaryInputs = null;
    private static NonNullList<Ingredient> secondaryInputs = null;
    private static NonNullList<Ingredient> catalysts = null;
    private static ItemStack result;
    private final ResourceLocation id;

    public LightningFusionRecipe(NonNullList<Ingredient> primaryInputItems, NonNullList<Ingredient> secondaryInputItems, NonNullList<Ingredient> catalystBlocks, ItemStack output, ResourceLocation id) {
        this.primaryInputs = primaryInputItems;
        this.secondaryInputs = secondaryInputItems;
        this.catalysts = catalystBlocks;
        this.result = output;
        this.id = id;
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }

        return primaryInputs.get(0).test(pContainer.getItem(0));
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

    public static NonNullList<Ingredient> getPrimaryIngredients() {
        return primaryInputs;
    }

    public static NonNullList<Ingredient> getSecondaryInputs() {
        return secondaryInputs;
    }

    public static NonNullList<Ingredient> getCatalysts() {
        return catalysts;
    }

    public static class Type implements RecipeType<LightningFusionRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "lightning_fusion";
    }

    public static class Serializer implements RecipeSerializer<LightningFusionRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(MoreTetraMaterials.MOD_ID, "lightning_fusion");

        @Override
        public LightningFusionRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {

            JsonArray primaryIngredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "primaryIngredient");
            JsonArray secondaryIngredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "secondaryIngredient");
            JsonArray catalysts = GsonHelper.getAsJsonArray(pSerializedRecipe, "catalyst");
            NonNullList<Ingredient> primaryInputs = NonNullList.withSize(1, Ingredient.EMPTY);
            NonNullList<Ingredient> secondaryInputs = NonNullList.withSize(1, Ingredient.EMPTY);
            NonNullList<Ingredient> catalystInputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for(int i = 0; i < primaryInputs.size(); i++) {
                primaryInputs.set(i, Ingredient.fromJson(primaryIngredients.get(i)));
            }

            for(int i = 0; i < secondaryInputs.size(); i++) {
                secondaryInputs.set(i, Ingredient.fromJson(secondaryIngredients.get(i)));
            }

            for(int i = 0; i < catalystInputs.size(); i++) {
                catalystInputs.set(i, Ingredient.fromJson(catalysts.get(i)));
            }

            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));

            return new LightningFusionRecipe(primaryInputs, secondaryInputs, catalystInputs, output, pRecipeId);
        }

        @Override
        public @Nullable LightningFusionRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> primaryInputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);
            NonNullList<Ingredient> secondaryInputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);
            NonNullList<Ingredient> catalystInputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < primaryInputs.size(); i++) {
                primaryInputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            for(int i = 0; i < secondaryInputs.size(); i++) {
                secondaryInputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            for(int i = 0; i < catalystInputs.size(); i++) {
                catalystInputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack output = pBuffer.readItem();
            return new LightningFusionRecipe(primaryInputs, secondaryInputs, catalystInputs, output, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, LightningFusionRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.primaryInputs.size());
            pBuffer.writeInt(pRecipe.secondaryInputs.size());
            pBuffer.writeInt(pRecipe.catalysts.size());

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItemStack(pRecipe.getResultItem(null),false);
        }
    }
}
