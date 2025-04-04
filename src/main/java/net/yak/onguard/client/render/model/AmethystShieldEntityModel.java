package net.yak.onguard.client.render.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.yak.onguard.OnGuard;

import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class AmethystShieldEntityModel extends Model { // commented out code is what I did for my own model before I switched it to the one from amy shield
    public static final EntityModelLayer AMETHYST_SHIELD = new EntityModelLayer(OnGuard.id("amethyst_shield"), "main");
    private final ModelPart root;
    /*private static final String PLATE = "plate";
    private static final String HANDLE = "handle";
    private static final String LEFT_CRYSTAL = "left_crystal";
    private static final String RIGHT_CRYSTAL = "right_crystal";
    private final ModelPart plate;
    private final ModelPart handle;
    private final ModelPart left_crystal;
    private final ModelPart right_crystal;*/

    public AmethystShieldEntityModel(ModelPart root) {
        super(root, RenderLayer::getEntityCutout);
        this.root = root;
        /*this.plate = root.getChild("plate");
        this.handle = root.getChild("handle");
        this.left_crystal = root.getChild("left_crystal");
        this.right_crystal = root.getChild("right_crystal");*/
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        /*modelPartData.addChild("plate", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -11.0F, -2.0F, 12.0F, 22.0F, 1.0F), ModelTransform.NONE);
        modelPartData.addChild("handle", ModelPartBuilder.create().uv(26, 0).cuboid(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 6.0F), ModelTransform.NONE);
        modelPartData.addChild("left_crystal", ModelPartBuilder.create().uv(2, 2).cuboid(-1.12F, -6.0F, 5.0F, 5.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -9.0F, -6.0F, 0.0F, 0.3927F, 0.0F));
        modelPartData.addChild("right_crystal", ModelPartBuilder.create().uv(-1, 2).cuboid(-3.88F, -6.0F, 5.0F, 5.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, -9.0F, -6.0F, 0.0F, -0.3927F, 0.0F));*/
        ModelPartData amethist_shield = modelPartData.addChild("amethist_shield", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 13.0F, 0.0F));
        modelPartData.addChild("mainshield", ModelPartBuilder.create()
                .uv(0, 23).cuboid(-1.0F, -3.0F, -3.0F, 2.0F, 6.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-6.0F, -11.0F, -4.0F, 12.0F, 22.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 3.0F));

        ModelPartData skulk = modelPartData.addChild("skulk", ModelPartBuilder.create()
                .uv(10, 24).cuboid(-10.0F, -12.0F, 5.0F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(8.0F, 11.0F, -7.0F));

        skulk.addChild("glow", ModelPartBuilder.create()
                .uv(0, 35).cuboid(-10.0F, -12.0F, 5.0F, 4.0F, 4.0F, 2.0F, new Dilation(-0.1F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData amethist = modelPartData.addChild("amethist", ModelPartBuilder.create(), ModelTransform.origin(16.0F, 11.0F, -15.0F));

        ModelPartData budinfront = amethist.addChild("budinfront", ModelPartBuilder.create(), ModelTransform.of(-16.0F, -3.5F, 12.25F, 0.2618F, 0.0F, 0.0F));

        budinfront.addChild("right_font", ModelPartBuilder.create()
                .uv(42, 14).cuboid(0.0F, -7.0F, 0.0F, 8.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        budinfront.addChild("left_font", ModelPartBuilder.create()
                .uv(26, 14).cuboid(-8.0F, -7.0F, 0.0F, 8.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

        ModelPartData budinback = amethist.addChild("budinback", ModelPartBuilder.create(), ModelTransform.of(-16.0F, -8.0F, 11.75F, 0.1745F, 0.0F, 0.0F));

        budinback.addChild("left_back", ModelPartBuilder.create()
                .uv(26, 0).cuboid(-8.0F, -7.0F, 0.0F, 8.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

        budinback.addChild("right_back", ModelPartBuilder.create()
                .uv(42, 0).cuboid(0.0F, -7.0F, 0.0F, 8.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 0.0F, 0.0F, -0.3927F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    /*public ModelPart getPlate() {
        return this.plate;
    }

    public ModelPart getHandle() {
        return this.handle;
    }

    public ModelPart getLeftCrystal() {
        return this.left_crystal;
    }

    public ModelPart getRightCrystal() {
        return this.right_crystal;
    }*/
}
