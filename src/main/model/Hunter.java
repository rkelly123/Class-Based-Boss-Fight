package model;

import java.util.ArrayList;

// A class representing a player who has chosen the hunter class
public class Hunter extends Player {
    public static final String HUNTER_ABILITY_1 = "Head Shot";
    public static final String HUNTER_ABILITY_2 = "Haste";
    public static final String HUNTER_ABILITY_3 = "Evade";

    // MODIFIES: this
    // EFFECTS: constructs a new hunter object
    public Hunter() {
        speed = 50;
        totalhealth = 80;
        health = 80;
        resistance = "earth";
        defense = 2;
        innatedamage = 5;
        agility = 20;
        loadout = "hunter";
        abilities.add("Hunter's Mark");
    }

    // MODIFIES: this
    // EFFECTS: mark an enemy, causing it to take double damage
    public int passive() {
        if (!attackSequence.contains("Passive")) {
            innatedamage = 2 * innatedamage;
            System.out.println("Doubled your damage!");
            return 1;
        }
        return 0;
    }

    // EFFECTS: displays innate ability description
    @Override
    public String passiveDesc() {
        return "Hunter's Mark. Mark the enemy in the battle with the most health. \nMarked enemies take double damage.";
    }

    // MODIFIES: target of spell, enemy actor
    // EFFECTS: Add 5 damage to player
    public int headShot() {
        if (abilities.contains(HUNTER_ABILITY_1)
                && (attackSequence.indexOf(HUNTER_ABILITY_1) > 2 || attackSequence.indexOf(HUNTER_ABILITY_1) == -1)) {
            innatedamage = innatedamage + 5;
            System.out.println("Increased your damage by 5!");
            return 1;
        }
        return 0;
    }

    // EFFECTS: displays head shot ability description
    public String headShotDesc() {
        return "Head shot an enemy, gaining 5 damage. \nThis has a 3 turn cooldown.";
    }

    // MODIFIES: this
    // EFFECTS: gain 10 speed
    public int haste() {
        if (abilities.contains(HUNTER_ABILITY_2)) {
            speed = speed + 10;
            System.out.println("Increased your speed by 10!");
            return 1;
        }
        return 0;
    }

    // EFFECTS: displays haste ability description
    public String hasteDesc() {
        return "Gain 10 speed.";
    }

    // MODIFIES: this
    // EFFECTS: Make yourself untargettable momentarily
    public int evade() {
        if (abilities.contains(HUNTER_ABILITY_3) && attackSequence.indexOf(HUNTER_ABILITY_3) == -1) {
            agility = agility + 35;
            System.out.println("Increased your agility by 35!");
            return 1;
        }
        return 0;
    }

    // EFFECTS: displays evade ability description
    public String evadeDesc() {
        return "Drastically increase your agility. \nThis can be used once per combat.";
    }

    // EFFECTS: uses specified ability
    @Override
    public int doAbility(String str) {
        if (str.equals("Passive")) {
            passive();
            return 1;
        } else if (str.equals(HUNTER_ABILITY_1)) {
            headShot();
            return 2;
        } else if (str.equals(HUNTER_ABILITY_2)) {
            haste();
            return 3;
        } else if (str.equals(HUNTER_ABILITY_3)) {
            evade();
            return 4;
        } else {
            return -1;
        }
    }
}
