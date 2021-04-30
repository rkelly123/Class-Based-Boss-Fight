package model;

import ui.GraphicGame;

import java.util.ArrayList;

// A class representing a player who has chosen the berserker class
public class Berserker extends Player {
    public boolean usedSkullCrusher;
    public static final String BERSERKER_ABILITY_1 = "Bludgeon";
    public static final String BERSERKER_ABILITY_2 = "Bleed";
    public static final String BERSERKER_ABILITY_3 = "Skull Crusher";

    // MODIFIES: this
    // EFFECTS: constructs a new berserker object
    public Berserker() {
        speed = 40;
        totalhealth = 120;
        health = 120;
        resistance = "Weapon";
        defense = 5;
        innatedamage = 2;
        agility = 5;
        loadout = "berserker";
        abilities.add("Rage");
        usedSkullCrusher = false;
    }

    // EFFECTS: increases damage for each attack
    public int passive() {
        innatedamage += attackSequence.size();
        System.out.println("You just gained " + attackSequence.size() + " damage!");
        return 0;
    }

    // EFFECTS: displays innate ability description
    @Override
    public String passiveDesc() {
        return "Rage. Every time you attack an opponent, your innate damage increases by 1.";
    }

    // REQUIRES: attacksequence has a size of at least 1
    // MODIFIES: enemy actor, this
    // EFFECTS: Adds 5 to innate damage
    public int bludgeon() {
        if (abilities.contains(BERSERKER_ABILITY_1) && !attackSequence.get(0).equals(BERSERKER_ABILITY_1)) {
            innatedamage = innatedamage + 5;
            System.out.println("You gained 5 damage");
            return 1;
        }
        return 0;
    }

    // EFFECTS: displays bludgeon ability description
    public String bludgeonDesc() {
        return "Flip your weapon upside down and strike an enemy"
                + " with the hilt. \nThis adds 5 damage to your character!";
    }

    // REQUIRES: attacksequence has a size of at least 1
    // MODIFIES: target of spell, enemy actor
    // EFFECTS: makes next attack have a chance to cause a bleeding effect
    public int bleed() {
        if (abilities.contains(BERSERKER_ABILITY_2) && !attackSequence.get(0).equals(BERSERKER_ABILITY_2)) {
            speed = speed + 10;
            System.out.println("Your opponent is bleeding an is now slower due to the pain.");
            return 1;
        }
        return 0;
    }

    // EFFECTS: displays bleed ability description
    public String bleedDesc() {
        return "Your opponent begins to bleed which slows them down.";
    }

    // MODIFIES: this and target actor
    // EFFECTS: If below 40 hit points, gain 40 damage.
    public int skullCrusher() {
        if (abilities.contains(BERSERKER_ABILITY_3) && !attackSequence.contains(BERSERKER_ABILITY_3)
                 && health < 40) {
            innatedamage = innatedamage + 40;
            System.out.println("You gained 40 damage!");
            return 1;
        }
        return 0;
    }

    // EFFECTS: displays skull crusher ability description
    public String skullCrusherDesc() {
        return "If below 40 health, gain 40 damage\nThis may be used once per"
                + " combat.";
    }

    // EFFECTS: uses specified ability
    @Override
    public int doAbility(String str) {
        if (str.equals("Passive")) {
            passive();
            return 1;
        } else if (str.equals(BERSERKER_ABILITY_1)) {
            bludgeon();
            return 2;
        } else if (str.equals(BERSERKER_ABILITY_2)) {
            bleed();
            return 3;
        } else if (str.equals(BERSERKER_ABILITY_3)) {
            skullCrusher();
            return 4;
        } else {
            return -1;
        }
    }
}
