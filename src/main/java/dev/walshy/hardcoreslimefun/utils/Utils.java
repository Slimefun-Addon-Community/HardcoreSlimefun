package dev.walshy.hardcoreslimefun.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    private Utils() {}

    @Nullable
    public static <T> T randomValue(@Nonnull Set<T> set) {
        final int rand = ThreadLocalRandom.current().nextInt(set.size());

        int i = 0;
        for (T value : set) {
            if (i++ == rand) {
                return value;
            }
        }
        return null;
    }

    public static boolean chance(double chance) {
        return ThreadLocalRandom.current().nextDouble(100) + 1 <= chance;
    }

    public static void send(@Nonnull Player player, @Nonnull String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
