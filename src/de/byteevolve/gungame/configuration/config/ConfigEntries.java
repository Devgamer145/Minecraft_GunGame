package de.byteevolve.gungame.configuration.config;

import de.byteevolve.gungame.configuration.ConfigEntry;

public enum ConfigEntries implements ConfigEntry {

    //DEFAULT
    PREFIX(ConfigSections.MESSAGES, "prefix", "&8[&aGun&2Game&8]", "&8[&aGun&2Game&8]", "Text vor jeder Nachricht."),
    NOPERM(ConfigSections.MESSAGES, "noperm", "&7Du bist nicht berechtigt dieses Kommando zu verwenden.", "&7Du bist nicht berechtigt dieses Kommando zu verwenden.", "Nachricht wenn ein Spieler nicht die benötigten Rechte hat."),
    MUSTAPLAYER(ConfigSections.MESSAGES, "mustaplayer", "&7Du musst ein &aSpieler &7sein!", "&7Du musst ein &aSpieler &7sein!", "Nachricht an Entities, welche keine Spieler sind."),
    PLAYERNOTONLINE(ConfigSections.MESSAGES, "playernotonline", "&7Der Angegebene Spieler konnte nicht gefunden werden.", "&7Der Angegebene Spieler konnte nicht gefunden werden.", "Nachricht wenn ein Spieler nicht online ist."),

    //ARENA COMMAND
    PLAYERCREATESARENA(ConfigSections.MESSAGES, "playercreatesarena", "&cDu erstellst schon eine Arena", "&cDu erstellst schon eine Arena", "Nachricht wenn ein Spieler eine Arena erstellen will, aber schon eine erstellt"),
    ARENAEXISTS(ConfigSections.MESSAGES, "arenaexists", "&7Die Arena:&a %ARENANAME% &7gibt es schon.", "&7Die Arena:&a %ARENANAME% &7gibt es schon.", "Nachricht wenn ein Spieler eine Arena erstellen will, welche schon vorhanden ist. \n%ARENANAME% = Platzhalter für den Arenanamen"),
    PLAYERCREATEARENA(ConfigSections.MESSAGES, "playercreatearena", "&7Du erstellst nun die Arena:&a %ARENANAME%&7. (&2/arena edit&7)", "&7Du erstellst nun die Arena:&a %ARENANAME%&7. (&2/arena edit&7)", "Nachricht wenn ein Spieler eine Arena erstellt. \n%ARENANAME% = Platzhalter für den Arenaname"),
    ARENANOTEXISTS(ConfigSections.MESSAGES, "arenanotexists",  "&7Die Arena:&a %ARENANAME% &7gibt es nicht.", "&7Die Arena:&a %ARENANAME% &7gibt es nicht.", "Nachricht wenn es eine Arena nicht gibt. \n%ARENANAME% = Platzhalter für den Arenanamen"),
    PLAYERNOTCREATESARENA(ConfigSections.MESSAGES, "playernotcreatesarena", "&7Du erstellst gerade keine Arena. (&2/arena create Name&7)", "&7Du erstellst gerade keine Arena. (&2/arena create Name&7)", "Nachricht wenn ein Spieler eine Arena mit /arena edit bearbeiten will, aber keine erstellt."),
    ARENAHELP(ConfigSections.MESSAGES, "arenahelp", "&7Nutze &c/arena &7<&ccreate&7,&cedit&7> &7<&cName&7> &7<&cDisplayname&7>", "&7Nutze &c/arena &7<&ccreate&7,&cedit&7> &7<&cName&7> &7<&cDisplayname&7>", "Hilfe Nachricht zum erstellen einer Arena"),
    NOARENAEXISTS(ConfigSections.MESSAGES, "noarenaexists","&cEs wurde noch keine Arena erstellt.", "&cEs wurde noch keine Arena erstellt.", "Nachricht an einen Spieler, wenn keine Arena gefunden wurde."),

    //BUILD COMMAND
    BUILDON(ConfigSections.MESSAGES, "buildon", "&7Du bist nun im &aBuild-Modus&7.","&7Du bist nun im &aBuild-Modus&7.", "Nachricht an einen Spieler, welcher in den Build Modus geht."),
    BUILDOFF(ConfigSections.MESSAGES, "buildoff", "&7Du bist nun &cnicht &7mehr im &aBuild-Modus&7.","&7Du bist nun &cnicht &7mehr im &aBuild-Modus&7.", "Nachricht an einen Spieler, welcher aus dem Build Modus geht."),


    //MYSQL
    MYSQL_HOST(ConfigSections.MYSQL, "host", "localhost", "localhost", "Hostname deiner MySQl Datenbank"),
    MYSQL_PASSWORD(ConfigSections.MYSQL, "password", "password", "password", "Passwort deiner MySQl Datenbank"),
    MYSQL_DATABASE(ConfigSections.MYSQL, "database", "gungame", "gungame", "Zu benutzende Datenbank von MySQl"),
    MYSQL_PORT(ConfigSections.MYSQL, "port", 3306, 3306, "Port deiner MySQl Datenbank"),
    MYSQL_USERNAME(ConfigSections.MYSQL, "username", "root", "root", "Username deiner MySQl Datenbank");



    private ConfigSections section;
    private String path,desc;
    private Object value, defaultValue;
    ConfigEntries(ConfigSections section, String path, Object value, Object defaultValue, String desc){
        this.section = section;
        this.path = path;
        this.value = value;
        this.defaultValue = defaultValue;
        this.desc = desc;
    }

    public int getAsInt(){
        return (int) getValue();
    }

    public String getAsString(){
        return getValue().toString().replaceAll("&", "§");
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public Object getDefaultValue() {
        return this.defaultValue;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public ConfigSections getSection() {
        return this.section;
    }

    @Override
    public String getDescription() {
        return this.desc;
    }
}
