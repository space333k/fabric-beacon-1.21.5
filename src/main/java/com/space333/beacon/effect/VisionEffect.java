package com.space333.beacon.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;


public class VisionEffect extends StatusEffect {


    protected VisionEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
