/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;

/**
 *
 * @author David
 */
public class Button extends Applet {
    public Button() {
        setPreferredSize(new Dimension(340, 40));
        setBackground(Color.CYAN); //CAMBIAR POR DIBUJO
        //Graphics g = getGraphics();
    }
    
    @Override
    public boolean mouseDown(Event e, int x, int y) {
        setBackground(Color.BLUE); //CAMBIAR POR DIBUJO
        return true;
    }
    
    @Override
    public boolean mouseUp(Event e, int x, int y) {
        setBackground(Color.CYAN); //CAMBIAR POR DIBUJO
        Main.getInstance().startGame();
        return true;
    }
}