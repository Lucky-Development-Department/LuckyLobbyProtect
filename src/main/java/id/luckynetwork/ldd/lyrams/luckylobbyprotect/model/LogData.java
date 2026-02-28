package id.luckynetwork.ldd.lyrams.luckylobbyprotect.model;

import lombok.Builder;
import lombok.Data;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@Builder
public class LogData {

    private final String eventName;
    private final String worldName;
    private final long timestamp;

    private final Location playerLocation;
    private final Location eventLocation;

    @Nullable
    private final String details;

    public List<String> toFormattedString() {
        List<String> formatted = new LinkedList<>();
        formatted.add("§eEvent: §a" + this.eventName + "|" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(this.timestamp)));
        formatted.add("§eWorld: §a" + this.worldName);
        formatted.add("§ePlayer Location: " + this.playerLocation.getBlockX() + ", " + this.playerLocation.getBlockY() + ", " + this.playerLocation.getBlockZ());
        formatted.add("§eEvent Location: " + this.eventLocation.getBlockX() + ", " + this.eventLocation.getBlockY() + ", " + this.eventLocation.getBlockZ());
        if (this.details != null) {
            formatted.add("§eDetails: " + this.details);
        }

        return formatted;
    }

}
