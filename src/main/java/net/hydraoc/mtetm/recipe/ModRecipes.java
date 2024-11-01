package net.hydraoc.mtetm.recipe;

import net.hydraoc.mtetm.MoreTetraMaterials;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MoreTetraMaterials.MOD_ID);

    public static final RegistryObject<RecipeSerializer<HellSmeltingRecipe>> HELL_SMELTING_SERIALIZER =
            SERIALIZERS.register("hell_smelting", () -> HellSmeltingRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<SmashingRecipe>> SMASHING_SERIALIZER =
            SERIALIZERS.register("smashing", () -> SmashingRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<AlloySmeltingRecipe>> ALLOY_SMELTING_SERIALIZER =
            SERIALIZERS.register("alloy_smelting", () -> AlloySmeltingRecipe.Serializer.INSTANCE);
    
    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
