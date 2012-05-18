/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Point;
import java.awt.image.BufferedImage;
import nettower.singleton.SingletonGame;

/**
 *
 * @author David Moran Diaz
 */
public abstract class Tower extends Entity {
    int damage;
    int fireRate;
    int bulletsSpeed;
    int range;
    int upgradeCost;
    int recharge;
    
    public Tower(Point.Double iniPosition, int iniRadiusSize, BufferedImage iniImage, int iniDamage, int iniFireRate, int iniBulletsSpeed, int iniRange, int iniUpgradeCost) {
        super(iniPosition, iniRadiusSize, iniImage);
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
            shoot();
        }
        else {
            recharge--;
        }
    }
    
    public abstract void shoot();
    
    public void upgrade() {
        SingletonGame.getInstance().takeMoney(upgradeCost);
        onUpgrade();
        recharge = fireRate;
    }
    
    public abstract void onUpgrade();

    @Override
    public void remove() {
        SingletonGame.getInstance().removeTower(this);
    }
}
