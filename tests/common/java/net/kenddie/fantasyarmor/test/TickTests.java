package net.kenddie.fantasyarmor.test;

import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

import static net.kenddie.fantasyarmor.test.TestSupport.*;

public final class TickTests {
    public static void run(GameTestHelper helper) {
        var player = TickAccess.createPlayer(helper);
        try {
            equip(player, "eclipse_soldier");
            helper.onEachTick(() -> TickAccess.tick(player));
            helper.runAfterDelay(5, () -> {
                try {
                    check(helper, player.hasEffect(MinecraftAccess.STRENGTH) == ConfigAccess.effects(),
                            "Registered tick listener must respect configured effects");
                    check(helper, player.hasEffect(MinecraftAccess.RESISTANCE) == ConfigAccess.effects(),
                            "Registered tick listener must grant both set effects");
                    player.setItemSlot(EquipmentSlot.FEET, ItemStack.EMPTY);
                    player.removeAllEffects();
                    helper.runAfterDelay(5, () -> {
                        try {
                            check(helper, player.getActiveEffects().isEmpty(), "Tick listener must reject incomplete set");
                            helper.succeed();
                        } finally { TickAccess.close(player); }
                    });
                } catch (Throwable failure) {
                    TickAccess.close(player);
                    throw failure;
                }
            });
        } catch (Throwable failure) {
            TickAccess.close(player);
            throw failure;
        }
    }
    private TickTests() {}
}
