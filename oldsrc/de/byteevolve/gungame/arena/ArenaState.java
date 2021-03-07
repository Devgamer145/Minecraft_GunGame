package de.byteevolve.gungame.arena;

public enum ArenaState {

    FINISHED("§a"),
    UNFINISHED("§c");

    private final String prefix;

    public String getPrefix() {
        return prefix;
    }

    ArenaState(String prefix){
        this.prefix = prefix;
    }


}
