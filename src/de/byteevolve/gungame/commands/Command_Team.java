package de.byteevolve.gungame.commands;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.arena.ArenaTeamState;
import de.byteevolve.gungame.configuration.config.ConfigEntries;
import de.byteevolve.gungame.team.Team;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Command_Team implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            if(GunGame.getInstance().getGameHandler().getCurrent().getArenaTeamState().equals(ArenaTeamState.DISALLOWED)){
                sender.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.NOTEAMALLOWED.getAsString());
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
                                        toInvite.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TARGETTEAMINVITE.getAsString().replaceAll("%PLAYER%", player.getDisplayName()));
                                        IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + GunGame.getInstance().getPrefix() + "\",\"extra\":" +
                                                "[{\"text\":\"§aAnnehmen\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§7Klicke zum §aannehmen\"},\"clickEvent\":{\"action\":\"" +
                                                "run_command\",\"value\":\"/team accept\"}}, {\"text\":\" §7| \"}," +
                                                " {\"text\":\"§cAblehnen\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§7Klicke zum §cablehnen\"},\"clickEvent\":{\"action\":\"" +
                                                "run_command\",\"value\":\"/team deny\"}}]}");
                                        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(iChatBaseComponent);
                                        ((CraftPlayer) toInvite).getHandle().playerConnection.sendPacket(packetPlayOutChat);
                                        player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.PLAYERTEAMINVITE.getAsString().replaceAll("%TARGET%", toInvite.getDisplayName()));
                                    }else {
                                        player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.PLAYERINTEAM.getAsString());
                                    }
                                } else {
                                    player.sendMessage(GunGame.getInstance().getPlayerNotOnline());
                                }
                            } else {
                                player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.PLAYERCANTINVITETEAMMEMBER.getAsString());
                                return true;
                            }
                        } else {
                            GunGame.getInstance().getTeamHandler().getTeams().put(player.getUniqueId().toString(), new Team(player.getUniqueId().toString()));
                            Team team = GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString());
                            player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMCREATE.getAsString());
                            if(Bukkit.getPlayer(args[1]) != null){
                                Player toInvite = Bukkit.getPlayer(args[1]);
                                if(GunGame.getInstance().getTeamHandler().inTeam(toInvite.getUniqueId().toString()) == null) {
                                    if(GunGame.getInstance().getTeamHandler().hasInvite(toInvite.getUniqueId().toString()) != null){
                                        Team inviteTeam = GunGame.getInstance().getTeamHandler().hasInvite(toInvite.getUniqueId().toString());
                                        inviteTeam.getInvites().remove(toInvite.getUniqueId().toString());
                                    }
                                    team.getInvites().add(toInvite.getUniqueId().toString());
                                    toInvite.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TARGETTEAMINVITE.getAsString().replaceAll("%PLAYER%", player.getDisplayName()));
                                    IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + GunGame.getInstance().getPrefix() + "\",\"extra\":" +
                                            "[{\"text\":\"§aAnnehmen\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§7Klicke zum §aannehmen\"},\"clickEvent\":{\"action\":\"" +
                                            "run_command\",\"value\":\"/team accept\"}}, {\"text\":\" §7| \"}," +
                                            " {\"text\":\"§cAblehnen\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§7Klicke zum §cablehnen\"},\"clickEvent\":{\"action\":\"" +
                                            "run_command\",\"value\":\"/team deny\"}}]}");
                                    PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(iChatBaseComponent);
                                    ((CraftPlayer) toInvite).getHandle().playerConnection.sendPacket(packetPlayOutChat);
                                    player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.PLAYERTEAMINVITE.getAsString().replaceAll("%TARGET%", toInvite.getDisplayName()));
                                }else {
                                    player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.PLAYERINTEAM.getAsString());
                                }
                            } else {
                                player.sendMessage(GunGame.getInstance().getPlayerNotOnline());
                            }
                        }
                    }else {
                        player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMINVITEHELP.getAsString());
                    }
                    break;
                case "accept":
                    if(GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString()) == null) {
                        if (GunGame.getInstance().getTeamHandler().hasInvite(player.getUniqueId().toString()) != null) {
                            Team team = GunGame.getInstance().getTeamHandler().hasInvite(player.getUniqueId().toString());
                            if (team.getMembers().size() <= ConfigEntries.TEAMSIZE.getAsInt()) {
                                team.getInvites().remove(player.getUniqueId().toString());
                                team.getMembers().add(player.getUniqueId().toString());
                                for(String uuid : team.getMembers()){
                                    Player member = Bukkit.getPlayer(UUID.fromString(uuid));
                                    member.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMJOIN.getAsString().replaceAll("%PLAYER%", player.getDisplayName()));
                                }
                                Player owner = Bukkit.getPlayer(UUID.fromString(team.getOwner()));
                                owner.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMJOIN.getAsString().replaceAll("%PLAYER%", player.getDisplayName()));
                                player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMJOINPLAYER.getAsString().replaceAll("%OWNER%", owner.getDisplayName()));
                            } else {
                                player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMFULL.getAsString());
                                team.getInvites().remove(player.getUniqueId().toString());
                            }
                        } else {
                            player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.PLAYERHASNOTEAMINVITE.getAsString());
                        }
                    }else{
                        player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.PLAYERISINTEAM.getAsString());
                    }
                    break;
                case "deny":
                    if(GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString()) == null) {
                        if (GunGame.getInstance().getTeamHandler().hasInvite(player.getUniqueId().toString()) != null) {
                            Team team = GunGame.getInstance().getTeamHandler().hasInvite(player.getUniqueId().toString());
                            team.getInvites().remove(player.getUniqueId().toString());
                            Player owner = Bukkit.getPlayer(UUID.fromString(team.getOwner()));
                            owner.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMDENYOWNER.getAsString().replaceAll("%PLAYER%", player.getDisplayName()));
                            player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMDENYPLAYER.getAsString().replaceAll("%OWNER%", owner.getDisplayName()));
                        } else {
                            player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.PLAYERHASNOTEAMINVITE.getAsString());
                        }
                    }else{
                        player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.PLAYERISINTEAM.getAsString());
                    }
                    break;
                case "info":
                    if(GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString()) != null){
                        Team team = GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString());
                        Player owner = Bukkit.getPlayer(UUID.fromString(team.getOwner()));
                        player.sendMessage(GunGame.getInstance().getPrefix() +"§7§m§l-------------------------------------");
                        player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMINFOOWNER.getAsString().replaceAll("%OWNER%", owner.getDisplayName()));
                        for(String uuid : team.getMembers()){
                            Player member = Bukkit.getPlayer(UUID.fromString(uuid));
                            player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMINFOMEMBER.getAsString().replaceAll("%MEMBER%", member.getDisplayName()));
                        }
                        player.sendMessage(GunGame.getInstance().getPrefix() +"§7§m§l-------------------------------------");
                    }else {
                        player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.PLAYERNOTEAM.getAsString());
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
                                Bukkit.getPlayer(UUID.fromString(newOwner)).sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMNEWOWNER.getAsString());
                                GunGame.getInstance().getTeamHandler().getTeams().remove(player.getUniqueId().toString());
                                GunGame.getInstance().getTeamHandler().getTeams().put(newOwner, team);
                            }else{
                                GunGame.getInstance().getTeamHandler().getTeams().remove(player.getUniqueId().toString());
                                player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMDELETE.getAsString());
                                return true;
                            }
                        }else{
                            team.getMembers().remove(player.getUniqueId().toString());
                        }

                        for(String uuid : team.getMembers()){
                            Player member = Bukkit.getPlayer(UUID.fromString(uuid));
                            member.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMLEAVEMEMBER.getAsString().replaceAll("%PLAYER%", player.getDisplayName()));
                        }
                        Player owner = Bukkit.getPlayer(UUID.fromString(team.getOwner()));
                        owner.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMLEAVEMEMBER.getAsString().replaceAll("%PLAYER%", player.getDisplayName()));
                        player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMLEAVEPLAYER.getAsString().replaceAll("%OWNER%", owner.getDisplayName()));
                    }else {
                        player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.PLAYERNOTEAM.getAsString());
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
                                        toKick.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMKICKPLAYER.getAsString().replaceAll("%OWNER%", player.getDisplayName()));
                                        for(String uuid : team.getMembers()){
                                            Player member = Bukkit.getPlayer(UUID.fromString(uuid));
                                            member.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMLEAVEMEMBER.getAsString().replaceAll("%PLAYER%", toKick.getDisplayName()));
                                        }
                                        Player owner = Bukkit.getPlayer(UUID.fromString(team.getOwner()));
                                        owner.sendMessage(GunGame.getInstance().getPrefix() +  ConfigEntries.TEAMLEAVEMEMBER.getAsString().replaceAll("%PLAYER%", toKick.getDisplayName()));
                                    }else{
                                        player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMPLAYERNOTATTEAM.getAsString().replaceAll("%PLAYER%", toKick.getDisplayName()));
                                    }
                                }else{
                                    player.sendMessage(GunGame.getInstance().getPlayerNotOnline());
                                }
                            } else {
                                player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMNOPERMTOKICK.getAsString());
                            }
                        } else {
                            player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.PLAYERNOTEAM.getAsString());
                        }
                    }else {
                        player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMKICKHELP.getAsString());
                    }
                    break;
                default:
                    for(String line : ConfigEntries.TEAMHELP.getAsString().split("\n")){
                        player.sendMessage(GunGame.getInstance().getPrefix() + line);
                    }
                    break;
            }

        }else sender.sendMessage(GunGame.getInstance().getMustAPlayer());
        return true;
    }
}
