package net.kenddie.fantasyarmor.test;

import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.player.Player;

final class PlayerFixture implements AutoCloseable {
    private final Player player;
    private boolean closed;

    PlayerFixture(GameTestHelper helper) { player = TickAccess.createPlayer(helper); }

    Player player() { return player; }
    boolean isClosed() { return closed; }
    Runnable guard(Runnable action) { return () -> run(action); }
    void tick() { run(() -> TickAccess.tick(player)); }

    void run(Runnable action) {
        if (closed) return;
        try {
            action.run();
        } catch (RuntimeException | Error failure) {
            try {
                close();
            } catch (RuntimeException | Error cleanupFailure) {
                failure.addSuppressed(cleanupFailure);
            }
            throw failure;
        }
    }

    @Override
    public void close() {
        if (closed) return;
        closed = true;
        TickAccess.close(player);
    }
}
