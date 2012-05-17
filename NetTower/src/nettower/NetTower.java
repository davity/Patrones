/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import nettower.Grid;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import nettower.map.Map;
import nettower.singleton.SingletonGame;
import nettower.singleton.SingletonGraphics;

/**
 *
 * @author David Moran Diaz
 */
public class NetTower extends Applet implements Runnable {

    public static final int GAME_WIDTH = 640;
    public static final int GAME_HEIGHT = 480;
    //public static final int SCREEN_SCALE = 2;
    int i;
    Grid grid;
    public int x;
    public int y;
    public int ancho;
    public int alto;
    public int xspeed;
    public int yspeed;
    // Imagen para el double-buffer
    private Image dbImage;
    private Graphics dbg;
    private Input input = new Input();
    private Map level;

    public NetTower() {
        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
    }

    @Override
    public void init() {
        grid = new Grid();

        i = 0;
        x = 100;
        y = 100;
        ancho = 32;
        alto = 32;
        xspeed = +1;
        yspeed = +1;
        
        SingletonGame.getInstance().setMap(Art.map1, 15, 15);
        
        ArrayList<Point.Double> route = new ArrayList();
        route.add(new Point.Double(300,0));
        route.add(new Point.Double(300,400));
        route.add(new Point.Double(100,400));
        route.add(new Point.Double(100,200));
        route.add(new Point.Double(400,200));
        SingletonGame.getInstance().addRoute(route);
        
        route = new ArrayList();
        route.add(new Point.Double(0,300));
        route.add(new Point.Double(400,300));
        route.add(new Point.Double(400,100));
        route.add(new Point.Double(200,100));
        route.add(new Point.Double(200,400));
        SingletonGame.getInstance().addRoute(route);
        
        route = new ArrayList();
        route.add(new Point.Double(0, 100));
        route.add(new Point.Double(500,100));
        route.add(new Point.Double(500,200));
        route.add(new Point.Double(0,200));
        route.add(new Point.Double(0,300));
        route.add(new Point.Double(400,300));
        route.add(new Point.Double(400,400));
        route.add(new Point.Double(0,400));
        route.add(new Point.Double(0,500));
        SingletonGame.getInstance().addRoute(route);
    }

    @Override
    public void start() {
        Thread t = new Thread(this);
        t.start();
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
            while (unprocessedTime > 0) {
                unprocessedTime -= 1000000000 / 60;
                // Contador de iteraciones
                i++;



                // Movimiento horizontal
                if (x >= (GAME_HEIGHT - ancho)) {
                    x = GAME_HEIGHT - ancho;
                    xspeed = -1;
                    //Sound.bounce.play();
                } else if (x <= 0) {
                    x = 0;
                    xspeed = +1;
                    //Sound.bounce.play();
                }
                x += 10 * xspeed;

                // Movimiento vertical
                if (y >= (GAME_HEIGHT - ancho)) {
                    y = GAME_HEIGHT - ancho;
                    yspeed = -1;
                    //Sound.bounce.play();
                } else if (y <= 0) {
                    y = 0;
                    yspeed = +1;
                    //Sound.bounce.play();
                }
                y += 10 * yspeed;

                //Avance de las gallinas
                if (i % 200 == 0) {
                    SingletonGame.getInstance().addChicken();
                }
                SingletonGame.getInstance().step();

                if (max-- == 0) {
                    unprocessedTime = 0;
                    break;
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
        BufferedImage chicken = Art.chicken;
//        g.drawString("Hola mundo!", 100, 50);
//        g.drawString("i = " + i, 30, 30);

//        // Zona del mapa
//        g.setColor(Color.BLACK);
//        g.fillRect(0, 0, GAME_HEIGHT, GAME_HEIGHT);
//        // Zona del menu
//        g.setColor(Color.GREEN);
//        g.fillRect(GAME_HEIGHT, 0, GAME_WIDTH - GAME_HEIGHT, GAME_HEIGHT);

        // Testeando colores pal mapa
//        char[] boxes = map.parseMap(1);
//        if (boxes[0] == 'R') {
//            g.setColor(Color.GRAY);
//            g.fillRect(0, 0, 32, 32);
//        }
        SingletonGraphics.getInstance().setGraphics(g);
        SingletonGame.getInstance().draw();
        //oculta temporalmente para facilitar la visivilidad
        //g.drawImage(chicken, x, y, null);
    }

    @Override
    public void update(Graphics g) {

        // Inicializamos el buffer
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        // Limpiamos la pantalla en background
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // Pintamos los elementos en background
        dbg.setColor(getForeground());
        paint(dbg);

        // Pintamos la imagen en pantalla
        g.drawImage(dbImage, 0, 0, this);
    }

    public boolean mouseDown(Event e, int x_mouse, int y_mouse) {
        x = x_mouse;
        y = y_mouse;
        Point p = new Point();
        p = Grid.getBoxMiddle(x_mouse, y_mouse);
        Point.Double p2 = new Point.Double();
        p2.setLocation(p);
        nettower.entity.Tower tower = SingletonGame.getInstance().getTowerAt(p2);
        if (tower != null) {
            tower.upgrade();
        } else {
            SingletonGame.getInstance().addTower(p2);
        }

        return true;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("NetTower");
        NetTower nettower = new NetTower();
        frame.setLayout(new BorderLayout());
        frame.add(nettower, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        nettower.init();
        nettower.start();
    }
}
