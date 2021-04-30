package model;

import java.util.ArrayList;

// A class representing all players as well as enemies
public class Actor {
    public int speed;
    public int totalhealth;
    public int health;
    public String resistance;
    public int defense;
    public int innatedamage;
    public int agility;
    public boolean isPlayer;
    public ArrayList<String> abilities;
    public ArrayList<String> attackSequence;

    // MODIFIES: this
    // EFFECTS: constructs a new actor object
    public Actor() {
        isPlayer = false;
        attackSequence = new ArrayList<>();
        attackSequence.add("Begin Fight");
    }
}
