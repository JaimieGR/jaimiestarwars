
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