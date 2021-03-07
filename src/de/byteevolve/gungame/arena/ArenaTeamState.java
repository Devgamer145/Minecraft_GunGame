package de.byteevolve.gungame.arena;

public enum ArenaTeamState {

    ALLOWED("§aTeams erlaubt"),
    DISALLOWED("§cTeams verboten");

    private String prefix;

    public String getPrefix() {
        return prefix;
    }

    ArenaTeamState(String prefix) {
        this.prefix = prefix;
    }
}
