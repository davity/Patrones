/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Color;
import java.awt.Graphics;
import nettower.*;

/**
 *
 * @author David Moran Diaz
 */
public class Chicken extends Entity {

    public int life;
    public double speed;
    public Invasion invasion;

    public Chicken(int iniLife, double iniSpeed, Invasion i, int x, int y) {
        super(x, y);
        this.life = iniLife;
        this.speed = iniSpeed;
        this.invasion = i;
        art = Art.chicken;
    }

    public void advance() {
        x += speed;
        y += speed;
    }

    public void damage(int damage) {
        life -= damage;
        if (life <= 0) {
            this.die();
        }
    }

    public void die() {
        invasion.delete(this);
    }
}
