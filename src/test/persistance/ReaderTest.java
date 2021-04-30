package persistance;

import model.Player;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// A test class for the Reader class
class ReaderTest {
    private Player player;

    @Test
    void testParseCharacterFile1() {
        try {
            player = Reader.readPlayer(new File("./data/testplayer1"));
            assertEquals("berserker", player.loadout);
            assertEquals(1, player.level);
            assertEquals("Bludgeon", player.abilities.get(1));
            assertEquals("Skull Crusher", player.abilities.get(2));
            assertEquals(40, player.speed);
            assertEquals(120, player.totalhealth);
            assertEquals(25, player.defense);
            assertEquals(2, player.innatedamage);
            assertEquals(5, player.agility);
            assertEquals(120, player.health);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseCharacterFile2() {
        try {
            player = Reader.readPlayer(new File("./data/testplayer2"));
            assertEquals("hunter", player.loadout);
            assertEquals(1, player.level);
            assertEquals("evade", player.abilities.get(1));
            assertEquals("haste", player.abilities.get(2));
            assertEquals(50, player.speed);
            assertEquals(80, player.totalhealth);
            assertEquals(15, player.defense);
            assertEquals(5, player.innatedamage);
            assertEquals(20, player.agility);
            assertEquals(80, player.health);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseCharacterFile3() {
        try {
            player = Reader.readPlayer(new File("./data/testplayer3"));
            assertEquals("brute", player.loadout);
            assertEquals(1, player.level);
            assertEquals("Knockout Punch", player.abilities.get(1));
            assertEquals("Juggernaut", player.abilities.get(2));
            assertEquals(15, player.speed);
            assertEquals(200, player.totalhealth);
            assertEquals(50, player.defense);
            assertEquals(5, player.innatedamage);
            assertEquals(2, player.agility);
            assertEquals(200, player.health);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseCharacterFile4() {
        try {
            player = Reader.readPlayer(new File("./data/testplayer4"));
            assertEquals("wizard", player.loadout);
            assertEquals(1, player.level);
            assertEquals("Fire Ball", player.abilities.get(1));
            assertEquals("Polymorph", player.abilities.get(2));
            assertEquals(25, player.speed);
            assertEquals(80, player.totalhealth);
            assertEquals(10, player.defense);
            assertEquals(10, player.innatedamage);
            assertEquals(10, player.agility);
            assertEquals(80, player.health);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readPlayer(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testNoLoadout() {
        try {
            player = Reader.readPlayer(new File("./data/testplayer5"));
            Reader.player = player;
            Reader.loadCharacter();
            assertEquals(0, Reader.player.health);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}