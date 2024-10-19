package net.kenddie.fantasyarmor.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.kenddie.fantasyarmor.FantasyArmor;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FAConfig {

    private static final String CONFIG_FILE_PATH = "config/" + FantasyArmor.MOD_ID + "/" + FantasyArmor.MOD_ID + ".json";
    private static FAConfig instance;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public boolean useRecipes = true;
    public boolean applyArmorEffects = true;
    public boolean applyModificators = true;
    public boolean showDescriptions = true;

    public static FAConfig loadConfig() {
        File configFile = new File(CONFIG_FILE_PATH);

        if (configFile.exists()) {
            try (FileReader reader = new FileReader(configFile)) {
                instance = GSON.fromJson(reader, FAConfig.class);
            } catch (IOException e) {
                instance = new FAConfig();
            }
        } else {
            instance = new FAConfig();
            saveConfig();
        }
        return instance;
    }

    public static void saveConfig() {
        File configFile = new File(CONFIG_FILE_PATH);
        configFile.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(configFile)) {
            GSON.toJson(instance, writer);
        } catch (IOException e) {
            System.out.println("FA: can't save the config");
        }
    }

    public static FAConfig getInstance() {
        if (instance == null) {
            return loadConfig();
        }
        return instance;
    }
}