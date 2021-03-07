package de.byteevolve.gungame.arena;

import de.byteevolve.gungame.GunGame;
import org.bukkit.entity.Player;

import java.awt.geom.Area;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArenaHandler {

    private List<Arena> arenas;
    private Map<Player, Arena> arenaPlayerCreate;

    public ArenaHandler() {
        this.arenas = new ArrayList<>();
        this.arenaPlayerCreate = new HashMap<>();
        loadArenas();
    }

    public List<Arena> getArenas() {
        return arenas;
    }

    public boolean loadArenas() {
        this.arenas.clear();
        ResultSet resultSet = GunGame.getInstance().getMySQL().getResult("SELECT * FROM gg_arena;");
        try {
            while (resultSet.next()) {
                Arena arena = new Arena(resultSet.getString("NAME"));
                arena.setDisplayname(resultSet.getString("DISPLAYNAME"));
                arena.setSpawn(resultSet.getString("SPAWN"));
                arena.setMaxSpawn(resultSet.getString("MAXSPAWN"));
                arena.setMinSpawn(resultSet.getString("MINSPAWN"));
                arena.setFinished(resultSet.getInt(("FINISHED")));
                arena.setArenaTeamState(ArenaTeamState.valueOf(resultSet.getString("TEAMSTATE")));
                this.arenas.add(arena);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Arena getArenaByName(String name){
        for(Arena arena : arenas){
            if(arena.getName().equalsIgnoreCase(name)) return arena;
        }
        return null;
    }

    public boolean existArenaPlayerCreateByName(String name) {
        for (Player player : this.arenaPlayerCreate.keySet()){
            if(this.arenaPlayerCreate.get(player).getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }


    public Map<Player, Arena> getArenaPlayerCreate() {
        return arenaPlayerCreate;
    }

    public boolean existArenaByName(String name){
        for(Arena arena : this.arenas){
            if(arena.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }
}
