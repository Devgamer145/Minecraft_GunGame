package de.byteevolve.gungame.player;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.arena.Arena;
import de.byteevolve.gungame.arena.ArenaState;
import de.byteevolve.gungame.arena.ArenaTeamState;
import de.byteevolve.gungame.configuration.config.ConfigEntries;
import de.byteevolve.gungame.itembuilder.ItemBuilder;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerHandler {

    private final Player player;

    public PlayerHandler(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void sendActionBar(String text) {
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\"}"), (byte)2);
        sendPacket(packet);
    }

    public void sendScoreBoard() {
        Scoreboard sb = new Scoreboard();
        ScoreboardObjective obj = sb.registerObjective(ConfigEntries.SCOREBOARDNAME.getAsString(), IScoreboardCriteria.b);
        PacketPlayOutScoreboardObjective createpacket = new PacketPlayOutScoreboardObjective(obj, 0);
        PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
        obj.setDisplayName("GUNGAME");

        PlayerStats playerStats = new PlayerStats(this.player.getUniqueId().toString());

        String map;
        if(GunGame.getInstance().getGameHandler().getCurrent() != null) {
            map = GunGame.getInstance().getGameHandler().getCurrent().getDisplayname().replaceAll("&", "§");
        } else map = "-";

        PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);
        sendPacket(removePacket);
        sendPacket(createpacket);
        sendPacket(display);

        int i = 16;
        for(String line : ConfigEntries.SCOREBOARD.getAsString().split("\n")){
            line = line.replaceAll("%MAP%", map);
            line = line.replaceAll("%RECORD%", String.valueOf(playerStats.get(PlayerStatsType.HIGHSCORE)));
            line = line.replaceAll("%KILLS%", String.valueOf(playerStats.get(PlayerStatsType.KILLS)));
            line = line.replaceAll("%DEATHS%", String.valueOf(playerStats.get(PlayerStatsType.DEATHS)));
            line = line.replaceAll("%RANK%", String.valueOf(playerStats.getRank()));
            line = line.replaceAll("%KD%", String.valueOf(playerStats.getKD()));


            ScoreboardScore score = new ScoreboardScore(sb, obj, line);
            score.setScore(i);

            PacketPlayOutScoreboardScore packet= new PacketPlayOutScoreboardScore(score);
            sendPacket(packet);

            i--;
        }


    }

    public void sendPacket(Packet<?> packet) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public void openArenaEditMainInv(Arena arena){
        Inventory inventory = Bukkit.createInventory(null, 3*9, "§8Arena: §a" + arena.getName());
        player.openInventory(inventory);

        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if(i<8){
                    player.playSound(player.getLocation(), Sound.GLASS, 100, 100);
                }
                switch (i){
                    case 0:
                        inventory.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        inventory.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        inventory.setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        break;
                    case 1:
                        inventory.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        inventory.setItem(10, new ItemBuilder(Material.MAGMA_CREAM, 1).setName("§7Spawn setzen").build());
                        inventory.setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        break;
                    case 2:
                        inventory.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        inventory.setItem(11, new ItemBuilder(Material.BARRIER, 1).setName("§aObere-SpawnProt setzen").build());
                        inventory.setItem(20, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        break;
                    case 3:
                        inventory.setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        inventory.setItem(12, new ItemBuilder(Material.BARRIER, 1).setName("§aUntere-SpawnProt setzen").build());
                        inventory.setItem(21, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        break;
                    case 4:
                        inventory.setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        inventory.setItem(13, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        inventory.setItem(22, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        break;
                    case 5:
                        if(arena.getArenaTeamState() == null) arena.setArenaTeamState(ArenaTeamState.ALLOWED);
                        inventory.setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        inventory.setItem(14, new ItemBuilder(Material.ARMOR_STAND, 1).setName("§aTeamsState §7setzen").addLore("§7State: §a" + arena.getArenaTeamState().toString()).build());
                        inventory.setItem(23, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        break;
                    case 6:
                        inventory.setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        ArenaState arenaState = arena.getArenaStateFromInt(arena.getFinished());
                        inventory.setItem(15, new ItemBuilder(Material.BEACON, 1).setSubId(7).setName("§aStatus").addLore("§7Status: " + arenaState.getPrefix() + arenaState.toString()).build());
                        inventory.setItem(24, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        break;
                    case 7:
                        inventory.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        inventory.setItem(16, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        inventory.setItem(25, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        break;
                    case 8:
                        inventory.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        inventory.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
                        inventory.setItem(26, new ItemBuilder(Material.INK_SACK, 1).setSubId(10).setName("§aBeenden").build());
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                        cancel();
                        break;
                }

                i++;
            }
        }.runTaskTimerAsynchronously(GunGame.getInstance(), 0, 1);
    }
}
