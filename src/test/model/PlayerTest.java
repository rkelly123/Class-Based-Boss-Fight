package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

// A test class for the Player class
class PlayerTest {
    private Player testPlayer;
    private Player actualPlayer;

    @BeforeEach
    void runBefore() {
        testPlayer = new Hunter();
        actualPlayer = new Player();
    }

    @Test
    void testPlayer() {
        assertEquals(1, testPlayer.level);
        assertTrue(testPlayer.isPlayer);
        assertEquals(0, actualPlayer.abilities.size());
        assertEquals(0, testPlayer.healthpots.size());
    }

    @Test
    void testAddTotalHealth() {
        testPlayer.addTotalHealth();
        assertEquals(90, testPlayer.totalhealth);
    }

    @Test
    void testAddSpeed() {
        testPlayer.addSpeed();
        assertEquals(53, testPlayer.speed);
    }

    @Test
    void testAddDefense() {
        testPlayer.addDefense();
        assertEquals(3, testPlayer.defense);
    }

    @Test
    void testAddInnateDamage() {
        testPlayer.addInnateDamage();
        assertEquals(6, testPlayer.innatedamage);
    }

    @Test
    void testAddAgility() {
        testPlayer.addAgility();
        assertEquals(21, testPlayer.agility);
    }

    @Test
    void testPassiveDesc() {
        assertEquals("An undefined player has no innate ability.", actualPlayer.passiveDesc());
    }

    @Test
    void testAddHealthPotion() {
        assertEquals(0, testPlayer.healthpots.size());
        testPlayer.addHealthPotion();
        assertEquals(1, testPlayer.healthpots.size());
    }

    @Test
    void testDoAbility() {
        assertEquals(-1, actualPlayer.doAbility("Hello"));
    }
}

