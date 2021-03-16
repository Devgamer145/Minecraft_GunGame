package de.byteevolve.gungame.configuration.config;

import de.byteevolve.gungame.configuration.ConfigEntry;

public enum ConfigEntries implements ConfigEntry {

    MYSQL_HOST(ConfigSections.MYSQL, "host", "localhost", "localhost", "Hostname deiner MySQl Datenbank");

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
