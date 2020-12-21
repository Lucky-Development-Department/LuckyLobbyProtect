package id.luckynetwork.ldd.lyrams.luckylobbyprotect.api.impl;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import lombok.RequiredArgsConstructor;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class PlaceholderAPIExpansion extends PlaceholderExpansion {

    private final LuckyLobbyProtect plugin;

    @Override
    public @NotNull
    String getIdentifier() {
        return "lbp";
    }

    @Override
    public @NotNull
    String getAuthor() {
        return "LyraMS";
    }

    @Override
    public @NotNull
    List<String> getPlaceholders() {
        return Collections.singletonList("date");
    }

    @Override
    public @NotNull
    String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier) {
        return plugin.getPlaceholderHooks().onPlaceholderRequest(player, identifier);
    }
}
