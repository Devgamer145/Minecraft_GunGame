package de.byteevolve.gungame;
import com.sun.xml.internal.bind.v2.model.annotation.AbstractInlineAnnotationReaderImpl;
import de.byteevolve.gungame.arena.Arena;
import de.byteevolve.gungame.arena.ArenaHandler;
import de.byteevolve.gungame.arena.ArenaTeamState;
import de.byteevolve.gungame.commands.Command_arena;
import de.byteevolve.gungame.commands.Command_build;
import de.byteevolve.gungame.database.MySQL;
import de.byteevolve.gungame.game.GameHandler;
import de.byteevolve.gungame.kit.Kit;
import de.byteevolve.gungame.listener.Listener_ArenaEdit;
import de.byteevolve.gungame.listener.Listener_Build;
import de.byteevolve.gungame.listener.Listener_Game;
import de.byteevolve.gungame.listener.Listener_Join;
import de.byteevolve.gungame.location.LocationHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GunGame extends JavaPlugin {

    private static GunGame instance;
    private MySQL mySQL;
    private GameHandler gameHandler;
    private LocationHandler locationHandler;
    private ArenaHandler arenaHandler;
    private String prefix,noPerm, mustAPlayer,playerNotOnline;
    private List<UUID> build;


    @Override
    public void onEnable() {
        instance = this;

        this.prefix = "§8▎ §aGun§2Game §8× §7";
        this.noPerm = this.prefix + "§7Du bist nicht berechtigt dieses Kommando zu verwenden.";
        this.mustAPlayer = this.prefix + "§7Du musst ein §bSpieler §7sein!";
        this.playerNotOnline = this.prefix + "§7Der Angegebene Spieler konnte nicht gefunden werden.";

        this.mySQL = new MySQL("localhost", "root", "", "GunGame", 3306);
        this.locationHandler = new LocationHandler();
        this.arenaHandler = new ArenaHandler();
        this.gameHandler = new GameHandler();
        this.gameHandler.startGameTimer();
        this.build = new ArrayList<>();

        getCommand("arena").setExecutor(new Command_arena());
        getCommand("build").setExecutor(new Command_build());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new Listener_ArenaEdit(), this);
        pluginManager.registerEvents(new Listener_Join(), this);
        pluginManager.registerEvents(new Listener_Build(), this);
        pluginManager.registerEvents(new Listener_Game(), this);

        for(Player player : Bukkit.getOnlinePlayers()){
            if(GunGame.getInstance().getGameHandler().getCurrent() != null){
                player.teleport(GunGame.getInstance().getLocationHandler().getLocByName(GunGame.getInstance().getGameHandler().getCurrent().getSpawn()).getAsLocation());
            }else{
                player.sendMessage(GunGame.getInstance().getPrefix() + "§cEs wurde noch keine Arena erstellt.");
            }
                GunGame.getInstance().getGameHandler().getPlayerkits().put(player, Kit.LEVEL_0);
                GunGame.getInstance().getGameHandler().getPlayerkits().get(player).getKitInventory().load(player);
        }

    }

    public List<UUID> getBuild() {
        return build;
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public ArenaHandler getArenaHandler() {
        return arenaHandler;
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public LocationHandler getLocationHandler() {
        return locationHandler;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getNoPerm() {
        return noPerm;
    }

    public String getMustAPlayer() {
        return mustAPlayer;
    }

    public String getPlayerNotOnline() {
        return playerNotOnline;
    }

    public static GunGame getInstance() {
        return instance;
    }
}
