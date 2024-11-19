package net.hydraoc.mtetm.item;

import net.hydraoc.mtetm.MoreTetraMaterials;
import net.hydraoc.mtetm.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MoreTetraMaterials.MOD_ID);

    public static final RegistryObject<Item> MITHRIL_INGOT = ITEMS.register("mithril_ingot",
            () -> new ItemWTooltip(new Item.Properties(), "tooltip.mtetm.mithril"));

    public static final RegistryObject<Item> RAW_MITHRIL = ITEMS.register("raw_mithril",
            () -> new ItemWTooltip(new Item.Properties(), "tooltip.mtetm.mithril"));

    public static final RegistryObject<Item> MITHRIL_DUST = ITEMS.register("mithril_dust",
            () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> ADAMANTIUM_INGOT = ITEMS.register("adamantium_ingot",
            () -> new ItemWTooltip(new Item.Properties(), "tooltip.mtetm.adamantium"));

    public static final RegistryObject<Item> RAW_ADAMANTIUM = ITEMS.register("raw_adamantium",
            () -> new ItemWTooltip(new Item.Properties(), "tooltip.mtetm.adamantium"));

    public static final RegistryObject<Item> ADAMANTIUM_DUST = ITEMS.register("adamantium_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PANDORIUM_INGOT = ITEMS.register("pandorium_ingot",
            () -> new ItemWTooltip(new Item.Properties(), "tooltip.mtetm.pandorium"));

    public static final RegistryObject<Item> RAW_PANDORIUM = ITEMS.register("raw_pandorium",
            () -> new ItemWTooltip(new Item.Properties(), "tooltip.mtetm.pandorium"));

    public static final RegistryObject<Item> PANDORIUM_DUST = ITEMS.register("pandorium_dust",
            () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> BLOOD_EMERALD = ITEMS.register("blood_emerald",
            () -> new ItemWTooltip(new Item.Properties(), "tooltip.mtetm.scarlite"));

    public static final RegistryObject<Item> BLOOD_EMERALD_DUST = ITEMS.register("blood_emerald_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENDERFUSED_ADAMANTIUM_INGOT = ITEMS.register("enderfused_adamantium_ingot",
            () -> new ItemWTooltip(new Item.Properties(), "tooltip.mtetm.enderfused_adamantium"));

    public static final RegistryObject<Item> ENDERFUSED_ADAMANTIUM_DUST = ITEMS.register("enderfused_adamantium_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENDER_PEARL_DUST = ITEMS.register("ender_pearl_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOUL_STEEL_INGOT = ITEMS.register("soul_steel_ingot",
            () -> new ItemWTooltip(new Item.Properties(), "tooltip.mtetm.soul_steel"));

    public static final RegistryObject<Item> SOUL_STEEL_DUST = ITEMS.register("soul_steel_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOUL_QUARTZ_CRYSTAL = ITEMS.register("soul_quartz_crystal",
            () -> new ItemWTooltip(new Item.Properties(), "tooltip.mtetm.soul_quartz_crystal"));

    public static final RegistryObject<Item> NETHER_GEODE = ITEMS.register("nether_geode",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}