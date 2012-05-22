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
public class Pause extends Applet {
    public Pause() {}
    
    @Override
    public void paint(Graphics g) {
        //g.drawImage(, , , this);
    }
    
    @Override
    public boolean mouseUp(Event e, int x, int y) {
        Main.getInstance().resume();
        Main.getInstance().endGame();
        return true;
    }
}