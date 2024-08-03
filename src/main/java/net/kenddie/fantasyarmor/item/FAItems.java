package net.kenddie.fantasyarmor.item;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.item.armor.*;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings({"unused"})
public final class FAItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FantasyArmor.MOD_ID);

    public static final RegistryObject<Item> MOON_CRYSTAL = ITEMS.register("moon_crystal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ECLIPSE_SOLDIER_HELMET = ITEMS.register("eclipse_soldier_helmet", () -> new EclipseSoldierArmorItem(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> ECLIPSE_SOLDIER_CHESTPLATE = ITEMS.register("eclipse_soldier_chestplate", () -> new EclipseSoldierArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> ECLIPSE_SOLDIER_LEGGINGS = ITEMS.register("eclipse_soldier_leggings", () -> new EclipseSoldierArmorItem(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> ECLIPSE_SOLDIER_BOOTS = ITEMS.register("eclipse_soldier_boots", () -> new EclipseSoldierArmorItem(ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> DRAGONSLAYER_HELMET = ITEMS.register("dragonslayer_helmet", () -> new DragonslayerArmorItem(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> DRAGONSLAYER_CHESTPLATE = ITEMS.register("dragonslayer_chestplate", () -> new DragonslayerArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> DRAGONSLAYER_LEGGINGS = ITEMS.register("dragonslayer_leggings", () -> new DragonslayerArmorItem(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> DRAGONSLAYER_BOOTS = ITEMS.register("dragonslayer_boots", () -> new DragonslayerArmorItem(ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> HERO_HELMET = ITEMS.register("hero_helmet", () -> new HeroArmorItem(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> HERO_CHESTPLATE = ITEMS.register("hero_chestplate", () -> new HeroArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> HERO_LEGGINGS = ITEMS.register("hero_leggings", () -> new HeroArmorItem(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> HERO_BOOTS = ITEMS.register("hero_boots", () -> new HeroArmorItem(ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> GOLDEN_HORNS_HELMET = ITEMS.register("golden_horns_helmet", () -> new GoldenHornsArmorItem(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> GOLDEN_HORNS_CHESTPLATE = ITEMS.register("golden_horns_chestplate", () -> new GoldenHornsArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> GOLDEN_HORNS_LEGGINGS = ITEMS.register("golden_horns_leggings", () -> new GoldenHornsArmorItem(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> GOLDEN_HORNS_BOOTS = ITEMS.register("golden_horns_boots", () -> new GoldenHornsArmorItem(ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> THIEF_HELMET = ITEMS.register("thief_helmet", () -> new ThiefArmorItem(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> THIEF_CHESTPLATE = ITEMS.register("thief_chestplate", () -> new ThiefArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> THIEF_LEGGINGS = ITEMS.register("thief_leggings", () -> new ThiefArmorItem(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> THIEF_BOOTS = ITEMS.register("thief_boots", () -> new ThiefArmorItem(ArmorItem.Type.BOOTS));

    private FAItems() {
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
