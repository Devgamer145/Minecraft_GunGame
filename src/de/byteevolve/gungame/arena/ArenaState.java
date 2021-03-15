package de.byteevolve.gungame.arena;


/**
 * Available ArenaStates
 * {@link #FINISHED}
 * {@link #UNFINISHED}
 */
public enum ArenaState {

    /**
     * FINISHED
     */
    FINISHED("§a"),

    /**
     * UNFINISHED
     */
    UNFINISHED("§c");

    private final String prefix;

    /**
     * @return Prefix from ArenaState
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Construkter from ArenaStates
     * @param prefix from ArenaState
     */
    ArenaState(String prefix){
        this.prefix = prefix;
    }


}
