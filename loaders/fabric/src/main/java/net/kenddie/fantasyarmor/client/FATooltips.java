package net.kenddie.fantasyarmor.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.StringSplitter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSink;
import net.minecraft.util.StringDecomposer;

import java.util.Optional;
import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public final class FATooltips {
    private FATooltips() {}

    public static void append(Component text, StringSplitter splitter, int width, Consumer<Component> output) {
        // Decode section codes before wrapping so colors and decorations survive every line break.
        StyledRuns runs = new StyledRuns();
        StringDecomposer.iterateFormatted(text, Style.EMPTY, runs);
        MutableComponent formatted = runs.finish();
        for (var line : splitter.splitLines(formatted, Math.max(1, width), Style.EMPTY)) {
            MutableComponent component = Component.empty();
            line.visit((style, value) -> {
                component.append(Component.literal(value).setStyle(style));
                return Optional.empty();
            }, Style.EMPTY);
            output.accept(component);
        }
    }

    private static final class StyledRuns implements FormattedCharSink {
        private final MutableComponent result = Component.empty();
        private final StringBuilder text = new StringBuilder();
        private Style style = Style.EMPTY;

        @Override
        public boolean accept(int index, Style nextStyle, int codePoint) {
            if (!style.equals(nextStyle)) {
                flush();
                style = nextStyle;
            }
            text.appendCodePoint(codePoint);
            return true;
        }

        private void flush() {
            if (!text.isEmpty()) {
                result.append(Component.literal(text.toString()).setStyle(style));
                text.setLength(0);
            }
        }

        private MutableComponent finish() {
            flush();
            return result;
        }
    }
}
