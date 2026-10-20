package net.kenddie.fantasyarmor.test;

import net.minecraft.resources.ResourceLocation;

final class MinecraftAccess extends ComponentAccess {
    static ResourceLocation id(String value) { return ResourceLocation.parse(value); }
    private MinecraftAccess() {}
}
