package net.mcreator.bespin.procedure;

import net.minecraft.world.WorldServer;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.block.state.IBlockState;

import net.mcreator.bespin.item.ItemStablisationBubble;
import net.mcreator.bespin.block.BlockStableZersiumOreBlock;
import net.mcreator.bespin.ElementsBespin;

@ElementsBespin.ModElement.Tag
public class ProcedureUstablecheck extends ElementsBespin.ModElement {
	public ProcedureUstablecheck(ElementsBespin instance) {
		super(instance, 33);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Ustablecheck!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure Ustablecheck!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure Ustablecheck!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure Ustablecheck!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Ustablecheck!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((entity instanceof EntityPlayer)
				? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemStablisationBubble.block, (int) (1)))
				: false)) {
			{
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				IBlockState _bs = BlockStableZersiumOreBlock.block.getDefaultState();
				world.setBlockState(_bp, _bs, 3);
			}
		} else {
			world.playSound((EntityPlayer) null, x, y, z,
					(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.explode")),
					SoundCategory.NEUTRAL, (float) 9, (float) 1);
			if (world instanceof WorldServer)
				((WorldServer) world).spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, x, y, z, (int) 13, 3, 3, 3, 1, new int[0]);
			if (!world.isRemote) {
				world.createExplosion(null, (int) x, (int) y, (int) z, (float) 12, true);
			}
			world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
		}
	}
}
