package net.hydraoc.mtetm.datagen;

import net.hydraoc.mtetm.MoreTetraMaterials;
import net.hydraoc.mtetm.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MoreTetraMaterials.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Mithril item declarations
        simpleItem(ModItems.RAW_MITHRIL);
        simpleItem(ModItems.MITHRIL_INGOT);
        simpleItem(ModItems.MITHRIL_DUST);

        //Adamantium item declarations
        simpleItem(ModItems.RAW_ADAMANTIUM);
        simpleItem(ModItems.ADAMANTIUM_INGOT);
        simpleItem(ModItems.ADAMANTIUM_DUST);

        //Uru item declarations
        simpleItem(ModItems.RAW_PANDORIUM);
        simpleItem(ModItems.PANDORIUM_INGOT);
        simpleItem(ModItems.PANDORIUM_DUST);

        //Misc item declarations
        simpleItem(ModItems.ENDERFUSED_ADAMANTIUM_INGOT);
        simpleItem(ModItems.ENDERFUSED_ADAMANTIUM_DUST);
        simpleItem(ModItems.BLOOD_EMERALD);
        simpleItem(ModItems.BLOOD_EMERALD_DUST);
        simpleItem(ModItems.ENDER_PEARL_DUST);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MoreTetraMaterials.MOD_ID,"item/" + item.getId().getPath()));
    }
}
