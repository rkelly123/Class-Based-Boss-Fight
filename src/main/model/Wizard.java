package model;

import java.util.ArrayList;

// A class representing a player who has chosen the wizard class
public class Wizard extends Player {
    public static final String WIZARD_ABILITY_1 = "Fire Ball";
    public static final String WIZARD_ABILITY_2 = "Polymorph";
    public static final String WIZARD_ABILITY_3 = "Healing Word";

    // MODIFIES: this
    // EFFECTS: constructs a new wizard object
    public Wizard() {
        speed = 25;
        totalhealth = 80;
        health = 80;
        resistance = null;
        defense = 2;
        innatedamage = 10;
        agility = 10;
        loadout = "wizard";
        abilities.add("Ethereal Power");
    }

    // EFFECTS: displays innate ability description
    @Override
    public String passiveDesc() {
        return "Ethereal Power. All of your damage ignores opponent's defense unless they have resistance to magic";
    }

    // MODIFIES: target of the spell, enemy actor
    // EFFECTS: switch to using fireballs and increase damage
    public int fireBall() {
        if (abilities.contains(WIZARD_ABILITY_1)
                && (attackSequence.indexOf(WIZARD_ABILITY_1) > 2 || attackSequence.indexOf(WIZARD_ABILITY_1) == -1)) {
            innatedamage = innatedamage + 40;
            System.out.println("You gained 40 damage!");
            return 1;
        }
        return 0;
    }

    // EFFECTS: display fire ball description
    public String fireBallDesc() {
        return "Cast 3 Fire Balls. \nThis makes you gain 40 damage. It has a 3 turn cooldown.";
    }

    // MODIFIES: target of the spell, enemy actor
    // EFFECTS: Increases your health by 100
    public int polymorph() {
        if (abilities.contains(WIZARD_ABILITY_2) && attackSequence.indexOf(WIZARD_ABILITY_2) == -1) {
            totalhealth = totalhealth + 100;
            health = health + 100;
            System.out.println("You gained 100 health and turned into a giant beast!");
            return 1;
        }
        return 0;
    }

    // EFFECTS: display polymorph description
    public String polymorphDesc() {
        return "Transform yourself into a giant beast, gaining 100 health. This can be used once.";
    }

    // MODIFIES: this
    // EFFECTS: heal your character
    public int healingWord() {
        if (abilities.contains(WIZARD_ABILITY_3)
                && (attackSequence.indexOf(WIZARD_ABILITY_3) > 1 || attackSequence.indexOf(WIZARD_ABILITY_3) == -1)) {
            health = health + 20;
            System.out.println("You healed for 20 health!");
            return 1;
        }
        return 0;
    }

    // EFFECTS: displays healing word description
    public String healingWordDesc() {
        return "Heal yourself for 20 health. \nThis has a 2 turn cooldown.";
    }

    // EFFECTS: uses specified ability
    @Override
    public int doAbility(String str) {
        if (str.equals(WIZARD_ABILITY_1)) {
            fireBall();
            return 1;
        } else if (str.equals(WIZARD_ABILITY_2)) {
            polymorph();
            return 2;
        } else if (str.equals(WIZARD_ABILITY_3)) {
            healingWord();
            return 3;
        } else {
            return -1;
        }
    }
}