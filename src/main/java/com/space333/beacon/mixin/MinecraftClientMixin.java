package com.space333.beacon.mixin;

import com.space333.beacon.effect.ModEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Redirect(method = "hasOutline", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;isGlowing()Z"))
    public boolean checkVisibility(Entity instance) {
        MinecraftClient self = (MinecraftClient) (Object) this;
        PlayerEntity player = self.player;

        if(player != null && player.hasStatusEffect(ModEffects.VISION) && instance instanceof LivingEntity) {
            StatusEffectInstance vision = player.getStatusEffect(ModEffects.VISION);
            if(vision != null) {
                int level = vision.getAmplifier() + 1;
                if(player.distanceTo(instance) <= level * 10) {
                    return true;
                }
            }
        }


        return instance.isGlowing();
    }

}
