package de.byteevolve.gungame.commands;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.configuration.config.ConfigEntries;
import de.byteevolve.gungame.player.PlayerStats;
import de.byteevolve.gungame.player.PlayerStatsType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.print.DocFlavor;
import java.util.UUID;

public class Command_Stats implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 1){
                if(Bukkit.getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    PlayerStats playerStats = new PlayerStats(target.getUniqueId().toString());
                    for(String line : ConfigEntries.STATS.getAsString().split("\n")){
                        line = line.replaceAll("%PLAYER%", target.getDisplayName());
                        line = line.replaceAll("%KILLS%", playerStats.get(PlayerStatsType.KILLS).toString());
                        line = line.replaceAll("%DEAHTS%", playerStats.get(PlayerStatsType.DEATHS).toString());
                        line = line.replaceAll("%HIGHSCORE%", playerStats.get(PlayerStatsType.HIGHSCORE).toString());
                        line = line.replaceAll("%POINTS%", playerStats.get(PlayerStatsType.POINTS).toString());
                        line = line.replaceAll("%KD%", String.valueOf(playerStats.getKD()));
                        line = line.replaceAll("%RANK%", playerStats.getRank().toString());

                        player.sendMessage(GunGame.getInstance().getPrefix() + line);
                    }
                }else player.sendMessage(GunGame.getInstance().getPlayerNotOnline());
            }else{
                PlayerStats playerStats = new PlayerStats(player.getUniqueId().toString());

                for(String line : ConfigEntries.STATS.getAsString().split("\n")){
                    line = line.replaceAll("%PLAYER%", player.getDisplayName());
                    line = line.replaceAll("%KILLS%", playerStats.get(PlayerStatsType.KILLS).toString());
                    line = line.replaceAll("%DEAHTS%", playerStats.get(PlayerStatsType.DEATHS).toString());
                    line = line.replaceAll("%HIGHSCORE%", playerStats.get(PlayerStatsType.HIGHSCORE).toString());
                    line = line.replaceAll("%POINTS%", playerStats.get(PlayerStatsType.POINTS).toString());
                    line = line.replaceAll("%KD%", String.valueOf(playerStats.getKD()));
                    line = line.replaceAll("%RANK%", playerStats.getRank().toString());

                    player.sendMessage(GunGame.getInstance().getPrefix() + line);
                }
            }
        }else sender.sendMessage(GunGame.getInstance().getMustAPlayer());
        return true;
    }
}
