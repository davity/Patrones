
package nettower;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Pause extends Applet {
    
    /**
     * Constructor de Pause
     */
    public Pause() {}
    
    /**
     * Dado un Graphics dibuja el menu de pausa
     * 
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        /* Boton 1 */
        g.setColor(Color.LIGHT_GRAY);
        g.drawRoundRect(0, 0, 640, 480, 0, 0);
        
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(120, 170, 165, 75, 15, 15);
        g.setColor(Color.GRAY);
        g.fillRoundRect(123, 173, 158, 69, 15, 15);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString("CONTINUAR", 135, 215);
        
        /* Boton 2 */
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(420, 170, 100, 75, 15, 15);
        g.setColor(Color.GRAY);
        g.fillRoundRect(423, 173, 94, 69, 15, 15);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString("MENU", 440, 215);
    }
    
    /**
     * Majena los eventos de click. Comprueba si se ha pulsado
     * algun boton dentro del menu de pausa y actua en consecuencia
     * 
     * @param e
     * @param x
     * @param y
     * @return boolean
     */
    @Override
    public boolean mouseUp(Event e, int x, int y) {
        if (x >= 120 && x <= 285 && y >= 170 && y <= 245)
            Main.getInstance().resume();
        if (x >= 420 && x <= 520 && y >= 170 && y <= 245)
            Main.getInstance().endGame();
        return true;
    }
    
    /**
     * Manejo de eventos de teclado. Si se pulsa ESCAPE el juego se reanuda
     * 
     * @param e
     * @param key
     * @return boolean
     */
    @Override
    public boolean keyUp(Event e, int key) {
        switch (key) {
            case KeyEvent.VK_ESCAPE:
                Main.getInstance().resume();
                return true;
            default:
                return false;
        }
    }
}