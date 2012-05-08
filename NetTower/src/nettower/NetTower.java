/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import nettower.entity.Defense;
import nettower.entity.Invasion;
import java.applet.Applet;
import java.awt.*;
import javax.swing.JFrame;

/**
 *
 * @author David Moran Diaz
 */
public class NetTower extends Applet implements Runnable {

    public static final int GAME_WIDTH = 320;
    public static final int GAME_HEIGHT = 240;
    public static final int SCREEN_SCALE = 2;
    int i;
    Invasion invasion;
    Defense defense;
    public int x;
    public int y;
    public int ancho;
    public int alto;
    public int xspeed;
    public int yspeed;
    // Imagen para el double-buffer
    private Image dbImage;
    private Graphics dbg;

    public NetTower() {
        setPreferredSize(new Dimension(GAME_WIDTH * SCREEN_SCALE, GAME_HEIGHT * SCREEN_SCALE));
    }

    @Override
    public void init() {
        invasion = new Invasion();
        defense = new Defense();
        i = 0;
        x = 100;
        y = 100;
        ancho = 20;
        alto = 20;
        xspeed = +1;
        yspeed = +1;
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
                if (x >= (this.getSize().width - ancho)) {
                    x = this.getSize().width - ancho;
                    xspeed = -1;
                    Sound.bounce.play();
                } else if (x <= 0) {
                    x = 0;
                    xspeed = +1;
                    Sound.bounce.play();
                }
                x += 10 * xspeed;

                // Movimiento vertical
                if (y >= (this.getSize().height - ancho)) {
                    y = this.getSize().height - ancho;
                    yspeed = -1;
                    Sound.bounce.play();
                } else if (y <= 0) {
                    y = 0;
                    yspeed = +1;
                    Sound.bounce.play();
                }
                y += 10 * yspeed;
                
                //Avance de las gallinas
                invasion.step();
                if (i % 200 == 0) {
                    invasion.addEnemy();
                }
                
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
        g.drawString("Hola mundo!", 100, 50);
        g.drawString("i = " + i, 30, 30);
        g.setColor(Color.RED);
        g.fillRect(x, y, 20, 20);
        invasion.draw(g);
        defense.draw(g);
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
        
        defense.addTower(x, y);

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
