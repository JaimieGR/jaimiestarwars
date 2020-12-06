
package net.mcreator.bespin.util;

import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.mcreator.bespin.item.ItemRedstonePowerCell;
import net.mcreator.bespin.item.ItemGlowstonePowerCell;
import net.mcreator.bespin.ElementsBespin;

@ElementsBespin.ModElement.Tag
public class OreDictPowerCore extends ElementsBespin.ModElement {
	public OreDictPowerCore(ElementsBespin instance) {
		super(instance, 58);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		OreDictionary.registerOre("powercore", new ItemStack(ItemRedstonePowerCell.block, (int) (1)));
		OreDictionary.registerOre("powercore", new ItemStack(ItemGlowstonePowerCell.block, (int) (1)));
	}
}
