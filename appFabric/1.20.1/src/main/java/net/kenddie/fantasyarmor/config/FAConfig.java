package net.kenddie.fantasyarmor.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.kenddie.fantasyarmor.FantasyArmor;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FAConfig {

    // TODO: Change to Paths.get("config", "config.json"); so we don't have mod_id in path twice for no reason.
    private static final Path CONFIG_FILE_PATH = Paths.get("config", FantasyArmor.MOD_ID, FantasyArmor.MOD_ID + ".json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static ConfigValues values;

    static {
        loadDefaults();
    }

    private FAConfig() {
    }

    public static void save() {
        File configFile = CONFIG_FILE_PATH.toFile();
        File parentFile = configFile.getParentFile();

        if(parentFile == null) {
            return;
        }

        if(!parentFile.mkdirs() && !parentFile.isDirectory()) {
            FantasyArmor.LOGGER.warn("Failed to save config: Directory could not be created.");
            return;
        }

        try (FileWriter writer = new FileWriter(configFile)) {
            GSON.toJson(values, writer);
        } catch (IOException e) {
            FantasyArmor.LOGGER.warn("Failed to save config: ", e);
        }
    }

    public static void load() {
        try (FileReader reader = new FileReader(CONFIG_FILE_PATH.toFile())) {
            values = GSON.fromJson(reader, ConfigValues.class);
        } catch (IOException e) {
            FantasyArmor.LOGGER.warn("Failed to read config (default parameters will be used): ", e);
            loadDefaults();
        }
    }

    public static void loadDefaults() {
        values = new ConfigValues(
                true,
                true,
                true,
                false,
                250
        );
    }

    public static boolean exists() {
        return CONFIG_FILE_PATH.toFile().exists();
    }

    public static ConfigValues getValues() {
        return values;
    }
}