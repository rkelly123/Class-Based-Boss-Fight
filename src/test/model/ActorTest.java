package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// A test class for the Actor class
class ActorTest {
    private Actor testActor;

    @BeforeEach
    void runBefore() {
        testActor = new Actor();
    }

    @Test
    void testActor() {
        assertFalse(testActor.isPlayer);
    }
}