package de.byteevolve.gungame.player;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.database.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerStats {

    private String uuid;

    public PlayerStats(String uuid) {
        this.uuid = uuid;
        this.create();
    }

    private boolean exists() {
        try {
            GunGame gunGame = GunGame.getInstance();
            MySQL mySQL = gunGame.getMySQL();
            ResultSet resultSet = mySQL.getResult("SELECT * FROM gg_stats WHERE gg_stats.UUID='" + getUUID() +"';");
            if (resultSet.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void create() {
        if(!exists()) {
                GunGame gunGame = GunGame.getInstance();
                MySQL mySQL = gunGame.getMySQL();
                mySQL.update("INSERT INTO gg_stats VALUES('" +getUUID() +"', '"+ getName() +"',"
                        + "'0', '0', '0');");
        }
    }

    public Integer get(PlayerStatsType type) {

        try {
            GunGame gunGame = GunGame.getInstance();
            MySQL mySQL = gunGame.getMySQL();
            ResultSet resultSet = mySQL.getResult("SELECT " +type.toString() +" FROM gg_stats WHERE gg_stats.UUID='" + getUUID() +"';");
            if(resultSet.next()) return resultSet.getInt(type.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }
    public void add(PlayerStatsType type, int value) {
        set(type ,get(type) + value);
    }
    public void remove(PlayerStatsType type, int value) {
        set(type ,get(type) - value);
    }

    public void set(PlayerStatsType type, int value) {
        if(exists()) {
                GunGame gunGame = GunGame.getInstance();
                MySQL mySQL = gunGame.getMySQL();
                mySQL.update("UPDATE gg_stats SET " + type.toString() + "='" + value+ "' WHERE UUID='" + getUUID() + "';");
        }else create();
    }


    private String getName() {
        return Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName();
    }
    private String getUUID() {
        return this.uuid;
    }


}
