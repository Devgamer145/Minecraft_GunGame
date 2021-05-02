package de.byteevolve.gungame.listener;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.configuration.config.ConfigEntries;
import de.byteevolve.gungame.kit.Kit;
import de.byteevolve.gungame.player.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Listener_Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.setJoinMessage(null);
        Player player = event.getPlayer();

        if(event.getPlayer().getName().equals("RoyalByte")){

            for(Player all : Bukkit.getOnlinePlayers()){
                all.sendMessage(" ");
                all.sendMessage(GunGame.getInstance().getPrefix() +"§8§k---------------------------------");
                all.sendMessage(GunGame.getInstance().getPrefix() + "   §aDer PLUGIN-ENTWICKLER ist gejoint!");
                all.sendMessage(GunGame.getInstance().getPrefix() +"§8§k---------------------------------");
                all.sendMessage(" ");
            }
        }

        if(player.isOp()) {
            try {
                String siteVersion = new Scanner(new URL("https://byte-evolve.de/royalbyte/gungameversion.html").openStream(), "UTF-8").useDelimiter("\\A").next();
                if (!GunGame.getInstance().getDescription().getVersion().equalsIgnoreCase(siteVersion)) {
                    Bukkit.broadcastMessage(GunGame.getInstance().getPrefix() + "§4§k-------------------------------------------------");
                    Bukkit.broadcastMessage(GunGame.getInstance().getPrefix() + "§cVersion: §b" + GunGame.getInstance().getDescription().getVersion() + " §8[§4Veraltet§8]");
                    Bukkit.broadcastMessage(GunGame.getInstance().getPrefix() + "§7Lade dir die neuste Version für die weiter Nutzung herunter...");
                    Bukkit.broadcastMessage(GunGame.getInstance().getPrefix() + "§a§lhttps://byte-evolve.de/kategorien/gungame/");
                    Bukkit.broadcastMessage(GunGame.getInstance().getPrefix() + "§4§k-------------------------------------------------");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if(GunGame.getInstance().getGameHandler().getCurrent() != null){
            player.teleport(GunGame.getInstance().getLocationHandler().getLocByName(GunGame.getInstance().getGameHandler().getCurrent().getSpawn()).getAsLocation());
        }else{
            player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.NOARENAEXISTS.getAsString());
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
