package net.kenddie.fantasyarmor.item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public class ItemFantasyArmor extends ArmorItem {
    public ItemFantasyArmor(Type type) {
        super(ArmorMaterials.NETHERITE.getHolder().orElseThrow(), type, new Properties());
    }

    @Override
    public ResourceLocation getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlot equipmentSlot, ArmorMaterial.Layer layer, boolean innerModel) {
        return new ResourceLocation("fantasy_armor:textures/models/armor/" + ??? + ".png");
    }
}
