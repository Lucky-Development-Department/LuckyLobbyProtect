package id.luckynetwork.ldd.lyrams.luckylobbyprotect.command.subcommand;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import id.luckynetwork.ldd.lyrams.luckylobbyprotect.command.NeoBuildModeCommand;
import id.luckynetwork.lyrams.lyralibs.bukkit.command.LyraSubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public class ListCommand extends LyraSubCommand {

    private final NeoBuildModeCommand parent;
    private final LuckyLobbyProtect plugin;

    public ListCommand(NeoBuildModeCommand parent) {
        super("list");

        this.parent = parent;
        this.plugin = parent.getPlugin();
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        Set<Player> toggledPlayers = plugin.getNeoListenerManager().getToggledPlayers();
        if (toggledPlayers.isEmpty()) {
            commandSender.sendMessage("§e§lBUILDMODE §a/ §cNo players are in build mode!");
            return;
        }

        commandSender.sendMessage("§e§lBUILDMODE §a/ §aPlayers in build mode:");
        toggledPlayers.forEach(player -> commandSender.sendMessage("§e- §a" + player.getName()));
    }

    @Override
    public List<String> getTabSuggestions(CommandSender commandSender, String s, String[] strings) {
        return null;
    }
}
