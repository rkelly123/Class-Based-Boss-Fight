package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import model.*;
import persistance.Reader;
import persistance.Writer;
import java.io.IOException;

// A class that can be run to start a new instance of the game with a GUI
public class GraphicGame {
    static JFrame  window = new JFrame();
    static Container con = window.getContentPane();
    static JPanel titleNamePanel = new JPanel();
    static JPanel startButtonPanel = new JPanel();
    static JPanel mainTextPanel = new JPanel();
    static JPanel choiceButtonPanel = new JPanel();
    static JPanel playerPanel = new JPanel();
    static JPanel imagePanel = new JPanel();
    static JLabel titleNameLabel = new JLabel("Vangorian Defender");
    static JLabel hpLabel = new JLabel("HP:");
    static JLabel hpLabelNumber = new JLabel();
    static JLabel scoreLabel = new JLabel("Score:");
    static JLabel scoreLabelNumber = new JLabel();
    static JLabel healthPotionLabel = new JLabel("Pots:");
    static JLabel healthPotionLabelNumber = new JLabel();
    static JLabel image = new JLabel(new ImageIcon("lib/death.jpg"));
    static JButton startButton = new JButton("FIGHT!");
    static JButton choice1 = new JButton("Choice 1");
    static JButton choice2 = new JButton("Choice 2");
    static JButton choice3 = new JButton("Choice 3");
    static JButton choice4 = new JButton("Choice 4");
    static JTextArea mainTextArea = new JTextArea("This is the main text area");
    static Font titleFont = new Font("Times New Roman", Font.PLAIN, 96);
    static Font buttonFont = new Font("Times New Roman", Font.PLAIN, 60);
    static Font regularFont = new Font("Times New Roman", Font.PLAIN, 30);
    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();
    Player p1 = new Player();
    Actor g1 = new Actor();
    String position;
    private static final String PLAYER_FILE = "./data/player.txt";

    public static void main(String[] args) {
        new GraphicGame();
    }

    // EFFECTS: instantiates a game with GUI at the title screen
    public GraphicGame() {
        GuiEnvironment.createEnvironment();
        startButton.addActionListener(tsHandler);
    }

    // MODIFIES: this
    // EFFECTS: creates an intermediary game screen and begins to load player
    public void startGame() {
        createButtons();
        canLoadPlayer();
        playerSetup();
    }

    // MODIFIES: this
    // EFFECTS: loads player or begins character creation
    public void canLoadPlayer() {
        if (loadPlayer()) {
            chooseClass();
        } else {
            receiveStartingHealthPotions();
            mainTextArea.setText("Your saved character was loaded. Prepare for battle!");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates the buttons required for the GUI
    public void createButtons() {
        choiceOne();
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.white);
        choice2.setFont(regularFont);
        choice2.addActionListener(choiceHandler);
        choice2.setFocusPainted(false);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.white);
        choice3.setFont(regularFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.white);
        choice4.setFont(regularFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);
    }

