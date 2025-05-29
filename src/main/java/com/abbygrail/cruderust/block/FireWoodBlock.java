package com.abbygrail.cruderust.block;

import com.abbygrail.cruderust.block.state.FireWoodState;
import com.abbygrail.cruderust.core.registry.CrudeRustBlocks;
import com.abbygrail.cruderust.cruderust;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class FireWoodBlock extends Block implements SimpleWaterloggedBlock {

    public static final IntegerProperty CUTS = IntegerProperty.create("cuts", 1,12);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<FireWoodState> STATE = EnumProperty.create("state", FireWoodState.class);


    protected static final VoxelShape ONE_AABB = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 4.0D, 16.0D);
    protected static final VoxelShape TWO_AABB = Block.box(3.0D, 0.0D, 0.0D, 13.0D, 4.0D, 16.0D);
    protected static final VoxelShape THREE_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
    protected static final VoxelShape FOUR_AABB = Shapes.or(
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 4.0D, 6.0D, 16.0D, 8.0D, 10.0D)
            );
    protected static final VoxelShape FIVE_AABB = Shapes.or(
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 4.0D, 3.0D, 16.0D, 8.0D, 13.0D)
            );
    protected static final VoxelShape SIX_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape SEVEN_AABB = Shapes.or(
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.box(6.0D, 8.0D, 0.0D, 10.0D, 12.0D, 16.0D)
    );
    protected static final VoxelShape EIGHT_AABB = Shapes.or(
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.box(3.0D, 8.0D, 0.0D, 13.0D, 12.0D, 16.0D)
    );
    protected static final VoxelShape NINE_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
    protected static final VoxelShape TEN_AABB = Shapes.or(
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
            Block.box(0.0D, 12.0D, 6.0D, 16.0D, 16.0D, 10.0D)
    );
    protected static final VoxelShape ELEVEN_AABB = Shapes.or(
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
            Block.box(0.0D, 12.0D, 3.0D, 16.0D, 16.0D, 13.0D)
    );
    protected static final VoxelShape TWELVE_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public FireWoodBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(CUTS, 1).setValue(WATERLOGGED, false).setValue(FACING, Direction.NORTH).setValue(STATE, FireWoodState.OFF));
    }


    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        Direction direction = context.getHorizontalDirection().getOpposite();

        if (state.is(this)) {
            return state.setValue(CUTS, Math.min(12, state.getValue(CUTS) + 1));
        } else {
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
            boolean flag = fluidstate.getType() == Fluids.WATER;
            return super.getStateForPlacement(context).setValue(WATERLOGGED, flag).setValue(FACING, direction);
        }
    }

    protected boolean mayPlaceOn(BlockState p_56127_, BlockGetter p_56128_, BlockPos p_56129_) {
        return !p_56127_.getCollisionShape(p_56128_, p_56129_).getFaceShape(Direction.UP).isEmpty() || p_56127_.isFaceSturdy(p_56128_, p_56129_, Direction.UP);
    }

    @Override
    public boolean canSurvive(BlockState p_56109_, LevelReader p_56110_, BlockPos p_56111_) {
        BlockPos blockpos = p_56111_.below();
        return this.mayPlaceOn(p_56110_.getBlockState(blockpos), p_56110_, blockpos);
    }

    @Override
    public BlockState updateShape(BlockState p_56113_, Direction p_56114_, BlockState p_56115_, LevelAccessor p_56116_, BlockPos p_56117_, BlockPos p_56118_) {
        if (!p_56113_.canSurvive(p_56116_, p_56117_)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            if (p_56113_.getValue(WATERLOGGED)) {
                p_56116_.scheduleTick(p_56117_, Fluids.WATER, Fluids.WATER.getTickDelay(p_56116_));
            }

            return super.updateShape(p_56113_, p_56114_, p_56115_, p_56116_, p_56117_, p_56118_);
        }
    }

    @Override
    public boolean canBeReplaced(BlockState p_56101_, BlockPlaceContext p_56102_) {
        return !p_56102_.isSecondaryUseActive() && p_56102_.getItemInHand().is(this.asItem()) && p_56101_.getValue(CUTS) < 12 ? true : super.canBeReplaced(p_56101_, p_56102_);
    }

    @Override
    public VoxelShape getShape(BlockState p_56122_, BlockGetter p_56123_, BlockPos p_56124_, CollisionContext p_56125_) {
        return switch (p_56122_.getValue(CUTS)) {
            case 1 -> ONE_AABB;
            case 2 -> TWO_AABB;
            case 3 -> THREE_AABB;
            case 4 -> FOUR_AABB;
            case 5 -> FIVE_AABB;
            case 6 -> SIX_AABB;
            case 7 -> SEVEN_AABB;
            case 8 -> EIGHT_AABB;
            case 9 -> NINE_AABB;
            case 10 -> TEN_AABB;
            case 11 -> ELEVEN_AABB;
            default -> TWELVE_AABB;
        };
    }

    @Override
    public FluidState getFluidState(BlockState p_56131_) {
        return p_56131_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_56131_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_56120_) {
        p_56120_.add(CUTS, WATERLOGGED, FACING, STATE);
    }

}
