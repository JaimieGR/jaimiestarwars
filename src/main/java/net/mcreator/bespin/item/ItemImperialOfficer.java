
package net.mcreator.bespin.item;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import net.mcreator.bespin.ElementsBespin;

@ElementsBespin.ModElement.Tag
public class ItemImperialOfficer extends ElementsBespin.ModElement {
	@GameRegistry.ObjectHolder("bespin:imperialofficerhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("bespin:imperialofficerbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("bespin:imperialofficerlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("bespin:imperialofficerboots")
	public static final Item boots = null;
	public ItemImperialOfficer(ElementsBespin instance) {
		super(instance, 72);
	}

	@Override
	public void initElements() {
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("IMPERIALOFFICER", "bespin:officerarrmorimperial", 25, new int[]{2, 5, 6, 2}, 9,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("")), 0f);
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("imperialofficerhelmet")
				.setRegistryName("imperialofficerhelmet").setCreativeTab(CreativeTabs.COMBAT));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("imperialofficerbody")
				.setRegistryName("imperialofficerbody").setCreativeTab(CreativeTabs.COMBAT));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("imperialofficerlegs")
				.setRegistryName("imperialofficerlegs").setCreativeTab(CreativeTabs.COMBAT));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("imperialofficerboots")
				.setRegistryName("imperialofficerboots").setCreativeTab(CreativeTabs.COMBAT));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("bespin:imperialofficerhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("bespin:imperialofficerbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("bespin:imperialofficerlegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("bespin:imperialofficerboots", "inventory"));
	}
}
