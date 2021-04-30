package persistance;

import model.Berserker;
import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// A test class for the Writer class
class WriterTest {
    private static final String TEST_FILE = "./data/testplayer1.txt";
    private Writer testWriter;
    private Player player;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        player = new Berserker();
        player.abilities.add("Bludgeon");
        player.abilities.add("Skull Crusher");

    }

    @Test
    void testWriteAccounts() {
        // save player information to file
        testWriter.write(player);
        testWriter.close();

        // now read them back in and verify that the character has the expected values
        try {
            player = Reader.readPlayer(new File(TEST_FILE));
            assertEquals("berserker", player.loadout);
            assertEquals(1, player.level);
            assertEquals("Bludgeon", player.abilities.get(1));
            assertEquals("Skull Crusher", player.abilities.get(2));
            assertEquals(40, player.speed);
            assertEquals(120, player.totalhealth);
            assertEquals(5, player.defense);
            assertEquals(2, player.innatedamage);
            assertEquals(5, player.agility);
            assertEquals(120, player.health);

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}