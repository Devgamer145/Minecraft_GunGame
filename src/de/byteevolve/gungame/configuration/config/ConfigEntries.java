package de.byteevolve.gungame.configuration.config;

import de.byteevolve.gungame.configuration.ConfigEntry;

public enum ConfigEntries implements ConfigEntry {

    PREFIX(ConfigSections.MESSAGES, "prefix", "&8[&aGun&2Game&8]", "&8[&aGun&2Game&8]", "Text vor jeder Nachricht."),
    NOPERM(ConfigSections.MESSAGES, "noperm", "&7Du bist nicht berechtigt dieses Kommando zu verwenden.", "&7Du bist nicht berechtigt dieses Kommando zu verwenden.", "Nachricht wenn ein Spieler nicht die benötigten Rechte hat."),
    MUSTAPLAYER(ConfigSections.MESSAGES, "mustaplayer", "&7Du musst ein &aSpieler &7sein!", "&7Du musst ein &aSpieler &7sein!", "Nachricht an Entities, welche keine Spieler sind."),
    PLAYERNOTONLINE(ConfigSections.MESSAGES, "playernotonline", "&7Der Angegebene Spieler konnte nicht gefunden werden.", "&7Der Angegebene Spieler konnte nicht gefunden werden.", "Nachricht wenn ein Spieler nicht online ist."),

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
