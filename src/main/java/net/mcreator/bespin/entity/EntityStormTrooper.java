
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
import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.init.Blocks;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIFollow;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.Minecraft;

import net.mcreator.bespin.item.ItemDL44Blaster;
import net.mcreator.bespin.ElementsBespin;

@ElementsBespin.ModElement.Tag
public class EntityStormTrooper extends ElementsBespin.ModElement {
	public static final int ENTITYID = 16;
	public static final int ENTITYID_RANGED = 17;
	public EntityStormTrooper(ElementsBespin instance) {
		super(instance, 73);
	}

	@Override
	public void initElements() {
		elements.entities.add(() -> EntityEntryBuilder.create().entity(EntityCustom.class)
				.id(new ResourceLocation("bespin", "stormtrooper"), ENTITYID).name("stormtrooper").tracker(75, 3, true).egg(-1, -1).build());
		elements.entities.add(() -> EntityEntryBuilder.create().entity(EntityArrowCustom.class)
				.id(new ResourceLocation("bespin", "entitybulletstormtrooper"), ENTITYID_RANGED).name("entitybulletstormtrooper").tracker(64, 1, true)
				.build());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		Biome[] spawnBiomes = {Biome.REGISTRY.getObject(new ResourceLocation("bespin:tatflat")),};
		EntityRegistry.addSpawn(EntityCustom.class, 5, 8, 8, EnumCreatureType.CREATURE, spawnBiomes);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityCustom.class, renderManager -> {
			return new RenderLiving(renderManager, new Modelstormtrooper(), 0.5f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("bespin:textures/stormtrooper.png");
				}
			};
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityArrowCustom.class, renderManager -> {
			return new RenderSnowball<EntityArrowCustom>(renderManager, null, Minecraft.getMinecraft().getRenderItem()) {
				public ItemStack getStackToRender(EntityArrowCustom entity) {
					return new ItemStack(Blocks.LAPIS_BLOCK, (int) (1));
				}
			};
		});
	}
	public static class EntityCustom extends EntityMob implements IRangedAttackMob {
		public EntityCustom(World world) {
			super(world);
			setSize(0.7999999999999999f, 2.1f);
			experienceValue = 5;
			this.isImmuneToFire = false;
			setNoAI(!true);
			this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemDL44Blaster.block, (int) (1)));
		}

		@Override
		protected void initEntityAI() {
			super.initEntityAI();
			this.tasks.addTask(1, new EntityAIWander(this, 1));
			this.tasks.addTask(2, new EntityAIFollow(this, (float) 1, 10, 5));
			this.tasks.addTask(3, new EntityAILookIdle(this));
			this.tasks.addTask(4, new EntityAISwimming(this));
			this.tasks.addTask(5, new EntityAILeapAtTarget(this, (float) 0.8));
			this.targetTasks.addTask(6, new EntityAIHurtByTarget(this, true));
			this.tasks.addTask(7, new EntityAIOpenDoor(this, false));
			this.tasks.addTask(1, new EntityAIAttackRanged(this, 1.25D, 20, 10.0F));
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
			if (source.getImmediateSource() instanceof EntityArrow)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
			if (this.getEntityAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0D);
			if (this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
			if (this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15D);
			if (this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3D);
		}

		@Override
		public void setSwingingArms(boolean swingingArms) {
		}

		public void attackEntityWithRangedAttack(EntityLivingBase target, float flval) {
			EntityArrowCustom entityarrow = new EntityArrowCustom(this.world, this);
			double d0 = target.posY + (double) target.getEyeHeight() - 1.1;
			double d1 = target.posX - this.posX;
			double d3 = target.posZ - this.posZ;
			entityarrow.shoot(d1, d0 - entityarrow.posY + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1.6F, 12.0F);
			this.world.spawnEntity(entityarrow);
		}
	}

	public static class EntityArrowCustom extends EntityTippedArrow {
		public EntityArrowCustom(World a) {
			super(a);
		}

		public EntityArrowCustom(World worldIn, double x, double y, double z) {
			super(worldIn, x, y, z);
		}

		public EntityArrowCustom(World worldIn, EntityLivingBase shooter) {
			super(worldIn, shooter);
		}

		@Override
		public boolean canBeCollidedWith() {
			return false;
		}
	}

	public static class Modelstormtrooper extends ModelBase {
		private final ModelRenderer head;
		private final ModelRenderer body;
		private final ModelRenderer rightArm;
		private final ModelRenderer leftArm;
		private final ModelRenderer rightLeg;
		private final ModelRenderer leftLeg;
		public Modelstormtrooper() {
			textureWidth = 128;
			textureHeight = 128;
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 0.0F, 0.0F);
			head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -8.0F, -3.0F, 8, 8, 7, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 25, 10, -3.0F, -9.0F, -3.0F, 6, 1, 6, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 47, 41, -4.0F, -8.0F, -4.0F, 8, 3, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 42, 18, -4.0F, -4.0F, -4.0F, 8, 4, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 54, 0, 3.0F, -5.0F, -4.0F, 1, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 31, 7, -1.0F, -5.0F, -4.0F, 2, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 21, 54, -4.0F, -5.0F, -4.0F, 1, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 11, 30, 4.0F, -2.0F, -4.0F, 1, 2, 8, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 0, 26, -5.0F, -2.0F, -4.0F, 1, 2, 8, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 36, 61, -5.0F, -5.0F, 3.0F, 1, 3, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 58, 32, -5.0F, -4.0F, 2.0F, 1, 2, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 44, 12, 4.0F, -4.0F, 2.0F, 1, 2, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 31, 60, 4.0F, -5.0F, 3.0F, 1, 3, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 13, 41, 3.0F, -2.0F, -5.0F, 2, 2, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 25, 18, -3.0F, 0.0F, -5.0F, 6, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 11, 54, 1.0F, -1.0F, -5.0F, 1, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 44, 0, 1.0F, -3.0F, -5.0F, 1, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 38, 7, -2.0F, -3.0F, -5.0F, 1, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 51, 24, -2.0F, -1.0F, -5.0F, 1, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 49, 0, -3.0F, -2.0F, -5.0F, 1, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 11, 31, -1.0F, -2.0F, -5.0F, 2, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 0, 31, -1.0F, -5.0F, -5.0F, 2, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 22, 30, -2.0F, -4.0F, -5.0F, 4, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 46, 24, 2.0F, -2.0F, -5.0F, 1, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 0, 37, -5.0F, -2.0F, -5.0F, 2, 2, 1, 0.0F, false));
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 0.0F, 0.0F);
			body.cubeList.add(new ModelBox(body, 0, 16, -4.0F, 0.0F, -2.0F, 8, 5, 4, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 55, 0, 3.0F, 5.0F, -2.0F, 1, 1, 4, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 24, 54, -4.0F, 5.0F, -2.0F, 1, 1, 4, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 21, 22, -4.0F, 9.0F, -2.0F, 8, 3, 4, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 24, 0, -4.0F, 6.0F, -1.0F, 8, 3, 3, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 43, 27, -3.0F, 5.0F, -1.0F, 6, 1, 3, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 24, 60, -4.0F, 9.0F, -3.0F, 2, 3, 1, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 11, 26, -4.0F, 9.0F, 2.0F, 2, 3, 1, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 59, 6, -1.0F, 9.0F, -3.0F, 2, 3, 1, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 0, 26, -1.0F, 9.0F, 2.0F, 2, 3, 1, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 35, 56, -2.0F, 3.0F, 2.0F, 4, 3, 1, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 22, 33, 2.0F, 9.0F, -3.0F, 2, 3, 1, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 0, 0, 2.0F, 9.0F, 2.0F, 2, 3, 1, 0.0F, false));
			rightArm = new ModelRenderer(this);
			rightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
			rightArm.cubeList.add(new ModelBox(rightArm, 17, 41, -1.0F, 2.0F, -2.0F, 4, 8, 4, 0.0F, false));
			rightArm.cubeList.add(new ModelBox(rightArm, 44, 3, 0.0F, -2.0F, -2.0F, 3, 4, 4, 0.0F, false));
			rightArm.cubeList.add(new ModelBox(rightArm, 52, 59, -1.0F, -1.0F, -1.0F, 1, 3, 3, 0.0F, false));
			leftArm = new ModelRenderer(this);
			leftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
			leftArm.cubeList.add(new ModelBox(leftArm, 34, 43, -3.0F, 2.0F, -2.0F, 4, 8, 4, 0.0F, false));
			leftArm.cubeList.add(new ModelBox(leftArm, 47, 32, -3.0F, -2.0F, -2.0F, 3, 4, 4, 0.0F, false));
			leftArm.cubeList.add(new ModelBox(leftArm, 59, 21, 0.0F, -1.0F, -1.0F, 1, 3, 3, 0.0F, false));
			rightLeg = new ModelRenderer(this);
			rightLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
			rightLeg.cubeList.add(new ModelBox(rightLeg, 30, 30, -2.0F, 4.0F, -2.0F, 4, 8, 4, 0.0F, false));
			rightLeg.cubeList.add(new ModelBox(rightLeg, 0, 54, -1.0F, 1.0F, -2.0F, 3, 3, 4, 0.0F, false));
			rightLeg.cubeList.add(new ModelBox(rightLeg, 50, 12, -2.0F, 0.0F, -1.0F, 4, 1, 3, 0.0F, false));
			rightLeg.cubeList.add(new ModelBox(rightLeg, 43, 58, -2.0F, 1.0F, -1.0F, 1, 3, 3, 0.0F, false));
			leftLeg = new ModelRenderer(this);
			leftLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
			leftLeg.cubeList.add(new ModelBox(leftLeg, 0, 41, -2.0F, 4.0F, -2.0F, 4, 8, 4, 0.0F, false));
			leftLeg.cubeList.add(new ModelBox(leftLeg, 51, 51, -2.0F, 1.0F, -2.0F, 3, 3, 4, 0.0F, false));
			leftLeg.cubeList.add(new ModelBox(leftLeg, 51, 46, -2.0F, 0.0F, -1.0F, 3, 1, 3, 0.0F, false));
			leftLeg.cubeList.add(new ModelBox(leftLeg, 15, 54, 1.0F, 0.0F, -1.0F, 1, 4, 3, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			head.render(f5);
			body.render(f5);
			rightArm.render(f5);
			leftArm.render(f5);
			rightLeg.render(f5);
			leftLeg.render(f5);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
			super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.rightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.rightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.leftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.leftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		}
	}
}
