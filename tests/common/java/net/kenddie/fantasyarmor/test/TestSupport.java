package net.kenddie.fantasyarmor.test;

import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

final class TestSupport {
    record Piece(String getName, EquipmentSlot getSlot) {}
    static final List<Piece> PIECES = List.of(
            new Piece("helmet", EquipmentSlot.HEAD), new Piece("chestplate", EquipmentSlot.CHEST),
            new Piece("leggings", EquipmentSlot.LEGS), new Piece("boots", EquipmentSlot.FEET));

    static void equip(Player player, String set) {
        for (var piece : PIECES) player.setItemSlot(piece.getSlot(), stack(set + "_" + piece.getName()));
    }

    static Player player(GameTestHelper helper) { return MinecraftAccess.player(helper); }

    static ItemStack stack(String name) { return item(name).getDefaultInstance(); }
    static Item item(String name) {
        var item = MinecraftAccess.item("fantasy_armor:" + name);
        if (item == Items.AIR) throw new AssertionError("Missing item " + name);
        return item;
    }
    static void check(GameTestHelper helper, boolean condition, String message) {
        MinecraftAccess.check(helper, condition, message);
    }
    static void close(GameTestHelper helper, double actual, double expected, String message) {
        check(helper, Math.abs(actual - expected) < 1.0e-6, message + ": expected " + expected + ", got " + actual);
    }
    private TestSupport() {}
}
