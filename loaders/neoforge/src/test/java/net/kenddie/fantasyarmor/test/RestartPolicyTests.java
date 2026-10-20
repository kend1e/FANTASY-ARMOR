package net.kenddie.fantasyarmor.test;

import net.minecraft.gametest.framework.GameTestHelper;

public final class RestartPolicyTests {
    public static void run(GameTestHelper helper) {
        ConfigAccess.verifyRestartPolicy(helper);
        helper.succeed();
    }
    private RestartPolicyTests() {}
}
