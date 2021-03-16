package de.byteevolve.gungame.configuration;

import de.byteevolve.gungame.configuration.config.ConfigEntries;

import java.util.List;

public interface ConfigSection {

    String getName();
    List<ConfigEntries> getEntries();
    ConfigEntry getEntry(String name);
    String getDescription();

}
