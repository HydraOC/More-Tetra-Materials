package net.hydraoc.mtetm.block.custom;

import net.hydraoc.mtetm.block.entity.AbstractHellforgeBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public abstract class AbstractCFB extends BaseEntityBlock {
    public static final DirectionProperty FACING;
    public static final BooleanProperty LIT;

    protected AbstractCFB(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH)).setValue(LIT, false));
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            this.openContainer(level, pos, player);
            return InteractionResult.CONSUME;
        }
    }

    protected abstract void openContainer(Level var1, BlockPos var2, Player var3);

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity livingEntity, ItemStack itemStack) {
        if (itemStack.hasCustomHoverName()) {
            BlockEntity $$5 = level.getBlockEntity(pos);
            if ($$5 instanceof AbstractHellforgeBE) {
                ((AbstractHellforgeBE)$$5).setCustomName(itemStack.getHoverName());
            }
        }

    }

    public void onRemove(BlockState state1, Level level, BlockPos pos, BlockState state2, boolean p_48717_) {
        if (!state1.is(state2.getBlock())) {
            BlockEntity $$5 = level.getBlockEntity(pos);
            if ($$5 instanceof AbstractHellforgeBE) {
                if (level instanceof ServerLevel) {
                    Containers.dropContents(level, pos, (AbstractHellforgeBE)$$5);
                    ((AbstractHellforgeBE)$$5).getRecipesToAwardAndPopExperience((ServerLevel)level, Vec3.atCenterOf(pos));
                }

                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state1, level, pos, state2, p_48717_);
        }
    }

    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return (BlockState)state.setValue(FACING, rotation.rotate((Direction)state.getValue(FACING)));
    }

    public BlockState mirror(BlockState p_48719_, Mirror p_48720_) {
        return p_48719_.rotate(p_48720_.getRotation((Direction)p_48719_.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(new Property[]{FACING, LIT});
    }

    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createFurnaceTicker(Level level, BlockEntityType<T> p_151989_, BlockEntityType<? extends AbstractHellforgeBE> p_151990_) {
        return level.isClientSide ? null : createTickerHelper(p_151989_, p_151990_, AbstractHellforgeBE::serverTick);
    }

    static {
        FACING = HorizontalDirectionalBlock.FACING;
        LIT = BlockStateProperties.LIT;
    }
}
