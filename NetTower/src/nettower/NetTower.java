/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
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
    public int x;
    public int y;
    // Imagen para el double-buffer
    private Image dbImage;
    private Graphics dbg;

    public NetTower() {
        setPreferredSize(new Dimension(GAME_WIDTH * SCREEN_SCALE, GAME_HEIGHT * SCREEN_SCALE));
    }

    @Override
    public void init() {
        i = 0;
        x = 100;
        y = 100;
    }

    @Override
    public void start() {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            i++;
            x++;

            repaint();

            try {
                Thread.sleep(1000 / 30);
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Metagun");
        NetTower nettower = new NetTower();
        frame.setLayout(new BorderLayout());
        frame.add(nettower, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        nettower.init();
        nettower.start();
    }
}
