package net.mcreator.bespin.procedure;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import net.mcreator.bespin.entity.EntityAtRtWalker;
import net.mcreator.bespin.ElementsBespin;

@ElementsBespin.ModElement.Tag
public class ProcedureAtrtWalkerSpawnerRightClickedInAir extends ElementsBespin.ModElement {
	public ProcedureAtrtWalkerSpawnerRightClickedInAir(ElementsBespin instance) {
		super(instance, 23);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure AtrtWalkerSpawnerRightClickedInAir!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure AtrtWalkerSpawnerRightClickedInAir!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure AtrtWalkerSpawnerRightClickedInAir!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure AtrtWalkerSpawnerRightClickedInAir!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (!world.isRemote) {
			Entity entityToSpawn = new EntityAtRtWalker.EntityCustom(world);
			if (entityToSpawn != null) {
				entityToSpawn.setLocationAndAngles(x, (y + 1), z, world.rand.nextFloat() * 360F, 0.0F);
				world.spawnEntity(entityToSpawn);
			}
		}
	}
}
