package dev.walshy.hardcoreslimefun.events;

import dev.walshy.hardcoreslimefun.utils.Config;
import dev.walshy.hardcoreslimefun.utils.Keys;
import dev.walshy.hardcoreslimefun.utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.events.AndroidFarmEvent;
import io.github.thebusybiscuit.slimefun4.api.events.AndroidMineEvent;
import io.github.thebusybiscuit.slimefun4.implementation.items.androids.AndroidInstance;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.annotation.Nonnull;
import java.util.concurrent.TimeUnit;

/*
 * This class covers:
 *   - Chance for androids to malfunction (break for x time)
 */
public class AndroidEvents implements Listener {

    @EventHandler
    public void onAndroidMine(@Nonnull AndroidMineEvent event) {
        if (Utils.chance(Config.INSTANCE.getMalfunctionChance())) {
            setMalfunctioned(event.getAndroid());
        }
    }

    @EventHandler
    public void onAndroidMine(@Nonnull AndroidFarmEvent event) {
        if (Utils.chance(Config.INSTANCE.getMalfunctionChance())) {
            setMalfunctioned(event.getAndroid());
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onAndroidInteract(@Nonnull PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK
            && event.getClickedBlock() != null
            && !event.getClickedBlock().getType().isAir()
            && event.getClickedBlock().getState() instanceof Skull
        ) {
            final Skull skull = (Skull) event.getClickedBlock().getState();

            if (PersistentDataAPI.hasBoolean(skull, Keys.MALFUNCTIONED)) {
                if (PersistentDataAPI.getLong(skull, Keys.MALFUNCTION_TIME_OUT) <= System.currentTimeMillis()) {
                    event.setCancelled(true);
                    Utils.send(event.getPlayer(), Config.INSTANCE.getAndroidMalfunctioned());
                } else {
                    PersistentDataAPI.remove(skull, Keys.MALFUNCTIONED);
                    PersistentDataAPI.remove(skull, Keys.MALFUNCTION_TIME_OUT);
                }
            }
        }
    }

    private void setMalfunctioned(@Nonnull AndroidInstance android) {
        final Block block = android.getBlock();

        // Pause android
        BlockStorage.addBlockInfo(block, "paused", "true");

        // Set malfunctioned
        if (block.getState() instanceof Skull) {
            final Skull skull = (Skull) block.getState();

            PersistentDataAPI.setBoolean(skull, Keys.MALFUNCTIONED, true);
            PersistentDataAPI.setLong(
                skull,
                Keys.MALFUNCTION_TIME_OUT,
                System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(Config.INSTANCE.getMalfunctionDuration())
            );
            skull.update();
        }
    }
}
