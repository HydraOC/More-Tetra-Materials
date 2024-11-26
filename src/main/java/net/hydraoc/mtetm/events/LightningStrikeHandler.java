package net.hydraoc.mtetm.events;

import net.hydraoc.mtetm.block.ModBlocks;
import net.hydraoc.mtetm.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = "mtetm")
public class LightningStrikeHandler {

    @SubscribeEvent
    public static void onEntityStruckByLightning(EntityStruckByLightningEvent event){
        //Get the lightning and level objects
        LightningBolt bolt = event.getLightning();
        Level level = bolt.level();

        //Get the block the lightning strikes and the one below it
        BlockPos struckBlockPos = bolt.blockPosition().below();
        Block struckBlock = level.getBlockState(struckBlockPos).getBlock();
        Block struckBlockBelow = level.getBlockState(struckBlockPos.below()).getBlock();


        ItemEntity primary = null;
        ItemEntity secondary = null;

        int newCount = -1;
        int primaryCount;
        int secondaryCount;
        int discard = -1;
        int maxProcessed = 1;
        boolean doRecipe = false;

        if(event.getEntity() instanceof ItemEntity){
            event.getEntity().setInvulnerable(true);
        }

        List<Entity> hitEntities = bolt.getHitEntities().toList();

            if (struckBlock.asItem() == ModBlocks.SOUL_QUARTZ_BLOCK.get().asItem()) {
                doRecipe = true;
            }

            if (struckBlockBelow.asItem() == ModBlocks.SOUL_QUARTZ_BLOCK.get().asItem() && struckBlock == Blocks.LIGHTNING_ROD) {
                doRecipe = true;}


        if(doRecipe) {
            for (Entity hitEntity : hitEntities) {
                if (hitEntity instanceof ItemEntity) {
                    if(((ItemEntity) hitEntity).getItem().getItem() == Items.IRON_INGOT){
                        //MoreTetraMaterials.LOGGER.info("primary found");
                        primary = ((ItemEntity) hitEntity);
                        primary.setInvulnerable(true);
                    }
                    if(((ItemEntity) hitEntity).getItem().getItem() == Blocks.SOUL_SAND.asItem()){
                        //MoreTetraMaterials.LOGGER.info("secondary found");
                        secondary = ((ItemEntity) hitEntity);
                        secondary.setInvulnerable(true);
                    }
                }
            }

            if(primary == null || secondary == null){
                //MoreTetraMaterials.LOGGER.info("ingredients not found");
                return;
            }


            primaryCount = primary.getItem().getCount();
            secondaryCount = secondary.getItem().getCount();

            if(primaryCount > maxProcessed && secondaryCount > maxProcessed){
                newCount = maxProcessed;
            }else if(primaryCount < secondaryCount){
                discard = 0;
                newCount = primaryCount;
            }else if(primaryCount > secondaryCount){
                discard = 1;
                newCount = secondaryCount;
            }else {
                discard = 2;
                newCount = primaryCount;
            }

            ItemStack newItemStack = new ItemStack(ModItems.SOUL_STEEL_INGOT.get(), newCount);

            if (!level.isClientSide) { //Check if the event is serverside
                if(discard == 0){primary.discard();}
                else if(discard == 1){secondary.discard();}
                else if(discard == 2){
                    primary.discard();
                    secondary.discard();
                }else{
                    primary.getItem().setCount(primary.getItem().getCount()-maxProcessed);
                    secondary.getItem().setCount(secondary.getItem().getCount()-maxProcessed);
                }

                ItemEntity newItemEntity = new ItemEntity(level, primary.getX(), primary.getY(), primary.getZ(), newItemStack);
                newItemEntity.setInvulnerable(true);
                level.addFreshEntity(newItemEntity);
            }


        }
    }
}
