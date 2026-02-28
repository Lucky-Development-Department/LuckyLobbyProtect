package id.luckynetwork.ldd.lyrams.luckylobbyprotect.command.subcommand;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import id.luckynetwork.ldd.lyrams.luckylobbyprotect.command.NeoBuildModeCommand;
import id.luckynetwork.ldd.lyrams.luckylobbyprotect.util.Helper;
import id.luckynetwork.lyrams.lyralibs.bukkit.command.LyraSubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ToggleCommand extends LyraSubCommand {

    private final NeoBuildModeCommand parent;
    private final LuckyLobbyProtect plugin;

    public ToggleCommand(NeoBuildModeCommand parent) {
        super("toggle");

        this.parent = parent;
        this.plugin = parent.getPlugin();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            Player player;
            if ((player = Helper.getPlayerSender(sender)) == null) {
                sender.sendMessage("§e§lBUILDMODE §a/ §cYou must be player to toggle build mode!");
                return;
            }

            // toggle build mode for yourself
            Set<Player> toggledPlayers = plugin.getNeoListenerManager().getToggledPlayers();
            if (toggledPlayers.contains(player)) {
                toggledPlayers.remove(player);
                sender.sendMessage("§e§lBUILDMODE §a/ §cYou have §lDISABLED §cbuild mode!");
            } else {
                toggledPlayers.add(player);
                sender.sendMessage("§e§lBUILDMODE §a/ §aYou have §lENABLED §abuild mode!");
            }
            return;
        }

        if (args.length == 1) {
            // perhaps toggle build mode for another player
            String stateOrPlayer = args[0];
            if (stateOrPlayer.equalsIgnoreCase("on") || stateOrPlayer.equalsIgnoreCase("off")) {
                Player player;
                if ((player = Helper.getPlayerSender(sender)) == null) {
                    sender.sendMessage("§e§lBUILDMODE §a/ §cYou must be player to toggle build mode!");
                    return;
                }

                boolean state = stateOrPlayer.equalsIgnoreCase("on");
                toggle(state, player, sender);
                return;
            }

            Player otherPlayer;
            if ((otherPlayer = plugin.getServer().getPlayer(stateOrPlayer)) == null) {
                sender.sendMessage("§e§lBUILDMODE §a/ §cPlayer §l" + stateOrPlayer + " §cnot found!");
                return;
            }

            // toggle build mode for another player
            toggle(null, otherPlayer, sender);

            Set<Player> toggledPlayers = plugin.getNeoListenerManager().getToggledPlayers();
            if (toggledPlayers.contains(otherPlayer)) {
                toggledPlayers.remove(otherPlayer);
                sender.sendMessage("§e§lBUILDMODE §a/ §cYou have §lDISABLED §cbuild mode for §l" + otherPlayer.getName() + "§c!");
            } else {
                toggledPlayers.add(otherPlayer);
                sender.sendMessage("§e§lBUILDMODE §a/ §aYou have §lENABLED §abuild mode for §l" + otherPlayer.getName() + "§a!");
            }
            return;
        }

        // toggle build mode for another player
        Player otherPlayer;
        if ((otherPlayer = plugin.getServer().getPlayer(args[0])) == null) {
            sender.sendMessage("§e§lBUILDMODE §a/ §cPlayer §l" + args[0] + " §cnot found!");
            return;
        }

        String stateString = args[1];
        boolean state = stateString.equalsIgnoreCase("on");
        toggle(state, otherPlayer, sender);
    }

    private void toggle(@Nullable Boolean state, Player otherPlayer, CommandSender sender) {
        Set<Player> toggledPlayers = plugin.getNeoListenerManager().getToggledPlayers();
        if (state == null) {
            state = !toggledPlayers.contains(otherPlayer);
        }

        if (state) {
            toggledPlayers.add(otherPlayer);
            sender.sendMessage("§e§lBUILDMODE §a/ §aYou have §lENABLED §abuild mode for §l" + otherPlayer.getName() + "§a!");
        } else {
            toggledPlayers.remove(otherPlayer);
            sender.sendMessage("§e§lBUILDMODE §a/ §cYou have §lDISABLED §cbuild mode for §l" + otherPlayer.getName() + "§c!");
        }
    }

    @Override
    public List<String> getTabSuggestions(CommandSender commandSender, String s, String[] strings) {
        List<String> suggestions = new ArrayList<>();

        if (strings.length == 1) {
            suggestions.add("on");
            suggestions.add("off");
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                suggestions.add(player.getName());
            }

            return suggestions;
        }

        if (strings.length == 2) {
            suggestions.add("on");
            suggestions.add("off");
        }

        return suggestions;
    }
}
