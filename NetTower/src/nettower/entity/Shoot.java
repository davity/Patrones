/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import nettower.Art;

/**
 *
 * @author David
 */
public class Shoot extends Entity {

    Chicken target;
    int damage;
    int speed;
    Defense defense;

    public Shoot(int x, int y, Chicken iniTarget, int iniDamage, int iniSpeed, Defense iniDefense) {
        super((int) x, (int) y);
        art = Art.blueShoot;
        this.target = iniTarget;
        this.damage = iniDamage;
        this.speed = iniSpeed;
        this.defense = iniDefense;
    }

    public void step() {
        double tmpx, tmpy, tmph;
        
        tmpx = target.x - x;
        tmpy = target.y - y;
        tmph = Math.sqrt(Math.pow(tmpx, 2) + Math.pow(tmpy, 2));
        if (tmph <= speed) {
            this.impact();
        }
        x = x + speed * (tmpx / tmph);
        y = y + speed * (tmpy / tmph);
    }

    public void impact() {
        target.damage(damage);
        defense.delShoot(this);
    }
}
