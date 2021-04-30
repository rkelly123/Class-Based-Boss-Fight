package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// A test class for the Berserker class
class BerserkerTest {
    private Berserker testBerserker;

    @BeforeEach
    void runBefore() {
        testBerserker = new Berserker();
    }

    @Test
    void testBerserker() {
        assertEquals(40, testBerserker.speed);
        assertEquals(120, testBerserker.totalhealth);
        assertEquals("Weapon", testBerserker.resistance);
        assertEquals(5, testBerserker.defense);
        assertEquals(2, testBerserker.innatedamage);
        assertEquals(5, testBerserker.agility);
        assertEquals("berserker", testBerserker.loadout);
        assertTrue(testBerserker.abilities.contains("Rage"));
    }

    @Test
    void testPassive() {
        assertEquals(0, testBerserker.passive());
    }

    @Test
    void testPassiveDesc() {
        assertEquals("Rage. Every time you attack an opponent, your innate damage increases by 1."
                , testBerserker.passiveDesc());
    }

    @Test
    void testBludgeon() {
        assertEquals(0, testBerserker.bludgeon());
        testBerserker.abilities.add("Bludgeon");
        assertEquals(1, testBerserker.bludgeon());
        testBerserker.attackSequence.add(0, "Bludgeon");
        assertEquals(0, testBerserker.bludgeon());
    }

    @Test
    void testBludgeonDesc() {
        assertEquals("Flip your weapon upside down and strike an enemy"
                        + " with the hilt. \nThis adds 5 damage to your character!",
                testBerserker.bludgeonDesc());
    }

    @Test
    void testBleed() {
        assertEquals(0, testBerserker.bleed());
        testBerserker.abilities.add("Bleed");
        assertEquals(1, testBerserker.bleed());
        testBerserker.attackSequence.add(0, "Bleed");
        assertEquals(0, testBerserker.bleed());
    }

    @Test
    void testBleedDesc() {
        assertEquals("Your opponent begins to bleed which slows them down.", testBerserker.bleedDesc());
    }

    @Test
    void testSkullCrusher() {
        assertEquals(0, testBerserker.skullCrusher());
        testBerserker.abilities.add("Skull Crusher");
        testBerserker.health = 39;
        assertEquals(1, testBerserker.skullCrusher());
        testBerserker.attackSequence.add("Skull Crusher");
        assertEquals(0, testBerserker.skullCrusher());
    }

    @Test
    void testSkullCrusherDesc() {
        assertEquals("If below 40 health, gain 40 damage\nThis may be used once per"
                + " combat.", testBerserker.skullCrusherDesc());
    }

    @Test
    void testDoAbility() {
        assertEquals(1, testBerserker.doAbility("Passive"));
        assertEquals(2, testBerserker.doAbility("Bludgeon"));
        assertEquals(3, testBerserker.doAbility("Bleed"));
        assertEquals(4, testBerserker.doAbility("Skull Crusher"));
        assertEquals(-1, testBerserker.doAbility("Hello"));

    }
}