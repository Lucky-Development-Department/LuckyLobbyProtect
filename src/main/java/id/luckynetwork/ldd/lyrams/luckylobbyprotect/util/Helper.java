package id.luckynetwork.ldd.lyrams.luckylobbyprotect.util;

import lombok.experimental.UtilityClass;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public class Helper {

    @Nullable
    public Player getPlayerSender(CommandSender sender) {
        if (sender instanceof Player) {
            return (Player) sender;
        }

        return null;
    }
}
