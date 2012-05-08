/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Color;
import java.awt.Graphics;
import nettower.Art;
import nettower.Sound;

/**
 *
 * @author David Moran Diaz
 */
public class Tower extends Entity {
    
    public int damage;
    public int fireRate;
    public int range;
    private int recharge;
    private Defense defense;

    public Tower(int iniDamage, int iniFireRate, int iniRange, double x, double y, Defense def) {
        super((int) x, (int) y);
        this.damage = iniDamage;
        this.fireRate = iniFireRate;
        this.range = iniRange;
        this.art = Art.tower;
        this.recharge = iniFireRate;
        this.defense = def;
    }
    
    public void shoot(Invasion invasion) {
        if (recharge <= 0) {
            Chicken target = invasion.nearestChickenOnRange(range, (int)x, (int)y);
            if (target != null) {
                defense.addShoot(new Shoot((int)x, (int)y, target, damage, 1, defense));
                Sound.bounce.play();
            }
        }
        else {
            recharge--;
        }
    }
}