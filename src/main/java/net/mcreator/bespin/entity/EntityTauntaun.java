
package net.mcreator.bespin.entity;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.EnumHand;
import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelBase;

import net.mcreator.bespin.ElementsBespin;

@ElementsBespin.ModElement.Tag
public class EntityTauntaun extends ElementsBespin.ModElement {
	public static final int ENTITYID = 5;
	public static final int ENTITYID_RANGED = 6;
	public EntityTauntaun(ElementsBespin instance) {
		super(instance, 10);
	}

	@Override
	public void initElements() {
		elements.entities.add(() -> EntityEntryBuilder.create().entity(EntityCustom.class).id(new ResourceLocation("bespin", "tauntaun"), ENTITYID)
				.name("tauntaun").tracker(64, 3, true).egg(-3355444, -10079488).build());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		Biome[] spawnBiomes = {Biome.REGISTRY.getObject(new ResourceLocation("bespin:hothflat")),};
		EntityRegistry.addSpawn(EntityCustom.class, 20, 3, 30, EnumCreatureType.CREATURE, spawnBiomes);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityCustom.class, renderManager -> {
			return new RenderLiving(renderManager, new ModeltauntaunEARLYTEST(), 0.5f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("bespin:textures/tauntaunwooly.png");
				}
			};
		});
	}
	public static class EntityCustom extends EntityCreature {
		public EntityCustom(World world) {
			super(world);
			setSize(1f, 1.4f);
			experienceValue = 5;
			this.isImmuneToFire = false;
			setNoAI(!true);
		}

		@Override
		protected void initEntityAI() {
			super.initEntityAI();
			this.tasks.addTask(1, new EntityAIWander(this, 1));
			this.tasks.addTask(2, new EntityAILookIdle(this));
			this.tasks.addTask(3, new EntityAISwimming(this));
			this.tasks.addTask(4, new EntityAILeapAtTarget(this, (float) 0.8));
			this.tasks.addTask(5, new EntityAIPanic(this, 1.2));
			this.targetTasks.addTask(6, new EntityAIHurtByTarget(this, false));
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
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.CACTUS)
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
				this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(1D);
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

	public static class ModeltauntaunEARLYTEST extends ModelBase {
		private final ModelRenderer head;
		private final ModelRenderer body;
		private final ModelRenderer arm2;
		private final ModelRenderer arm;
		private final ModelRenderer leg;
		private final ModelRenderer leg2;
		public ModeltauntaunEARLYTEST() {
			textureWidth = 128;
			textureHeight = 128;
			head = new ModelRenderer(this);
			head.setRotationPoint(-2.0F, -11.0F, 3.0F);
			head.cubeList.add(new ModelBox(head, 0, 31, 0.0F, 4.0F, -16.0F, 6, 6, 8, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 20, 31, 1.0F, 6.0F, -18.0F, 4, 4, 2, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 0, 14, 1.0F, 3.0F, -15.0F, 4, 1, 6, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 45, 16, -2.0F, 9.0F, -17.0F, 1, 1, 4, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 0, 4, -2.0F, 8.0F, -18.0F, 1, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 0, 0, -1.0F, 8.0F, -14.0F, 1, 2, 2, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 13, 0, 6.0F, 8.0F, -14.0F, 1, 2, 2, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 46, 31, 7.0F, 9.0F, -17.0F, 1, 1, 4, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 3, 5, 7.0F, 8.0F, -18.0F, 1, 1, 1, 0.0F, false));
			body = new ModelRenderer(this);
			body.setRotationPoint(-3.0F, 6.0F, 5.0F);
			setRotationAngle(body, -0.1745F, 0.0F, 0.0F);
			body.cubeList.add(new ModelBox(body, 0, 0, -2.0F, -0.0899F, -15.8329F, 12, 10, 21, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 44, 44, 3.0F, 9.0419F, 2.0912F, 2, 1, 8, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 28, 31, 1.0F, -6.0899F, -17.8329F, 6, 11, 6, 0.0F, false));
			arm2 = new ModelRenderer(this);
			arm2.setRotationPoint(-1.0F, 8.0F, 7.0F);
			arm2.cubeList.add(new ModelBox(arm2, 29, 48, 6.0F, 2.0F, -18.0F, 2, 8, 2, 0.0F, false));
			arm = new ModelRenderer(this);
			arm.setRotationPoint(-11.0F, 8.0F, 7.0F);
			arm.cubeList.add(new ModelBox(arm, 45, 6, 6.0F, 2.0F, -18.0F, 2, 8, 2, 0.0F, false));
			leg = new ModelRenderer(this);
			leg.setRotationPoint(-13.0F, 16.0F, 19.0F);
			leg.cubeList.add(new ModelBox(leg, 20, 51, 6.0F, 1.0F, -19.0F, 2, 6, 2, 0.0F, false));
			leg.cubeList.add(new ModelBox(leg, 0, 45, 5.0F, -6.0F, -21.0F, 3, 7, 7, 0.0F, false));
			leg.cubeList.add(new ModelBox(leg, 45, 0, 5.0F, 7.0F, -21.0F, 3, 1, 5, 0.0F, false));
			leg2 = new ModelRenderer(this);
			leg2.setRotationPoint(1.0F, 16.0F, 19.0F);
			leg2.cubeList.add(new ModelBox(leg2, 0, 31, 6.0F, 1.0F, -19.0F, 2, 6, 2, 0.0F, false));
			leg2.cubeList.add(new ModelBox(leg2, 0, 0, 6.0F, -6.0F, -21.0F, 3, 7, 7, 0.0F, false));
			leg2.cubeList.add(new ModelBox(leg2, 13, 45, 5.0F, 7.0F, -21.0F, 3, 1, 5, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			head.render(f5);
			body.render(f5);
			arm2.render(f5);
			arm.render(f5);
			leg.render(f5);
			leg2.render(f5);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
			super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
			this.leg2.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		}
	}
}
