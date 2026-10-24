package net.kenddie.fantasyarmor.test;

import net.kenddie.fantasyarmor.item.armor.ArmorSetEffects;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;

import static net.kenddie.fantasyarmor.test.TestSupport.*;

public final class ConfigTests {
    public static void run(GameTestHelper h) {
        check(h, ConfigAccess.loaded(), "Server configs must be loaded before tests");
        ConfigAccess.withSettings(() -> verifySettings(h));
        h.succeed();
    }

    private static void verifySettings(GameTestHelper h) {
        boolean modifiers = ConfigAccess.modifiers();
        boolean durability = ConfigAccess.durability();
        boolean effects = ConfigAccess.effects();
        double armor = ConfigAccess.helmetArmor();
        try {
            ConfigAccess.helmetArmor(11.0);
            ConfigAccess.modifiers(false);
            ConfigAccess.durability(true);
            ConfigAccess.effects(false);
            var stack = stack("eclipse_soldier_helmet");
            MinecraftAccess.applySettings(stack);
            check(h, stack.isDamageableItem(), "Enabled durability must make armor damageable");
            stack.setDamageValue(13);
            var player = player(h);
            double baseSpeed = player.getAttributeValue(Attributes.MOVEMENT_SPEED);
            player.setItemSlot(EquipmentSlot.HEAD, stack);
            player.tick();
            close(h, player.getAttributeValue(Attributes.ARMOR), 11.0, "Configured protection");
            close(h, player.getAttributeValue(Attributes.MOVEMENT_SPEED), baseSpeed, "Disabled bonus modifiers");
            equip(player, "eclipse_soldier");
            ArmorSetEffects.tick(player, ConfigAccess.effects(), 241, false, true);
            check(h, player.getActiveEffects().isEmpty(), "Configured effect toggle");
            ConfigAccess.durability(false);
            MinecraftAccess.applySettings(stack);
            check(h, !stack.isDamageableItem() && MinecraftAccess.unbreakable(stack), "Disabled durability must make armor unbreakable");
            check(h, stack.getDamageValue() == 13, "Applying settings must preserve accumulated damage");
            ConfigAccess.durability(true);
            MinecraftAccess.applySettings(stack);
            check(h, stack.isDamageableItem() && stack.getDamageValue() == 13,
                    "Re-enabling durability must retain previous damage");
        } finally {
            ConfigAccess.helmetArmor(armor);
            ConfigAccess.modifiers(modifiers);
            ConfigAccess.durability(durability);
            ConfigAccess.effects(effects);
        }
    }

    private ConfigTests() {}
}
