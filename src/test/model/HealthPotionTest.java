package model;

import com.sun.javafx.scene.control.skin.LabeledImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

// A test class for the HealthPotion class
public class HealthPotionTest {
    HealthPotion hp;

    @BeforeEach
    void runBefore() {
        hp = new HealthPotion();
    }
}
