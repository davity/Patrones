/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Color;
import java.awt.Graphics;
import nettower.*;
import java.util.ArrayList;
import java.awt.Point;

/**
 *
 * @author David Moran Diaz
 */
public class Chicken extends Entity {

    public int life;
    public double speed;
    public Invasion invasion;
    ArrayList route;

    public Chicken(int iniLife, double iniSpeed, Invasion i, int x, int y, ArrayList iniRoute) {
        super(x, y);
        this.life = iniLife;
        this.speed = iniSpeed;
        this.invasion = i;
        this.art = Art.chicken;
        this.route = iniRoute;
    }

    public void damage(int damage) {
        if (life > 0) {
            life -= damage;
            if (life <= 0) {
                this.die();
            }
        }
    }
    
    //Muy similar a Shoot.step(), quizás deberiamos fusionarlas
    public void advance() {
        double tmpx, tmpy, tmph;
        
        tmpx = ((Point)route.get(0)).x - x;
        tmpy = ((Point)route.get(0)).y - y;
        tmph = Math.sqrt(Math.pow(tmpx, 2) + Math.pow(tmpy, 2));
        if (tmph <= speed) {
            route.remove(0);
            if (route.size() <= 0) {
                this.remove();
            }
        }
        x = x + speed * (tmpx / tmph);
        y = y + speed * (tmpy / tmph);
    }
    
    public void remove() {
        invasion.delete(this);
    }

    public void die() {
        remove();
    }
}
