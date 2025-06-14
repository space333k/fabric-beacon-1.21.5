package com.space333.beacon;

import com.space333.beacon.component.ModDataComponentType;
import com.space333.beacon.effect.ModEffects;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Beacon implements ModInitializer {
	public static final String MOD_ID = "beacon";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModEffects.registerEffects();
		ModDataComponentType.registerDataComponentTypes();
	}
}