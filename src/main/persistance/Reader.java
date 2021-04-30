package persistance;

import model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read data from a file
public class Reader {
    public static final String DELIMITER = ",";
    public static Player player;
    public static ArrayList<String> lineComponents;

    // EFFECTS: returns character parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static Player readPlayer(File file) throws IOException {
        List<String> fileContent = readFile(file);
        parseContent(fileContent);
        return player;
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a character with information parsed from list of strings
    private static void parseContent(List<String> fileContent) {
        for (String line : fileContent) {
            lineComponents = splitString(line);
        }
        parseCharacter(lineComponents);
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 10 where element 0 represents the
    // loadout of the character, element 1 represents level, element 2-3 represent learned abilities, element 4
    // represents speed, element 5 represents totalhealth, element 6 represents defense, element 7 represents
    // innate damage, element 8 represents agility, and element 9 represents health.
    // MODIFIES: player
    // EFFECTS: makes a new player with all saved character information
    private static void parseCharacter(List<String> components) {
        player = new Player();
        player.loadout = components.get(0);
        loadCharacter();
        player.level = Integer.parseInt(components.get(1));
        player.abilities.add(components.get(2));
        player.abilities.add(components.get(3));
        player.speed = Integer.parseInt(components.get(4));
        player.totalhealth = Integer.parseInt(components.get(5));
        player.defense = Integer.parseInt(components.get(6));
        player.innatedamage = Integer.parseInt(components.get(7));
        player.agility = Integer.parseInt(components.get(8));
        player.health = Integer.parseInt(components.get(9));
    }

    // MODIFIES: player
    // EFFECTS: creates a new player with the proper loadout chosen
    public static void loadCharacter() {
        switch (player.loadout) {
            case "berserker":
                loadBerserker();
                break;
            case "brute":
                loadBrute();
                break;
            case "hunter":
                loadHunter();
                break;
            case "wizard":
                loadWizard();
        }
        player.health = 0;
    }

    // MODIFIES: player
    // EFFECTS: creates a new Berserker
    public static void loadBerserker() {
        player = new Berserker();
    }

    // MODIFIES: player
    // EFFECTS: creates a new Brute
    private static void loadBrute() {
        player = new Brute();
    }

    // MODIFIES: player
    // EFFECTS: creates a new Hunter
    private static void loadHunter() {
        player = new Hunter();
    }

    // MODIFIES: player
    // EFFECTS: creates a new Wizard
    private static void loadWizard() {
        player = new Wizard();
    }
}