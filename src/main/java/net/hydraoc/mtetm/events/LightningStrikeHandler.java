package net.hydraoc.mtetm.events;

import net.hydraoc.mtetm.MoreTetraMaterials;
import net.hydraoc.mtetm.recipe.LightningFusion.LightningFusionRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = "mtetm")
public class LightningStrikeHandler {
    static int maxCount = 4;

    @SubscribeEvent
    public static void onEntityStruckByLightning(EntityStruckByLightningEvent event) {
        if(!(event.getEntity() instanceof ItemEntity)){ //If the hit entity is not an item, I don't want this running
            return;
        }

        if(event.getEntity() instanceof ItemEntity){
            event.getEntity().setInvulnerable(true);
        }

        ArrayList<ItemEntity> hitItems = new ArrayList<ItemEntity>(); //ArrayList to store all hit items

        //Reduce the number of entities we have to check to only the item entities
        for(Entity hitEntity : event.getLightning().getHitEntities().toList()){
            if(hitEntity instanceof ItemEntity){
                hitEntity.setInvulnerable(true);
                hitItems.add((ItemEntity) hitEntity);
            }
        }

        //Run nothing else if there are no items, it will only waste resources
        if(hitItems.isEmpty()){
            MoreTetraMaterials.LOGGER.info("No items found");
            return;
        }

        Level level = event.getLightning().level();
        List<LightningFusionRecipe> recipeList = level.getRecipeManager().getAllRecipesFor(LightningFusionRecipe.Type.INSTANCE);
        ArrayList<LightningFusionRecipe> possibleRecipes = new ArrayList<>();
        ItemEntity hitEntity = (ItemEntity) event.getEntity();

        //Reduce all recipes down to those whose primary ingredient match the hit entity
        for(LightningFusionRecipe recipe : recipeList){
            if(hitEntity.getItem().getItem() == recipe.getPrimary().getItems()[0].getItem()){
                possibleRecipes.add(recipe);
            }
        }

        //Exit if there are no matching recipes
        if(possibleRecipes.isEmpty()){
            MoreTetraMaterials.LOGGER.info("No recipies found");
            return;
        }

        RegistryAccess registry = level.registryAccess();
        LightningFusionRecipe foundrecipe = null;
        ItemEntity sEntity = null;

        for(LightningFusionRecipe recipe : possibleRecipes){
            for(ItemEntity item : hitItems){
                if(foundrecipe != null){
                    break;
                }
                if(item.getItem().getItem() == recipe.getSecondary().getItems()[0].getItem()){
                    foundrecipe = recipe;
                    sEntity = item;
                }
            }
            if(foundrecipe != null){
                break;
            }
        }

        //No matching recipes found, so exit the handler
        if(foundrecipe == null){
            MoreTetraMaterials.LOGGER.info("No secondary found");
            return;
        }

        for(ItemEntity hit : hitItems){
            if(hit.getItem().getItem() == foundrecipe.getResultItem(registry).getItem()){
                return;
            }
        }

        //Get the block the lightning strikes and the one below it
        BlockPos struckBlockPos = event.getLightning().blockPosition().below();
        Block struckBlock = level.getBlockState(struckBlockPos).getBlock();
        Block struckBlockBelow = level.getBlockState(struckBlockPos.below()).getBlock();
        Item catalyst = foundrecipe.getCatalyst().getItems()[0].getItem();

        boolean doRecipe = false;

        if (struckBlock.asItem() == catalyst) {
            doRecipe = true;
        }

        if (struckBlockBelow.asItem() == catalyst && struckBlock == Blocks.LIGHTNING_ROD) {
            doRecipe = true;
        }

        if(!doRecipe){
            MoreTetraMaterials.LOGGER.info("No catalyst found");
        }
        if(doRecipe){
            int pCount = hitEntity.getItem().getCount();
            int sCount = sEntity.getItem().getCount();
            int produceCount = 0;

            if(pCount >= maxCount && sCount >= maxCount){
                produceCount = maxCount;
                hitEntity.getItem().setCount(hitEntity.getItem().getCount()-maxCount);
                sEntity.getItem().setCount(sEntity.getItem().getCount()-maxCount);
            }else if(pCount > sCount){
                produceCount = sCount;
                hitEntity.getItem().setCount(hitEntity.getItem().getCount()-maxCount);
                sEntity.discard();
            }else if(pCount < sCount){
                produceCount = pCount;
                sEntity.getItem().setCount(sEntity.getItem().getCount()-maxCount);
                hitEntity.discard();
            }else{
                produceCount = pCount;
                sEntity.discard();
                hitEntity.discard();
            }

            ItemStack newItemStack = new ItemStack(foundrecipe.getResultItem(registry).getItem(), produceCount);
            ItemEntity newItemEntity = new ItemEntity(level, hitEntity.getX(), hitEntity.getY(), hitEntity.getZ(), newItemStack);
            newItemEntity.setInvulnerable(true);
            level.addFreshEntity(newItemEntity);
        }

    }

}