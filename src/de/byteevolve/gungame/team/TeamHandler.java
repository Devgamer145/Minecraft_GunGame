package de.byteevolve.gungame.team;

import java.util.HashMap;
import java.util.Map;

public class TeamHandler {

    private Map<String, Team> teams;

    public TeamHandler() {
        this.teams = new HashMap<>();
    }

    public Team inTeam(String uuid){
        for(Team team : teams.values()){
            if(team.getOwner().equals(uuid)) return team;
            if(team.getMembers().contains(uuid)) return team;
        }
        return null;
    }

    /**
     *
     * @param uuid
     * @return
     */
    public Team hasInvite(String uuid){
        for(Team team : teams.values()){
            if(team.getInvites().contains(uuid)) return team;
        }
        return null;
    }

    public Map<String, Team> getTeams() {
        return teams;
    }

    public void setTeams(Map<String, Team> teams) {
        this.teams = teams;
    }
}
