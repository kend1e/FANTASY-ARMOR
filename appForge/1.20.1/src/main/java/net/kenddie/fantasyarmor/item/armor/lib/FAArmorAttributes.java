package net.kenddie.fantasyarmor.item.armor.lib;

public record FAArmorAttributes(
        double armor,
        double armorToughness,
        double knockbackResistance,
        double movementSpeed,
        double maxHealth,
        double attackDamage,
        double attackSpeed,
        double luck
)

{
    public static class Builder {
        private double armor = 0;
        private double armorToughness = 0;
        private double knockbackResistance = 0;
        private double movementSpeed = 0;
        private double maxHealth = 0;
        private double attackDamage = 0;
        private double attackSpeed = 0;
        private double luck = 0;

        public Builder armor(double armor) {
            this.armor = armor;
            return this;
        }

        public Builder armorToughness(double armorToughness) {
            this.armorToughness = armorToughness;
            return this;
        }

        public Builder knockbackResistance(double knockbackResistance) {
            this.knockbackResistance = knockbackResistance;
            return this;
        }

        public Builder movementSpeed(double movementSpeed) {
            this.movementSpeed = movementSpeed;
            return this;
        }

        public Builder maxHealth(double maxHealth) {
            this.maxHealth = maxHealth;
            return this;
        }

        public Builder attackDamage(double attackDamage) {
            this.attackDamage = attackDamage;
            return this;
        }

        public Builder attackSpeed(double attackSpeed) {
            this.attackSpeed = attackSpeed;
            return this;
        }

        public Builder luck(double luck) {
            this.luck = luck;
            return this;
        }

        public FAArmorAttributes build() {
            return new FAArmorAttributes(
                    armor,
                    armorToughness,
                    knockbackResistance,
                    movementSpeed,
                    maxHealth,
                    attackDamage,
                    attackSpeed,
                    luck
            );
        }
    }
}