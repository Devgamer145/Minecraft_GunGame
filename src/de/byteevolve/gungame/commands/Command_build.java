package de.byteevolve.gungame.commands;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.kit.Kit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_build implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(GunGame.getInstance().getMustAPlayer());
            return true;
        }
        Player player = (Player) sender;
        if(!player.hasPermission("GunGame.Build")){
            player.sendMessage(GunGame.getInstance().getNoPerm());
            return true;
        }

        if(GunGame.getInstance().getBuild().contains(player.getUniqueId())){
            GunGame.getInstance().getBuild().remove(player.getUniqueId());
            if(GunGame.getInstance().getGameHandler().getCurrent() != null){
                player.teleport(GunGame.getInstance().getLocationHandler().getLocByName(GunGame.getInstance().getGameHandler().getCurrent().getSpawn()).getAsLocation());
            }else{
                player.sendMessage(GunGame.getInstance().getPrefix() + "§cEs wurde noch keine Arena erstellt.");
            }

            if(GunGame.getInstance().getGameHandler().getPlayerkits().containsKey(player)){
                GunGame.getInstance().getGameHandler().getPlayerkits().get(player).getKitInventory().load(player);
            }else{
                GunGame.getInstance().getGameHandler().getPlayerkits().put(player, Kit.LEVEL_0);
                GunGame.getInstance().getGameHandler().getPlayerkits().get(player).getKitInventory().load(player);
            }
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(GunGame.getInstance().getPrefix() + "§7Du bist nun §cnicht §7mehr im §aBuild-Modus§7.");
        }else{
            GunGame.getInstance().getBuild().add(player.getUniqueId());
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(GunGame.getInstance().getPrefix() + "§7Du bist nun im §aBuild-Modus§7.");
        }
        return true;
    }
}
