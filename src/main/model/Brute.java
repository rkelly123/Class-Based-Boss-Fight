package model;

import java.util.ArrayList;

// A class representing a player who has chosen the brute class
public class Brute extends Player {
    public static final String BRUTE_ABILITY_1 = "Body Slam";
    public static final String BRUTE_ABILITY_2 = "Juggernaut";
    public static final String BRUTE_ABILITY_3 = "Knockout Punch";

    // MODIFIES: this
    // EFFECTS: constructs a new brute object
    public Brute() {
        speed = 15;
        totalhealth = 200;
        health = 200;
        resistance = "Weapon";
        defense = 15;
        innatedamage = 5;
        agility = 2;
        loadout = "brute";
        abilities.add("Tank");
    }

    // MODIFIES: this
    // EFFECTS: double your health in return for half of your speed and damage
    public int passive() {
        health = health * 2;
        totalhealth = totalhealth * 2;
        speed = speed / 2;
        innatedamage = innatedamage / 2;
        System.out.println("You now have " + health + " health, " + speed + " speed, and " + innatedamage + " damage.");
        return 0;
    }

    // EFFECTS: displays innate ability description
    @Override
    public String passiveDesc() {
        return "Tank. Double your health at the cost of half of your speed and damage.";
    }

    // MODIFIES: target of spell, enemy actor
    // EFFECTS: Use your heartiness to increase your damage
    public int bodySlam() {
        if (abilities.contains(BRUTE_ABILITY_1)) {
            innatedamage = innatedamage + (int) (0.05 * health);
            System.out.println("Your damage was increased by " + 0.05 * health);
            return 1;
        }
        return 0;
    }

    // EFFECTS: displays body slam ability description
    public String bodySlamDesc() {
        return "Increase your damage by 5 percent of your health.\nThis has a 3 turn cooldown.";
    }

    // MODIFIES: this
    // EFFECTS: gain 3 defense
    public int juggernaut() {
        if (abilities.contains(BRUTE_ABILITY_2) && (attackSequence.indexOf(BRUTE_ABILITY_2) > 2
                || attackSequence.indexOf(BRUTE_ABILITY_2) == -1)) {
            defense = defense + 5;
            System.out.println("You gained 3 defense!");
            return 1;
        }
        return 0;
    }

    // EFFECTS: displays juggernaut ability description
    public String juggernautDesc() {
        return "Brace for impact and increase defense by 5. \nIt has a 3 turn cooldown";
    }

    // REQUIRES: attacksequence has a size of at least 1
    // MODIFIES: this
    // EFFECTS: adds 3 to your damage
    public int knockoutPunch() {
        if (abilities.contains(BRUTE_ABILITY_3) && attackSequence.indexOf(BRUTE_ABILITY_3) != 0) {
            innatedamage = innatedamage + 3;
            System.out.println("Added 3 to your damage!");
            return 1;
        }
        return 0;
    }

    // EFFECTS: displays knockout punch ability description
    public String knockoutPunchDesc() {
        return "Adds 3 to your damage. \nIt has a 1 turn cooldown.";
    }

    // EFFECTS: uses specified ability
    @Override
    public int doAbility(String str) {
        if (str.equals("Passive")) {
            passive();
            return 1;
        } else if (str.equals(BRUTE_ABILITY_1)) {
            bodySlam();
            return 2;
        } else if (str.equals(BRUTE_ABILITY_2)) {
            juggernaut();
            return 3;
        } else if (str.equals(BRUTE_ABILITY_3)) {
            knockoutPunch();
            return 4;
        } else {
            return -1;
        }
    }
}
