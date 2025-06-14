package com.space333.beacon.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
                .add(Blocks.COAL_BLOCK)
                .add(Blocks.REDSTONE_BLOCK)
                //.add(Blocks.COPPER_BLOCK)
                .add(Blocks.WAXED_COPPER_BLOCK)
                .add(Blocks.AMETHYST_BLOCK)
                .add(Blocks.LAPIS_BLOCK)
                .add(Blocks.QUARTZ_BLOCK);

    }


}
