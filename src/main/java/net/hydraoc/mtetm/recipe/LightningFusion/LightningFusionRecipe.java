package net.hydraoc.mtetm.recipe.LightningFusion;

import com.google.gson.JsonObject;
import net.hydraoc.mtetm.MoreTetraMaterials;
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
    private final Ingredient primary;
    private final Ingredient secondary;
    private final Ingredient catalyst;
    private final ItemStack result;
    private final ResourceLocation id;

    public LightningFusionRecipe(Ingredient primaryInputItems, Ingredient secondaryInputItems, Ingredient catalystBlocks, ItemStack output, ResourceLocation id) {
        this.primary = primaryInputItems;
        this.secondary = secondaryInputItems;
        this.catalyst = catalystBlocks;
        this.result = output;
        this.id = id;
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }

        return primary.test(pContainer.getItem(0));
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

    public Ingredient getPrimary() {
        return primary;
    }

    public Ingredient getSecondary() {
        return secondary;
    }

    public Ingredient getCatalyst() {
        return catalyst;
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

            Ingredient primaryInput = Ingredient.fromJson(GsonHelper.getNonNull(pSerializedRecipe, "primaryIngredient"));
            Ingredient secondaryInput = Ingredient.fromJson(GsonHelper.getNonNull(pSerializedRecipe, "secondaryIngredient"));
            Ingredient catalystBlock = Ingredient.fromJson(GsonHelper.getNonNull(pSerializedRecipe, "catalyst"));

            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));

            return new LightningFusionRecipe(primaryInput, secondaryInput, catalystBlock, output, pRecipeId);
        }

        @Override
        public @Nullable LightningFusionRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {

            Ingredient primaryInput = Ingredient.fromNetwork(pBuffer);
            Ingredient secondaryInput = Ingredient.fromNetwork(pBuffer);
            Ingredient catalystBlock = Ingredient.fromNetwork(pBuffer);
            ItemStack output = pBuffer.readItem();

            return new LightningFusionRecipe(primaryInput, secondaryInput, catalystBlock, output, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, LightningFusionRecipe pRecipe) {
            pRecipe.primary.toNetwork(pBuffer);
            pRecipe.secondary.toNetwork(pBuffer);
            pRecipe.catalyst.toNetwork(pBuffer);
            pBuffer.writeItemStack(pRecipe.getResultItem(null),false);

        }
    }
}
