package net.kenddie.fantasyarmor.test;

import net.minecraft.gametest.framework.GameTestHelper;

import static net.kenddie.fantasyarmor.test.TestSupport.*;

public final class TickCleanupTests {
    public static void run(GameTestHelper helper) {
        var fixture = new PlayerFixture(helper);
        var failure = new IllegalStateException("Expected tick failure");
        var failingTick = fixture.guard(() -> { throw failure; });
        fixture.run(() -> {
            helper.onEachTick(() -> {
                try {
                    failingTick.run();
                } catch (IllegalStateException actual) {
                    check(helper, actual == failure, "Cleanup must preserve the original failure");
                }
            });
            helper.runAfterDelay(2, () -> {
                try {
                    check(helper, fixture.isClosed(), "Tick failure must close the fixture");
                    var player = fixture.player();
                    check(helper, player.isRemoved(), "Tick failure must discard the player");
                    check(helper, !helper.getLevel().getServer().getPlayerList().getPlayers().contains(player),
                            "Tick failure must remove the player from the server");
                    fixture.close();
                    helper.succeed();
                } finally {
                    fixture.close();
                }
            });
        });
    }
    private TickCleanupTests() {}
}
