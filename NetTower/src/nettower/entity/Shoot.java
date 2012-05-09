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
        if (target.x > x) {
            x += speed;
            if (target.x <= x) {
                x = target.x;
            }
        } else {
            x -= speed;
            if (target.x >= x) {
                x = target.x;
            }
        }
        if (target.y > y) {
            y += speed;
            if (target.y <= y) {
                y = target.y;
            }
        } else {
            y -= speed;
            if (target.y >= y) {
                y = target.y;
            }
        }
        if (x == target.x && y == target.y) {
            this.impact();
        }
    }

    public void impact() {
        target.damage(damage);
        defense.delShoot(this);
    }
}
