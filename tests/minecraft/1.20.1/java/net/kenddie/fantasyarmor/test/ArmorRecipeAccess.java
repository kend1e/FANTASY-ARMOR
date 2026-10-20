package net.kenddie.fantasyarmor.test;

import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import java.util.Optional;

final class ArmorRecipeAccess {
    static final boolean PRESERVES_DATA = true;
    static Optional<ItemStack> upgrade(GameTestHelper h, ItemStack template, ItemStack base, ItemStack addition) {
        var input = new SimpleContainer(template, base, addition);
        return h.getLevel().getRecipeManager().getRecipeFor(RecipeType.SMITHING, input, h.getLevel())
                .map(recipe -> recipe.assemble(input, h.getLevel().registryAccess()));
    }

    private ArmorRecipeAccess() {}
}
