package net.yak.onguard.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.systems.RenderPass;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.DefaultFramebufferSet;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.ObjectAllocator;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.yak.onguard.OnGuard;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Shadow @Nullable private Identifier postProcessorId;

    @Shadow protected abstract void setPostProcessor(Identifier id);

    @Shadow public abstract void clearPostProcessor();

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "tick", at = @At("TAIL"))
    private void modId$shader(CallbackInfo ci) {
        if (this.client.world.getTickManager().shouldTick()) {
            if (this.client.getCameraEntity() instanceof LivingEntity) {
                if (this.postProcessorId == null && client.options.hudHidden) {
                    setPostProcessor(Identifier.ofVanilla("tethered")); // dude. fuck yes
                    OnGuard.LOGGER.info(this.postProcessorId.toString());
                }
                if (!client.options.hudHidden && this.postProcessorId != null && this.postProcessorId.toString().equals("minecraft:tethered")) {
                    this.clearPostProcessor();
                }
            }
        }
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gl/PostEffectProcessor;render(Lnet/minecraft/client/gl/Framebuffer;Lnet/minecraft/client/util/ObjectAllocator;Ljava/util/function/Consumer;)V"))
    private void hi(PostEffectProcessor instance, Framebuffer framebuffer, ObjectAllocator objectAllocator, Consumer<RenderPass> additionalUniformsSetter, Operation<Void> original) {
        if (this.postProcessorId != null && !this.postProcessorId.toString().equals("minecraft:tethered") && client.getCameraEntity() instanceof LivingEntity && client.options.hudHidden) {
            // possible loop here if you had like multiple shaders or an event or whatever
            PostEffectProcessor postEffectProcessor  = this.client.getShaderLoader().loadPostEffect(Identifier.ofVanilla("tethered"), DefaultFramebufferSet.MAIN_ONLY);
            original.call(postEffectProcessor, framebuffer, objectAllocator, additionalUniformsSetter);
        }
        original.call(instance, framebuffer, objectAllocator, additionalUniformsSetter);
    }

}
