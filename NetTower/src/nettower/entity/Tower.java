/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author David Moran Diaz
 */
public class Tower extends Entity {
    
    public int damage;
    public int fireRate;

    public Tower(int iniDamage, int iniFireRate, double posx, double posy) {
        this.damage = iniDamage;
        this.fireRate = iniFireRate;
        this.x = posx;
        this.y = posy;
    }
    
    public void draw(Graphics g)
    {
        g.setColor(Color.GREEN);
        g.fillRect((int)x, (int)y, 20, 20);
    }
}