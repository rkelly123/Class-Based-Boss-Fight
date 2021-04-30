package model;

import com.sun.javafx.scene.control.skin.LabeledImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

// A test class for the Brute class
class BruteTest {
    private Brute testBrute;

    @BeforeEach
    void runBefore() {
        testBrute = new Brute();
    }

    @Test
    void testBrute() {
        assertEquals(15, testBrute.speed);
        assertEquals(200, testBrute.totalhealth);
        assertEquals("Weapon", testBrute.resistance);
        assertEquals(15, testBrute.defense);
        assertEquals(5, testBrute.innatedamage);
        assertEquals(2, testBrute.agility);
        assertEquals("brute", testBrute.loadout);
        assertTrue(testBrute.abilities.contains("Tank"));
    }

    @Test
    void testPassive() {
       assertEquals(0, testBrute.passive());
    }

    @Test
    void testPassiveDesc() {
        assertEquals("Tank. Double your health at the cost of half of your speed and damage.",
                testBrute.passiveDesc());
    }

    @Test
    void testBodySlam() {
        assertEquals(0, testBrute.bodySlam());
        testBrute.abilities.add("Body Slam");
        assertEquals(1, testBrute.bodySlam());
        testBrute.attackSequence.add(0, "Body Slam");
        assertEquals(1, testBrute.bodySlam());
        testBrute.attackSequence.add(0, "testplayer1");
        testBrute.attackSequence.add(0, "testplayer1");
        testBrute.attackSequence.add(0, "testplayer1");
        assertEquals(1, testBrute.bodySlam());
    }

    @Test
    void testBodySlamDesc() {
        assertEquals("Increase your damage by 5 percent of your health.\nThis has a 3 turn cooldown."
                , testBrute.bodySlamDesc());
    }

    @Test
    void testJuggernaut() {
        assertEquals(0, testBrute.juggernaut());
        testBrute.abilities.add("Juggernaut");
        assertEquals(1, testBrute.juggernaut());
        testBrute.attackSequence.add(0, "Juggernaut");
        assertEquals(0, testBrute.juggernaut());
        testBrute.attackSequence.add(0, "testplayer1");
        testBrute.attackSequence.add(0, "testplayer1");
        testBrute.attackSequence.add(0, "testplayer1");
        assertEquals(1, testBrute.juggernaut());
    }

    @Test
    void testJuggernautDesc() {
        assertEquals("Brace for impact and increase defense by 5. \nIt has a 3 turn cooldown",
                testBrute.juggernautDesc());
    }

    @Test
    void testKnockoutPunch() {
        assertEquals(0, testBrute.knockoutPunch());
        testBrute.abilities.add("Knockout Punch");
        assertEquals(1, testBrute.knockoutPunch());
        testBrute.attackSequence.add(0, "Knockout Punch");
        assertEquals(0, testBrute.knockoutPunch());
        testBrute.attackSequence.add(0, "testplayer1");
        assertEquals(1, testBrute.knockoutPunch());
    }

    @Test
    void testKnockoutPunchDesc() {
        assertEquals("Adds 3 to your damage. \nIt has a 1 turn cooldown.",
                testBrute.knockoutPunchDesc());
    }

    @Test
    void testDoAbility() {
        assertEquals(1, testBrute.doAbility("Passive"));
        assertEquals(2, testBrute.doAbility("Body Slam"));
        assertEquals(3, testBrute.doAbility("Juggernaut"));
        assertEquals(4, testBrute.doAbility("Knockout Punch"));
        assertEquals(-1, testBrute.doAbility("Hello"));
    }
}