package net.kenddie.fantasyarmor.test;

import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

import static net.kenddie.fantasyarmor.test.TestSupport.*;

public final class TickTests {
    public static void run(GameTestHelper helper) {
        var fixture = new PlayerFixture(helper);
        var player = fixture.player();
        fixture.run(() -> {
            equip(player, "eclipse_soldier");
            helper.onEachTick(fixture::tick);
            helper.runAfterDelay(5, fixture.guard(() -> {
                check(helper, player.hasEffect(MinecraftAccess.STRENGTH) == ConfigAccess.effects(),
                        "Registered tick listener must respect configured effects");
                check(helper, player.hasEffect(MinecraftAccess.RESISTANCE) == ConfigAccess.effects(),
                        "Registered tick listener must grant both set effects");
                player.setItemSlot(EquipmentSlot.FEET, ItemStack.EMPTY);
                player.removeAllEffects();
                helper.runAfterDelay(5, fixture.guard(() -> {
                    check(helper, player.getActiveEffects().isEmpty(), "Tick listener must reject incomplete set");
                    fixture.close();
                    helper.succeed();
                }));
            }));
        });
    }
    private TickTests() {}
}
