package de.byteevolve.gungame.configuration;

import de.byteevolve.gungame.configuration.config.ConfigSections;

public interface ConfigEntry {

    String getPath();
    Object getValue();
    Object getDefaultValue();
    void setValue(Object value);
    ConfigSections getSection();
    String getDescription();


}
