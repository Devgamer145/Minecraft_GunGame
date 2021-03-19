package de.byteevolve.gungame.game;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.arena.Arena;
import de.byteevolve.gungame.arena.ArenaTeamState;
import de.byteevolve.gungame.configuration.config.ConfigEntries;
import de.byteevolve.gungame.kit.Kit;
import de.byteevolve.gungame.player.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;

public class GameHandler {

    private Arena current;
    private Map<Player, Kit> playerkits;

    public GameHandler(){
        if(!GunGame.getInstance().getArenaHandler().getArenas().isEmpty()){
            this.current = GunGame.getInstance().getArenaHandler().getArenas().get(0);
        }else this.current = null;
        this.playerkits = new HashMap<>();
    }
    public Kit getKitFromID(int id){
        if(id >= Kit.LEVEL_60.getId()) return Kit.LEVEL_60;
        for(Kit kit : Kit.values()){
            if(kit.getId() == id) return kit;
        }
        return null;
    }

    public Map<Player, Kit> getPlayerkits() {
        return playerkits;
    }

    public void setPlayerkits(Map<Player, Kit> playerkits) {
        this.playerkits = playerkits;
    }

    public Arena getCurrent() {
        return current;
    }

    public void setCurrent(Arena current) {
        this.current = current;
    }

    public void startGameTimer() {
        new BukkitRunnable() {
            int i = ConfigEntries.MAPCHANGECOUNTER.getAsInt() * 60;
            int arena = 0;
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()){
                    new PlayerHandler(player).sendActionBar("§7Mapchange in§8: §a" + i + "-Sekunden §8§l︳ §7" + getCurrent().getArenaTeamState().getPrefix());
                }
                i--;
                switch (i){
                    case 300: case 120: case 60: case 30: case 15: case 10:
                        Bukkit.broadcastMessage(GunGame.getInstance().getPrefix() + ConfigEntries.MAPCHANGETIMER.getAsString().replaceAll("%SECONDS%", String.valueOf(i)));
                        break;
                    case 5: case 4: case 3: case 2:
                        Bukkit.broadcastMessage(GunGame.getInstance().getPrefix() + ConfigEntries.MAPCHANGETIMER.getAsString().replaceAll("%SECONDS%", String.valueOf(i)));
                        for(Player player : Bukkit.getOnlinePlayers()){
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        }
                        break;
                    case 1:
                            if(GunGame.getInstance().getArenaHandler().getArenas().size() > 1){
                                int newArena = arena +1;
                                if(GunGame.getInstance().getArenaHandler().getArenas().size()<= newArena){
                                    newArena = 0;
                                }

                                setCurrent(GunGame.getInstance().getArenaHandler().getArenas().get(newArena));
                                arena = newArena;

                                for(Player player : Bukkit.getOnlinePlayers()){
                                    if(GunGame.getInstance().getTeamHandler().inTeam(player.getUniqueId().toString())!= null) {
                                        if (getCurrent().getArenaTeamState().equals(ArenaTeamState.DISALLOWED)) {
                                            player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.TEAMDELETE.getAsString());
                                        }
                                    }
                                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                                    player.sendMessage(GunGame.getInstance().getPrefix() + ConfigEntries.MAPCHANGE.getAsString().replaceAll("%MAP%", getCurrent().getDisplayname().replaceAll("&", "§")));
                                    new PlayerHandler(player).sendScoreBoard();
                                    GunGame.getInstance().getGameHandler().getPlayerkits().put(player, Kit.LEVEL_0);
                                    GunGame.getInstance().getGameHandler().getPlayerkits().get(player).getKitInventory().load(player);
                                    player.teleport(GunGame.getInstance().getLocationHandler().getLocByName(getCurrent().getSpawn()).getAsLocation());
                                    player.setLevel(0);
                                }
                                GunGame.getInstance().getTeamHandler().getTeams().clear();
                            }
                            i = 30 * 60;
                        break;

                }
            }
        }.runTaskTimerAsynchronously(GunGame.getInstance(), 0, 20);
    }
}
