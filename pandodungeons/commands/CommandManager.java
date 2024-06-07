package pandodungeons.pandodungeons.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pandodungeons.pandodungeons.PandoDungeons;

public class CommandManager implements CommandExecutor {
    private final PandoDungeons plugin;
    private final DungeonsPlayCommand playCommand;
    private final DungeonsLeaveCommand leaveCommand;

    public CommandManager(PandoDungeons plugin) {
        this.plugin = plugin;
        this.playCommand = new DungeonsPlayCommand(plugin);
        this.leaveCommand = new DungeonsLeaveCommand(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Verificar si el comando ejecutado es el comando "dungeons"
        if (command.getName().equalsIgnoreCase("dungeons")) {
            // Verificar si no se proporcionaron argumentos
            if (args.length == 0) {
                sender.sendMessage("Comandos disponibles: /dungeons play, /dungeons leave");
                return true;
            }
        }

        switch (args[0].toLowerCase()) {
            case "play":
                return playCommand.onCommand(sender, command, label, args);
            case "leave":
                return leaveCommand.onCommand(sender, command, label, args);
            default:
                sender.sendMessage("Comando desconocido. Uso: /dungeons play, /dungeons leave");
                return true;
        }
    }
}
