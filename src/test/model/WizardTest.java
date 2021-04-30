package model;

import com.sun.javafx.scene.control.skin.LabeledImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// A test class for the Wizard class
class WizardTest {
    private Wizard testWizard;

    @BeforeEach
    void runBefore() {
        testWizard = new Wizard();
    }

    @Test
    void testWizard() {
        assertEquals(25, testWizard.speed);
        assertEquals(80, testWizard.totalhealth);
        assertEquals(null, testWizard.resistance);
        assertEquals(2, testWizard.defense);
        assertEquals(10, testWizard.innatedamage);
        assertEquals(10, testWizard.agility);
        assertEquals("wizard", testWizard.loadout);
        assertTrue(testWizard.abilities.contains("Ethereal Power"));
    }

    @Test
    void testPassiveDesc() {
        assertEquals("Ethereal Power. All of your damage ignores opponent's defense unless they have"
                + " resistance to magic", testWizard.passiveDesc());
    }

    @Test
    void testFireBall() {
        assertEquals(0, testWizard.fireBall());
        testWizard.abilities.add("Fire Ball");
        assertEquals(1, testWizard.fireBall());
        testWizard.attackSequence.add(0, "Fire Ball");
        assertEquals(0, testWizard.fireBall());
        testWizard.attackSequence.add(0, "testplayer1");
        testWizard.attackSequence.add(0, "testplayer1");
        testWizard.attackSequence.add(0, "testplayer1");
        assertEquals(1, testWizard.fireBall());
    }

    @Test
    void testFireBallDesc() {
        assertEquals("Cast 3 Fire Balls. \nThis makes you gain 40 damage. It has a 3 turn cooldown.",
                testWizard.fireBallDesc());
    }

    @Test
    void testPolymorph() {
        assertEquals(0, testWizard.polymorph());
        testWizard.abilities.add("Polymorph");
        assertEquals(1, testWizard.polymorph());
        testWizard.attackSequence.add(0, "Polymorph");
        assertEquals(0, testWizard.polymorph());
    }

    @Test
    void testPolymorphDesc() {
        assertEquals("Transform yourself into a giant beast, gaining 100 health. This can be used once."
                , testWizard.polymorphDesc());
    }

    @Test
    void testHealingWord() {
        assertEquals(0, testWizard.healingWord());
        testWizard.abilities.add("Healing Word");
        assertEquals(1, testWizard.healingWord());
        testWizard.attackSequence.add(0, "Healing Word");
        assertEquals(0, testWizard.healingWord());
        testWizard.attackSequence.add(0,"testplayer1");
        testWizard.attackSequence.add(0, "testplayer1");
        assertEquals(1, testWizard.healingWord());
    }

    @Test
    void testHealingWordDesc() {
        assertEquals("Heal yourself for 20 health. \nThis has a 2 turn cooldown.",
                testWizard.healingWordDesc());
    }

    @Test
    void testDoAbility() {
        assertEquals(1, testWizard.doAbility("Fire Ball"));
        assertEquals(2, testWizard.doAbility("Polymorph"));
        assertEquals(3, testWizard.doAbility("Healing Word"));
        assertEquals(-1, testWizard.doAbility("Hello"));
    }
}