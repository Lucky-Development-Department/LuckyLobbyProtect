package id.luckynetwork.ldd.lyrams.luckylobbyprotect.command;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import id.luckynetwork.ldd.lyrams.luckylobbyprotect.command.subcommand.ToggleCommand;
import id.luckynetwork.lyrams.lyralibs.bukkit.command.LyraParentCommand;
import id.luckynetwork.lyrams.lyralibs.core.command.CommandHelpGenerator;
import id.luckynetwork.lyrams.lyralibs.core.command.data.CommandExample;
import id.luckynetwork.lyrams.lyralibs.core.command.data.CommandInfo;
import lombok.Getter;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class NeoBuildModeCommand extends LyraParentCommand {

    @Getter
    private final LuckyLobbyProtect plugin;
    private final List<String> helpMessage;

    public NeoBuildModeCommand(LuckyLobbyProtect plugin) {
        super("buildmode", "lucky.buildmode", List.of("bm"));
        this.plugin = plugin;
        this.helpMessage = new ArrayList<>();

        this.buildHelpMessage();
        this.addSubCommand(new ToggleCommand(this));
    }

    @Override
    public void sendDefaultMessage(CommandSender commandSender) {
        this.helpMessage.forEach(commandSender::sendMessage);
    }

    private void buildHelpMessage() {
        List<CommandInfo> commandInfoList = new ArrayList<>();
        commandInfoList.add(CommandInfo.newBuilder()
                .command("/buildmode help")
                .description("Shows this help message")
                .build());
        commandInfoList.add(CommandInfo.newBuilder()
                .command("/buildmode toggle <on/off> [player]")
                .description("Toggles build mode for yourself or another player")
                .build());
        commandInfoList.add(CommandInfo.newBuilder()
                .command("/buildmode list")
                .description("Lists all players in build mode")
                .build());

        commandInfoList.add(CommandInfo.newBuilder()
                .command("/buildmode whitelist add <player> <listener>")
                .description("Adds a player to the build mode whitelist")
                .example(CommandExample.newBuilder()
                        .example("/buildmode whitelist LyraMS BlockPlaceEvent")
                        .addDescription("LyraMS", "The player to add to the whitelist")
                        .addDescription("BlockPlaceEvent", "The listener to add to the whitelist")
                        .build())
                .build());
        commandInfoList.add(CommandInfo.newBuilder()
                .command("/buildmode whitelist remove <player> <listener/all>")
                .description("Removes a player from the build mode whitelist")
                .example(CommandExample.newBuilder()
                        .example("/buildmode whitelist remove LyraMS BlockPlaceEvent")
                        .addDescription("LyraMS", "The player to remove from the whitelist")
                        .addDescription("BlockPlaceEvent", "The listener to remove from the whitelist")
                        .build())
                .build());
        commandInfoList.add(CommandInfo.newBuilder()
                .command("/buildmode whitelist clear")
                .description("Clears the build mode whitelist")
                .build());
        commandInfoList.add(CommandInfo.newBuilder()
                .command("/buildmode whitelist list")
                .description("Lists all players in the build mode whitelist")
                .build());

        commandInfoList.add(CommandInfo.newBuilder()
                .command("/buildmode log show [player]")
                .description("Shows the build mode log for yourself or another player")
                .build());
        commandInfoList.add(CommandInfo.newBuilder()
                .command("/buildmode log clear [player/all]")
                .description("Clears the build mode log for yourself or another player or all players")
                .build());
        commandInfoList.add(CommandInfo.newBuilder()
                .command("/buildmode reload")
                .description("Reloads the configuration")
                .build());


        List<String> generated = CommandHelpGenerator.generateHelpCommand("LuckyBuildMode", "1.2.0", List.of("LyraMS"), commandInfoList);
        this.helpMessage.addAll(generated);
    }
}
