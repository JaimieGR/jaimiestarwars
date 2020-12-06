
package net.mcreator.bespin.entity;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.EnumHand;
import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelBase;

import net.mcreator.bespin.item.ItemWalkerSignalBeacon;
import net.mcreator.bespin.ElementsBespin;

import java.util.Iterator;
import java.util.ArrayList;

@ElementsBespin.ModElement.Tag
public class EntityAtRtWalker extends ElementsBespin.ModElement {
	public static final int ENTITYID = 13;
	public static final int ENTITYID_RANGED = 14;
	public EntityAtRtWalker(ElementsBespin instance) {
		super(instance, 17);
	}

	@Override
	public void initElements() {
		elements.entities.add(() -> EntityEntryBuilder.create().entity(EntityCustom.class).id(new ResourceLocation("bespin", "atrtwalker"), ENTITYID)
				.name("atrtwalker").tracker(64, 3, true).egg(-3355444, -6750208).build());
	}

	private Biome[] allbiomes(net.minecraft.util.registry.RegistryNamespaced<ResourceLocation, Biome> in) {
		Iterator<Biome> itr = in.iterator();
		ArrayList<Biome> ls = new ArrayList<Biome>();
		while (itr.hasNext())
			ls.add(itr.next());
		return ls.toArray(new Biome[ls.size()]);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityCustom.class, renderManager -> {
			return new RenderLiving(renderManager, new Modelfrog(), 0.5f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("bespin:textures/walker.png");
				}
			};
		});
	}
	public static class EntityCustom extends EntityCreature {
		public EntityCustom(World world) {
			super(world);
			setSize(1f, 3f);
			experienceValue = 0;
			this.isImmuneToFire = true;
			setNoAI(!true);
		}

		@Override
		protected void initEntityAI() {
			super.initEntityAI();
			this.tasks.addTask(1, new EntityAITempt(this, 1, new ItemStack(ItemWalkerSignalBeacon.block, (int) (1)).getItem(), false));
		}

		@Override
		public EnumCreatureAttribute getCreatureAttribute() {
			return EnumCreatureAttribute.UNDEFINED;
		}

		@Override
		protected Item getDropItem() {
			return null;
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("block.anvil.break"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("block.anvil.destroy"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof EntityPotion)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public boolean processInteract(EntityPlayer entity, EnumHand hand) {
			super.processInteract(entity, hand);
			entity.startRiding(this);
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			ItemStack itemstack = entity.getHeldItem(hand);
			return true;
		}

		@Override
		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
			if (this.getEntityAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.5D);
			if (this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
			if (this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
			if (this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3D);
		}

		@Override
		public void travel(float ti, float tj, float tk) {
			Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
			if (this.isBeingRidden()) {
				this.rotationYaw = entity.rotationYaw;
				this.prevRotationYaw = this.rotationYaw;
				this.rotationPitch = entity.rotationPitch * 0.5F;
				this.setRotation(this.rotationYaw, this.rotationPitch);
				this.jumpMovementFactor = this.getAIMoveSpeed() * 0.15F;
				this.renderYawOffset = entity.rotationYaw;
				this.rotationYawHead = entity.rotationYaw;
				this.stepHeight = 1.0F;
				if (entity instanceof EntityLivingBase) {
					this.setAIMoveSpeed((float) this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
					float forward = ((EntityLivingBase) entity).moveForward;
					float strafe = ((EntityLivingBase) entity).moveStrafing;
					super.travel(strafe, 0, forward);
				}
				this.prevLimbSwingAmount = this.limbSwingAmount;
				double d1 = this.posX - this.prevPosX;
				double d0 = this.posZ - this.prevPosZ;
				float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
				if (f1 > 1.0F)
					f1 = 1.0F;
				this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
				this.limbSwing += this.limbSwingAmount;
				return;
			}
			this.stepHeight = 0.5F;
			this.jumpMovementFactor = 0.02F;
			super.travel(ti, tj, tk);
		}
	}

	public static class Modelfrog extends ModelBase {
		private final ModelRenderer whole;
		private final ModelRenderer seat;
		private final ModelRenderer leg2;
		private final ModelRenderer high2;
		private final ModelRenderer low2;
		private final ModelRenderer leg3;
		private final ModelRenderer high3;
		private final ModelRenderer low3;
		private final ModelRenderer handles;
		public Modelfrog() {
			textureWidth = 128;
			textureHeight = 128;
			whole = new ModelRenderer(this);
			whole.setRotationPoint(0.0F, 24.0F, -5.0F);
			seat = new ModelRenderer(this);
			seat.setRotationPoint(0.0F, -11.0F, -1.0F);
			whole.addChild(seat);
			seat.cubeList.add(new ModelBox(seat, 34, 0, -5.0F, -26.0F, 0.0F, 10, 4, 14, 0.0F, false));
			seat.cubeList.add(new ModelBox(seat, 32, 32, -4.0F, -27.0F, 0.0F, 8, 1, 14, 0.0F, false));
			seat.cubeList.add(new ModelBox(seat, 0, 32, -4.0F, -22.0F, -3.0F, 8, 3, 16, 0.0F, false));
			seat.cubeList.add(new ModelBox(seat, 20, 56, -4.0F, -32.0F, -3.0F, 8, 11, 2, 1.0F, false));
			seat.cubeList.add(new ModelBox(seat, 20, 20, -3.0F, -31.0F, -4.0F, 6, 9, -1, 1.0F, false));
			seat.cubeList.add(new ModelBox(seat, 0, 32, -2.0F, -29.0F, -5.0F, 4, 7, -1, 1.0F, false));
			seat.cubeList.add(new ModelBox(seat, 0, 51, -3.0F, -32.0F, 13.0F, 6, 11, 4, 0.0F, false));
			seat.cubeList.add(new ModelBox(seat, 0, 0, -1.0F, -20.0F, -15.0F, 2, 2, 30, 0.0F, false));
			seat.cubeList.add(new ModelBox(seat, 19, 28, -7.0F, -32.0F, 0.0F, 4, 1, 1, 0.0F, false));
			seat.cubeList.add(new ModelBox(seat, 51, 18, -2.0F, -21.0F, -3.0F, 4, 4, 5, 0.0F, false));
			seat.cubeList.add(new ModelBox(seat, 20, 18, 3.0F, -32.0F, 0.0F, 4, 1, 1, 0.0F, false));
			seat.cubeList.add(new ModelBox(seat, 7, 0, -1.0F, -29.0F, 17.0F, 2, 2, 1, 0.0F, false));
			leg2 = new ModelRenderer(this);
			leg2.setRotationPoint(6.0F, 0.0F, 7.0F);
			whole.addChild(leg2);
			setRotationAngle(leg2, 0.0F, 0.0F, 0.0F);
			leg2.cubeList.add(new ModelBox(leg2, 39, 47, -3.0F, -2.0F, -6.0F, 4, 2, 9, 0.0F, false));
			high2 = new ModelRenderer(this);
			high2.setRotationPoint(0.0F, -1.0261F, 4.8191F);
			leg2.addChild(high2);
			setRotationAngle(high2, 0.7854F, 0.0F, 0.0F);
			high2.cubeList.add(new ModelBox(high2, 40, 58, -2.0F, -29.1931F, 16.2865F, 2, 15, 3, 0.0F, false));
			low2 = new ModelRenderer(this);
			low2.setRotationPoint(0.0F, 0.0F, 0.0F);
			leg2.addChild(low2);
			setRotationAngle(low2, -0.3491F, 0.0F, 0.0F);
			low2.cubeList.add(new ModelBox(low2, 10, 0, -2.0F, -26.0979F, -2.8612F, 2, 26, 3, 0.0F, false));
			leg3 = new ModelRenderer(this);
			leg3.setRotationPoint(-4.0F, 0.0F, 7.0F);
			whole.addChild(leg3);
			setRotationAngle(leg3, 0.0F, 0.0F, 0.0F);
			leg3.cubeList.add(new ModelBox(leg3, 34, 18, -3.0F, -2.0F, -6.0F, 4, 2, 9, 0.0F, false));
			high3 = new ModelRenderer(this);
			high3.setRotationPoint(0.0F, -1.0261F, 4.8191F);
			leg3.addChild(high3);
			setRotationAngle(high3, 0.7854F, 0.0F, 0.0F);
			high3.cubeList.add(new ModelBox(high3, 20, 0, -2.0F, -29.1931F, 16.2865F, 2, 15, 3, 0.0F, false));
			low3 = new ModelRenderer(this);
			low3.setRotationPoint(0.0F, 0.0F, 0.0F);
			leg3.addChild(low3);
			setRotationAngle(low3, -0.3491F, 0.0F, 0.0F);
			low3.cubeList.add(new ModelBox(low3, 0, 0, -2.0F, -26.0979F, -2.8612F, 2, 26, 3, 0.0F, false));
			handles = new ModelRenderer(this);
			handles.setRotationPoint(5.0F, 0.0F, 0.0F);
			whole.addChild(handles);
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			whole.render(f5);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
			super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
			this.leg2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.leg3.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}
}
