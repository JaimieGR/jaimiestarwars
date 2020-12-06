
package net.mcreator.bespin.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.mcreator.bespin.item.ItemZersiumIngot;
import net.mcreator.bespin.block.BlockZersiumOre;
import net.mcreator.bespin.ElementsBespin;

@ElementsBespin.ModElement.Tag
public class RecipeZersiumSmelt extends ElementsBespin.ModElement {
	public RecipeZersiumSmelt(ElementsBespin instance) {
		super(instance, 30);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(BlockZersiumOre.block, (int) (1)), new ItemStack(ItemZersiumIngot.block, (int) (1)), 1F);
	}
}
