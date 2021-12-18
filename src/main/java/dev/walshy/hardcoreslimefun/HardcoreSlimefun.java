package dev.walshy.hardcoreslimefun;

import dev.walshy.hardcoreslimefun.events.AndroidEvents;
import dev.walshy.hardcoreslimefun.events.Events;
import dev.walshy.hardcoreslimefun.utils.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;

/*
 * Ideas:
 *   + Reset research on death
 *   + Chance to reset all research on death
 *   + Research fail chance
 *   + Android fail chance (farm/mine)
 *   * Enchanter fail chance
 *   * Disenchant fail chance
 *   * Block placer fail chance
 *   * Reactors actually explode
 *   * Explosives can damage/knockback
 *   * Ancient altar can fail and instead output some rubbish item :^)
 */
public class HardcoreSlimefun extends JavaPlugin {

    private static HardcoreSlimefun instance;

    @Override
    public void onEnable() {
        setInstance(this);
        saveDefaultConfig();

        Config.INSTANCE.load(this.getConfig());

        if (getServer().getPluginManager().getPlugin("Slimefun") == null) {
            getLogger().severe("Slimefun not found! Disabling plugin...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (getConfig().getBoolean("auto-update") && getDescription().getVersion().startsWith("DEV - ")) {
            new GitHubBuildsUpdater(this, getFile(), "Slimefun-Addon-Community/HardcoreSlimefun/main", "DEV").start();
        }

        new HardcoreMetrics(this).start();

        getServer().getPluginManager().registerEvents(new Events(), this);
        getServer().getPluginManager().registerEvents(new AndroidEvents(), this);
    }

    @Override
    public void onDisable() {
        setInstance(null);
    }

    public static HardcoreSlimefun getInstance() {
        return instance;
    }

    private static void setInstance(@Nullable HardcoreSlimefun plugin) {
        instance = plugin;
    }
}
