package net.hydraoc.mtetm.datagen.loot;

import net.hydraoc.mtetm.block.ModBlocks;
import net.hydraoc.mtetm.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }


    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.MITHRIL_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_MITHRIL_BLOCK.get());
        this.dropSelf(ModBlocks.ADAMANTIUM_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_ADAMANTIUM_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_PANDORIUM_BLOCK.get());
        this.dropSelf(ModBlocks.PANDORIUM_BLOCK.get());
        this.dropSelf(ModBlocks.ENDERFUSED_ADAMNTIUM_BLOCK.get());
        this.dropSelf(ModBlocks.BLOOD_EMERALD_BLOCK.get());

        this.add(ModBlocks.MITHRIL_ORE.get(),
                block -> createMithrilDrops(ModBlocks.MITHRIL_ORE.get()));
        this.add(ModBlocks.DEEPSLATE_MITHRIL_ORE.get(),
                block -> createMithrilDrops(ModBlocks.DEEPSLATE_MITHRIL_ORE.get()));

        this.add(ModBlocks.ADAMANTIUM_ORE.get(),
                block -> createAdamantiumDrops(ModBlocks.ADAMANTIUM_ORE.get()));

        this.add(ModBlocks.PANDORIUM_ORE.get(),
                block -> createPandoriumDrops(ModBlocks.PANDORIUM_ORE.get()));

        this.add(ModBlocks.BLOOD_EMERALD_ORE.get(),
                block -> createBloodEmeraldDrops(ModBlocks.BLOOD_EMERALD_ORE.get()));

    }

    protected LootTable.Builder createMithrilDrops (Block p_251306_) {
        return createSilkTouchDispatchTable(p_251306_, (LootPoolEntryContainer.Builder)this.applyExplosionDecay(p_251306_,
                LootItem.lootTableItem(ModItems.RAW_MITHRIL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected LootTable.Builder createAdamantiumDrops (Block p_251306_) {
        return createSilkTouchDispatchTable(p_251306_, (LootPoolEntryContainer.Builder)this.applyExplosionDecay(p_251306_,
                LootItem.lootTableItem(ModItems.RAW_ADAMANTIUM.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected LootTable.Builder createPandoriumDrops (Block p_251306_) {
        return createSilkTouchDispatchTable(p_251306_, (LootPoolEntryContainer.Builder)this.applyExplosionDecay(p_251306_,
                LootItem.lootTableItem(ModItems.RAW_PANDORIUM.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected LootTable.Builder createBloodEmeraldDrops (Block p_251306_) {
        return createSilkTouchDispatchTable(p_251306_, (LootPoolEntryContainer.Builder)this.applyExplosionDecay(p_251306_,
                LootItem.lootTableItem(ModItems.BLOOD_EMERALD.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
