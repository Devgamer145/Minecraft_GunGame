package de.byteevolve.gungame.player;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.arena.Arena;
import de.byteevolve.gungame.arena.ArenaState;
import de.byteevolve.gungame.arena.ArenaTeamState;
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
        ScoreboardObjective obj = sb.registerObjective("§aGun§2Game", IScoreboardCriteria.b);
        PacketPlayOutScoreboardObjective createpacket = new PacketPlayOutScoreboardObjective(obj, 0);
        PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
        obj.setDisplayName("LOBBY");

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        PlayerStats playerStats = new PlayerStats(this.player.getUniqueId().toString());

        String map;
        if(GunGame.getInstance().getGameHandler().getCurrent() != null) {
            map = GunGame.getInstance().getGameHandler().getCurrent().getDisplayname().replaceAll("&", "§");
        } else map = "-";

        ScoreboardScore a = new ScoreboardScore(sb, obj, "§8§M§l------------" + "§2");
        ScoreboardScore a2 = new ScoreboardScore(sb, obj, "§6✦ §8▎ §7Map");
        ScoreboardScore a3 = new ScoreboardScore(sb, obj, " §8➥ §7" + map+ "§a");
        ScoreboardScore a4 = new ScoreboardScore(sb, obj, "§8  ");
        ScoreboardScore a8 = new ScoreboardScore(sb, obj, "§c✪ §8▎ §7Rekord");
        ScoreboardScore a9 = new ScoreboardScore(sb, obj, " §8➥ §7" + playerStats.get(PlayerStatsType.HIGHSCORE) + "§c");
        ScoreboardScore a10 = new ScoreboardScore(sb, obj, " " + "§d");
        ScoreboardScore a11 = new ScoreboardScore(sb, obj, "§c❤ §8▎ §7Kills");
        ScoreboardScore a12 = new ScoreboardScore(sb, obj, " §8➥ §7" + playerStats.get(PlayerStatsType.KILLS));
        ScoreboardScore a13 = new ScoreboardScore(sb, obj, " " + "§1");
        ScoreboardScore a14 = new ScoreboardScore(sb, obj, "§c✿ §8▎ §7Rang");
        ScoreboardScore a15 = new ScoreboardScore(sb, obj, " §8➥ §7#" + playerStats.getRank());
        ScoreboardScore a19 = new ScoreboardScore(sb, obj, "§8§M§l------------" + "§3");

        a.setScore(17);
        a2.setScore(16);
        a3.setScore(15);
        a4.setScore(14);
        a8.setScore(10);
        a9.setScore(9);
        a10.setScore(8);
        a11.setScore(7);
        a12.setScore(6);
        a13.setScore(5);
        a14.setScore(4);
        a15.setScore(3);
        a19.setScore(2);

        PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);
        PacketPlayOutScoreboardScore pa1 = new PacketPlayOutScoreboardScore(a);
        PacketPlayOutScoreboardScore pa2 = new PacketPlayOutScoreboardScore(a2);
        PacketPlayOutScoreboardScore pa3 = new PacketPlayOutScoreboardScore(a3);
        PacketPlayOutScoreboardScore pa4 = new PacketPlayOutScoreboardScore(a4);
        PacketPlayOutScoreboardScore pa8 = new PacketPlayOutScoreboardScore(a8);
        PacketPlayOutScoreboardScore pa9 = new PacketPlayOutScoreboardScore(a9);
        PacketPlayOutScoreboardScore pa10 = new PacketPlayOutScoreboardScore(a10);
        PacketPlayOutScoreboardScore pa11 = new PacketPlayOutScoreboardScore(a11);
        PacketPlayOutScoreboardScore pa12 = new PacketPlayOutScoreboardScore(a12);
        PacketPlayOutScoreboardScore pa19 = new PacketPlayOutScoreboardScore(a19);
        PacketPlayOutScoreboardScore pa13 = new PacketPlayOutScoreboardScore(a13);
        PacketPlayOutScoreboardScore pa14 = new PacketPlayOutScoreboardScore(a14);
        PacketPlayOutScoreboardScore pa15 = new PacketPlayOutScoreboardScore(a15);

        sendPacket(removePacket);
        sendPacket(createpacket);
        sendPacket(display);
        sendPacket(pa1);
        sendPacket(pa2);
        sendPacket(pa3);
        sendPacket(pa4);
        sendPacket(pa8);
        sendPacket(pa9);
        sendPacket(pa10);
        sendPacket(pa11);
        sendPacket(pa12);
        sendPacket(pa19);
        sendPacket(pa13);
        sendPacket(pa14);
        sendPacket(pa15);

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
