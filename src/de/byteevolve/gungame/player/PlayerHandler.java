package de.byteevolve.gungame.player;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.arena.Arena;
import de.byteevolve.gungame.arena.ArenaState;
import de.byteevolve.gungame.arena.ArenaTeamState;
import de.byteevolve.gungame.itembuilder.ItemBuilder;
import de.byteevolve.gungame.player.actionbar.*;
import de.byteevolve.gungame.player.respawn.*;
import de.byteevolve.gungame.player.scoreboard.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public class  PlayerHandler {

    private final Player player;

    public PlayerHandler(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void respawnPlayer() {
        GunGame.getInstance().getRespawn().respawn(this.player);
    }

    public void sendActionBar(String text) {
        GunGame.getInstance().getActionbar().send(this.player, text);
    }

    public void sendScoreBoard() {
        GunGame.getInstance().getScoreboard().sendScoreboard(this.player);
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
