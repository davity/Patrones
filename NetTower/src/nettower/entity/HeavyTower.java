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
 * @author David
 */
public class HeavyTower extends Tower {
    public HeavyTower(Point.Double iniPosition) {
        super(Art.heavyTower, 16, iniPosition, 200, 400, 2, 200, 200);
    }
    
    @Override
    public void shoot() {
        Chicken target = SingletonGame.getInstance().getNearestInRangeChicken(position, range);
        if (target != null) {
            SingletonGame.getInstance().addBullet(Art.purpleBullet, 4, position, target, damage, bulletsSpeed);
        }
        recharge = fireRate;
    }

    @Override
    public void onUpgrade() {
        damage *= 2;
        range *= 1.1;
        upgradeCost *= 1.5;
    }
}
