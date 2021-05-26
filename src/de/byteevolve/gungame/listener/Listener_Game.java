package de.byteevolve.gungame.listener;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.arena.Arena;
import de.byteevolve.gungame.configuration.config.ConfigEntries;
import de.byteevolve.gungame.kit.Kit;
import de.byteevolve.gungame.player.PlayerHandler;
import de.byteevolve.gungame.player.PlayerStats;
import de.byteevolve.gungame.player.PlayerStatsType;
import de.byteevolve.gungame.sound.Sounds;
import de.byteevolve.gungame.team.Team;
import net.minecraft.server.v1_16_R3.PacketPlayInClientCommand;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
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
        }else{
            event.setCancelled(true);
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
            playerStats.add(PlayerStatsType.POINTS, 10);
            if(playerStats.get(PlayerStatsType.HIGHSCORE) < newKitKiller.getId()) playerStats.set(PlayerStatsType.HIGHSCORE, newKitKiller.getId());
            new PlayerHandler(killer).sendScoreBoard();
            killer.setLevel(newKitKiller.getId());
            killer.setMaxHealth(20);
            killer.setHealth(20);
            killer.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.KILLERKILLS.getAsString().replaceAll("%PLAYER%", player.getDisplayName()));
            Sounds.LEVEL_UP.play(killer, 10, 10);
            player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.PLAYERKILLED.getAsString().replaceAll("%KILLER%", killer.getDisplayName()));
        }else{
            player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.PLAYERDEAD.getAsString());
        }
        player.setVelocity(new Vector(0, 0, 0));
        new PlayerHandler(player).respawnPlayer();

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
        }.runTaskLater(GunGame.getInstance(), 3);
        PlayerStats playerStats = new PlayerStats(player.getUniqueId().toString());
        playerStats.add(PlayerStatsType.DEATHS, 1);
        playerStats.remove(PlayerStatsType.POINTS, 5);
        new PlayerHandler(player).sendScoreBoard();
        if(GunGame.getInstance().getGameHandler().getCurrent() != null) {
            event.setRespawnLocation(GunGame.getInstance().getLocationHandler().getLocByName(GunGame.getInstance().getGameHandler().getCurrent().getSpawn()).getAsLocation());
        }
    }

}
