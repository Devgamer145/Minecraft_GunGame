package de.byteevolve.gungame.arena;


import de.byteevolve.gungame.configuration.config.ConfigEntries;

/**
 * Available ArenaTeamStates
 * {@link #ALLOWED}
 * {@link #DISALLOWED}
 */
public enum ArenaTeamState {

    /**
     * ALLOWED
     */
    ALLOWED(ConfigEntries.TEAMSTATEALLOWED.getAsString()),

    /**
     * DISALLOWED
     */
    DISALLOWED(ConfigEntries.TEAMSTATEDISALLOWED.getAsString());

    private String prefix;

    /**
     * @return Prefix from ArenaTeamState
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Construkter from ArenaTeamState
     * @param prefix from ArenaTeamState
     */
    ArenaTeamState(String prefix) {
        this.prefix = prefix;
    }
}
