package de.byteevolve.gungame.arena;

import de.byteevolve.gungame.GunGame;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ArenaHandler dient zum verwalten aller Maps im GunGame System.
 * @author RoyalByte | Adrian Schiel
 * @since 0.1
 * @version 1.0
 * @see Arena
 */
public class ArenaHandler {

    private List<Arena> arenas;
    private Map<Player, Arena> arenaPlayerCreate;

    /**
     * Konstrukter für die Klasse {@link ArenaHandler}
     */
    public ArenaHandler() {
        this.arenas = new ArrayList<>();
        this.arenaPlayerCreate = new HashMap<>();
        loadArenas();
    }

    /**
     * Gibt die Arena Liste wieder.
     * In dieser werden alle Arenen des Systems registriert
     * @return Liste aller Arenen
     */
    public List<Arena> getArenas() {
        return arenas;
    }

    /**
     * Lädt alle Arenen aus der SQL Datenbank aus, und schreibt sie in die Arena Liste
     * @return Wahrheitswert, ob das laden geklappt hat
     */
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


    /**
     * Gibt eine Arena anhand ihres Namens in der Arena Liste wieder {@link Arena}
     * @param name Der abzufragende Name
     * @return Gesuchte Arena
     */
    public Arena getArenaByName(String name){
        for(Arena arena : arenas){
            if(arena.getName().equalsIgnoreCase(name)) return arena;
        }
        return null;
    }

    /**
     * Fragt ab ob es eine Arena mit einem bestimmten Namen gibt, welcher ein Spieler erstellt.
     * @param name Der abzufragende Name
     * @return Wahrheitswert, ob diese Arena gerade von einem Spieler erstellt wird.
     */
    public boolean existArenaPlayerCreateByName(String name) {
        for (Player player : this.arenaPlayerCreate.keySet()){
            if(this.arenaPlayerCreate.get(player).getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    /**
     * Gibt die Map ArenaPlayerCreate wieder
     * @return Map<Player, Arena>
     */
    public Map<Player, Arena> getArenaPlayerCreate() {
        return arenaPlayerCreate;
    }

    /**
     * Fragt ab ob es eine Arena mit einem bestimmten Namen gibt.
     * @param name Der abzufragende Name
     * @return Wahrheitswert, ob diese Arena vorhanden ist.
     */
    public boolean existArenaByName(String name){
        for(Arena arena : this.arenas){
            if(arena.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }
}
