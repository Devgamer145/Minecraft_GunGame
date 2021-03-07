package de.byteevolve.gungame.listener;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.kit.Kit;
import de.byteevolve.gungame.player.PlayerStats;
import de.byteevolve.gungame.player.PlayerStatsType;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Listener_Game implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        event.setDeathMessage(null);
        event.getDrops().clear();
        event.setDroppedExp(0);
        if(event.getEntity().getKiller() != null){
            Player killer = event.getEntity().getKiller();
            Kit kitKiller = GunGame.getInstance().getGameHandler().getPlayerkits().get(killer);
            Kit newKitKiller = GunGame.getInstance().getGameHandler().getKitFromID(kitKiller.getId()+1);
            GunGame.getInstance().getGameHandler().getPlayerkits().put(killer, newKitKiller);
            newKitKiller.getKitInventory().load(killer);

            new PlayerStats(killer.getUniqueId().toString()).add(PlayerStatsType.KILLS, 1);

            killer.setLevel(newKitKiller.getId());
            killer.sendMessage(GunGame.getInstance().getPrefix() + "§7Du hast §a" + player.getDisplayName() + "§7 getötet.");
            killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 10, 10);
            player.sendMessage(GunGame.getInstance().getPrefix() + "§7Du wurdest von §c" + killer.getDisplayName() + "§7 getötet.");
        }else{
            player.sendMessage(GunGame.getInstance().getPrefix() + "§7Du bist gestorben.");
        }
        player.setVelocity(new Vector(0, 0, 0));
        player.spigot().respawn();
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        Kit kit = GunGame.getInstance().getGameHandler().getPlayerkits().get(player);
        Kit newKit = GunGame.getInstance().getGameHandler().getKitFromID(kit.getId()/2);
        GunGame.getInstance().getGameHandler().getPlayerkits().put(player, newKit);
        new BukkitRunnable(){
            @Override
            public void run() {
                newKit.getKitInventory().load(player);
            }
        }.runTaskLater(GunGame.getInstance(), 10);
        System.out.println(kit.getId() + "  " + newKit.getId() + "   " + kit.getId()/2);

        new PlayerStats(player.getUniqueId().toString()).add(PlayerStatsType.DEATHS, 1);
        event.setRespawnLocation(GunGame.getInstance().getLocationHandler().getLocByName(GunGame.getInstance().getGameHandler().getCurrent().getSpawn()).getAsLocation());
        player.setLevel(newKit.getId());

    }

}
