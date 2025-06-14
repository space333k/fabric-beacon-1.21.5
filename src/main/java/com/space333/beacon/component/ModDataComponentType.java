package com.space333.beacon.component;

import com.space333.beacon.Beacon;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;

import java.util.UUID;
import java.util.function.UnaryOperator;

public class ModDataComponentType {
    public static final ComponentType<String> VISIBLE = register("visible", builder -> builder.codec(Codecs.NON_EMPTY_STRING).packetCodec(PacketCodecs.STRING));

    private  static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Beacon.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build()
        );
    }

    public static void registerDataComponentTypes() {
        Beacon.LOGGER.info("Registering Data Component Types for" + Beacon.MOD_ID);
    }
}
