package net.kenddie.fantasyarmor.test;

import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import static net.kenddie.fantasyarmor.test.TestSupport.*;

public final class AttributeTests {
    public static void run(GameTestHelper h) {
        ConfigAccess.withSettings(() -> verifyAttributes(h));
        h.succeed();
    }

    private static void verifyAttributes(GameTestHelper h) {
        var player = player(h);
        double initialArmor = player.getAttributeValue(Attributes.ARMOR);
        double initialSpeed = player.getAttributeValue(Attributes.MOVEMENT_SPEED);
        boolean modifiers = ConfigAccess.modifiers();
        try {
            ConfigAccess.modifiers(true);
            ItemStack helmet = stack("eclipse_soldier_helmet");
            MinecraftAccess.applySettings(helmet);
            player.setItemSlot(EquipmentSlot.HEAD, helmet);
            player.tick();
            close(h, player.getAttributeValue(Attributes.ARMOR), initialArmor + ConfigAccess.helmetArmor(), "Equipped armor");
            close(h, player.getAttributeValue(Attributes.MOVEMENT_SPEED), initialSpeed * 1.1, "Helmet speed bonus");
            player.setItemSlot(EquipmentSlot.HEAD, helmet.copy());
            player.tick();
            close(h, player.getAttributeValue(Attributes.MOVEMENT_SPEED), initialSpeed * 1.1, "Re-equipping must not stack bonuses");
            player.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
            player.tick();
            close(h, player.getAttributeValue(Attributes.ARMOR), initialArmor, "Removing armor");
            close(h, player.getAttributeValue(Attributes.MOVEMENT_SPEED), initialSpeed, "Removing speed bonus");
        } finally {
            ConfigAccess.modifiers(modifiers);
        }
    }

    private AttributeTests() {}
}
