package dev.walshy.hardcoreslimefun.events;

import dev.walshy.hardcoreslimefun.utils.Config;
import dev.walshy.hardcoreslimefun.utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.events.ResearchUnlockEvent;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import javax.annotation.Nonnull;

public class Events implements Listener {

    /*
     * This event covers:
     *   - Losing a random research on death
     *   - Chance to lose all research on death
     */
    @EventHandler
    public void onDeath(@Nonnull PlayerDeathEvent event) {
        PlayerProfile.get(event.getEntity(), profile -> {
            final boolean shouldResetAllResearches = Utils.chance(Config.INSTANCE.getResetAllResearchesOnDeath());

            // If we should reset all researches, do it
            if (shouldResetAllResearches) {
                for (Research research : profile.getResearches()) {
                    profile.setResearched(research, false);
                }
                Utils.send(event.getEntity(), Config.INSTANCE.getLostAllResearch());

            } else if (Config.INSTANCE.isResetResearchOnDeath()) {
                // If we should reset a random one, do it
                final Research randomResearch = Utils.randomValue(profile.getResearches());
                if (randomResearch == null) return;

                profile.setResearched(randomResearch, false);
                String message = Config.INSTANCE.getLostRandomResearch().replace("%research%", randomResearch.getName(event.getEntity()));
                Utils.send(event.getEntity(), message);
            }
        });
    }

    /*
     * This event covers:
     *   - Chance to fail researching
     */
    @EventHandler
    public void onResearch(@Nonnull ResearchUnlockEvent event) {
        final boolean shouldResearchFail = Utils.chance(Config.INSTANCE.getResearchFailChance());

        if (shouldResearchFail) {
            event.setCancelled(true);
            Utils.send(event.getPlayer(), Config.INSTANCE.getResearchFailed());
        }
    }
}
