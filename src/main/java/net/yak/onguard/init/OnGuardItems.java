package net.yak.onguard.init;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.component.type.BlocksAttacksComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.SoundEvents;
import net.yak.onguard.OnGuard;
import net.yak.onguard.item.AmethystShieldItem;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class OnGuardItems {

    public static final Item AMETHYST_SHIELD = register("amethyst_shield", AmethystShieldItem::new,
            (new Item.Settings()).maxDamage(450).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT).repairable(Items.AMETHYST_SHARD).equippableUnswappable(EquipmentSlot.OFFHAND)
                    .component(DataComponentTypes.BLOCKS_ATTACKS, new BlocksAttacksComponent(0.25f, 1.0f, List.of(new BlocksAttacksComponent.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                            new BlocksAttacksComponent.ItemDamage(3.0F, 1.0F, 1.0F), Optional.of(DamageTypeTags.BYPASSES_SHIELD), Optional.of(SoundEvents.ITEM_SHIELD_BLOCK), Optional.of(SoundEvents.ITEM_SHIELD_BREAK)))
                    .component(DataComponentTypes.BREAK_SOUND, SoundEvents.ITEM_SHIELD_BREAK));


    public static void init() {

    }

    public static Item register(String id, Function<Item.Settings, Item> factory, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, OnGuard.id(id));
        Item item = factory.apply(settings.registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }
}
