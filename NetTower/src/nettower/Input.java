/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import java.awt.event.MouseEvent;

/**
 *
 * @author David Moran Diaz
 */
public class Input {
    
    public static final int LCLICK = 0;
    public static final int RCLICK = 1;
    
    public boolean[] buttons = new boolean[8];
    public boolean[] oldButtons = new boolean[8];

    public void set(int key, boolean down) {
        int button = -1;

        if (key == MouseEvent.BUTTON1) button = LCLICK;
        if (key == MouseEvent.BUTTON1) button = LCLICK;

        if (button >= 0) {
            buttons[button] = down;
        }
    }
    
    public void tick() {
        System.arraycopy(buttons, 0, oldButtons, 0, buttons.length);
    }

    public void releaseAllKeys() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = false;
        }
    }
}