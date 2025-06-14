package com.space333.beacon.mixin;

import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GameRenderer.class)
public class GameRenderMixin {

    @ModifyConstant(method = "getNightVisionStrength", constant = @Constant(intValue = 200))
    private static int reduceNightVisionDimerTimer(int constant) {
        return constant/2;
    }

}
