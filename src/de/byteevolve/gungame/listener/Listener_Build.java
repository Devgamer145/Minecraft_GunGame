package de.byteevolve.gungame.listener;

import de.byteevolve.gungame.GunGame;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Listener_Build implements Listener {

    @EventHandler
    public void onBreack(BlockBreakEvent event){
        Player player = event.getPlayer();
        if(!(GunGame.getInstance().getBuild().contains(player.getUniqueId())
                && player.getGameMode().equals(GameMode.CREATIVE))){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        if(!(GunGame.getInstance().getBuild().contains(player.getUniqueId())
                && player.getGameMode().equals(GameMode.CREATIVE))){
            event.setCancelled(true);
        }
    }
}
