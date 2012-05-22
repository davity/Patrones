/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import java.applet.Applet;
import java.awt.Event;

/**
 *
 * @author David
 */
public class Pause extends Applet {
    public Pause() {}
    
    @Override
    public boolean keyDown(Event e, int key) {
        Main.getInstance().resume();
        return true;
    }
}