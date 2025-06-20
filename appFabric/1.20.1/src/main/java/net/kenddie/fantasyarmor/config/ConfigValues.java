package net.kenddie.fantasyarmor.config;

import com.google.gson.annotations.SerializedName;

public record ConfigValues(
        boolean applyArmorEffects,

        // For backwards compatibility
        @SerializedName("applyModificators")
        boolean applyModifiers,

        boolean showDescriptions,

        boolean showParticles,

        int descrtiptionsLength

//        boolean useRecipes
) {
}
