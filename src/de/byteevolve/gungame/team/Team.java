package de.byteevolve.gungame.team;

import java.util.ArrayList;
import java.util.List;

public class Team {

    String owner;
    List<String> members, invites;

    public Team(String owner) {
        this.owner = owner;
        this.members = new ArrayList<>();
        this.invites = new ArrayList<>();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<String> getInvites() {
        return invites;
    }

    public void setInvites(List<String> invites) {
        this.invites = invites;
    }
}
