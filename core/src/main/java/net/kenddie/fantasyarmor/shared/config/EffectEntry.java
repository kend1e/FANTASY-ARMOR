package net.kenddie.fantasyarmor.shared.config;

import java.util.Optional;
import java.util.regex.Pattern;

public record EffectEntry(String id, int duration, int amplifier) {
    private static final Pattern ID = Pattern.compile("[a-z0-9_.-]+:[a-z0-9/._-]+");

    public EffectEntry {
        if (id == null || !ID.matcher(id).matches()) {
            throw new IllegalArgumentException("Invalid effect identifier: " + id);
        }
        if (duration != -1 && duration <= 0) {
            throw new IllegalArgumentException("Effect duration must be positive or -1 (infinite)");
        }
        if (amplifier < 0 || amplifier > 255) {
            throw new IllegalArgumentException("Effect amplifier must be between 0 and 255");
        }
    }

    /** Config syntax is independent of Minecraft's version-specific registry API. */
    public static Optional<EffectEntry> parse(Object value) {
        if (!(value instanceof String text)) return Optional.empty();
        var parts = text.split(",", -1);
        if (parts.length != 3) return Optional.empty();
        var id = parts[0].trim();
        if (!id.contains(":")) id = "minecraft:" + id;
        try {
            return Optional.of(new EffectEntry(id, Integer.parseInt(parts[1].trim()),
                    Integer.parseInt(parts[2].trim())));
        } catch (IllegalArgumentException exception) {
            return Optional.empty();
        }
    }

    public static boolean isValidConfigValue(Object value) {
        return parse(value).isPresent();
    }

    public String toConfigValue() {
        return id + "," + duration + "," + amplifier;
    }
}
