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
    int upgradeCost;
    int recharge;
    
    public Tower(Point.Double iniPosition, int iniDamage, int iniFireRate, int iniBulletsSpeed, int iniRange, int iniUpgradeCost) {
        super(iniPosition, 16, Art.tower);
        damage = iniDamage;
        fireRate = iniFireRate;
        bulletsSpeed = iniBulletsSpeed;
        range = iniRange;
        upgradeCost = iniUpgradeCost;
        
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
        SingletonGame.getInstance().takeMoney(upgradeCost);
        damage *= 1.1;
        fireRate *= 0.9;
        bulletsSpeed *= 1.1;
        range *= 1.1;
        upgradeCost *= 1.1;
        recharge = fireRate;
    }

    @Override
    public void remove() {
        SingletonGame.getInstance().removeTower(this);
    }
}
