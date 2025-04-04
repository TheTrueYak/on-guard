package net.yak.onguard;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.item.model.special.SpecialModelTypes;
import net.yak.onguard.client.render.AmethystShieldModelRenderer;
import net.yak.onguard.client.render.model.AmethystShieldEntityModel;

public class OnGuardClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(AmethystShieldEntityModel.AMETHYST_SHIELD, AmethystShieldEntityModel::getTexturedModelData);

        SpecialModelTypes.ID_MAPPER.put(OnGuard.id("amethyst_shield"), AmethystShieldModelRenderer.Unbaked.CODEC);
    }

}
