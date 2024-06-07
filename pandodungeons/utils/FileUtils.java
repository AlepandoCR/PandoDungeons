package pandodungeons.pandodungeons.utils;

import org.bukkit.plugin.java.JavaPlugin;
import pandodungeons.pandodungeons.PandoDungeons;

import java.io.File;

public class FileUtils {
    private static final String PLAYER_LOCATIONS_FILE_NAME = "player_locations.json";
    private static final String DUNGEONS_DATA_FOLDER_NAME = "dungeons_data";
    private static final String DUNGEONS_FILE_NAME = "dungeons.json";

    public static File getPlayerLocationsFile() {
        JavaPlugin plugin = PandoDungeons.getPlugin(PandoDungeons.class);
        File dataFolder = plugin.getDataFolder();
        return new File(dataFolder, PLAYER_LOCATIONS_FILE_NAME);
    }

    public static File getDungeonsDataFile() {
        JavaPlugin plugin = PandoDungeons.getPlugin(PandoDungeons.class);
        File dataFolder = plugin.getDataFolder();
        return new File(dataFolder, DUNGEONS_DATA_FOLDER_NAME + File.separator + DUNGEONS_FILE_NAME);
    }
}
