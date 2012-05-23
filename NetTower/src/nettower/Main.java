
package nettower;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Main {
    private static Main instance = new Main();
    private static JFrame frame = new JFrame("Chickens Defense");
    private static Menu menu = new Menu();
    private Game game;
    private Pause pause;
    private static Records records = new Records();
    private boolean[] state = new boolean[2];
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
        records.loadRecords();
        menu.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        frame.add(menu, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
    
    /**
     * Devuelve la instancia del objeto
     * 
     * @return Main
     */
    public static Main getInstance() {
        return instance;
    }
    
    /**
     * Inicia el juego. Oculta la pantalla de menu, muestra la pantalla del 
     * juego, la inicializa y la arranca
     * 
     * @param map 
     */
    public void startGame(int map) {
        if (!state[0]) {
            menu.setVisible(false);
            if (game == null) {
                game = new Game();
                game.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
            }
            game.map = map;
            frame.add(game, BorderLayout.CENTER);
            state[0] = true;
            game.setVisible(true);
            frame.pack();
            game.init();
            game.start();
        }
    }
    
    /**
     * Finaliza la partida. Elimina la pantalla de juego guardando previamente
     * el record de puntos y muestra el menu principal.
     */
    public void endGame() {
        if (state[0]) {
            if (state[1]) {
                frame.remove(pause);
                state[1] = false;
            }
            records.newRecord(game.map, game.end());
            frame.remove(game);
            records.saveRecords();
            state[0] = false;
            menu.setVisible(true);
        }
    }
    
    /**
     * Pausa la ejecución del juego
     */
    public void pause() {
        if (state[0] && !state[1]) {
            game.pause();
            game.setVisible(false);
            if (pause == null) {
                pause = new Pause();
                pause.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
            }
            frame.add(pause, BorderLayout.CENTER);
            state[1] = true;
            frame.pack();
        }
    }
    
    /**
     * Reanuda la ejecución del juego
     */
    public void resume() {
        if (state[0] && state[1]) {
            frame.remove(pause);
            state[1] = false;
            game.setVisible(true);
            game.resume();
        }
    }
    
    // LO HE IMPLEMENTADO EN LA CLASE MENU - davity
//    public void showHelp() {
//        //System.out.println("Not yet implemented");
//    }
    
    /**
     * Cierra el juego
     */
    public void exit() {
        frame.dispose();
        System.exit(0);
    }
    
    /**
     * Dado un mapa devuelve su puntuacion
     * 
     * @param map
     * @return String
     */
    public String getRecord(int map) {
        return records.getRecord(map);
    }
}