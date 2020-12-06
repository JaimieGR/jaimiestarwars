
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