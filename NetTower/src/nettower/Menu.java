/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *
 * @author David
 */
public class Menu extends Applet {
    
    private boolean showInstructions;
    
    /**
     * Constructor de Menu. Establece la variable de mostrar ayuda a falso
     */
    public Menu() {
        showInstructions = false;
    }
    
    /**
     * Metodo que pinta la pantalla del menu. Muestra toda la interfaz
     * o la ayuda en funcion de la variable showInstructions
     * 
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        if (!showInstructions) {
            g.drawImage(Art.mainMenu, 0, 0, this);
            g.setColor(Color.BLUE);
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString("Mejor puntuación:", 54, 262);
            g.drawString("Mejor puntuación:", 254, 262);
            g.drawString("Mejor puntuación:", 454, 262);
            g.drawString(Main.getInstance().getRecord(0), 54, 292);
            g.drawString(Main.getInstance().getRecord(1), 254, 292);
            g.drawString(Main.getInstance().getRecord(2), 454, 292);
        } else {
            g.drawImage(Art.instructions, 0, 0, this);
        }
            
    }
    
    /**
     * Controla los eventos de click para comenzar una partida en un mapa,
     * mostrar las instrucciones o salir del juego
     * 
     * @param e
     * @param x
     * @param y
     * @return boolean
     */
    @Override
    public boolean mouseUp(Event e, int x, int y) {
        
        if (!showInstructions) {
            int itemPressed;

            itemPressed = getItemPressed(x, y);
            switch (itemPressed) {
                case 1:
                    Main.getInstance().startGame(0);
                    return true;
                case 2:
                    Main.getInstance().startGame(1);
                    return true;
                case 3:
                    Main.getInstance().startGame(2);
                    return true;
                case 4:
//                    Main.getInstance().showHelp();
                    showInstructions = true;
                    repaint();
                    return true;
                case 5:
                    Main.getInstance().exit();
                    return true;
                default:
                    return false;
            }
        } else {
            showInstructions = false;
            repaint();
            return true;
        }
    }
    
    /**
     * Comprueba que boton del menu se ha pulsado
     * 
     * @param x
     * @param y
     * @return int
     */
    private int getItemPressed(int x, int y) {
        if (x >= 46 && x <= 198 && y >= 135 && y < 342) return 1;
        if (x >= 246 && x <= 398 && y >= 135 && y < 342) return 2;
        if (x >= 446 && x <= 598 && y >= 135 && y < 342) return 3;
        if (x >= 32 && x <= 178 && y >= 369 && y < 415) return 4;
        if (x >= 457 && x <= 566 && y >= 369 && y < 414) return 5;
        return 0;
    }
    
    /**
     * Comprueba si se estan mostrando las instrucciones y se ha pulsado
     * la tecla escape. En caso afirmativo, vuelve al menu principal.
     * 
     * @param e
     * @param key
     * @return boolean
     */
    @Override
    public boolean keyUp(Event e, int key) {
        if (showInstructions) {
            switch (key) {
                case KeyEvent.VK_ESCAPE:
                    showInstructions = false;
                    repaint();
                    return true;
                default:
                    return false;
            }
        }
        return true;
    }
}