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
    public int upgradeCost;
    int recharge;
    
    public Tower(BufferedImage iniImage, int iniRadiusSize, Point.Double iniPosition, int iniDamage, int iniFireRate, int iniBulletsSpeed, int iniRange, int iniUpgradeCost) {
        super(iniImage, iniRadiusSize, iniPosition);
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
        if (SingletonGame.getInstance().canPay(upgradeCost)) {
            SingletonGame.getInstance().takeMoney(upgradeCost);
            onUpgrade();
            recharge = fireRate;
        }
    }
    
    public abstract void onUpgrade();

    @Override
    public void remove() {
        SingletonGame.getInstance().removeTower(this);
    }
}
