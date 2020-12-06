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