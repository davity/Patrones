/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import java.applet.Applet;
import java.awt.Event;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public class Menu extends Applet {
    public Menu() {}
    
    @Override
    public void paint(Graphics g) {
        //g.drawImage(, , , this);
    }
    
    @Override
    public boolean mouseUp(Event e, int x, int y) {
        Main.getInstance().startGame();
        return true;
    }
}