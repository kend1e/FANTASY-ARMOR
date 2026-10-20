package net.kenddie.fantasyarmor.test;

import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.item.ItemStack;
import java.util.List;

import static net.kenddie.fantasyarmor.test.TestSupport.*;

public final class ShapelessRecipeTests {
    public static void run(GameTestHelper h) {
        for (var recipe : RecipeTests.declarations()) {
            for (var piece : PIECES) {
                var a = new ItemStack(MinecraftAccess.item(recipe.template()));
                var b = new ItemStack(MinecraftAccess.item("minecraft:netherite_" + piece.getName()));
                var c = new ItemStack(MinecraftAccess.item(recipe.addition()));
                var expected = item(recipe.id() + "_" + piece.getName());
                for (var order : List.of(List.of(a, b, c), List.of(a, c, b), List.of(b, a, c),
                        List.of(b, c, a), List.of(c, a, b), List.of(c, b, a))) {
                    var small = RecipeAccess.craft(h, 2, 2, List.of(order.get(0), ItemStack.EMPTY, order.get(1), order.get(2))).orElseThrow();
                    var large = RecipeAccess.craft(h, 3, 3, List.of(ItemStack.EMPTY, order.get(0), ItemStack.EMPTY,
                            order.get(1), ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, order.get(2))).orElseThrow();
                    check(h, small.is(expected) && large.is(expected), "Armor crafting must be shapeless");
                }
            }
        }
        h.succeed();
    }
    private ShapelessRecipeTests() {}
}
