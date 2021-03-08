package de.byteevolve.gungame.commands;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.arena.ArenaTeamState;
import de.byteevolve.gungame.team.Team;
import javafx.print.PageLayout;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Locale;
import java.util.UUID;

public class Command_Team implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            if(GunGame.getInstance().getGameHandler().getCurrent().getArenaTeamState().equals(ArenaTeamState.DISALLOWED)){
                sender.sendMessage(GunGame.getInstance().getPrefix() + "§7Bei dieser §aMap §7kannst du diesen Command §cnicht §7benutzen.");
                return true;
            }
            Player player = (Player) sender;
            if(args.length == 0){
                player.sendMessage(GunGame.getInstance().getPrefix() + "§aInformationen zum Teamsystem");
                player.sendMessage(GunGame.getInstance().getPrefix() + "§a/team invite §7<§aSpieler§7> §7Lädt einen Spieler in das Team ein");
                player.sendMessage(GunGame.getInstance().getPrefix() + "§a/team accept §7Nimmt eine Anfrage an");
                player.sendMessage(GunGame.getInstance().getPrefix() + "§a/team deny §7Lehnt eine Anfrage ab");
                player.sendMessage(GunGame.getInstance().getPrefix() + "§a/team info §7Zeigt alle Mitglieder deines Teams an");
                player.sendMessage(GunGame.getInstance().getPrefix() + "§a/team leave §7Verlässt das Team");
                player.sendMessage(GunGame.getInstance().getPrefix() + "§a/team kick §7<§aSpieler§7> Kickt einen Spieler aus dem Team");
                return true;
            }

            switch (args[0].toLowerCase()){
                case "invite":
                    if(args.length == 2) {
                        if (GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString()) != null) {
                            Team team = GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString());
                            if (team.getOwner().equals(player.getUniqueId().toString())) {
                                if(Bukkit.getPlayer(args[1]) != null){
                                    Player toInvite = Bukkit.getPlayer(args[1]);
                                    if(GunGame.getInstance().getTeamHandler().inTeam(toInvite.getUniqueId().toString()) == null) {
                                        if(GunGame.getInstance().getTeamHandler().hasInvite(toInvite.getUniqueId().toString()) != null){
                                            Team inviteTeam = GunGame.getInstance().getTeamHandler().hasInvite(toInvite.getUniqueId().toString());
                                            inviteTeam.getInvites().remove(toInvite.getUniqueId().toString());
                                        }
                                        team.getInvites().add(toInvite.getUniqueId().toString());
                                        toInvite.sendMessage(GunGame.getInstance().getPrefix() + "§7Du wurdest von §a" + player.getDisplayName() + "§7 zu einem §aTeam §7eingeladen.");
                                        IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + GunGame.getInstance().getPrefix() + "\",\"extra\":" +
                                                "[{\"text\":\"§aAnnehmen\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§7Klicke zum §aannehmen\"},\"clickEvent\":{\"action\":\"" +
                                                "run_command\",\"value\":\"/team accept\"}}, {\"text\":\" §7| \"}," +
                                                " {\"text\":\"§cAblehnen\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§7Klicke zum §cablehnen\"},\"clickEvent\":{\"action\":\"" +
                                                "run_command\",\"value\":\"/team deny\"}}]}");
                                        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(iChatBaseComponent);
                                        ((CraftPlayer) toInvite).getHandle().playerConnection.sendPacket(packetPlayOutChat);
                                        player.sendMessage(GunGame.getInstance().getPrefix() + "§7Du hast §a" + toInvite.getDisplayName() + "§7 zu einem Team eingeladen.");
                                    }else {
                                        player.sendMessage(GunGame.getInstance().getPrefix() + "§cDer Spieler ist schon in einem Team");
                                    }
                                } else {
                                    player.sendMessage(GunGame.getInstance().getPlayerNotOnline());
                                }
                            } else {
                                player.sendMessage(GunGame.getInstance().getPrefix() + "§cEs kann nur der Team-Inhaber andere einladen.");
                                return true;
                            }
                        } else {
                            GunGame.getInstance().getTeamHandler().getTeams().put(player.getUniqueId().toString(), new Team(player.getUniqueId().toString()));
                            Team team = GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString());
                            player.sendMessage(GunGame.getInstance().getPrefix() + "§7Du hast ein §aTeam §7erstellt.");
                            if(Bukkit.getPlayer(args[1]) != null){
                                Player toInvite = Bukkit.getPlayer(args[1]);
                                if(GunGame.getInstance().getTeamHandler().inTeam(toInvite.getUniqueId().toString()) == null) {
                                    if(GunGame.getInstance().getTeamHandler().hasInvite(toInvite.getUniqueId().toString()) != null){
                                        Team inviteTeam = GunGame.getInstance().getTeamHandler().hasInvite(toInvite.getUniqueId().toString());
                                        inviteTeam.getInvites().remove(toInvite.getUniqueId().toString());
                                    }
                                    team.getInvites().add(toInvite.getUniqueId().toString());
                                    toInvite.sendMessage(GunGame.getInstance().getPrefix() + "§7Du wurdest von §a" + player.getDisplayName() + "§7 zu einem §aTeam §7eingeladen.");
                                    IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + GunGame.getInstance().getPrefix() + "\",\"extra\":" +
                                            "[{\"text\":\"§aAnnehmen\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§7Klicke zum §aannehmen\"},\"clickEvent\":{\"action\":\"" +
                                            "run_command\",\"value\":\"/team accept\"}}, {\"text\":\" §7| \"}," +
                                            " {\"text\":\"§cAblehnen\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§7Klicke zum §cablehnen\"},\"clickEvent\":{\"action\":\"" +
                                            "run_command\",\"value\":\"/team deny\"}}]}");
                                    PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(iChatBaseComponent);
                                    ((CraftPlayer) toInvite).getHandle().playerConnection.sendPacket(packetPlayOutChat);
                                    player.sendMessage(GunGame.getInstance().getPrefix() + "§7Du hast §a" + toInvite.getDisplayName() + "§7 zu einem Team eingeladen.");
                                }else {
                                    player.sendMessage(GunGame.getInstance().getPrefix() + "§cDer Spieler ist schon in einem Team");
                                }
                            } else {
                                player.sendMessage(GunGame.getInstance().getPlayerNotOnline());
                            }
                        }
                    }else {
                        player.sendMessage(GunGame.getInstance().getPrefix() + "§cBitte nutze /team invite <Player>");
                    }
                    break;
                case "accept":
                    if(GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString()) == null) {
                        if (GunGame.getInstance().getTeamHandler().hasInvite(player.getUniqueId().toString()) != null) {
                            Team team = GunGame.getInstance().getTeamHandler().hasInvite(player.getUniqueId().toString());
                            if (team.getMembers().size() <= 2) {
                                team.getInvites().remove(player.getUniqueId().toString());
                                team.getMembers().add(player.getUniqueId().toString());
                                for(String uuid : team.getMembers()){
                                    Player member = Bukkit.getPlayer(UUID.fromString(uuid));
                                    member.sendMessage(GunGame.getInstance().getPrefix() + "§a" + player.getDisplayName() + "§7 ist dem Team §2beigetreten§7.");
                                }
                                Player owner = Bukkit.getPlayer(UUID.fromString(team.getOwner()));
                                owner.sendMessage(GunGame.getInstance().getPrefix() + "§a" + player.getDisplayName() + "§7 ist dem Team §2beigetreten§7.");
                                player.sendMessage(GunGame.getInstance().getPrefix() + "§7Du bist dem Team von §a" + owner.getDisplayName() + " §2beigetreten§7.");
                            } else {
                                player.sendMessage(GunGame.getInstance().getPrefix() + "§cDas Team ist schon voll.");
                                team.getInvites().remove(player.getUniqueId().toString());
                            }
                        } else {
                            player.sendMessage(GunGame.getInstance().getPrefix() + "§cDu wurdest zu keinem Team eingeladen.");
                        }
                    }else{
                        player.sendMessage(GunGame.getInstance().getPrefix() + "§cDu bist schon in einem Team.");
                    }
                    break;
                case "deny":
                    if(GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString()) == null) {
                        if (GunGame.getInstance().getTeamHandler().hasInvite(player.getUniqueId().toString()) != null) {
                            Team team = GunGame.getInstance().getTeamHandler().hasInvite(player.getUniqueId().toString());
                            team.getInvites().remove(player.getUniqueId().toString());
                            Player owner = Bukkit.getPlayer(UUID.fromString(team.getOwner()));
                            owner.sendMessage(GunGame.getInstance().getPrefix() + "§a" + player.getDisplayName() + "§7 hat deine Anfrage §cabgelehnt§7.");
                            player.sendMessage(GunGame.getInstance().getPrefix() + "§7Du hast die Anfrage von §a" + owner.getDisplayName() + " §cabgelehnt§7.");
                        } else {
                            player.sendMessage(GunGame.getInstance().getPrefix() + "§cDu wurdest zu keinem Team eingeladen.");
                        }
                    }else{
                        player.sendMessage(GunGame.getInstance().getPrefix() + "§cDu bist schon in einem Team.");
                    }
                    break;
                case "info":
                    if(GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString()) != null){
                        Team team = GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString());
                        Player owner = Bukkit.getPlayer(UUID.fromString(team.getOwner()));
                        player.sendMessage(GunGame.getInstance().getPrefix() +"§7§m§l-------------------------------------");
                        player.sendMessage(GunGame.getInstance().getPrefix() + "§cOwner§7: §a" + owner.getDisplayName());
                        for(String uuid : team.getMembers()){
                            Player member = Bukkit.getPlayer(UUID.fromString(uuid));
                            player.sendMessage(GunGame.getInstance().getPrefix() + "§7Member: §a" + member.getDisplayName());
                        }
                        player.sendMessage(GunGame.getInstance().getPrefix() +"§7§m§l-------------------------------------");
                    }else {
                        player.sendMessage(GunGame.getInstance().getPrefix() + "§cDu bist in keinem Team!");
                    }
                    break;
                case "leave":
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
                                player.sendMessage(GunGame.getInstance().getPrefix() + "§7Du hast dein Team §caufgelöst§7.");
                                return true;
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
                        player.sendMessage(GunGame.getInstance().getPrefix() + "§7Du hast das Team von §a" + owner.getDisplayName() + " §cverlassen§7.");
                    }else {
                        player.sendMessage(GunGame.getInstance().getPrefix() + "§cDu bist in keinem Team!");
                    }
                    break;
                case "kick":
                    if(args.length == 2) {
                        if (GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString()) != null) {
                            Team team = GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString());
                            if (team.getOwner().equals(player.getUniqueId().toString())) {
                                if(Bukkit.getPlayer(args[1]) != null){
                                    Player toKick = Bukkit.getPlayer(args[1]);
                                    if(team.getMembers().contains(toKick.getUniqueId().toString())){
                                        team.getMembers().remove(toKick.getUniqueId().toString());
                                        toKick.sendMessage(GunGame.getInstance().getPrefix() + "§7Du wurdest aus dem Team von §a" + player.getDisplayName() + " §centfernt§7.");
                                        for(String uuid : team.getMembers()){
                                            Player member = Bukkit.getPlayer(UUID.fromString(uuid));
                                            member.sendMessage(GunGame.getInstance().getPrefix() + "§7" + toKick.getDisplayName() + "§7 hat das Team §cverlassen§7.");
                                        }
                                        Player owner = Bukkit.getPlayer(UUID.fromString(team.getOwner()));
                                        owner.sendMessage(GunGame.getInstance().getPrefix() + "§a" + toKick.getDisplayName() + "§7 hat das Team §cverlassen§7.");
                                    }else{
                                        player.sendMessage(GunGame.getInstance().getPrefix() + "§cDer Spieler " +toKick.getDisplayName() +" ist nicht in deinem Team.");
                                    }
                                }else{
                                    player.sendMessage(GunGame.getInstance().getPlayerNotOnline());
                                }
                            } else {
                                player.sendMessage(GunGame.getInstance().getPrefix() + "§cDu hast nicht die Berechtigung andere aus dem Team zu kicken.");
                            }
                        } else {
                            player.sendMessage(GunGame.getInstance().getPrefix() + "§cDu bist in keinem Team!");
                        }
                    }else {
                        player.sendMessage(GunGame.getInstance().getPrefix() + "§cBitte nutze /team kick <Player>");
                    }
                    break;
                default:
                    player.sendMessage(GunGame.getInstance().getPrefix() + "§aInformationen zum Teamsystem");
                    player.sendMessage(GunGame.getInstance().getPrefix() + "§a/team invite §7<§aSpieler§7> §7Lädt einen Spieler in das Team ein");
                    player.sendMessage(GunGame.getInstance().getPrefix() + "§a/team accept §7Nimmt eine Anfrage an");
                    player.sendMessage(GunGame.getInstance().getPrefix() + "§a/team deny §7Lehnt eine Anfrage ab");
                    player.sendMessage(GunGame.getInstance().getPrefix() + "§a/team info §7Zeigt alle Mitglieder deines Teams an");
                    player.sendMessage(GunGame.getInstance().getPrefix() + "§a/team leave §7Verlässt das Team");
                    player.sendMessage(GunGame.getInstance().getPrefix() + "§a/team kick §7<§aSpieler§7> Kickt einen Spieler aus dem Team");
                    break;
            }

        }else sender.sendMessage(GunGame.getInstance().getMustAPlayer());
        return true;
    }
}
