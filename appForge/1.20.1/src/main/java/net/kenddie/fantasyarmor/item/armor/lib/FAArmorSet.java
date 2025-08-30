package net.kenddie.fantasyarmor.item.armor.lib;

import net.kenddie.fantasyarmor.item.armor.sets.ChessBoardKnightArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.CrucibleKnightArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.DarkCoverArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.DarkLordArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.DeadGladiatorArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.DragonslayerArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.EclipseSoldierArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.EveningGhostArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.FeasterArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.FogGuardArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.ForgottenTraceArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.GildedHuntArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.GoldenExecutionArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.GoldenHornsArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.GraveSentinelArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.HeroArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.LadyMariaArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.MaleniaArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.OldKnightArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.RedeemerArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.RoninArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.SilverKnightArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.SparkOfDawnArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.SunsetWingsArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.ThiefArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.TwinnedArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.WanderingWizardArmorItem;
import net.kenddie.fantasyarmor.item.armor.sets.WindWorshipperArmorItem;
import net.minecraft.world.item.ArmorItem;

import java.util.function.Supplier;

public enum FAArmorSet {
    ECLIPSE_SOLDIER(EclipseSoldierArmorItem::new),
    DRAGONSLAYER(DragonslayerArmorItem::new),
    HERO(HeroArmorItem::new),
    GOLDEN_HORNS(GoldenHornsArmorItem::new),
    THIEF(ThiefArmorItem::new),
    WANDERING_WIZARD(WanderingWizardArmorItem::new),
    CHESS_BOARD_KNIGHT(ChessBoardKnightArmorItem::new),
    DARK_LORD(DarkLordArmorItem::new),
    SUNSET_WINGS(SunsetWingsArmorItem::new),
    FOG_GUARD(FogGuardArmorItem::new),
    DARK_COVER(DarkCoverArmorItem::new),
    SPARK_OF_DAWN(SparkOfDawnArmorItem::new),
    GOLDEN_EXECUTION(GoldenExecutionArmorItem::new),
    FORGOTTEN_TRACE(ForgottenTraceArmorItem::new),
    REDEEMER(RedeemerArmorItem::new),
    TWINNED(TwinnedArmorItem::new),
    GILDED_HUNT(GildedHuntArmorItem::new),
    LADY_MARIA(LadyMariaArmorItem::new),
    CRUCIBLE_KNIGHT(CrucibleKnightArmorItem::new),
    EVENING_GHOST(EveningGhostArmorItem::new),
    RONIN(RoninArmorItem::new),
    MALENIA(MaleniaArmorItem::new),
    OLD_KNIGHT(OldKnightArmorItem::new),
    SILVER_KNIGHT(SilverKnightArmorItem::new),
    DEAD_GLADIATOR(DeadGladiatorArmorItem::new),
    FLESH_OF_THE_FEASTER(FeasterArmorItem::new),
    WIND_WORSHIPPER(WindWorshipperArmorItem::new),
    GRAVE_SENTINEL(GraveSentinelArmorItem::new);

    private final TriFunction<FAArmorSet, ArmorItem.Type, Supplier<FAArmorAttributes>, FAArmorItem> factory;
    private final String name;

    FAArmorSet(TriFunction<FAArmorSet, ArmorItem.Type, Supplier<FAArmorAttributes>, FAArmorItem> factory) {
        this.factory = factory;
        this.name = name().toLowerCase();
    }

    FAArmorSet(TriFunction<FAArmorSet, ArmorItem.Type, Supplier<FAArmorAttributes>, FAArmorItem> factory, String name) {
        this.factory = factory;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public FAArmorItem create(ArmorItem.Type type, Supplier<FAArmorAttributes> attributesSupplier) {
        return factory.apply(this, type, attributesSupplier);
    }

    public String getGeoPath() {
        return "geo/" + name + "_armor.geo.json";
    }

    public String getTexturePath() {
        return "textures/armor/" + name + "_armor.png";
    }

    @FunctionalInterface
    public interface TriFunction<A, B, C, R> {
        R apply(A a, B b, C c);
    }
}
