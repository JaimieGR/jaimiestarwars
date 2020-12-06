
package net.mcreator.bespin.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.mcreator.bespin.item.ItemDedlaniteIngot;
import net.mcreator.bespin.block.BlockDedlaniteOre;
import net.mcreator.bespin.ElementsBespin;

@ElementsBespin.ModElement.Tag
public class RecipeDedlaniteOreSmelting extends ElementsBespin.ModElement {
	public RecipeDedlaniteOreSmelting(ElementsBespin instance) {
		super(instance, 64);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(BlockDedlaniteOre.block, (int) (1)), new ItemStack(ItemDedlaniteIngot.block, (int) (1)), 0.7F);
	}
}
