package de.byteevolve.gungame.location;

import de.byteevolve.gungame.GunGame;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Loc {

    private String name, world;
    private double x,y,z;
    private float yaw, pitch;

    public Loc(String name) {
        this.name = name;
    }

    public boolean exists(){
        return GunGame.getInstance().getLocationHandler().existByName(this.name);
    }

    private void save() {
        GunGame.getInstance().getMySQL().update("INSERT INTO gg_locs VALUES " +
                "(NULL, '" +getName()+"', '" +getX()+"', '"+ getY() +"', '" +getZ()+"', '"
                +getYaw() +"', '" +getPitch()+ "', '" +getWorld() + "')");
    }

    public void setFromLocation(Location location){
        setWorld(location.getWorld().getName());
        setPitch(location.getPitch());
        setYaw(location.getYaw());
        setY(location.getY());
        setX(location.getX());
        setZ(location.getZ());
    }

    public boolean delete(){
        if (GunGame.getInstance().getLocationHandler().existByName(this.name)) {
            GunGame.getInstance().getMySQL().update("DELETE FROM gg_locs WHERE NAME='" +getName()+ "';");
            return true;
        }
        return false;
    }

    public void update() {
        if (GunGame.getInstance().getLocationHandler().existByName(this.name)) {
            GunGame.getInstance().getMySQL().update("UPDATE gg_locs SET gg_locs.NAME='" + getName() + "'," +
                    "gg_locs.Y='" +getY()+"', gg_locs.Z='" + getZ()+ "', gg_locs.YAW='" + getYaw()+ "'," +
                    "gg_locs.PITCH='" +getPitch()+ "', gg_locs.WORLD='" +getWorld()+ "' WHERE gg_locs.NAME='" +getName() +"';");
            GunGame.getInstance().getLocationHandler().loadLocs();
        } else {
            GunGame.getInstance().getMySQL().update("INSERT INTO gg_locs VALUES " +
                    "(NULL, '" +getName()+"', '" +getX()+"', '"+ getY() +"', '" +getZ()+"', '"
                    +getYaw() +"', '" +getPitch()+ "', '" +getWorld() + "')");

            GunGame.getInstance().getLocationHandler().loadLocs();
        }


    }


    public Location getAsLocation(){
        if(exists()){
            return new Location(Bukkit.getWorld(getWorld()), getX(), getY(),getZ(), getYaw(),getPitch());
        }else return null;
    }

    public String getName() {
        return name;
    }

    public Loc setName(String name) {
        this.name = name;
        return this;
    }

    public String getWorld() {
        return world;
    }

    public Loc setWorld(String world) {
        this.world = world;
        return this;
    }

    public double getX() {
        return x;
    }

    public Loc setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public Loc setY(double y) {
        this.y = y;
        return this;
    }

    public double getZ() {
        return z;
    }

    public Loc setZ(double z) {
        this.z = z;
        return this;
    }

    public float getYaw() {
        return yaw;
    }

    public Loc setYaw(float yaw) {
        this.yaw = yaw;
        return this;
    }

    public float getPitch() {
        return pitch;
    }

    public Loc setPitch(float pitch) {
        this.pitch = pitch;
        return this;
    }
}
