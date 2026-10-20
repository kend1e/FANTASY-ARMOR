package net.kenddie.fantasyarmor.test;

import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

final class TickAccess {
    @SuppressWarnings("removal")
    static Player createPlayer(GameTestHelper helper) { return helper.makeMockServerPlayerInLevel(); }
    // No client connection drives doTick in the test server.
    static void tick(Player player) { ((ServerPlayer) player).doTick(); }
    static void close(Player player) {
        var serverPlayer = (ServerPlayer) player;
        serverPlayer.level().getServer().getPlayerList().remove(serverPlayer);
        serverPlayer.discard();
    }
    private TickAccess() {}
}
