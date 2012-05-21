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
public class CommonTower extends Tower {
    public CommonTower(Point.Double iniPosition) {
        super(Art.tower, 16, iniPosition, 20, 100, 10, 100, 200);
    }
    
    @Override
    public void shoot() {
        Chicken target = SingletonGame.getInstance().getNearestInRangeChicken(position, range);
        if (target != null) {
            SingletonGame.getInstance().addBullet(Art.redBullet, 4, position, target, damage, bulletsSpeed);
        }
        recharge = fireRate;
    }

    @Override
    public void onUpgrade() {
        damage *= 1.1;
        fireRate *= 0.9;
        bulletsSpeed *= 1.1;
        range *= 1.1;
        upgradeCost *= 1.1;
    }
}
