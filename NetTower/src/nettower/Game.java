/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import java.applet.Applet;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import nettower.interpreter.PathInterpreter;
import nettower.singleton.SingletonGame;
import nettower.singleton.SingletonGraphics;

/**
 *
 * @author David Moran Diaz
 */
public class Game extends Applet implements Runnable {
    private int i;
    private Thread thread;
    private Graphics doubleBufferedGraphics;
    private Image doubleBufferedImage;
    public int map;
    private boolean pause;
    private static final String PATHS_MAP_0 = "map_0.dat";
    private static final String PATHS_MAP_1 = "map_1.dat";
    private static final String PATHS_MAP_2 = "map_2.dat";
    
    public Game() {}
    
    /**
     * Método de inicialización de la clase. Carga las rutas de los mapas
     * y los records de los mismos
     */
    @Override
    public void init() {
        i = 0;
        pause = false;
        SingletonGame.getInstance().clear();
        PathInterpreter pathInterpreter = new PathInterpreter();
        ArrayList<ArrayList> routes;
        switch (map) {
            case 1:
                SingletonGame.getInstance().setMap(Art.map_1, 15, 15);
                routes = pathInterpreter.parseMap(PATHS_MAP_1);
                SingletonGame.getInstance().giveMoney(800);
                break;
            case 2:
                SingletonGame.getInstance().setMap(Art.map_2, 15, 15);
                routes = pathInterpreter.parseMap(PATHS_MAP_2);
                SingletonGame.getInstance().giveMoney(1600);
                break;
            default:
                SingletonGame.getInstance().setMap(Art.map_0, 15, 15);
                routes = pathInterpreter.parseMap(PATHS_MAP_0);
                SingletonGame.getInstance().giveMoney(400);
        }
        for (int n = 0; n < routes.size(); n++) {
            SingletonGame.getInstance().addRoute(routes.get(n));
        }
        SingletonGame.getInstance().record = Main.getInstance().getRecord(map);
    }
    
