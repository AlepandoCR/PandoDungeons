package pandodungeons.pandodungeons;

import org.bukkit.plugin.java.JavaPlugin;
import pandodungeons.pandodungeons.commands.CommandManager;
import pandodungeons.pandodungeons.utils.FileUtils;

import java.io.File;

public final class PandoDungeons extends JavaPlugin {
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        // Create data folder if it doesn't exist
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }


        // Plugin startup logic
        this.getCommand("dungeons").setExecutor(new CommandManager(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
