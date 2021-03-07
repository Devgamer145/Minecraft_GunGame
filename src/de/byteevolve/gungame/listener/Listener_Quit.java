package de.byteevolve.gungame.listener;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class Listener_Quit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        if(GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString()) != null){
            Team team = GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString());
            if(team.getOwner().equals(player.getUniqueId().toString())){
                if(team.getMembers().size() > 0) {
                    String newOwner = team.getMembers().get(0);
                    team.setOwner(newOwner);
                    team.getMembers().remove(newOwner);
                    Bukkit.getPlayer(UUID.fromString(newOwner)).sendMessage(GunGame.getInstance().getPrefix() + "§7Du bist nun der §cOwner §7des §aTeams§7.");
                    GunGame.getInstance().getTeamHandler().getTeams().remove(player.getUniqueId().toString());
                    GunGame.getInstance().getTeamHandler().getTeams().put(newOwner, team);
                }else{
                    GunGame.getInstance().getTeamHandler().getTeams().remove(player.getUniqueId().toString());
                    return;
                }
            }else{
                team.getMembers().remove(player.getUniqueId().toString());
            }

            for(String uuid : team.getMembers()){
                Player member = Bukkit.getPlayer(UUID.fromString(uuid));
                member.sendMessage(GunGame.getInstance().getPrefix() + "§a" + player.getDisplayName() + "§7 hat das Team §cverlassen§7.");
            }
            Player owner = Bukkit.getPlayer(UUID.fromString(team.getOwner()));
            owner.sendMessage(GunGame.getInstance().getPrefix() + "§a" + player.getDisplayName() + "§7 hat das Team §cverlassen§7.");
        }
    }
}
