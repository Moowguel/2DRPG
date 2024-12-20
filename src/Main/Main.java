package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2dAdventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack(); // causes the window to be sized to fit the preferred size and layout of its subcomponents

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();//create the gameloop "time"
        gamePanel.setupGame();//instance the object as start the program

    }
}