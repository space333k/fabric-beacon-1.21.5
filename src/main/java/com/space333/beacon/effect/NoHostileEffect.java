package com.space333.beacon.effect;

import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class NoHostileEffect {

    public static void removeHostile(World world, BlockPos pos, double range) {
        List<HostileEntity> hostileMobs = world.getEntitiesByClass(
                HostileEntity.class,
                new Box(pos).expand(range).stretch(0.0, world.getHeight(), 0.0),
                HostileEntity::isAlive
        );

        for(HostileEntity hostileMob: hostileMobs) {
            hostileMob.discard();
        }
    }

}
