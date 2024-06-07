package pandodungeons.pandodungeons.commands;
import org.bukkit.Location;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pandodungeons.pandodungeons.PandoDungeons;
import pandodungeons.pandodungeons.utils.LocationUtils;

public class DungeonsLeaveCommand implements CommandExecutor {
    private final PandoDungeons plugin;

    public DungeonsLeaveCommand(PandoDungeons plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando solo puede ser ejecutado por un jugador.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0 || !args[0].equalsIgnoreCase("leave")) {
            player.sendMessage("Uso: /dungeons leave");
            return true;
        }

        String playerUUID = player.getUniqueId().toString();
        if (LocationUtils.getPlayerLocationData(playerUUID) == null) {
            player.sendMessage("No estás en ninguna dungeon.");
            return true;
        }

        Location originalLocation = LocationUtils.getLocationFromJSON(LocationUtils.getPlayerLocationData(playerUUID));
        if (originalLocation != null) {
            player.teleport(originalLocation);
            player.sendMessage("Has salido de la dungeon y has sido teletransportado de regreso.");
            LocationUtils.removePlayerLocationData(playerUUID);
        } else {
            player.sendMessage("Hubo un error al intentar salir de la dungeon.");
        }

        return true;
    }
}
