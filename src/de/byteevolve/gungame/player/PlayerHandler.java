package de.byteevolve.gungame.player;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.arena.Arena;
import de.byteevolve.gungame.arena.ArenaState;
import de.byteevolve.gungame.arena.ArenaTeamState;
import de.byteevolve.gungame.itembuilder.ItemBuilder;
import de.byteevolve.gungame.player.actionbar.*;
import de.byteevolve.gungame.player.scoreboard.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;


public class PlayerHandler {

    private final Player player;

    public PlayerHandler(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void sendActionBar(String text) {

        GGActionbar ggActionbar = null;
        String version = "N/A";
        try{
            version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

            switch (version) {
                case "v1_8_R3":
                    ggActionbar = new v1_8_R3_Actionbar();
                    break;
                case "v1_9_R2":
                    ggActionbar = new v1_9_R2_Actionbar();
                    break;
                case "v1_10_R1":
                    ggActionbar = new v1_10_R1_Actionbar();
                    break;
                case "v1_11_R1":
                    ggActionbar = new v1_11_R1_Actionbar();
                    break;
                case "v1_12_R1":
                    ggActionbar = new v1_12_R1_Actionbar();
                    break;
                case "v1_13_R2":
                    ggActionbar = new v1_13_R2_Actionbar();
                    break;
                case "v1_14_R1":
                    ggActionbar = new v1_14_R1_Actionbar();
                    break;
                case "v1_15_R1":
                    ggActionbar = new v1_15_R1_Actionbar();
                    break;
                case "v1_16_R3":
                    ggActionbar = new v1_16_R3_Actionbar();
                    break;
            }

            ggActionbar.send(this.player, text);

        }catch (ArrayIndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public void sendScoreBoard() {
        GGScoreboard ggScoreboard = null;
        String version = "N/A";
        try{
            version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

            switch (version) {
                case "v1_8_R3":
                    ggScoreboard = new v1_8_R3_Scoreboard();
                    break;
                case "v1_9_R2":
                    ggScoreboard = new v1_9_R2_Scoreboard();
                    break;
                case "v1_10_R1":
                    ggScoreboard = new v1_10_R1_Scoreboard();
                    break;
                case "v1_11_R1":
                    ggScoreboard = new v1_11_R1_Scoreboard();
                    break;
                case "v1_12_R1":
                    ggScoreboard = new v1_12_R1_Scoreboard();
                    break;
                case "v1_13_R2":
                    ggScoreboard = new v1_13_R2_Scoreboard();
                    break;
                case "v1_14_R1":
                    ggScoreboard = new v1_14_R1_GunGame();
                    break;
                case "v1_15_R1":
                    ggScoreboard = new v1_15_R1_Scoreboard();
                    break;
                case "v1_16_R3":
                    ggScoreboard = new v1_16_R3_Scoreboard();
                    break;
            }

            ggScoreboard.sendScoreboard(this.player);

        }catch (ArrayIndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }



    public void openArenaEditMainInv(Arena arena){
        Inventory inventory = Bukkit.createInventory(null, 3*9, "§8Arena: §a" + arena.getName());
        player.openInventory(inventory);

        for (int i = 0; i < inventory.getSize() ; i++) {
            inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
        }
        inventory.setItem(10, new ItemBuilder(Material.MAGMA_CREAM, 1).setName("§7Spawn setzen").build());
        inventory.setItem(11, new ItemBuilder(Material.BARRIER, 1).setName("§aObere-SpawnProt setzen").build());
        inventory.setItem(12, new ItemBuilder(Material.BARRIER, 1).setName("§aUntere-SpawnProt setzen").build());

        if(arena.getArenaTeamState() == null) arena.setArenaTeamState(ArenaTeamState.ALLOWED);
        inventory.setItem(14, new ItemBuilder(Material.ARMOR_STAND, 1).setName("§aTeamsState §7setzen").addLore("§7State: §a" + arena.getArenaTeamState().toString()).build());
        ArenaState arenaState = arena.getArenaStateFromInt(arena.getFinished());
        inventory.setItem(15, new ItemBuilder(Material.BEACON, 1).setSubId(7).setName("§aStatus").addLore("§7Status: " + arenaState.getPrefix() + arenaState.toString()).build());
        inventory.setItem(26, new ItemBuilder(Material.INK_SACK, 1).setSubId(10).setName("§aBeenden").build());

    }
}
