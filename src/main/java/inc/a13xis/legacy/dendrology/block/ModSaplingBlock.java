package inc.a13xis.legacy.dendrology.block;

import com.google.common.collect.ImmutableList;
import inc.a13xis.legacy.dendrology.TheMod;
import inc.a13xis.legacy.koresample.tree.DefinesSapling;
import inc.a13xis.legacy.koresample.tree.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class ModSaplingBlock extends SaplingBlock {
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", ModSlabBlock.EnumType.class);

	public ModSaplingBlock(Iterable<? extends DefinesSapling> subBlocks) {
		super(ImmutableList.copyOf(subBlocks));
		setCreativeTab(TheMod.INSTANCE.creativeTab());
		setHardness(0.0F);
		setSoundType(SoundType.PLANT);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, ModSlabBlock.EnumType.ACEMUS));
	}

	@Override
	protected String resourcePrefix() {
		return TheMod.getResourcePrefix();
	}

	@Override
	public boolean isTypeAt(World worldIn, BlockPos pos, Enum type) {
		return type instanceof ModSlabBlock.EnumType && type.equals(worldIn.getBlockState(pos).getValue(VARIANT));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		ModSlabBlock.EnumType id = ModSlabBlock.EnumType.fromId(meta);
		return meta < 8 ? getDefaultState().withProperty(VARIANT, ModSlabBlock.EnumType.fromId(meta)).withProperty(STAGE, 0) : getDefaultState().withProperty(VARIANT, ModSlabBlock.EnumType.fromId(meta)).withProperty(STAGE, 1);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		ModSlabBlock.EnumType type = (ModSlabBlock.EnumType) state.getValue(VARIANT);
		return type.ordinal();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{VARIANT, STAGE});
	}
}
