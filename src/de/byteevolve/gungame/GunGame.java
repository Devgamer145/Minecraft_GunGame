package de.byteevolve.gungame;

import de.byteevolve.gungame.arena.ArenaHandler;
import de.byteevolve.gungame.bstats.Metrics;
import de.byteevolve.gungame.commands.Command_Stats;
import de.byteevolve.gungame.commands.Command_Team;
import de.byteevolve.gungame.commands.Command_arena;
import de.byteevolve.gungame.commands.Command_build;
import de.byteevolve.gungame.configuration.ConfigHandler;
import de.byteevolve.gungame.configuration.config.ConfigEntries;
import de.byteevolve.gungame.database.MySQL;
import de.byteevolve.gungame.game.GameHandler;
import de.byteevolve.gungame.itembuilder.unbreakable.*;
import de.byteevolve.gungame.kit.Kit;
import de.byteevolve.gungame.listener.*;
import de.byteevolve.gungame.location.LocationHandler;
import de.byteevolve.gungame.player.actionbar.*;
import de.byteevolve.gungame.player.respawn.*;
import de.byteevolve.gungame.player.scoreboard.*;
import de.byteevolve.gungame.team.TeamHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class GunGame extends JavaPlugin {

    private static GunGame instance;
    private MySQL mySQL;
    private GameHandler gameHandler;
    private LocationHandler locationHandler;
    private ArenaHandler arenaHandler;
    private TeamHandler teamHandler;
    private String prefix,noPerm, mustAPlayer,playerNotOnline;
    private List<UUID> build;
    private ConfigHandler configHandler;
    private GGRespawn respawn;
    private GGActionbar actionbar;
    private GGScoreboard scoreboard;
    private Unbreakable unbreakable;


    @Override
    public void onEnable() {
        instance = this;
        new Metrics(this, 11293);

        this.configHandler = new ConfigHandler();
        this.prefix = ConfigEntries.PREFIX.getAsString();
        this.noPerm = this.prefix + ConfigEntries.NOPERM.getAsString();
        this.mustAPlayer = this.prefix + ConfigEntries.MUSTAPLAYER.getAsString();
        this.playerNotOnline = this.prefix + ConfigEntries.PLAYERNOTONLINE.getAsString();
        this.mySQL = new MySQL(ConfigEntries.MYSQL_HOST.getAsString(), ConfigEntries.MYSQL_USERNAME.getAsString(),
                ConfigEntries.MYSQL_PASSWORD.getAsString(), ConfigEntries.MYSQL_DATABASE.getAsString(),
                ConfigEntries.MYSQL_PORT.getAsInt());
        if(this.mySQL.isConnected()) {
            this.locationHandler = new LocationHandler();
            this.arenaHandler = new ArenaHandler();
            this.gameHandler = new GameHandler();
            this.teamHandler = new TeamHandler();
            this.build = new ArrayList<>();

            this.loadVersions();

            if (!this.arenaHandler.getArenas().isEmpty()) {
                this.gameHandler.startGameTimer();
            }

            try {
                String siteVersion = new Scanner(new URL("https://byte-evolve.de/royalbyte/gungameversion.html").openStream(), "UTF-8").useDelimiter("\\A").next();
                if (!getDescription().getVersion().equalsIgnoreCase(siteVersion)) {
                    Bukkit.broadcastMessage(GunGame.getInstance().getPrefix() + "§4§k-------------------------------------------------");
                    Bukkit.broadcastMessage(GunGame.getInstance().getPrefix() + "§cVersion: §b" + getDescription().getVersion() + " §8[§4Veraltet§8]");
                    Bukkit.broadcastMessage(GunGame.getInstance().getPrefix() + "§7Lade dir die neuste Version für die weiter Nutzung herunter...");
                    Bukkit.broadcastMessage(GunGame.getInstance().getPrefix() + "§a§lhttps://byte-evolve.de/kategorien/gungame/");
                    Bukkit.broadcastMessage(GunGame.getInstance().getPrefix() + "§4§k-------------------------------------------------");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            getCommand("arena").setExecutor(new Command_arena());
            getCommand("build").setExecutor(new Command_build());
            getCommand("team").setExecutor(new Command_Team());
            getCommand("stats").setExecutor(new Command_Stats());

            PluginManager pluginManager = Bukkit.getPluginManager();
            pluginManager.registerEvents(new Listener_ArenaEdit(), this);
            pluginManager.registerEvents(new Listener_Join(), this);
            pluginManager.registerEvents(new Listener_Build(), this);
            pluginManager.registerEvents(new Listener_Game(), this);
            pluginManager.registerEvents(new Listener_Quit(), this);

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (GunGame.getInstance().getGameHandler().getCurrent() != null) {
                    player.teleport(GunGame.getInstance().getLocationHandler().getLocByName(GunGame.getInstance().getGameHandler().getCurrent().getSpawn()).getAsLocation());
                } else {
                    player.sendMessage(getPrefix() + ConfigEntries.NOARENAEXISTS.getAsString());
                }
                GunGame.getInstance().getGameHandler().getPlayerkits().put(player, Kit.LEVEL_0);
                GunGame.getInstance().getGameHandler().getPlayerkits().get(player).getKitInventory().load(player);
            }
        }
    }

    private void loadVersions(){
        String version = "N/A";
        try{
            version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

            switch (version) {
                case "v1_8_R3":
                    this.actionbar = new v1_8_R3_Actionbar();
                    this.scoreboard = new v1_8_R3_Scoreboard();
                    this.respawn = new v1_8_R3_Respawn();
                    this.unbreakable = new v1_8_R3_Unbreakable();
                    break;
                case "v1_9_R2":
                    this.actionbar = new v1_9_R2_Actionbar();
                    this.scoreboard = new v1_9_R2_Scoreboard();
                    this.respawn = new v1_9_R2_Respawn();
                    this.unbreakable = new v1_9_R2_Unbreakable();
                    break;
                case "v1_10_R1":
                    this.actionbar = new v1_10_R1_Actionbar();
                    this.scoreboard = new v1_10_R1_Scoreboard();
                    this.respawn = new v1_10_R1_Respawn();
                    this.unbreakable = new v1_10_R1_Unbreakable();
                    break;
                case "v1_11_R1":
                    this.actionbar = new v1_11_R1_Actionbar();
                    this.scoreboard = new v1_11_R1_Scoreboard();
                    this.respawn = new v1_11_R1_Respawn();
                    this.unbreakable = new v1_11_R1_Unbreakable();
                    break;
                case "v1_12_R1":
                    this.actionbar = new v1_12_R1_Actionbar();
                    this.scoreboard = new v1_12_R1_Scoreboard();
                    this.respawn = new v1_12_R1_Respawn();
                    this.unbreakable = new v1_12_R1_Unbreakable();
                    break;
                case "v1_13_R2":
                    this.actionbar = new v1_13_R2_Actionbar();
                    this.scoreboard = new v1_13_R2_Scoreboard();
                    this.respawn = new v1_13_R2_Respawn();
                    this.unbreakable = new v1_13_R2_Unbreakable();
                    break;
                case "v1_14_R1":
                    this.actionbar = new v1_14_R1_Actionbar();
                    this.scoreboard = new v1_14_R1_Scoreboard();
                    this.respawn = new v1_14_R1_Respawn();
                    this.unbreakable = new v1_14_R1_Unbreakable();
                    break;
                case "v1_15_R1":
                    this.actionbar = new v1_15_R1_Actionbar();
                    this.scoreboard = new v1_15_R1_Scoreboard();
                    this.respawn = new v1_15_R1_Respawn();
                    this.unbreakable = new v1_15_R1_Unbreakable();
                    break;
                case "v1_16_R3":
                    this.actionbar = new v1_16_R3_Actionbar();
                    this.scoreboard = new v1_16_R3_Scoreboard();
                    this.respawn = new v1_16_R3_Respawn();
                    this.unbreakable = new v1_16_R3_Unbreakable();
                    break;
            }
        }catch (ArrayIndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        if(this.mySQL != null) {
            if (this.mySQL.isConnected()) this.mySQL.close();
        }
    }

    public GGRespawn getRespawn() {
        return respawn;
    }

    public GGActionbar getActionbar() {
        return actionbar;
    }

    public GGScoreboard getScoreboard() {
        return scoreboard;
    }

    public Unbreakable getUnbreakable() {
        return unbreakable;
    }

    public TeamHandler getTeamHandler() {
        return teamHandler;
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
