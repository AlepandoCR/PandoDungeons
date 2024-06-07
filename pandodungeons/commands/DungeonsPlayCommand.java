package pandodungeons.pandodungeons.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.World;
import pandodungeons.pandodungeons.PandoDungeons;
import pandodungeons.pandodungeons.utils.LocationUtils;

import java.util.Random;

public class DungeonsPlayCommand implements CommandExecutor {
    private final PandoDungeons plugin;

    public DungeonsPlayCommand(PandoDungeons plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Verificar si el remitente no es un jugador
        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando solo puede ser ejecutado por un jugador.");
            return true;
        }

        Player player = (Player) sender;

        // Verificar si no se proporcionaron argumentos o si el primer argumento no es "play"
        if (args.length == 0 || !args[0].equalsIgnoreCase("play")) {
            player.sendMessage("Uso: /dungeons play");
            return true;
        }
        //Verificar que el jugador no tenga una dungeon activa ya
        if (LocationUtils.getPlayerLocationData(player.getUniqueId().toString()) != null) {
            player.sendMessage("¡Ya estás en una dungeon! Usa '/dungeons leave' para salir.");
            return true;
        }

        Location originalLocation = player.getLocation();
        Location dungeonLocation = getRandomDungeonLocation();

        LocationUtils.savePlayerLocationData(player.getUniqueId().toString(), originalLocation);

        player.teleport(dungeonLocation);
        player.sendMessage("¡Has sido teletransportado a una dungeon en el mundo 'dungeons'!");

    
        Block block = dungeonLocation.subtract(0, 1, 0).getBlock();
        block.setType(Material.STONE);

        return true;
    }

    private Location getRandomDungeonLocation() {
        World world = plugin.getServer().getWorld("dungeons");
        if (world == null) {
            return null;
        }

        Random random = new Random();
        int x = random.nextInt(10000) - 5000;
        int z = random.nextInt(10000) - 5000;
        return new Location(world, x, 61, z);
    }
}
