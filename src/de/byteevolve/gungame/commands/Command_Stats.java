package de.byteevolve.gungame.commands;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.player.PlayerStats;
import de.byteevolve.gungame.player.PlayerStatsType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                    player.sendMessage(GunGame.getInstance().getPrefix() +"§7§m§l-------------------------------------");
                    player.sendMessage(GunGame.getInstance().getPrefix() + "§7Kills: §a" + playerStats.get(PlayerStatsType.KILLS));
                    player.sendMessage(GunGame.getInstance().getPrefix() + "§7Tode: §a" + playerStats.get(PlayerStatsType.DEATHS));
                    player.sendMessage(GunGame.getInstance().getPrefix() + "§7Rekord: §a" + playerStats.get(PlayerStatsType.HIGHSCORE));
                    player.sendMessage(GunGame.getInstance().getPrefix() + "§7KD: §a" + playerStats.getKD());
                    player.sendMessage(GunGame.getInstance().getPrefix() +"§7§m§l-------------------------------------");
                }else player.sendMessage(GunGame.getInstance().getPlayerNotOnline());
            }else{
                PlayerStats playerStats = new PlayerStats(player.getUniqueId().toString());
                player.sendMessage(GunGame.getInstance().getPrefix() +"§7§m§l-------------------------------------");
                player.sendMessage(GunGame.getInstance().getPrefix() + "§7Kills: §a" + playerStats.get(PlayerStatsType.KILLS));
                player.sendMessage(GunGame.getInstance().getPrefix() + "§7Tode: §a" + playerStats.get(PlayerStatsType.DEATHS));
                player.sendMessage(GunGame.getInstance().getPrefix() + "§7Rekord: §a" + playerStats.get(PlayerStatsType.HIGHSCORE));
                player.sendMessage(GunGame.getInstance().getPrefix() + "§7KD: §a" + playerStats.getKD());
                player.sendMessage(GunGame.getInstance().getPrefix() +"§7§m§l-------------------------------------");
            }
        }else sender.sendMessage(GunGame.getInstance().getMustAPlayer());
        return true;
    }
}
