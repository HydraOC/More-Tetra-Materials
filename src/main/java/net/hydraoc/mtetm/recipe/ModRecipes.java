package net.hydraoc.mtetm.recipe;

import net.hydraoc.mtetm.MoreTetraMaterials;
import net.hydraoc.mtetm.recipe.HellSmelting.HellSmeltingRecipe;
import net.hydraoc.mtetm.recipe.LightningFusion.LightningFusionRecipe;
import net.hydraoc.mtetm.recipe.Smashing.SmashingRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
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

    //public static final RegistryObject<RecipeSerializer<LightningFusionRecipe>> LIGHTNING_SERIALIZER =
            //SERIALIZERS.register("lightning_fusion", () -> LightningFusionRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
