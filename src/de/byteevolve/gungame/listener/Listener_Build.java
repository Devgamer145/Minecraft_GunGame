package de.byteevolve.gungame.listener;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.configuration.config.ConfigEntries;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Listener_Build implements Listener {

    @EventHandler
    public void onBreack(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!(GunGame.getInstance().getBuild().contains(player.getUniqueId())
                && player.getGameMode().equals(GameMode.CREATIVE))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!(GunGame.getInstance().getBuild().contains(player.getUniqueId())
                && player.getGameMode().equals(GameMode.CREATIVE))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.SOIL) {
            if((boolean) ConfigEntries.ANTICROPTRAMPLE.getValue()) {
                event.setCancelled(true);
            }
        }
    }
}
