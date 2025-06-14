package com.space333.beacon.effect;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Map;

public class BeaconEffects {

    public static final Map<Block, RegistryEntry<StatusEffect>> EFFECT_MAP = Map.of(
            Blocks.DIAMOND_BLOCK, StatusEffects.REGENERATION,
            Blocks.GOLD_BLOCK, StatusEffects.HASTE,
            Blocks.IRON_BLOCK, StatusEffects.RESISTANCE,
            Blocks.COAL_BLOCK, StatusEffects.NIGHT_VISION,
            Blocks.EMERALD_BLOCK, StatusEffects.SATURATION,
            Blocks.REDSTONE_BLOCK, ModEffects.VISION,
            Blocks.WAXED_COPPER_BLOCK, StatusEffects.JUMP_BOOST,
            Blocks.AMETHYST_BLOCK, StatusEffects.STRENGTH,
            Blocks.LAPIS_BLOCK, StatusEffects.INVISIBILITY,
            Blocks.QUARTZ_BLOCK, StatusEffects.SPEED
    );
}
