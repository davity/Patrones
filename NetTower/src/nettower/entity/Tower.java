/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Point;
import nettower.Art;
import nettower.singleton.SingletonGame;

/**
 *
 * @author David Moran Diaz
 */
public class Tower extends Entity {
    int damage;
    int fireRate;
    int bulletsSpeed;
    int range;
    int recharge;
    
    public Tower(Point.Double iniPosition, int iniDamage, int iniFireRate, int iniBulletsSpeed, int iniRange) {
        super(iniPosition, 16, Art.tower);
        damage = iniDamage;
        fireRate = iniFireRate;
        bulletsSpeed = iniBulletsSpeed;
        range = iniRange;
        
        recharge = fireRate;
    }

    @Override
    public void step() {
        if (recharge == 0) {
            Chicken target = SingletonGame.getInstance().getNearestInRangeChicken(getPosition(), range);
            if (target != null) {
                SingletonGame.getInstance().addBullet(getPosition(), target, damage, bulletsSpeed);
                recharge = fireRate;
            }
        }
        else {
            recharge--;
        }
    }
    
    public void upgrade() {
        damage *= 0.1;
        fireRate *= 0.1;
        bulletsSpeed *= 0.1;
        range *= 0.1;
        recharge = fireRate;
    }

    @Override
    public void remove() {
        SingletonGame.getInstance().removeTower(this);
    }
}
