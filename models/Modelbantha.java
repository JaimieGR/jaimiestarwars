
public static class Modelbantha extends ModelBase {
	private final ModelRenderer leg;
	private final ModelRenderer leg2;
	private final ModelRenderer head;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer body;

	public Modelbantha() {
		textureWidth = 256;
		textureHeight = 256;

		leg = new ModelRenderer(this);
		leg.setRotationPoint(-9.0F, 24.0F, -3.0F);
		leg.cubeList.add(new ModelBox(leg, 77, 80, -7.0F, -10.0F, 20.0F, 7, 10, 7, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(-9.0F, 24.0F, -3.0F);
		leg2.cubeList.add(new ModelBox(leg2, 49, 80, 18.0F, -10.0F, 20.0F, 7, 10, 7, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 80, -8.0F, -43.0F, -35.0F, 16, 16, 17, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 14, 37, -11.0F, -41.0F, -32.0F, 3, 3, 8, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 34, 8.0F, -41.0F, -32.0F, 3, 3, 8, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 18, 24, -13.0F, -32.0F, -34.0F, 3, 3, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 21, 7, 11.0F, -32.0F, -34.0F, 3, 3, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 66, 97, -12.0F, -41.0F, -24.0F, 3, 12, 3, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 80, 10.0F, -41.0F, -24.0F, 3, 12, 3, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 36, 37, -13.0F, -35.0F, -37.0F, 3, 6, 3, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 34, 20, 11.0F, -35.0F, -37.0F, 3, 6, 3, 0.0F, false));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(-9.0F, 24.0F, -3.0F);
		leg3.cubeList.add(new ModelBox(leg3, 0, 17, 18.0F, -10.0F, -11.0F, 7, 10, 7, 0.0F, false));

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(-9.0F, 24.0F, -3.0F);
		leg4.cubeList.add(new ModelBox(leg4, 0, 0, -7.0F, -10.0F, -11.0F, 7, 10, 7, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(16.0F, 16.0F, -8.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -32.0F, -32.0F, -11.0F, 32, 32, 48, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		leg.render(f5);
		leg2.render(f5);
		head.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		body.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.leg4.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.leg2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.leg3.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
	}
}