    /**
     * Crea un hilo y lo arranca
     */
    @Override
    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * Metodo principal de ejecución. Lleva la cuenta del tiempo, controla
     * que se ejecuten las acciones 60 veces por segundo exactas y ejecuta
     * los métodos para pintar la pantalla
     */
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long unprocessedTime = 0;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }


        while (true) {
            long now = System.nanoTime();
            unprocessedTime += now - lastTime;
            lastTime = now;

            int max = 10;
            if (!pause) {
                while (unprocessedTime > 0) {
                    unprocessedTime -= 1000000000 / 60;
                    // Contador de iteraciones
                    i++;

                    if (i >= 400 && i % 80 == 0) {
                        if (i >= 1000) {
                            SingletonGame.getInstance().addChicken(i / 1000);
                        }
                        else {
                            SingletonGame.getInstance().addChicken(1);
                        }
                    }
                    SingletonGame.getInstance().step();

                    if (max-- == 0) {
                        unprocessedTime = 0;
                        break;
                    }
                }
        }

            repaint();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {;
            }
        }
    }
    
    /**
     * Pasa el Graphics con el que se va a dibujar al singleton de graficos
     * y llama al metodo de dibujo del singleton de juego.
     * 
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        SingletonGraphics.getInstance().setGraphics(g);
        SingletonGame.getInstance().draw();
    }
    
    /**
     * Metodo donde se implementa el doble buffer para evitar el flickering
     * en la pantalla al dibujar
     */
    @Override
    public void update(Graphics g) {
        if (doubleBufferedImage == null) {
            doubleBufferedImage = createImage(getSize().width, getSize().height);
            doubleBufferedGraphics = doubleBufferedImage.getGraphics();
        }
        paint(doubleBufferedGraphics);
        g.drawImage(doubleBufferedImage, 0, 0, this);
    }
    
    /**
     * Controla el evento del raton al arrastrarlo. Va reposicionando
     * el cursor de juego por el mapa colocandolo en la casilla donde
     * se encuentra el puntero del raton
     * 
     * @param e
     * @param x_mouse
     * @param y_mouse
     * @return boolean
     */
    @Override
    public boolean mouseDrag(Event e, int x_mouse, int y_mouse) {
        Point.Double p = new Point.Double();
        
        if (Grid.isAMapPosition(x_mouse, y_mouse)) {
            p.setLocation(Grid.getBoxMiddle(x_mouse, y_mouse));
            /* Comprobamos si el puntero esta en la casilla pintada */
            if (SingletonGame.getInstance().cursor.position.distance(p) != 0) {
                /* Si el puntero no esta en la casilla, lo movemos */
                SingletonGame.getInstance().cursor.position = p;
            }
        }
        
        return true;
    }
    
    /**
     * Controla el evento de click del raton. Comprueba si se debe mover el
     * cursor o si se ha pinchado en un botón de la pantalla de juego.
     * 
     * @param e
     * @param x_mouse
     * @param y_mouse
     * @return boolean
     */
    @Override
    public boolean mouseUp(Event e, int x_mouse, int y_mouse) {
        int x = x_mouse;
        int y = y_mouse;
        Point.Double p = new Point.Double();
        boolean canBuild = false;
        nettower.entity.Tower tower = null;
        
        if (Grid.isAMapPosition(x_mouse, y_mouse)) {
            p.setLocation(Grid.getBoxMiddle(x_mouse, y_mouse));
                SingletonGame.getInstance().cursor.position = p;
        } else {
            if (Grid.isAMenuPosition(x_mouse, y_mouse)) {
                if (SingletonGame.getInstance().isBuildable()) {
                    tower = SingletonGame.getInstance().getTowerAt();
                }
                switch (getMenuElementType(x_mouse, y_mouse)) {
                        case "common":
                            if (tower == null)
                                SingletonGame.getInstance().addTower(0);
                            break;
                        case "area":
                            if (tower == null)
                                SingletonGame.getInstance().addTower(1);
                            break;
                        case "heavy":
                            if (tower == null)
                                SingletonGame.getInstance().addTower(2);
                            break;
                        case "upgrade":
                            if (tower != null)
                                tower.upgrade();
                            break;
                        case "menu":
                            Main.getInstance().pause();
                            Main.getInstance().endGame();
                            break;
                        case "pause":
                            Main.getInstance().pause();
                            break;
                    }
                
            }
        }
        
        return true;
    }
    
    /**
     * Controla los eventos de las teclas. Comprueba si se ha pulsado
     * la tecla ESCAPE y pausa el juego en caso afirmativo
     * 
     * @param e
     * @param key
     * @return boolean
     */
    @Override
    public boolean keyUp(Event e, int key) {
        switch (key) {
            case KeyEvent.VK_ESCAPE:
                Main.getInstance().pause();
                return true;
            default:
                return false;
        }
    }
    
    /**
     * Dada una posición de la pantalla de juego, comprueba si es un botón
     * del menu y devuelve un string que indica el boton que es.
     * 
     * @param ox
     * @param oy
     * @return String
     */
    private String getMenuElementType(int ox, int oy) {
        String element="nothing";
        
        if (ox >= 489 && ox <= 730);
        /* Torreta normal */
        if (oy >= 150 && oy <= 186) element = "common";
        else
            /* Torreta de area */ 
            if (oy >= 190 && oy <= 226) element = "area";
        else 
            /* Torreta heavy */
            if (oy >= 230 && oy <= 266) element = "heavy";
        else 
            /* Upgrade */
            if (oy >= 270 && oy <= 306) element = "upgrade";
        else 
            /* Pausa */
            if (oy >= 333 && oy <= 386) element = "pause";
        else 
            /* Menu principal */
            if (oy >= 416 && oy <= 454) element = "menu";

        return element;
    }
    
    /**
     * ########################################################################################
     * 
     * @return int
     */
    public int end() {
        pause = true;
        return SingletonGame.getInstance().getPoints();
    }
    
    /**
     * Cambia el estado de juego a pausado
     */
    public void pause() {
        pause = true;
    }
    
    /**
     * Cambia el estado de juego a funcionando
     */
    public void resume() {
        pause = false;
    }
}
