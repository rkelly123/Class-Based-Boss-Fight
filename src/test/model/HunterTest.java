package model;

import com.sun.javafx.scene.control.skin.LabeledImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// A test class for the Hunter class
class HunterTest {
    private Hunter testHunter;

    @BeforeEach
    void runBefore() {
        testHunter = new Hunter();
    }

    @Test
    void testHunter() {
        assertEquals(50, testHunter.speed);
        assertEquals(80, testHunter.totalhealth);
        assertEquals("earth", testHunter.resistance);
        assertEquals(2, testHunter.defense);
        assertEquals(5, testHunter.innatedamage);
        assertEquals(20, testHunter.agility);
        assertEquals("hunter", testHunter.loadout);
        assertTrue(testHunter.abilities.contains("Hunter's Mark"));
    }

    @Test
    void testPassive() {
        assertEquals(1, testHunter.passive());
        testHunter.attackSequence.add("Passive");
        assertEquals(0, testHunter.passive());
    }

    @Test
    void testPassiveDesc() {
        assertEquals("Hunter's Mark. Mark the enemy in the battle with the most health."
                        + " \nMarked enemies take double damage.", testHunter.passiveDesc());
    }

    @Test
    void testHeadShot() {
        assertEquals(0, testHunter.headShot());
        testHunter.abilities.add("Head Shot");
        assertEquals(1, testHunter.headShot());
        testHunter.attackSequence.add(0, "Head Shot");
        assertEquals(0, testHunter.headShot());
        testHunter.attackSequence.add(0, "testplayer1");
        testHunter.attackSequence.add(0, "testplayer1");
        testHunter.attackSequence.add(0, "testplayer1");
        assertEquals(1, testHunter.headShot());
    }

    @Test
    void testHeadShotDesc() {
        assertEquals("Head shot an enemy, gaining 5 damage. \nThis has a 3 turn cooldown.",
                testHunter.headShotDesc());
    }

    @Test
    void testHaste() {
        assertEquals(0, testHunter.haste());
        testHunter.abilities.add("Haste");
        assertEquals(1, testHunter.haste());
    }

    @Test
    void testHasteDesc() {
        assertEquals("Gain 10 speed.", testHunter.hasteDesc());
    }

    @Test
    void testEvade() {
        assertEquals(0, testHunter.evade());
        testHunter.abilities.add("Evade");
        assertEquals(1, testHunter.evade());
        testHunter.attackSequence.add(0,"Evade");
        assertEquals(0, testHunter.evade());
    }

    @Test
    void testEvadeDesc() {
        assertEquals("Drastically increase your agility. \nThis can be used once per combat.",
                testHunter.evadeDesc());
    }

    @Test
    void testDoAbility() {
        assertEquals(1, testHunter.doAbility("Passive"));
        assertEquals(2, testHunter.doAbility("Head Shot"));
        assertEquals(3, testHunter.doAbility("Haste"));
        assertEquals(4, testHunter.doAbility("Evade"));
        assertEquals(-1, testHunter.doAbility("Hello"));
    }
}