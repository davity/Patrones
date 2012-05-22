/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author David
 */
public class Main {
    private static Main instance = new Main();
    private static JFrame frame = new JFrame("Chickens Defense");
    private static Menu menu;
    private static final int GAME_WIDTH = 640;
    private static final int GAME_HEIGHT = 480;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        menu = new Menu();
        menu.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        frame.add(menu, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
    
    public static Main getInstance() {
        return instance;
    }
    
    public void startGame() {
        menu.setVisible(false);
        Game game = new Game();
        game.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        frame.add(game, BorderLayout.CENTER);
        frame.pack();
        game.init();
        game.start();
    }
    
    public void showMenu() {
        menu.setVisible(true);
    }
}