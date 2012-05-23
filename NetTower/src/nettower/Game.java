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
    private static final String PATHS_MAP_1 = "entrada.txt";
    private static final String PATHS_MAP_2 = "entrada.txt";
    private static final String PATHS_MAP_3 = "entrada.txt";
    
    public Game() {}
    
    @Override
    public void init() {
        i = 0;
        pause = false;
        PathInterpreter pathInterpreter = new PathInterpreter();
        ArrayList<ArrayList> routes;
        switch (map) {
            case 1:
                SingletonGame.getInstance().setMap(Art.map1, 15, 15);
                routes = pathInterpreter.parseMap(PATHS_MAP_2);
                break;
            case 2:
                SingletonGame.getInstance().setMap(Art.map1, 15, 15);
                routes = pathInterpreter.parseMap(PATHS_MAP_3);
                break;
            default:
                SingletonGame.getInstance().setMap(Art.map1, 15, 15);
                routes = pathInterpreter.parseMap(PATHS_MAP_1);
        }
        for (int n = 0; n < routes.size(); n++) {
            SingletonGame.getInstance().addRoute(routes.get(n));
        }
        SingletonGame.getInstance().clear();
        SingletonGame.getInstance().record = Main.getInstance().getRecord(map);
    }
    
    @Override
    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
    
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

                    if (i % 100 == 0) {
                        SingletonGame.getInstance().addChicken(i/1000);
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
    
    @Override
    public void paint(Graphics g) {
        SingletonGraphics.getInstance().setGraphics(g);
        SingletonGame.getInstance().draw();
    }
    
    @Override
    public void update(Graphics g) {
        if (doubleBufferedImage == null) {
            doubleBufferedImage = createImage(getSize().width, getSize().height);
            doubleBufferedGraphics = doubleBufferedImage.getGraphics();
        }
        paint(doubleBufferedGraphics);
        g.drawImage(doubleBufferedImage, 0, 0, this);
    }
    
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
    
    @Override
    public boolean mouseDown(Event e, int x_mouse, int y_mouse) {
        int x = x_mouse;
        int y = y_mouse;
        Point.Double p = new Point.Double();
        boolean canBuild = false;
        nettower.entity.Tower tower = null;
        
        if (Grid.isAMapPosition(x_mouse, y_mouse)) {
            p.setLocation(Grid.getBoxMiddle(x_mouse, y_mouse));
            /* Comprobamos si el puntero esta en la casilla pintada */
//            if (SingletonGame.getInstance().cursor.position.distance(p) == 0) {
//                /* Actualizar torreta si hay una torreta en la casilla */
//                nettower.entity.Tower tower = SingletonGame.getInstance().getTowerAt();
//                if (tower != null) {
//                    tower.upgrade();
//                }
//            } else {
                /* Si el puntero no esta en la casilla, lo movemos */
                SingletonGame.getInstance().cursor.position = p;
//            }
        } else {
            if (Grid.isAMenuPosition(x_mouse, y_mouse)) {
                if (SingletonGame.getInstance().isBuildable()) {
                    tower = SingletonGame.getInstance().getTowerAt();
                }
                switch (Grid.getMenuElementType(x_mouse, y_mouse)) {
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
                        case "pause":
                            Main.getInstance().pause();
                            break;
                        case "menu":
                            /* 
                             * ESTE ENDGAME SOLO REINICIA LA PANTALLA!!
                             */
                            Main.getInstance().pause();
                            Main.getInstance().endGame();
                            break;
                    }
                
            }
        }
        
        return true;
    }
    
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
    
    public int end() {
        pause = true;
        return SingletonGame.getInstance().getPoints();
    }
    
    public void pause() {
        pause = true;
    }
    
    public void resume() {
        pause = false;
    }
}