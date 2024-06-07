package pandodungeons.pandodungeons.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LocationUtils {
    private static final File playerLocationsFile = FileUtils.getPlayerLocationsFile();

    public static JSONObject getPlayerLocationData(String playerUUID) {
        JSONObject playerLocations = loadPlayerLocations();
        return (JSONObject) playerLocations.get(playerUUID);
    }

    public static void savePlayerLocationData(String playerUUID, Location location) {
        JSONObject playerLocations = loadPlayerLocations();
        JSONObject data = new JSONObject();
        data.put("world", location.getWorld().getName());
        data.put("x", location.getX());
        data.put("y", location.getY());
        data.put("z", location.getZ());
        playerLocations.put(playerUUID, data);
        saveLocationDataToFile(playerLocations);
    }

    public static void removePlayerLocationData(String playerUUID) {
        JSONObject playerLocations = loadPlayerLocations();
        playerLocations.remove(playerUUID);
        saveLocationDataToFile(playerLocations);
    }

    public static Location getLocationFromJSON(JSONObject jsonLocation) {
        String worldName = (String) jsonLocation.get("world");
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            return null;
        }
        double x = (double) jsonLocation.get("x");
        double y = (double) jsonLocation.get("y");
        double z = (double) jsonLocation.get("z");
        return new Location(world, x, y, z);
    }

    private static JSONObject loadPlayerLocations() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(playerLocationsFile)) {
            return (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            return new JSONObject();
        }
    }

    private static void saveLocationDataToFile(JSONObject data) {
        try (FileWriter writer = new FileWriter(playerLocationsFile)) {
            writer.write(data.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
