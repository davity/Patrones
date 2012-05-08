/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import java.awt.*;

/**
 *
 * @author David
 */
public class Enemy {
    private int position;
    
    public Enemy() {
        position = 0;
    }
    
    public void advance() {
        position++;
    }
    
    public void draw(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(position, 200, 20, 20);
    }
}
