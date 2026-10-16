package fantasy.definitions

import fantasy.build.*

val armor = armorCatalog {
    armor("eclipse_soldier") {
        template = "minecraft:gold_nugget"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.1, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.2, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.2, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, durability = 500.0))
        effect("minecraft:strength", duration = 259, amplifier = 0)
        effect("minecraft:resistance", duration = 259, amplifier = 0)
    }

    armor("dragonslayer") {
        template = "minecraft:nether_wart"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.2, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.2, attackSpeed = 0.1, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.2, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, durability = 500.0))
        effect("minecraft:strength", duration = 259, amplifier = 0)
        effect("minecraft:fire_resistance", duration = 259, amplifier = 0)
    }

    armor("hero") {
        template = "minecraft:iron_ingot"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, attackDamage = 0.1, attackSpeed = 0.1, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.1, attackDamage = 0.1, attackSpeed = 0.1, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.1, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, durability = 500.0))
        effect("minecraft:regeneration", duration = 259, amplifier = 0)
    }

    armor("golden_horns") {
        template = "minecraft:flint"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.03, attackSpeed = 0.1, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.01, attackSpeed = 0.1, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.02, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.04, durability = 500.0))
        effect("minecraft:haste", duration = 259, amplifier = 1)
    }

    armor("thief") {
        template = "minecraft:leather"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, attackSpeed = 0.1, luck = 1.0, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, attackSpeed = 0.1, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.1, attackSpeed = 0.1, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.1, attackSpeed = 0.05, durability = 500.0))
        effect("minecraft:jump_boost", duration = 259, amplifier = 0)
    }

    armor("wandering_wizard") {
        template = "minecraft:spider_eye"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, luck = 2.0, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, durability = 500.0))
        effect("minecraft:water_breathing", duration = 259, amplifier = 0)
        effect("minecraft:night_vision", duration = 259, amplifier = 0)
        effect("minecraft:fire_resistance", duration = 259, amplifier = 0)
    }

    armor("chess_board_knight") {
        template = "minecraft:honeycomb"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.2, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.2, maxHealth = 2.0, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.2, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.2, maxHealth = 2.0, durability = 500.0))
        effect("minecraft:haste", duration = 259, amplifier = 0)
        effect("minecraft:regeneration", duration = 259, amplifier = 0)
        effect("minecraft:strength", duration = 259, amplifier = 0)
    }

    armor("dark_lord") {
        template = "minecraft:bone"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, attackSpeed = 0.02, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.2, attackSpeed = 0.03, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.2, attackSpeed = 0.03, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, attackSpeed = 0.02, durability = 500.0))
        effect("minecraft:strength", duration = 259, amplifier = 0)
        effect("minecraft:night_vision", duration = 259, amplifier = 0)
        effect("minecraft:fire_resistance", duration = 259, amplifier = 0)
    }

    armor("sunset_wings") {
        template = "minecraft:gunpowder"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, maxHealth = 1.0, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, maxHealth = 1.0, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, maxHealth = 1.0, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, maxHealth = 1.0, durability = 500.0))
        effect("minecraft:jump_boost", duration = 259, amplifier = 0)
    }

    armor("fog_guard") {
        template = "minecraft:phantom_membrane"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, maxHealth = 1.0, attackSpeed = 0.1, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.1, maxHealth = 1.0, attackSpeed = 0.01, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.1, maxHealth = 1.0, attackSpeed = 0.02, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, maxHealth = 1.0, attackSpeed = 0.03, durability = 500.0))
        effect("minecraft:fire_resistance", duration = 259, amplifier = 0)
        effect("minecraft:resistance", duration = 259, amplifier = 0)
    }

    armor("dark_cover") {
        template = "minecraft:coal"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, maxHealth = 2.0, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.2, attackSpeed = 0.1, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.2, maxHealth = 1.0, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, durability = 500.0))
        effect("minecraft:regeneration", duration = 259, amplifier = 0)
        effect("minecraft:strength", duration = 259, amplifier = 0)
    }

    armor("spark_of_dawn") {
        template = "minecraft:amethyst_shard"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, maxHealth = 1.0, attackSpeed = 0.1, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.1, attackSpeed = 0.05, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.2, movementSpeed = 0.05, maxHealth = 1.0, attackSpeed = 0.05, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, durability = 500.0))
        effect("minecraft:night_vision", duration = 259, amplifier = 0)
        effect("minecraft:fire_resistance", duration = 259, amplifier = 0)
    }

    armor("golden_execution") {
        template = "minecraft:ender_pearl"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.2, maxHealth = 2.0, attackSpeed = 0.05, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.2, maxHealth = 4.0, attackSpeed = 0.05, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.2, maxHealth = 1.0, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.2, maxHealth = 1.0, durability = 500.0))
        effect("minecraft:strength", duration = 259, amplifier = 0)
    }

    armor("forgotten_trace") {
        template = "minecraft:prismarine_crystals"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, luck = 1.0, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.03, maxHealth = 2.0, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.02, maxHealth = 1.0, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.2, movementSpeed = 0.05, maxHealth = 1.0, luck = 1.0, durability = 500.0))
        effect("minecraft:haste", duration = 259, amplifier = 0)
        effect("minecraft:regeneration", duration = 259, amplifier = 0)
    }

    armor("redeemer") {
        template = "minecraft:emerald"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, maxHealth = 2.0, attackSpeed = 0.05, luck = 1.0, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.02, maxHealth = 2.0, attackSpeed = 0.03, luck = 1.0, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.2, movementSpeed = 0.04, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.02, durability = 500.0))
        effect("minecraft:strength", duration = 259, amplifier = 0)
        effect("minecraft:fire_resistance", duration = 259, amplifier = 0)
    }

    armor("twinned") {
        template = "minecraft:quartz"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.2, movementSpeed = 0.02, attackSpeed = 0.1, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.02, attackSpeed = 0.02, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.2, movementSpeed = 0.02, attackSpeed = 0.05, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.02, attackSpeed = 0.05, durability = 500.0))
        effect("minecraft:regeneration", duration = 259, amplifier = 0)
        effect("minecraft:haste", duration = 259, amplifier = 1)
    }

    armor("gilded_hunt") {
        template = "minecraft:raw_gold"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, maxHealth = 1.0, attackSpeed = 0.1, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.1, maxHealth = 1.0, attackSpeed = 0.1, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.1, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, durability = 500.0))
        effect("minecraft:jump_boost", duration = 259, amplifier = 0)
        effect("minecraft:strength", duration = 259, amplifier = 0)
    }

    armor("lady_maria") {
        template = "minecraft:feather"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, attackSpeed = 0.05, luck = 1.0, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, attackSpeed = 0.05, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.03, durability = 500.0))
        effect("minecraft:strength", duration = 259, amplifier = 0)
        effect("minecraft:regeneration", duration = 259, amplifier = 0)
    }

    armor("crucible_knight") {
        template = "minecraft:blaze_powder"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.2, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.2, maxHealth = 2.0, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.2, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, durability = 500.0))
        effect("minecraft:fire_resistance", duration = 259, amplifier = 0)
        effect("minecraft:regeneration", duration = 259, amplifier = 0)
    }

    armor("evening_ghost") {
        template = "minecraft:dried_kelp"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, luck = 1.0, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.03, luck = 1.0, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.04, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, durability = 500.0))
        effect("minecraft:regeneration", duration = 259, amplifier = 0)
        effect("minecraft:night_vision", duration = 259, amplifier = 0)
    }

    armor("ronin") {
        template = "minecraft:sugar_cane"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, attackSpeed = 0.1, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.1, attackSpeed = 0.1, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.03, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, durability = 500.0))
        effect("minecraft:jump_boost", duration = 259, amplifier = 0)
    }

    armor("malenia") {
        template = "minecraft:ghast_tear"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.07, attackSpeed = 0.05, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.2, movementSpeed = 0.05, attackSpeed = 0.05, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.05, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, movementSpeed = 0.08, durability = 500.0))
        effect("minecraft:regeneration", duration = 259, amplifier = 0)
    }

    armor("old_knight") {
        template = "minecraft:obsidian"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.2, maxHealth = 1.0, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.2, maxHealth = 2.0, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.2, maxHealth = 1.0, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, durability = 500.0))
        effect("minecraft:strength", duration = 259, amplifier = 0)
        effect("minecraft:resistance", duration = 259, amplifier = 0)
    }

    armor("silver_knight") {
        template = "minecraft:snowball"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, attackSpeed = 0.03, luck = 1.0, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 3.0, knockbackResistance = 0.2, attackSpeed = 0.03, luck = 1.0, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 3.0, knockbackResistance = 0.1, attackSpeed = 0.02, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 3.0, knockbackResistance = 0.1, luck = 1.0, durability = 500.0))
        effect("minecraft:luck", duration = 259, amplifier = 0)
        effect("minecraft:strength", duration = 259, amplifier = 0)
    }

    armor("dead_gladiator") {
        template = "minecraft:red_sand"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 2.0, knockbackResistance = 0.1, attackDamage = 0.05, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 2.0, knockbackResistance = 0.2, attackDamage = 0.1, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 2.0, knockbackResistance = 0.1, attackDamage = 0.05, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 2.0, knockbackResistance = 0.1, durability = 500.0))
        effect("minecraft:regeneration", duration = 259, amplifier = 0)
    }

    armor("flesh_of_the_feaster") {
        template = "minecraft:slime_ball"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 1.0, knockbackResistance = 0.1, maxHealth = 1.0, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 1.0, knockbackResistance = 0.2, maxHealth = 2.0, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 1.0, knockbackResistance = 0.1, maxHealth = 1.0, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 1.0, knockbackResistance = 0.1, durability = 500.0))
        effect("minecraft:resistance", duration = 259, amplifier = 0)
    }

    armor("wind_worshipper") {
        template = "minecraft:paper"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 2.0, knockbackResistance = 0.1, attackSpeed = 0.03, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 2.0, knockbackResistance = 0.2, attackSpeed = 0.03, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 2.0, knockbackResistance = 0.1, attackSpeed = 0.02, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 2.0, knockbackResistance = 0.1, movementSpeed = 0.03, durability = 500.0))
        effect("minecraft:jump_boost", duration = 259, amplifier = 0)
    }

    armor("grave_sentinel") {
        template = "minecraft:lily_of_the_valley"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 2.0, knockbackResistance = 0.1, attackSpeed = 0.03, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 2.0, knockbackResistance = 0.2, attackSpeed = 0.03, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 2.0, knockbackResistance = 0.1, attackSpeed = 0.02, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 2.0, knockbackResistance = 0.2, movementSpeed = 0.03, durability = 500.0))
        effect("minecraft:strength", duration = 259, amplifier = 0)
        effect("minecraft:fire_resistance", duration = 259, amplifier = 0)
    }

    armor("ornstein") {
        template = "minecraft:gold_ingot"
        piece(Piece.HELMET, Attributes(armor = 3.0, armorToughness = 2.0, knockbackResistance = 0.1, maxHealth = 2.0, attackDamage = 0.05, durability = 440.0))
        piece(Piece.CHESTPLATE, Attributes(armor = 8.0, armorToughness = 2.0, knockbackResistance = 0.2, maxHealth = 2.0, attackDamage = 0.05, durability = 640.0))
        piece(Piece.LEGGINGS, Attributes(armor = 6.0, armorToughness = 2.0, knockbackResistance = 0.1, movementSpeed = 0.03, maxHealth = 2.0, durability = 580.0))
        piece(Piece.BOOTS, Attributes(armor = 3.0, armorToughness = 2.0, knockbackResistance = 0.2, movementSpeed = 0.03, durability = 500.0))
        effect("minecraft:regeneration", duration = 259, amplifier = 0)
        effect("minecraft:resistance", duration = 259, amplifier = 0)
    }

}
