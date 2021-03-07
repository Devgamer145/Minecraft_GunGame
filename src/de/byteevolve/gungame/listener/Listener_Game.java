package de.byteevolve.gungame.listener;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.arena.Arena;
import de.byteevolve.gungame.kit.Kit;
import de.byteevolve.gungame.player.PlayerHandler;
import de.byteevolve.gungame.player.PlayerStats;
import de.byteevolve.gungame.player.PlayerStatsType;
import de.byteevolve.gungame.team.Team;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Listener_Game implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            Arena arena = GunGame.getInstance().getGameHandler().getCurrent();
            if(arena.atSpawn(player.getLocation()) || arena.atSpawn(event.getDamager().getLocation()) ){
                event.setCancelled(true);
            }
            if(GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString()) != null){
                Team team = GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString());
                if(team.getMembers().contains(event.getDamager().getUniqueId().toString()) || team.getOwner().equals(event.getDamager().getUniqueId().toString())){
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        if(!(GunGame.getInstance().getBuild().contains(player.getUniqueId())
                && player.getGameMode().equals(GameMode.CREATIVE))){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPickUp(PlayerPickupItemEvent event){
        Player player = event.getPlayer();
        if(!(GunGame.getInstance().getBuild().contains(player.getUniqueId())
                && player.getGameMode().equals(GameMode.CREATIVE))){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) event.setCancelled(true);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Material material = event.getPlayer().getLocation().getBlock().getType();
        if(material == Material.STATIONARY_WATER || material == Material.WATER) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HARM, 20, 200));
        }
    }

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
            PlayerStats playerStats = new PlayerStats(killer.getUniqueId().toString());
            playerStats.add(PlayerStatsType.KILLS, 1);
            if(playerStats.get(PlayerStatsType.HIGHSCORE) < newKitKiller.getId()) playerStats.set(PlayerStatsType.HIGHSCORE, newKitKiller.getId());
            new PlayerHandler(killer).sendScoreBoard();

            killer.setLevel(newKitKiller.getId());
            killer.setHealth(20);
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
                player.setLevel(newKit.getId());
            }
        }.runTaskLater(GunGame.getInstance(), 10);
        new PlayerStats(player.getUniqueId().toString()).add(PlayerStatsType.DEATHS, 1);
        new PlayerHandler(player).sendScoreBoard();
        event.setRespawnLocation(GunGame.getInstance().getLocationHandler().getLocByName(GunGame.getInstance().getGameHandler().getCurrent().getSpawn()).getAsLocation());

    }

}
