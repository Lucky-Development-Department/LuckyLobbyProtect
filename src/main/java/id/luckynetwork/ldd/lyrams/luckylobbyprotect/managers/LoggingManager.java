package id.luckynetwork.ldd.lyrams.luckylobbyprotect.managers;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import id.luckynetwork.ldd.lyrams.luckylobbyprotect.model.LogData;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public class LoggingManager {

    private final LuckyLobbyProtect plugin;

    private final HashMap<Player, List<LogData>> logMap = new HashMap<>();

    public void logEvent(Player player, LogData logData) {
        // max 100 logs
        List<LogData> logs = this.logMap.get(player);
        if (logs.size() >= 100) {
            logs.remove(0);
        }

        logs.add(logData);
    }

    public List<LogData> getLogs(@Nullable Player player) {
        return player == null ? null : this.logMap.get(player);
    }

    public void clearLogs(@Nullable Player player) {
        if (player == null) {
            this.logMap.clear();
        } else {
            this.logMap.remove(player);
        }
    }
}
