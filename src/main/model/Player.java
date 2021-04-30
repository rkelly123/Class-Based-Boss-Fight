package model;

import java.util.ArrayList;
import java.io.PrintWriter;
import persistance.*;

// A class representing an actor that is a player
public class Player extends Actor implements Saveable {
    public int level;
    public String loadout;
    public ArrayList<HealthPotion> healthpots;

    // MODIFIES: this
    // EFFECTS: constructs a new player object
    public Player() {
        level = 1;
        isPlayer = true;
        abilities = new ArrayList<>();
        healthpots = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds 10 total health to Player
    public void addTotalHealth() {
        totalhealth = totalhealth + 10;
        health = health + 10;
    }

    // MODIFIES: this
    // EFFECTS: adds 3 total speed to Player
    public void addSpeed() {
        speed = speed + 3;
    }

    // MODIFIES: this
    // EFFECTS: adds 3 total defense to Player
    public void addDefense() {
        defense = defense + 1;
    }

    // MODIFIES: this
    // EFFECTS: adds 1 innate damage to Player
    public void addInnateDamage() {
        innatedamage = innatedamage + 1;
    }

    // MODIFIES: this
    // EFFECTS: adds 1 agility to Player
    public void addAgility() {
        agility = agility + 1;
    }

    // EFFECTS: displays innate ability description
    public String passiveDesc() {
        return "An undefined player has no innate ability.";
    }

    // MODIFIES: this
    // EFFECTS: give the player a health potion
    public void addHealthPotion() {
        healthpots.add(new HealthPotion());
    }

    // EFFECTS: saves the character
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(loadout);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(level);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(abilities.get(1));
        printWriter.print(Reader.DELIMITER);
        printWriter.print(abilities.get(2));
        printWriter.print(Reader.DELIMITER);
        printWriter.print(speed);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(totalhealth);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(defense);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(innatedamage);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(agility);
        printWriter.print(Reader.DELIMITER);
        printWriter.println(health);
    }

    public int doAbility(String str) {
        return -1;
    }
}

