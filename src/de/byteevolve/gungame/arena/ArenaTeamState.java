package de.byteevolve.gungame.arena;


/**
 * Available ArenaTeamStates
 * {@link #ALLOWED}
 * {@link #DISALLOWED}
 */
public enum ArenaTeamState {

    /**
     * ALLOWED
     */
    ALLOWED("§aTeams erlaubt"),

    /**
     * DISALLOWED
     */
    DISALLOWED("§cTeams verboten");

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
