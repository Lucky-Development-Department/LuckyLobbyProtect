package id.luckynetwork.ldd.lyrams.luckylobbyprotect.command;

import com.google.common.base.Joiner;
import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.*;

public class BuildModeCommand extends Command {

    private final LuckyLobbyProtect plugin;

    public BuildModeCommand(LuckyLobbyProtect plugin) {
        super("buildmode");
        this.plugin = plugin;
        this.setAliases(Arrays.asList("build", "bm"));
        this.registerCommand(this);
    }

    private void registerCommand(Command command) {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);

            CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
            commandMap.register(plugin.getDescription().getName(), command);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            execute(sender, args);
            return false;
        }

        Player player = (Player) sender;
        if (!sender.hasPermission("lucky.buildmode")) {
            sender.sendMessage("§e§lBUILDMODE §a/ §cYou don't have permission to toggle build mode!");
            return false;
        }

        if (args.length == 0) {
            if (plugin.getBuildModeList().contains(player)) {
                plugin.getBuildModeList().remove(player);
                sender.sendMessage("§e§lBUILDMODE §a/ §eBuild mode §cdisabled§e.");
            } else {
                plugin.getBuildModeList().add(player);
                sender.sendMessage("§e§lBUILDMODE §a/ §eBuild mode §aenabled§e.");
            }
            return false;
        }

        execute(sender, args);
        return false;
    }

    private void execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                plugin.getListenerManager().reload();

                sender.sendMessage("§e§lBUILDMODE §a/ §eConfig reloaded!");
            } else if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage("§cUsage: /buildmode");
                sender.sendMessage("§cUsage: /buildmode reload");
                sender.sendMessage("§cUsage: /buildmode listEvents");
                sender.sendMessage("§cUsage: /buildmode check <player>");
                sender.sendMessage("§cUsage: /buildmode whitelist <player> <event>");
                sender.sendMessage("§cUsage: /buildmode unwhitelist <player> <event>");
                sender.sendMessage("§cUsage: /buildmode unwhitelist <player>");
            } else if (args[0].equalsIgnoreCase("listEvents")) {
                sender.sendMessage("§e§lBUILDMODE §a/ §eList of active events: §d" + Joiner.on(", ").join(plugin.getListenerManager().getActiveListeners()));
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage("§e§lBUILDMODE §a/ §cPlayer not found!");
                    return;
                }

                if (plugin.getBuildModeList().contains(target)) {
                    plugin.getBuildModeList().remove(target);
                    sender.sendMessage("§e§lBUILDMODE §a/ §eBuild mode §cdisabled §efor §d" + target.getName() + "§e.");
                    target.sendMessage("§e§lBUILDMODE §a/ §eBuild mode §cdisabled§d!");
                } else {
                    plugin.getBuildModeList().add(target);
                    sender.sendMessage("§e§lBUILDMODE §a/ §eBuild mode §aenabled §efor §d" + target.getName() + "§e.");
                    target.sendMessage("§e§lBUILDMODE §a/ §eBuild mode §aenabled!");
                }
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("unwhitelist")) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage("§e§lBUILDMODE §a/ §cPlayer not found!");
                    return;
                }

                if (!plugin.getListenerManager().getWhitelistedMap().containsKey(target)) {
                    if (plugin.getBuildModeList().contains(target)) {
                        sender.sendMessage("§e§lBUILDMODE §a/ §cTarget is already using build mode!");
                    } else {
                        sender.sendMessage("§e§lBUILDMODE §a/ §cTarget is not bypassing anything!");
                    }
                    return;
                }

                plugin.getListenerManager().getWhitelistedMap().remove(target);
                sender.sendMessage("§e§lBUILDMODE §a/ §eSuccessfully §cunwhitelisted §d" + target.getName() + "§e.");
            } else if (args[0].equalsIgnoreCase("check")) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage("§e§lBUILDMODE §a/ §cPlayer not found!");
                    return;
                }

                HashMap<Player, List<String>> whitelistedMap = plugin.getListenerManager().getWhitelistedMap();
                if (whitelistedMap.containsKey(target)) {
                    List<String> whitelists = new ArrayList<>(whitelistedMap.get(target));
                    Collections.sort(whitelists);

                    sender.sendMessage("§e§lBUILDMODE §a/ §d" + target.getName() + " §eis bypassing §6" + Joiner.on(",").join(whitelists) + "§e.");
                } else {
                    if (plugin.getBuildModeList().contains(target)) {
                        sender.sendMessage("§e§lBUILDMODE §a/ §cTarget is already using build mode!");
                    } else {
                        sender.sendMessage("§e§lBUILDMODE §a/ §cTarget is not bypassing anything!");
                    }
                }
            } else {
                sender.sendMessage("§cUsage: /buildmode");
                sender.sendMessage("§cUsage: /buildmode reload");
                sender.sendMessage("§cUsage: /buildmode listEvents");
                sender.sendMessage("§cUsage: /buildmode check <player>");
                sender.sendMessage("§cUsage: /buildmode whitelist <player> <event>");
                sender.sendMessage("§cUsage: /buildmode unwhitelist <player> <event>");
                sender.sendMessage("§cUsage: /buildmode unwhitelist <player>");
            }
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("whitelist")) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage("§e§lBUILDMODE §a/ §cPlayer not found!");
                    return;
                }

                HashMap<Player, List<String>> whitelistedMap = plugin.getListenerManager().getWhitelistedMap();
                if (whitelistedMap.containsKey(target)) {
                    List<String> whitelists = new ArrayList<>(whitelistedMap.get(target));
                    if (whitelists.contains(args[2].toLowerCase())) {
                        sender.sendMessage("§e§lBUILDMODE §a/ §c§l" + target.getName() + " §cis already bypassing §l" + args[2].toLowerCase() + "§c!");
                        return;
                    }
                    whitelists.add(args[2].toLowerCase());

                    whitelistedMap.put(target, whitelists);
                } else {
                    whitelistedMap.put(target, Collections.singletonList(args[2].toLowerCase()));
                }

                sender.sendMessage("§e§lBUILDMODE §a/ §d" + target.getName() + " §eis now bypassing §6" + args[2].toLowerCase() + "§e.");
            } else if (args[0].equalsIgnoreCase("unwhitelist")) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage("§e§lBUILDMODE §a/ §cPlayer not found!");
                    return;
                }

                HashMap<Player, List<String>> whitelistedMap = plugin.getListenerManager().getWhitelistedMap();
                if (whitelistedMap.containsKey(target)) {
                    List<String> whitelists = new ArrayList<>(whitelistedMap.get(target));
                    if (!whitelists.contains(args[2].toLowerCase())) {
                        sender.sendMessage("§e§lBUILDMODE §a/ §c§l" + target.getName() + " §cis not bypassing §l" + args[2].toLowerCase() + "§c!");
                        return;
                    }
                    whitelists.remove(args[2].toLowerCase());

                    whitelistedMap.put(target, whitelists);
                    sender.sendMessage("§e§lBUILDMODE §a/ §d" + target.getName() + " §eis no longer bypassing §6" + args[2].toLowerCase() + "§e.");
                } else {
                    if (plugin.getBuildModeList().contains(target)) {
                        sender.sendMessage("§e§lBUILDMODE §a/ §cTarget is already using build mode!");
                    } else {
                        sender.sendMessage("§e§lBUILDMODE §a/ §cTarget is not bypassing anything!");
                    }
                }
            } else {
                sender.sendMessage("§cUsage: /buildmode");
                sender.sendMessage("§cUsage: /buildmode reload");
                sender.sendMessage("§cUsage: /buildmode listEvents");
                sender.sendMessage("§cUsage: /buildmode check <player>");
                sender.sendMessage("§cUsage: /buildmode whitelist <player> <event>");
                sender.sendMessage("§cUsage: /buildmode unwhitelist <player> <event>");
                sender.sendMessage("§cUsage: /buildmode unwhitelist <player>");
            }
        } else {
            sender.sendMessage("§cUsage: /buildmode");
            sender.sendMessage("§cUsage: /buildmode reload");
            sender.sendMessage("§cUsage: /buildmode listEvents");
            sender.sendMessage("§cUsage: /buildmode check <player>");
            sender.sendMessage("§cUsage: /buildmode whitelist <player> <event>");
            sender.sendMessage("§cUsage: /buildmode unwhitelist <player> <event>");
            sender.sendMessage("§cUsage: /buildmode unwhitelist <player>");
        }
    }
}
