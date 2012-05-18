/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Point;
import java.util.ArrayList;
import nettower.Art;
import nettower.singleton.SingletonGame;

/**
 *
 * @author David
 */
public class AreaTower extends Tower {
    public AreaTower(Point.Double iniPosition) {
        super(Art.tower, 16, iniPosition, 1, 200, 10, 200, 200);
    }
    
    @Override
    public void shoot() {
        ArrayList<Chicken> targets = SingletonGame.getInstance().getInRangeChickens(position, range);
        for (int n = 0; n < targets.size(); n++) {
            SingletonGame.getInstance().addBullet(Art.bullet, 4, position, targets.get(n), damage, bulletsSpeed);
        }
    }

    @Override
    public void onUpgrade() {
        damage *= 1.1;
        bulletsSpeed *= 1.1;
        range *= 1.1;
        upgradeCost *= 1.1;
    }
}
