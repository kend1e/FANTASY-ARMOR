package net.kenddie.fantasyarmor.test;

import net.minecraft.core.component.DataComponents;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.Objects;

import static net.kenddie.fantasyarmor.test.TestSupport.*;

public final class StackUpdateTests {
    public static void run(GameTestHelper helper) {
        var fixture = new PlayerFixture(helper);
        fixture.run(() -> {
            var expected = stack("eclipse_soldier_helmet");
            var stale = new ItemStack(expected.getItem());
            stale.set(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
            stale.set(DataComponents.MAX_DAMAGE, expected.getMaxDamage() + 100);
            stale.set(DataComponents.DAMAGE, 13);
            stale.remove(DataComponents.UNBREAKABLE);
            var loaded = MinecraftAccess.roundTrip(helper, stale);
            check(helper, loaded.getMaxDamage() != expected.getMaxDamage(), "Fixture must contain outdated durability");
            check(helper, !Objects.equals(loaded.get(DataComponents.ATTRIBUTE_MODIFIERS), expected.get(DataComponents.ATTRIBUTE_MODIFIERS)),
                    "Fixture must contain outdated attributes");
            fixture.player().setItemSlot(EquipmentSlot.HEAD, loaded);
            helper.onEachTick(fixture::tick);
            helper.runAfterDelay(5, fixture.guard(() -> {
                var equipped = fixture.player().getItemBySlot(EquipmentSlot.HEAD);
                check(helper, Objects.equals(equipped.get(DataComponents.ATTRIBUTE_MODIFIERS), expected.get(DataComponents.ATTRIBUTE_MODIFIERS)),
                        "Equipped armor must refresh outdated attributes");
                check(helper, equipped.getMaxDamage() == expected.getMaxDamage(), "Equipped armor must refresh outdated durability");
                check(helper, Objects.equals(equipped.get(DataComponents.UNBREAKABLE), expected.get(DataComponents.UNBREAKABLE)),
                        "Equipped armor must apply the configured durability toggle");
                check(helper, equipped.getOrDefault(DataComponents.DAMAGE, 0) == 13, "Refreshing armor must preserve damage");
                close(helper, fixture.player().getAttributeValue(Attributes.ARMOR), ConfigAccess.helmetArmor(),
                        "Refreshed protection must apply to the player");
                fixture.close();
                helper.succeed();
            }));
        });
    }
    private StackUpdateTests() {}
}
