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


            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage(GunGame.getInstance().getPrefix() +"§8§k---------------------------------");
            Bukkit.broadcastMessage(GunGame.getInstance().getPrefix() + "   §aDer PLUGIN-ENTWICKLER ist gejoint!");
            Bukkit.broadcastMessage(GunGame.getInstance().getPrefix() +"§8§k---------------------------------");
            Bukkit.broadcastMessage(" ");
            }

        if(player.isOp()) {
            try {
                String siteVersion = new Scanner(new URL("https://byte-evolve.de/royalbyte/gungameversion.html").openStream(), "UTF-8").useDelimiter("\\A").next();
                if (!GunGame.getInstance().getDescription().getVersion().equalsIgnoreCase(siteVersion)) {
                    player.sendMessage(GunGame.getInstance().getPrefix() + "§4§k-------------------------------------------------");
                    player.sendMessage(GunGame.getInstance().getPrefix() + "§cVersion: §b" + GunGame.getInstance().getDescription().getVersion() + " §8[§4Veraltet§8]");
                    player.sendMessage(GunGame.getInstance().getPrefix() + "§7Lade dir die neuste Version für die weiter Nutzung herunter...");
                    player.sendMessage(GunGame.getInstance().getPrefix() + "§a§lhttps://byte-evolve.de/kategorien/gungame/");

                    player.sendMessage(GunGame.getInstance().getPrefix() + "§4§k-------------------------------------------------");
                    Bukkit.getPluginManager().disablePlugin(GunGame.getInstance());
                    return;
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
