package io.github.thebusybiscuit.hardcoreslimefun;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.mrCookieSlime.Slimefun.cscorelib2.updater.GitHubBuildsUpdater;

public class HardcoreSlimefun extends JavaPlugin implements SlimefunAddon {

    private boolean resetResearchesOnDeath;
    private int researchFailureChance;

    @Override
    public void onEnable() {
        // Read something from your config.yml
        Config cfg = new Config(this);

        if (cfg.getBoolean("options.auto-update")) {
            new GitHubBuildsUpdater(this, getFile(), "Slimefun-Addon-Community/HardcoreSlimefun/master").start();
        }

        try {
            resetResearchesOnDeath = cfg.getBoolean("hardcore-settings.researches.reset-on-death");
            researchFailureChance = cfg.getInt("hardcore-settings.researches.failure-chance");
        } catch (Exception x) {
            getLogger().log(Level.SEVERE, "Failed to read hardcore settings from config!", x);
        }

        try {
            new HardcoreMetrics(this).start();
        } catch (Exception x) {
            getLogger().log(Level.SEVERE, "Failed to set up Metrics", x);
        }
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/Slimefun-Addon-Community/HardcoreSlimefun/issues";
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    public boolean isResearchResetOnDeathEnabled() {
        return resetResearchesOnDeath;
    }

    public int getResearchFailureChance() {
        return researchFailureChance;
    }

}
