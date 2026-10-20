package net.kenddie.fantasyarmor.test;

import net.kenddie.fantasyarmor.item.armor.ArmorSetEffects;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

import static net.kenddie.fantasyarmor.test.TestSupport.*;

public final class SetTests {
    public static void run(GameTestHelper h) {
        var player = player(h);
        equip(player, "eclipse_soldier");
        ArmorSetEffects.tick(player, true, 241, false, true);
        check(h, player.hasEffect(MinecraftAccess.STRENGTH) && player.hasEffect(MinecraftAccess.RESISTANCE), "Full set effects missing");
        player.removeAllEffects();
        player.setItemSlot(EquipmentSlot.FEET, ItemStack.EMPTY);
        ArmorSetEffects.tick(player, true, 241, false, true);
        check(h, player.getActiveEffects().isEmpty(), "Incomplete set must not grant effects");
        player.setItemSlot(EquipmentSlot.FEET, stack("hero_boots"));
        ArmorSetEffects.tick(player, true, 241, false, true);
        check(h, player.getActiveEffects().isEmpty(), "Mixed set must not grant effects");
        equip(player, "eclipse_soldier");
        ArmorSetEffects.tick(player, false, 241, false, true);
        check(h, player.getActiveEffects().isEmpty(), "Disabled effects must not be granted");
        h.succeed();
    }

    private SetTests() {}
}
