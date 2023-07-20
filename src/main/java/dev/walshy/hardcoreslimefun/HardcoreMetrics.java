package dev.walshy.hardcoreslimefun;

import dev.walshy.hardcoreslimefun.utils.Config;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.Callable;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

class HardcoreMetrics {

    private final HardcoreSlimefun plugin;

    HardcoreMetrics(@Nonnull HardcoreSlimefun plugin) {
        this.plugin = plugin;
    }

    void start() {
        Metrics metrics = new Metrics(plugin, 13641);

        addPercentChart(metrics, "chance-to-reset-random-research", 25, Config.INSTANCE::getResetResearchOnDeath);
        addPercentChart(metrics, "chance_to_reset_all_researches_on_death", 25,
            Config.INSTANCE::getResetAllResearchesOnDeath);

        addPercentChart(metrics, "chance_for_research_failure", 25, Config.INSTANCE::getResearchFailChance);

        addPercentChart(metrics, "chance_for_android_malfunction", 25, Config.INSTANCE::getResearchFailChance);
    }

    @ParametersAreNonnullByDefault
    private void addBooleanPie(Metrics metrics, String label, BooleanSupplier setting) {
        metrics.addCustomChart(new SimplePie(label, getPieText(setting)));
    }

    @ParametersAreNonnullByDefault
    private void addPercentChart(Metrics metrics, String label, double rangeStep, DoubleSupplier setting) {
        metrics.addCustomChart(new SimplePie(label, () -> {
            double value = setting.getAsDouble();
            double lastStep = 0;
            for (double d = rangeStep; d <= 100; d += rangeStep) {
                // if (13 <= 25) { return "0-25%"; }
                if (value <= d) {
                    return lastStep + "% - " + d + '%';
                }
                lastStep = d;
            }

            // This shouldn't fire
            return "0%";
        }));
    }

    @Nonnull
    private Callable<String> getPieText(@Nonnull BooleanSupplier setting) {
        return () -> setting.getAsBoolean() ? "Yes" : "No";
    }

}