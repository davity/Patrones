/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import nettower.Art;

/**
 *
 * @author David Moran Diaz
 */
public class Entity {

    public double x;
    public double y;
    BufferedImage art;

    public Entity(int posx, int posy) {
        this.x = posx;
        this.y = posy;
    }

    //NOT USED
    public void setPosition(int posx, int posy) {
        this.x = posx;
        this.y = posy;
    }

    public void draw(Graphics g) {
        g.drawImage(art, (int) x, (int) y, null);
    }
}
