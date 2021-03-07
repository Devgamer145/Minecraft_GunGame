package de.byteevolve.gungame.arena;

import de.byteevolve.gungame.GunGame;
import org.bukkit.Location;

import java.awt.geom.Area;

public class Arena {

    private String name, spawn, minSpawn, maxSpawn, displayname;
    private ArenaTeamState arenaTeamState;
    private int finished;

    public Arena(String name) {
        this.name = name;
    }

    public void update(){
        if(getArenaTeamState() == null) setArenaTeamState(ArenaTeamState.DISALLOWED);
        if(getSpawn() == null) setSpawn("");
        if(getMinSpawn() == null) setMinSpawn("");
        if(getMaxSpawn() == null) setMaxSpawn("");
        if(getDisplayname() == null) setDisplayname("");
        if (GunGame.getInstance().getArenaHandler().existArenaByName(getName())) {
            GunGame.getInstance().getMySQL().update("UPDATE gg_arena SET " +
                    "DISPLAYNAME='" +getDisplayname() +"', SPAWN='" + getSpawn()+ "',"+
                    "MAXSPAWN='" + getMaxSpawn()+"', MINSPAWN='" + getMinSpawn()+"',"+
                    "TEAMSTATE='" +getArenaTeamState().toString()+ "' FINISHED='" + getFinished() +"' WHERE NAME='" + getName()+"';");
            GunGame.getInstance().getArenaHandler().loadArenas();
        } else {
            GunGame.getInstance().getMySQL().update("INSERT INTO gg_arena VALUES('" +getName() +"', '" + getDisplayname()+"','" +
                   getSpawn() +"', '" +getMaxSpawn() +"', '" + getMinSpawn() +"', '" + getArenaTeamState().toString()+"', '1');");
            GunGame.getInstance().getArenaHandler().loadArenas();
        }
    }


    public boolean atSpawn(Location location){
        Location min = GunGame.getInstance().getLocationHandler().getLocByName(this.minSpawn).getAsLocation();
        Location max = GunGame.getInstance().getLocationHandler().getLocByName(this.maxSpawn).getAsLocation();
        if(min.getWorld().getName() == max.getWorld().getName()){
            if(location.getWorld().getName() == min.getWorld().getName()){
                if((location.getBlockX() >= min.getBlockX() && location.getBlockX() <= max.getBlockX()) || (location.getBlockX() <= min.getBlockX() && location.getBlockX() >= max.getBlockX())){
                    if((location.getBlockZ() >= min.getBlockZ() && location.getBlockZ() <= max.getBlockZ()) || (location.getBlockZ() <= min.getBlockZ() && location.getBlockZ() >= max.getBlockZ())){
                        return true;
                    }
                }
            }
        }
        return false;
    }



    public ArenaState getArenaStateFromInt(int state){
        switch (state){
            case 1:
                return ArenaState.FINISHED;
            default:
                return ArenaState.UNFINISHED;
        }
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public ArenaTeamState getArenaTeamState() {
        return arenaTeamState;
    }

    public void setArenaTeamState(ArenaTeamState arenaTeamState) {
        this.arenaTeamState = arenaTeamState;
    }

    public String getSpawn() {
        return spawn;
    }

    public void setSpawn(String spawn) {
        this.spawn = spawn;
    }

    public String getMinSpawn() {
        return minSpawn;
    }

    public void setMinSpawn(String minSpawn) {
        this.minSpawn = minSpawn;
    }

    public String getMaxSpawn() {
        return maxSpawn;
    }

    public void setMaxSpawn(String maxSpawn) {
        this.maxSpawn = maxSpawn;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
