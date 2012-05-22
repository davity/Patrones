/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author David
 */
public class Main {
    private static Main instance = new Main();
    private static JFrame frame = new JFrame("Chickens Defense");
    private static Menu menu = new Menu();
    private Game game;
    private Pause pause;
    private Records records;
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
        if (game == null) {
            game = new Game();
            game.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        }
        frame.add(game, BorderLayout.CENTER);
        frame.pack();
        game.init();
        game.start();
        records = new Records();
        load();
        System.out.println(records.record[1]);
        records.record[1]++;
        save();
    }
    
    public void endGame() {
        game.stop();
        frame.remove(game);
        menu.setVisible(true);
    }
    
    public void pause() {
        game.pause();
        game.setVisible(false);
        if (pause == null) {
            pause = new Pause();
            pause.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        }
        frame.add(pause, BorderLayout.CENTER);
        frame.pack();
    }
    
    public void resume() {
        frame.remove(pause);
        game.setVisible(true);
        game.resume();
    }
    
    public void save() {
        try {
            File file = new File("records.dat");
            if(!file.exists()) {
                file.createNewFile();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(records.save());
                writer.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void load() {
        try {
            File file = new File("records.dat");
            if(file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    records.load(reader.readLine());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}