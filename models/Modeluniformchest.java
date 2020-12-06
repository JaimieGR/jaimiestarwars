
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