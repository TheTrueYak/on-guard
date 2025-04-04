package net.yak.onguard;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import net.yak.onguard.init.OnGuardItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnGuard implements ModInitializer {
	public static final String MOD_ID = "onguard";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("yap yap yap hiiii custom shield :>");

		OnGuardItems.init();


	}

	public static Identifier id(String id) {
		return Identifier.of(MOD_ID, id);
	}
}