package de.byteevolve.gungame.game;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.arena.Arena;
import de.byteevolve.gungame.kit.Kit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

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
            @Override
            public void run() {

            }
        }.runTaskTimerAsynchronously(GunGame.getInstance(), 0, 20);
    }
}
