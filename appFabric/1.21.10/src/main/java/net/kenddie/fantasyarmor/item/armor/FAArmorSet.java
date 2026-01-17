package net.kenddie.fantasyarmor.item.armor;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.Supplier;

public enum FAArmorSet {
    ECLIPSE_SOLDIER(FAArmorSets.EclipseSoldierArmorItem::new),
    DRAGONSLAYER(FAArmorSets.DragonslayerArmorItem::new),
    HERO(FAArmorSets.HeroArmorItem::new),
    GOLDEN_HORNS(FAArmorSets.GoldenHornsArmorItem::new),
    THIEF(FAArmorSets.ThiefArmorItem::new),
    WANDERING_WIZARD(FAArmorSets.WanderingWizardArmorItem::new),
    CHESS_BOARD_KNIGHT(FAArmorSets.ChessBoardKnightArmorItem::new),
    DARK_LORD(FAArmorSets.DarkLordArmorItem::new),
    SUNSET_WINGS(FAArmorSets.SunsetWingsArmorItem::new),
    FOG_GUARD(FAArmorSets.FogGuardArmorItem::new),
    DARK_COVER(FAArmorSets.DarkCoverArmorItem::new),
    SPARK_OF_DAWN(FAArmorSets.SparkOfDawnArmorItem::new),
    GOLDEN_EXECUTION(FAArmorSets.GoldenExecutionArmorItem::new),
    FORGOTTEN_TRACE(FAArmorSets.ForgottenTraceArmorItem::new),
    REDEEMER(FAArmorSets.RedeemerArmorItem::new),
    TWINNED(FAArmorSets.TwinnedArmorItem::new),
    GILDED_HUNT(FAArmorSets.GildedHuntArmorItem::new),
    LADY_MARIA(FAArmorSets.LadyMariaArmorItem::new),
    CRUCIBLE_KNIGHT(FAArmorSets.CrucibleKnightArmorItem::new),
    EVENING_GHOST(FAArmorSets.EveningGhostArmorItem::new),
    RONIN(FAArmorSets.RoninArmorItem::new),
    MALENIA(FAArmorSets.MaleniaArmorItem::new),
    OLD_KNIGHT(FAArmorSets.OldKnightArmorItem::new),
    SILVER_KNIGHT(FAArmorSets.SilverKnightArmorItem::new),
    DEAD_GLADIATOR(FAArmorSets.DeadGladiatorArmorItem::new),
    FLESH_OF_THE_FEASTER(FAArmorSets.FeasterArmorItem::new),
    WIND_WORSHIPPER(FAArmorSets.WindWorshipperArmorItem::new),
    GRAVE_SENTINEL(FAArmorSets.GraveSentinelArmorItem::new),
    ORNSTEIN(FAArmorSets.OrnsteinArmorItem::new);

    private final QuadFunction<FAArmorSet, ArmorType, Supplier<FAArmorAttributes>, Item.Properties, FAArmorItem> factory;
    private final String name;

    FAArmorSet(QuadFunction<FAArmorSet, ArmorType, Supplier<FAArmorAttributes>, Item.Properties, FAArmorItem> factory) {
        this.factory = factory;
        this.name = name().toLowerCase();
    }

    public String getName() {
        return name;
    }

    public FAArmorItem create(ArmorType type, Supplier<FAArmorAttributes> attributesSupplier, Item.Properties properties) {
        return factory.apply(this, type, attributesSupplier, properties);
    }

    public String getGeoPath() {
        return "geo/" + name + "_armor.geo.json";
    }

    public String getTexturePath() {
        return "textures/armor/" + name + "_armor.png";
    }

    public String getOverlayPath() {
        return "textures/armor/" + name + "_armor_overlay.png";
    }

    @FunctionalInterface
    public interface QuadFunction<A, B, C, D, R> {
        R apply(A a, B b, C c, D d);
    }
}
