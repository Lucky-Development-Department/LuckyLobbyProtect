package id.luckynetwork.ldd.lyrams.luckylobbyprotect.command.subcommand;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import id.luckynetwork.ldd.lyrams.luckylobbyprotect.command.NeoBuildModeCommand;
import id.luckynetwork.lyrams.lyralibs.bukkit.command.LyraSubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WhitelistCommand extends LyraSubCommand {

    private final NeoBuildModeCommand parent;
    private final LuckyLobbyProtect plugin;


    public WhitelistCommand(NeoBuildModeCommand parent) {
        super("whitelist");

        this.parent = parent;
        this.plugin = parent.getPlugin();
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (args.length == 0) {
            parent.sendDefaultMessage(commandSender);
            return;
        }

        String action = args[0];
        String[] subArgs = new String[args.length - 1];
        switch (action) {
            case "add":
                // add a player to the build mode whitelist
                if (args.length < 3) {
                    parent.sendDefaultMessage(commandSender);
                    return;
                }

                System.arraycopy(args, 1, subArgs, 0, args.length - 1);
                addWhitelist(commandSender, subArgs);
                break;
            case "remove":
                // remove a player from the build mode whitelist
                if (args.length < 3) {
                    parent.sendDefaultMessage(commandSender);
                    return;
                }

                System.arraycopy(args, 1, subArgs, 0, args.length - 1);
                removeWhitelist(commandSender, subArgs);
                break;
            case "clear":
                // clear the build mode whitelist
                clearWhitelist(commandSender);
                break;
            case "list":
                // list all players in the build mode whitelist
                listWhitelist(commandSender);
                break;
            default:
                parent.sendDefaultMessage(commandSender);
        }
    }

    @Override
    public List<String> getTabSuggestions(CommandSender commandSender, String s, String[] strings) {
        return null;
    }

    private void addWhitelist(CommandSender commandSender, String[] args) {
        // add a player to the build mode whitelist
        Player otherPlayer;
        if ((otherPlayer = plugin.getServer().getPlayer(args[1])) == null) {
            commandSender.sendMessage("§e§lBUILDMODE §a/ §cPlayer not found!");
            return;
        }

        List<String> whitelisted = plugin.getNeoListenerManager().getWhitelistedMap().get(otherPlayer);
        if (whitelisted == null) {
            whitelisted = new ArrayList<>();
        }

        whitelisted.add(args[2]);
        commandSender.sendMessage("§e§lBUILDMODE §a/ §eAdded §a" + args[2] + " §eto §d" + otherPlayer.getName() + "'s §ewhitelist.");
    }

    private void removeWhitelist(CommandSender commandSender, String[] args) {
        // remove a player from the build mode whitelist
        Player otherPlayer;
        if ((otherPlayer = plugin.getServer().getPlayer(args[1])) == null) {
            commandSender.sendMessage("§e§lBUILDMODE §a/ §cPlayer not found!");
            return;
        }

        if (args[2].equalsIgnoreCase("clear") || args[2].equalsIgnoreCase("all")) {
            plugin.getNeoListenerManager().getWhitelistedMap().remove(otherPlayer);
            return;
        }

        List<String> whitelisted = plugin.getNeoListenerManager().getWhitelistedMap().get(otherPlayer);
        if (whitelisted == null) {
            whitelisted = new ArrayList<>();
        }

        whitelisted.remove(args[2]);
        commandSender.sendMessage("§e§lBUILDMODE §a/ §eRemoved §c" + args[2] + " §efrom §d" + otherPlayer.getName() + "'s §ewhitelist.");
    }

    private void clearWhitelist(CommandSender commandSender) {
        // clear the build mode whitelist
        plugin.getNeoListenerManager().getWhitelistedMap().clear();
        commandSender.sendMessage("§e§lBUILDMODE §a/ §eCleared the build mode whitelist.");
    }

    private void listWhitelist(CommandSender commandSender) {
        // list all players in the build mode whitelist
        HashMap<Player, List<String>> whitelistedMap = plugin.getNeoListenerManager().getWhitelistedMap();

        StringBuilder builder = new StringBuilder();
        builder.append("§e§lBUILDMODE §a/ §fWhitelisted players: ");
        for (Player player : whitelistedMap.keySet()) {
            builder.append(player.getName()).append(", ");
        }

        commandSender.sendMessage(builder.toString());
        commandSender.sendMessage("§e§lBUILDMODE §a/ §fTotal: §d" + whitelistedMap.size());
    }
}
