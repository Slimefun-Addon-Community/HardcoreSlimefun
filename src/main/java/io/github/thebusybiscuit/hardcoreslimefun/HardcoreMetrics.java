package io.github.thebusybiscuit.hardcoreslimefun;

import java.util.concurrent.Callable;
import java.util.function.BooleanSupplier;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;

class HardcoreMetrics {

    private final HardcoreSlimefun plugin;

    HardcoreMetrics(@Nonnull HardcoreSlimefun plugin) {
        this.plugin = plugin;
    }

    void start() {
        Metrics metrics = new Metrics(plugin, 10096);

        addBooleanPie(metrics, "reset_researches_on_death", plugin::isResearchResetOnDeathEnabled);
    }

    @ParametersAreNonnullByDefault
    private void addBooleanPie(Metrics metrics, String label, BooleanSupplier setting) {
        metrics.addCustomChart(new SimplePie(label, getPieText(setting)));
    }

    @Nonnull
    private Callable<String> getPieText(@Nonnull BooleanSupplier setting) {
        return () -> setting.getAsBoolean() ? "Yes" : "No";
    }

}