    // MODIFIES: this
    // EFFECTS: a button making helper
    public void choiceOne() {
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.white);
        choice1.setFont(regularFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);
    }

    // MODIFIES: this
    // EFFECTS: sets up the character stat panel
    public void playerSetup() {
        hpLabelNumber.setText("" + p1.health);
        scoreLabelNumber.setText("" + (50000 - g1.health));
        healthPotionLabelNumber.setText("" + p1.healthpots.size());
    }

    // MODIFIES: this
    // EFFECTS: allows the player to choose a class
    public void chooseClass() {
        position = "chooseClass";
        playerPanel.setVisible(false);
        mainTextArea.setText(chooseClassIntro());
        choice1.setText("Berserker");
        choice2.setText("Brute");
        choice3.setText("Hunter");
        choice4.setText("Wizard");
    }

    // MODIFIES: this
    // EFFECTS: begins the point buy procedure
    public void pointBuy() {
        position = "pointBuy";
        playerPanel.setVisible(false);
        mainTextArea.setText(pointBuyIntro());
        choice1.setText("Health");
        choice2.setText("Damage");
        choice3.setText("Agility");
        choice4.setText("Defense");
    }

    // MODIFIES: this
    // EFFECTS: allows the player to choose an ability
    public void chooseAbilities() {
        position = "chooseAbilities";
        playerPanel.setVisible((false));
        mainTextArea.setText("You may now choose an ability.");
        switch (p1.loadout) {
            case "berserker":
                caseBerserker();
                break;
            case "brute":
                caseBrute();
                break;
            case "hunter":
                choice1.setText(Hunter.HUNTER_ABILITY_1);
                choice2.setText(Hunter.HUNTER_ABILITY_2);
                choice3.setText(Hunter.HUNTER_ABILITY_3);
                choice4.setText("");
                break;
            case "wizard":
                choice1.setText(Wizard.WIZARD_ABILITY_1);
                choice2.setText(Wizard.WIZARD_ABILITY_2);
                choice3.setText(Wizard.WIZARD_ABILITY_3);
                choice4.setText("");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: represents the ability choosing case where the player is a berserker
    public void caseBerserker() {
        choice1.setText(Berserker.BERSERKER_ABILITY_1);
        choice2.setText(Berserker.BERSERKER_ABILITY_2);
        choice3.setText(Berserker.BERSERKER_ABILITY_3);
        choice4.setText("");
    }

    // MODIFIES: this
    // EFFECTS: represents the ability choosing case where the player is a brute
    public void caseBrute() {
        choice1.setText(Brute.BRUTE_ABILITY_1);
        choice2.setText(Brute.BRUTE_ABILITY_2);
        choice3.setText(Brute.BRUTE_ABILITY_3);
        choice4.setText("");
    }

    // MODIFIES: this
    // EFFECTS: gives the player 3 starting health potions
    public void receiveStartingHealthPotions() {
        position = "receiveStartingHealthPotions";
        playerPanel.setVisible(false);
        mainTextArea.setText("You have received 3 health potions!");
        p1.addHealthPotion();
        p1.addHealthPotion();
        p1.addHealthPotion();
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    // MODIFIES: this
    // EFFECTS: saves the character's state
    public void saveCharacter() {
        position = "saveCharacter";
        playerPanel.setVisible(false);
        mainTextArea.setText("Hit the Save button to save your character, or simply hit continue to enter the fight!");
        choice1.setText("Save");
        choice2.setText("Continue");
    }

    // MODIFIES: this
    // EFFECTS: gives some introductory fight information
    public void fightStart() {
        position = "fightStart";
        g1.health = 50000;
        g1.innatedamage = 5;
        mainTextArea.setText("The fight is commencing! You will have the choice between 3 options in combat: attacking,"
                + " healing, and using your ability. Increase your score by doing damage! Plan wisely.");
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    // MODIFIES: this
    // EFFECTS: represents the main fight loop of the game
    public void fight() {
        hpLabelNumber.setText("" + p1.health);
        scoreLabelNumber.setText("" + (50000 - g1.health));
        healthPotionLabelNumber.setText("" + p1.healthpots.size());
        position = "fight";
        playerPanel.setVisible(true);
        if (p1.health > 0) {
            basicCombat();
        } else if (p1.health > -1000) {
            hpLabelNumber.setText("Dead");
            position = "fightStart";
            choice1.setText(">");
            choice2.setVisible(false);
            choice3.setVisible(false);
            choice4.setVisible(false);
            p1.health = -20000;
        } else if (p1.health < -1000) {
            endGame();
        }
    }

    // EFFECTS: ends the game, displaying the score and an image
    public void endGame() {
        choice1.setVisible(false);
        playerPanel.setVisible(false);
        titleNamePanel.setVisible(true);
        mainTextPanel.setVisible(false);
        choiceButtonPanel.setVisible(false);
        titleNamePanel.setBounds(50, 100, 900, 100);
        titleNameLabel.setText("Game Over, Score: " + (50000 - g1.health));
        imagePanel.setBounds(50,150,900, 400);
        imagePanel.setBackground(Color.black);
        imagePanel.add(image);
        con.add(imagePanel);
        imagePanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: your basic combat options
    public void basicCombat() {
        mainTextArea.setText("What would you like to do?");
        choice1.setText("Attack");
        choice2.setText("Heal");
        choice3.setText("Ability");
        choice4.setVisible(false);
    }

    // EFFECTS: introduces pointBuy method
    private static String pointBuyIntro() {
        return "POINT BUY:\n"
                + "All classes have some preexisting stats, however, you may choose your play style by"
                + " choosing which stats to increase.";
    }

    public class TitleScreenHandler implements ActionListener {

        // MODIFIES: this
        // EFFECTS: creates a game screen
        public void actionPerformed(ActionEvent event) {
            GuiEnvironment.createGameScreen();
            startGame();
        }
    }

    public class ChoiceHandler implements ActionListener {

        // MODIFIES: this
        // EFFECTS: sorts through all possible game state inputs and button pushes
        public void actionPerformed(ActionEvent event) {
            String yourChoice = event.getActionCommand();
            switchStatement(yourChoice);
        }

        // MODIFIES: this
        // EFFECTS: represents all of the possible button inputs
        public void switchStatement(String yourChoice) {
            switch (position) {
                case "chooseClass":
                    switchChooseClass(yourChoice);
                    break;
                case "pointBuy":
                    switchPointBuy(yourChoice);
                    break;
                case "chooseAbilities":
                    caseChooseAbilities(yourChoice);
                    break;
                case "receiveStartingHealthPotions":
                    switchReceiveStartingHealthPotions(yourChoice);
                    break;
                case "saveCharacter":
                    switchSaveCharacter(yourChoice);
                    break;
                case "fightStart":
                    switchFightStart(yourChoice);
                    break;
                case "fight":
                    switchFight(yourChoice);
                    break;
            }
        }

        // MODIFIES: this
        // EFFECTS: allows the player to choose their abilities
        public void caseChooseAbilities(String yourChoice) {
            switch (p1.loadout) {
                case "berserker":
                    switchChooseAbilitiesBerserker(yourChoice);
                    receiveStartingHealthPotions();
                    break;
                case "brute":
                    switchChooseAbilitiesBrute(yourChoice);
                    receiveStartingHealthPotions();
                    break;
                case "hunter":
                    switchChooseAbilitiesHunter(yourChoice);
                    receiveStartingHealthPotions();
                    break;
                case "wizard":
                    switchChooseAbilitiesWizard(yourChoice);
                    receiveStartingHealthPotions();
                    break;
            }
        }

        // MODIFIES: this
        // EFFECTS: allows the player to choose their class
        public void switchChooseClass(String yourChoice) {
            switch (yourChoice) {
                case "c1":
                    p1 = new Berserker();
                    pointBuy();
                    break;
                case "c2":
                    p1 = new Brute();
                    pointBuy();
                    break;
                case "c3":
                    p1 = new Hunter();
                    pointBuy();
                    break;
                case "c4":
                    p1 = new Wizard();
                    pointBuy();
                    break;
            }
        }

        // MODIFIES: this
        // EFFECTS: allows the player to conduct a point buy
        public void switchPointBuy(String yourChoice) {
            switch (yourChoice) {
                case "c1":
                    addHealthCase();
                    break;
                case "c2":
                    p1.addInnateDamage();
                    for (int i = 0; i < 2; i++) {
                        chooseAbilities();
                    }
                    break;
                case "c3":
                    p1.addAgility();
                    for (int i = 0; i < 2; i++) {
                        chooseAbilities();
                    }
                    break;
                case "c4":
                    p1.addDefense();
                    for (int i = 0; i < 2; i++) {
                        chooseAbilities();
                    }
                    break;
            }
        }

        // MODIFIES: this
        // EFFECTS: the point buy case where the player adds health points
        public void addHealthCase() {
            p1.addTotalHealth();
            for (int i = 0; i < 2; i++) {
                chooseAbilities();
            }
        }

        // MODIFIES: this
        // EFFECTS: allows the player to choose abilities as a berserker
        public void switchChooseAbilitiesBerserker(String yourChoice) {
            switch (yourChoice) {
                case "c1":
                    p1.abilities.add(Berserker.BERSERKER_ABILITY_1);
                case "c2":
                    p1.abilities.add(Berserker.BERSERKER_ABILITY_2);
                case "c3":
                    p1.abilities.add(Berserker.BERSERKER_ABILITY_3);
                    p1.abilities.add(Berserker.BERSERKER_ABILITY_3);
            }
        }

        // MODIFIES: this
        // EFFECTS: allows the player to choose abilities as a brute
        public void switchChooseAbilitiesBrute(String yourChoice) {
            switch (yourChoice) {
                case "c1":
                    p1.abilities.add(Brute.BRUTE_ABILITY_1);
                case "c2":
                    p1.abilities.add(Brute.BRUTE_ABILITY_2);
                case "c3":
                    p1.abilities.add(Brute.BRUTE_ABILITY_3);
                    p1.abilities.add(Brute.BRUTE_ABILITY_3);
            }
        }

        // MODIFIES: this
        // EFFECTS: allows the player to choose abilities as a hunter
        public void switchChooseAbilitiesHunter(String yourChoice) {
            switch (yourChoice) {
                case "c1":
                    p1.abilities.add(Hunter.HUNTER_ABILITY_1);
                case "c2":
                    p1.abilities.add(Hunter.HUNTER_ABILITY_2);
                case "c3":
                    p1.abilities.add(Hunter.HUNTER_ABILITY_3);
                    p1.abilities.add(Hunter.HUNTER_ABILITY_3);
            }
        }

        // MODIFIES: this
        // EFFECTS: allows the player to choose abilities as a wizard
        public void switchChooseAbilitiesWizard(String yourChoice) {
            switch (yourChoice) {
                case "c1":
                    p1.abilities.add(Wizard.WIZARD_ABILITY_1);
                case "c2":
                    p1.abilities.add(Wizard.WIZARD_ABILITY_2);
                case "c3":
                    p1.abilities.add(Wizard.WIZARD_ABILITY_3);
                    p1.abilities.add(Wizard.WIZARD_ABILITY_3);
            }
        }

        // MODIFIES: this
        // EFFECTS: moves on to the save character screen
        public void switchReceiveStartingHealthPotions(String yourChoice) {
            switch (yourChoice) {
                case "c1":
                    saveCharacter();
                    break;
            }
        }

        // MODIFIES: this
        // EFFECTS: allows the player to choose to save their character
        public void switchSaveCharacter(String yourChoice) {
            switch (yourChoice) {
                case "c1":
                    savePlayer();
                    fightStart();
                    break;
                case "c2":
                    fightStart();
                    break;
            }
        }

        // MODIFIES: this
        // EFFECTS: the player can choose when to start the fight
        public void switchFightStart(String yourChoice) {
            switch (yourChoice) {
                case "c1":
                    fight();
            }
        }

        // MODIFIES: this
        // EFFECTS: represents all of the player's fighting options
        public void switchFight(String yourChoice) {
            switch (yourChoice) {
                case "c1":
                    int hitDamage = (int) (Math.random() * p1.innatedamage + 1);
                    g1.health -= hitDamage;
                    fight();
                    mainTextArea.setText("You hit the goliath for " + hitDamage + " damage!");
                    break;
                case "c2":
                    if (p1.healthpots.size() > 0) {
                        p1.health += 25;
                        p1.healthpots.remove(0);
                        fight();
                        mainTextArea.setText("You healed for 25 health");
                    } else {
                        mainTextArea.setText("You are all out of health potions");
                    }
                    break;
                case "c3":
                    checkAbility1();
                    break;
            }
            goliathTurn();
        }

        // MODIFIES: goliath, p1
        // EFFECTS: allows the goliath to take a turn
        public void goliathTurn() {
            g1.attackSequence.add("Attack");
            int goliathDamageNotBlocked = (int) (Math.random() * g1.innatedamage + 1);
            int goliathDamage = goliathDamageNotBlocked - (int) (Math.random() * p1.defense + 1);
            if (goliathDamage <= 0) {
                goliathDamage = 0;
            }
            if (Math.random() * 100 + 1 > p1.agility) {
                p1.health = p1.health - goliathDamage;
                g1.innatedamage = g1.innatedamage + 2;
                mainTextArea.append("\nThe goliath did " + goliathDamage + " damage!");
            } else {
                mainTextArea.append("\nThe goliath missed!");
            }
            findHealthPotions();
        }

        // MODIFIES: p1
        // EFFECTS: adds a health potion to player
        public void findHealthPotions() {
            if (Math.random() * 100 + 1 > 80) {
                p1.addHealthPotion();
                mainTextArea.append("\nYou found a health potion!");
            }
        }

        // MODIFIES: this
        // EFFECTS: checks what ability is used when the player uses the ability button
        public void checkAbility1() {
            String ability1 = p1.abilities.get(1);
            p1.doAbility(ability1);
            fight();
            mainTextArea.setText("You successfully used your ability: " + ability1 + "!");
        }
    }


    // EFFECTS: Introduces the chooseClass method
    public String chooseClassIntro() {
        return "Choose a class from Berserker, Brute, Hunter, and Wizard. For example, type Wizard to "
                + "choose Wizard\n";
    }

    // MODIFIES: this
    // EFFECTS: loads player from PLAYER_FILE, if that file exists;
    // otherwise initializes new game with default values
    public boolean loadPlayer() {
        try {
            p1 = Reader.readPlayer(new File(PLAYER_FILE));
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    // EFFECTS: saves state of character to PLAYER_FILE
    public void savePlayer() {
        try {
            Writer writer = new Writer(new File(PLAYER_FILE));
            writer.write(p1);
            writer.close();
            System.out.println("Accounts saved to file " + PLAYER_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + PLAYER_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }
}
