
package net.mcreator.bespin.util;

import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.mcreator.bespin.item.ItemDurasteel;
import net.mcreator.bespin.ElementsBespin;

@ElementsBespin.ModElement.Tag
public class OreDictIngotDurasteel extends ElementsBespin.ModElement {
	public OreDictIngotDurasteel(ElementsBespin instance) {
		super(instance, 41);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		OreDictionary.registerOre("ingotdurasteel", new ItemStack(ItemDurasteel.block, (int) (1)));
	}
}
