package net.hydraoc.mtetm.item;

import net.hydraoc.mtetm.MoreTetraMaterials;
import net.hydraoc.mtetm.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoreTetraMaterials.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("mtetm_item_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MITHRIL_INGOT.get()))
                    .title(Component.translatable("creativetab.mtetm_item_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.MITHRIL_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_MITHRIL_ORE.get());
                        pOutput.accept(ModBlocks.BLOOD_EMERALD_ORE.get());
                        pOutput.accept(ModBlocks.ADAMANTIUM_ORE.get());
                        pOutput.accept(ModBlocks.PANDORIUM_ORE.get());

                        pOutput.accept(ModItems.RAW_MITHRIL.get());
                        pOutput.accept(ModBlocks.RAW_MITHRIL_BLOCK.get());
                        pOutput.accept(ModItems.RAW_ADAMANTIUM.get());
                        pOutput.accept(ModBlocks.RAW_ADAMANTIUM_BLOCK.get());
                        pOutput.accept(ModItems.RAW_PANDORIUM.get());
                        pOutput.accept(ModBlocks.RAW_PANDORIUM_BLOCK.get());

                        pOutput.accept(ModItems.MITHRIL_DUST.get());
                        pOutput.accept(ModItems.BLOOD_EMERALD_DUST.get());
                        pOutput.accept(ModItems.ADAMANTIUM_DUST.get());
                        pOutput.accept(ModItems.PANDORIUM_DUST.get());
                        pOutput.accept(ModItems.ENDER_PEARL_DUST.get());
                        pOutput.accept(ModItems.ENDERFUSED_ADAMANTIUM_DUST.get());

                        pOutput.accept(ModItems.MITHRIL_INGOT.get());
                        pOutput.accept(ModBlocks.MITHRIL_BLOCK.get());
                        pOutput.accept(ModItems.BLOOD_EMERALD.get());
                        pOutput.accept(ModBlocks.BLOOD_EMERALD_BLOCK.get());
                        pOutput.accept(ModItems.ADAMANTIUM_INGOT.get());
                        pOutput.accept(ModBlocks.ADAMANTIUM_BLOCK.get());
                        pOutput.accept(ModItems.PANDORIUM_INGOT.get());
                        pOutput.accept(ModBlocks.PANDORIUM_BLOCK.get());
                        pOutput.accept(ModItems.ENDERFUSED_ADAMANTIUM_INGOT.get());
                        pOutput.accept(ModBlocks.ENDERFUSED_ADAMNTIUM_BLOCK.get());

                        pOutput.accept(ModBlocks.HELLFORGE.get());
                        pOutput.accept(ModBlocks.ALLOYFORGE.get());

                        pOutput.accept(ModItems.SOUL_STEEL_INGOT.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}