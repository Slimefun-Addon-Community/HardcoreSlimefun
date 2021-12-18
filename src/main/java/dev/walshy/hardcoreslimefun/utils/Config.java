package dev.walshy.hardcoreslimefun.utils;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

import javax.annotation.Nonnull;

@Getter
public class Config {

    public static final Config INSTANCE = new Config();

    // on-death.reset-random-research
    private boolean resetResearchOnDeath;
    // on-death.chance-to-reset-all-researches
    private double resetAllResearchesOnDeath;

    // on-research.chance-of-failure
    private double researchFailChance;

    // android.chance-to-malfunction
    private double malfunctionChance;
    // android.malfunction-duration
    private long malfunctionDuration;

    ////////////////////////
    // Messages
    ////////////////////////
    // lost-random-research
    private String lostRandomResearch;
    // lost-all-research
    private String lostAllResearch;
    // research-failed
    private String researchFailed;
    // android-malfunctioned
    private String androidMalfunctioned;

    public void load(@Nonnull FileConfiguration config) {
        resetResearchOnDeath = config.getBoolean("on-death.reset-random-research", true);
        resetAllResearchesOnDeath = getPercent(config, "on-death.chance-to-reset-all-researches", 5);

        researchFailChance = getPercent(config, "on-research.chance-of-failure", 10);

        malfunctionChance = getPercent(config, "android.chance-to-malfunction", 10);
        malfunctionDuration = config.getLong("android.malfunction-duration", 30);

        ////////////////////////
        // Messages
        ////////////////////////
        lostRandomResearch = config.getString("messages.lost-random-research", "&cYou lost a random research!");
        lostAllResearch = config.getString("messages.lost-all-research", "&cYou lost all your research!");
        researchFailed = config.getString("messages.research-failed", "&cResearch failed!");
        androidMalfunctioned = config.getString("messages.android-malfunctioned",
            "&cYour Android has malfunctioned! Let it cool down and start it again");
    }

    private double getPercent(@Nonnull FileConfiguration config, @Nonnull String path, double defaultValue) {
        final double d = config.getDouble(path, defaultValue);
        if (d > 100) {
            return 100;
        } else if (d < 0) {
            return 0;
        } else {
            return d;
        }
    }
}
