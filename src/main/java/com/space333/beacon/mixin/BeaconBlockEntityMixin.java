package com.space333.beacon.mixin;

import com.space333.beacon.Beacon;
import com.space333.beacon.effect.BeaconEffects;
import com.space333.beacon.effect.NoHostileEffect;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(BeaconBlockEntity.class)
public class BeaconBlockEntityMixin {
    @Unique
    private static int level;

    @Unique
    private static Block baseBlock = Blocks.AIR;

    @ModifyConstant(method = "updateLevel", constant = @Constant(intValue = 4))
    private static int getBaseBlock(int constant) {
        return 1000;
    }

    @Inject(method = "updateLevel", at = @At(value = "HEAD"))
    private static void resetBaseBlock(World world, int x, int y, int z, CallbackInfoReturnable<Integer> cir) {
        baseBlock = Blocks.AIR;
    }

    @Redirect(method = "updateLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    private static boolean getBaseBlock(BlockState instance, TagKey tagKey) {
        if(!instance.isIn(tagKey)) {
            return false;
        }
        Block block = instance.getBlock();
        if(baseBlock == Blocks.AIR) {
            baseBlock = block;
        }
        else if(baseBlock != block) {
            return false;
        }

        return true;
    }

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private static void updateNetheriteBeacon(World world, BlockPos pos, BlockState state, BeaconBlockEntity blockEntity, CallbackInfo ci) {

        if(world.getBlockState(pos.offset(Direction.DOWN)).getBlock() == Blocks.NETHERITE_BLOCK && level > 0) {
            NoHostileEffect.removeHostile(world, pos, level * 25);
        }

    }

    @Inject(method = "applyPlayerEffects", at = @At(value = "HEAD"), cancellable = true)
    private static void applyEffects(World world, BlockPos pos, int beaconLevel, @Nullable RegistryEntry<StatusEffect> primaryEffect, @Nullable RegistryEntry<StatusEffect> secondaryEffect, CallbackInfo ci) {
        primaryEffect = BeaconEffects.EFFECT_MAP.get(baseBlock);
        level = beaconLevel;

        if (!world.isClient && primaryEffect != null) {
            double range = 0;
            double range_increase = 15;
            for(int i = 0; i < level; i++) {
                range += range_increase;
                range_increase *= 0.99;
            }

            int effectLevel = 0;
            if (beaconLevel >= 4) {
                effectLevel = 1;
            }

            int effectDuration = (10 + beaconLevel) * 20;
            Box box = new Box(pos).expand(range).stretch(0.0, world.getHeight(), 0.0);
            List<PlayerEntity> list = world.getNonSpectatingEntities(PlayerEntity.class, box);

            for (PlayerEntity playerEntity : list) {
                playerEntity.addStatusEffect(new StatusEffectInstance(primaryEffect, effectDuration, effectLevel, true, true));
            }
        }
        ci.cancel();
    }

}
