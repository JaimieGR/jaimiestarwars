
package net.mcreator.bespin.item;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBase;

import net.mcreator.bespin.ElementsBespin;

@ElementsBespin.ModElement.Tag
public class ItemImperialchest extends ElementsBespin.ModElement {
	@GameRegistry.ObjectHolder("bespin:imperialchesthelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("bespin:imperialchestbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("bespin:imperialchestlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("bespin:imperialchestboots")
	public static final Item boots = null;
	public ItemImperialchest(ElementsBespin instance) {
		super(instance, 78);
	}

	@Override
	public void initElements() {
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("IMPERIALCHEST", "bespin:nofuckingarmor", 25, new int[]{2, 5, 6, 2}, 9,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("")), 0f);
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST) {
			@Override
			@SideOnly(Side.CLIENT)
			public ModelBiped getArmorModel(EntityLivingBase living, ItemStack stack, EntityEquipmentSlot slot, ModelBiped defaultModel) {
				ModelBiped armorModel = new ModelBiped();
				armorModel.bipedBody = new Modeluniformchest().bone;
				armorModel.isSneak = living.isSneaking();
				armorModel.isRiding = living.isRiding();
				armorModel.isChild = living.isChild();
				return armorModel;
			}
		}.setUnlocalizedName("imperialchestbody").setRegistryName("imperialchestbody").setCreativeTab(CreativeTabs.COMBAT));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("bespin:imperialchestbody", "inventory"));
	}
	public static class Modeluniformchest extends ModelBase {
		private final ModelRenderer bone;
		public Modeluniformchest() {
			textureWidth = 64;
			textureHeight = 64;
			bone = new ModelRenderer(this);
			bone.setRotationPoint(0.0F, 24.0F, 0.0F);
			bone.cubeList.add(new ModelBox(bone, 0, 0, -13.0F, -6.0F, 7.0F, 10, 6, 13, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 0, 19, -3.0F, -5.0F, 16.0F, 4, 4, 4, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 16, 19, -17.0F, -5.0F, 16.0F, 4, 4, 4, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			bone.render(f5);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}
