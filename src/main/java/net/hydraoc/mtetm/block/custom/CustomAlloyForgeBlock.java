package net.hydraoc.mtetm.block.custom;

import net.hydraoc.mtetm.block.entity.AbstractAlloyForgeBE;
import net.hydraoc.mtetm.block.entity.AlloyForgeBlockEntity;
import net.hydraoc.mtetm.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

public class CustomAlloyForgeBlock extends AbstractAlloyForgeBlock {

    private final String tooltip;

    public CustomAlloyForgeBlock(Properties p_53627_, String tooltipTranslatable) {
        super(p_53627_);
        this.tooltip = tooltipTranslatable;
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AlloyForgeBlockEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState p_153274_, BlockEntityType<T> betype) {
        return createFurnaceTicker(level, betype, ModBlockEntities.ALLOYFORGE_BE.get());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter p_49817_, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable(tooltip));
        super.appendHoverText(pStack, p_49817_, pTooltipComponents, pIsAdvanced);
    }

    public void openContainer(Level level, BlockPos pos, Player player) {
        BlockEntity $$3 = level.getBlockEntity(pos);
        if ($$3 instanceof AlloyForgeBlockEntity) {
            NetworkHooks.openScreen((ServerPlayer) player, (MenuProvider)$$3, pos);
        }
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randSource) {
        if ((Boolean)state.getValue(LIT)) {
            double $$4 = (double)pos.getX() + 0.5;
            double $$5 = (double)pos.getY()+0.5;
            double $$6 = (double)pos.getZ() + 0.5;
            level.setBlock(pos, (BlockState)state.setValue(LIT, true), 3);
            if (randSource.nextDouble() < 0.1) {
                level.playLocalSound($$4, $$5, $$6, SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 0.25F, 0.5F, false);
            }

            Direction $$7 = (Direction)state.getValue(FACING);
            Direction.Axis $$8 = $$7.getAxis();
            double $$9 = 0.52;
            double $$10 = randSource.nextDouble() * 0.6 - 0.3;
            double $$11 = $$8 == Direction.Axis.X ? (double)$$7.getStepX() * 0.52 : $$10;
            double $$12 = randSource.nextDouble() * 6.0 / 10.0;
            double $$13 = $$8 == Direction.Axis.Z ? (double)$$7.getStepZ() * 0.52 : $$10;
            level.addParticle(ParticleTypes.FLAME, $$4 + $$11, $$5 + $$12, $$6 + $$13, 0.0, 0.0, 0.0);
        }
    }
}

