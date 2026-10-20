package net.kenddie.fantasyarmor.test;

import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.player.Player;

final class TickAccess {
    static Player createPlayer(GameTestHelper helper) { return TestSupport.player(helper); }
    static void tick(Player player) { player.tick(); }
    static void close(Player player) { player.discard(); }
    private TickAccess() {}
}
