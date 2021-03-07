package de.byteevolve.gungame.listener;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.kit.Kit;
import de.byteevolve.gungame.player.PlayerHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listener_Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        if(GunGame.getInstance().getGameHandler().getCurrent() != null){
            player.teleport(GunGame.getInstance().getLocationHandler().getLocByName(GunGame.getInstance().getGameHandler().getCurrent().getSpawn()).getAsLocation());
        }else{
            player.sendMessage(GunGame.getInstance().getPrefix() + "§cEs wurde noch keine Arena erstellt.");
        }

        player.setLevel(0);

        new PlayerHandler(player).sendScoreBoard();

        if(GunGame.getInstance().getGameHandler().getPlayerkits().containsKey(player)){
            GunGame.getInstance().getGameHandler().getPlayerkits().get(player).getKitInventory().load(player);
        }else{
            GunGame.getInstance().getGameHandler().getPlayerkits().put(player, Kit.LEVEL_0);
            GunGame.getInstance().getGameHandler().getPlayerkits().get(player).getKitInventory().load(player);
        }

    }
}
