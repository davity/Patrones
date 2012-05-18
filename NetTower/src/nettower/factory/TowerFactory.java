/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.factory;

import java.awt.Point;
import nettower.entity.AreaTower;
import nettower.entity.CommonTower;
import nettower.entity.HeavyTower;
import nettower.entity.Tower;

/**
 *
 * @author David
 */
public class TowerFactory extends Factory {
    public Tower getTower(Point.Double position, int type) {
        switch (type) {
            case 1: return new HeavyTower(position);
            case 2: return new AreaTower(position);
            default: return new CommonTower(position);
        }
    }
}
