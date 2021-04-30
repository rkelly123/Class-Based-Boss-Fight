package ui;

import javax.swing.*;
import java.awt.*;

public class GuiEnvironment extends GraphicGame {
// A class to handle some of the key GUI setup

    // MODIFIES: GraphicGame
    // EFFECTS: begins to set up various key environment characteristics for the start screen
    public static void createEnvironment() {
        window.setSize(1000, 600);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        titleNamePanel.setBounds(100, 100, 800, 200);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);
        startButtonPanel.setBounds(375, 400, 187, 100);
        startButtonPanel.setBackground(Color.black);
        startButtonPanel.setBorder(null);
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(buttonFont);
        con.add(startButtonPanel);
        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);
        con.add(titleNamePanel);
        window.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates an intermediary game screen and begins to load player
    public static void createGameScreen() {
        imagePanel.setVisible(false);
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);
        mainTextPanel.setBounds(100, 100, 800, 250);
        mainTextPanel.setBackground(Color.black);
        con.add(mainTextPanel);
        mainTextArea.setBounds(100, 100, 800, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(regularFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setEditable(false);
        mainTextPanel.add(mainTextArea);
        choiceButtonPanel.setBounds(250, 350, 500, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));
        con.add(choiceButtonPanel);
        createPlayerPanel();
    }

    // MODIFIES: this
    // EFFECTS: creates a top panel with some character stats
    public static void createPlayerPanel() {
        playerPanel.setBounds(100, 15, 800, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1, 4));
        con.add(playerPanel);
        hpLabel.setFont(regularFont);
        hpLabel.setForeground(Color.white);
        playerPanel.add(hpLabel);
        hpLabelNumber.setFont(regularFont);
        hpLabelNumber.setForeground(Color.white);
        playerPanel.add(hpLabelNumber);
        scoreLabel.setFont(regularFont);
        scoreLabel.setForeground(Color.white);
        playerPanel.add(scoreLabel);
        scoreLabelNumber.setFont(regularFont);
        scoreLabelNumber.setForeground(Color.white);
        playerPanel.add(scoreLabelNumber);
        healthPotionLabel.setFont(regularFont);
        healthPotionLabel.setForeground(Color.white);
        playerPanel.add(healthPotionLabel);
        healthPotionLabelNumber.setFont(regularFont);
        healthPotionLabelNumber.setForeground(Color.white);
        playerPanel.add(healthPotionLabelNumber);
    }
}
