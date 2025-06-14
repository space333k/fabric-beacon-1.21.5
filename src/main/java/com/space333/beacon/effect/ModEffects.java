package com.space333.beacon.effect;

import com.space333.beacon.Beacon;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class ModEffects {
    private static Object EntityAttributes;
    public static final RegistryEntry<StatusEffect> VISION = registerStatusEffect("vision",
            new VisionEffect(StatusEffectCategory.BENEFICIAL, Formatting.LIGHT_PURPLE.getColorValue()));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Beacon.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        Beacon.LOGGER.info("Registering Mod Effects for " + Beacon.MOD_ID);
    }
}
