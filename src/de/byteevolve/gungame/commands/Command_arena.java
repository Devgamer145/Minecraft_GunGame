package de.byteevolve.gungame.commands;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.arena.Arena;
import de.byteevolve.gungame.configuration.config.ConfigEntries;
import de.byteevolve.gungame.player.PlayerHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_arena implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(GunGame.getInstance().getMustAPlayer());
            return true;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("GunGame.arena")) {
            player.sendMessage(GunGame.getInstance().getNoPerm());
            return true;
        }

        if(args.length >= 3){
            if(args[0].equalsIgnoreCase("create")){
                String dpn = "";
                for (int i = 2; i < args.length; i++) {
                    dpn = dpn + args[i] + " ";
                }
                if(GunGame.getInstance().getArenaHandler().getArenaPlayerCreate().containsKey(player)){
                    player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.PLAYERCREATESARENA.getAsString());
                    return true;
                }

                if(GunGame.getInstance().getArenaHandler().existArenaByName(args[1]) || GunGame.getInstance().getArenaHandler().existArenaPlayerCreateByName(args[1])){
                    player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.ARENAEXISTS.getAsString().replaceAll("%ARENANAME%", args[1]));
                    return true;
                }

                Arena arena = new Arena(args[1]);
                arena.setDisplayname(dpn);
                GunGame.getInstance().getArenaHandler().getArenaPlayerCreate().put(player, arena);
                player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.PLAYERCREATEARENA.getAsString().replaceAll("%ARENANAME%", args[1]));
                return true;
            }
        }

        if(args.length == 2) {
            switch (args[0].toLowerCase()) {
                case "edit":
                    if (GunGame.getInstance().getArenaHandler().existArenaByName(args[1])) {
                        GunGame.getInstance().getArenaHandler().getArenaEditList().put(player, GunGame.getInstance().getArenaHandler().getArenaByName(args[1]));
                        new PlayerHandler(player).openArenaEditMainInv(GunGame.getInstance().getArenaHandler().getArenaByName(args[1]));
                    } else
                        player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.ARENANOTEXISTS.getAsString().replaceAll("%ARENANAME%", args[1]));
                    break;
                case "delete":
                    break;
            }
        }
        if(args.length == 1){
            if(args[0].equalsIgnoreCase("edit")){
            if(GunGame.getInstance().getArenaHandler().getArenaPlayerCreate().containsKey(player))
                new PlayerHandler(player).openArenaEditMainInv(GunGame.getInstance().getArenaHandler().getArenaPlayerCreate().get(player));
            else player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.PLAYERNOTCREATESARENA.getAsString());
        }else if(args[0].equalsIgnoreCase("list")){
                if(GunGame.getInstance().getArenaHandler().getArenas().isEmpty())
                    player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.NOARENAEXISTS.getAsString());

            for(Arena arena : GunGame.getInstance().getArenaHandler().getArenas()){
                player.sendMessage(ConfigEntries.PREFIX.getAsString() + "§a" + arena.getDisplayname().replaceAll("&", "§") + "§7»" + arena.getArenaTeamState().getPrefix());
            }
            }else player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.ARENAHELP.getAsString());
    }else player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.ARENAHELP.getAsString());




        return true;
    }
}
