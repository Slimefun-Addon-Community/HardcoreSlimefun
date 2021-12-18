package dev.walshy.hardcoreslimefun.utils;

import dev.walshy.hardcoreslimefun.HardcoreSlimefun;
import org.bukkit.NamespacedKey;

import javax.annotation.Nonnull;

public class Keys {

    public static final NamespacedKey MALFUNCTION_TIME_OUT = create("malfunction_time_out");

    private Keys() {}

    @Nonnull
    private static NamespacedKey create(@Nonnull String value) {
        return new NamespacedKey(HardcoreSlimefun.getInstance(), value);
    }
}
