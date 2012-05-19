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
public class ChickChicken extends Chicken {
    Chicken mom;
    
    public ChickChicken(Chicken iniMom) {
        super(Art.chicken, 16, iniMom.route, 10, 4, 4, 4);
        mom = iniMom;
        if (SingletonGame.getInstance().random.nextBoolean()) {
            SingletonGame.getInstance().insertSpecificChicken(new ChickChicken(this));
            SingletonGame.getInstance().insertSpecificChicken(new ChickChicken(this));
        }
        route = null;
    }
    
    @Override
    public Point.Double getPointTarget() {
        if (route != null) {
            return super.getPointTarget();
        }
        else {
            return new Point.Double(mom.position.x + SingletonGame.getInstance().random.nextInt(16) - SingletonGame.getInstance().random.nextInt(16), mom.position.y + SingletonGame.getInstance().random.nextInt(16) - SingletonGame.getInstance().random.nextInt(16));
        }
    }
    
    @Override
    public void onReach() {
        if (route != null) {
            super.onReach();
        }
    }
    
    @Override
    public void onStep() {
        if (route == null && !mom.exist()) {
            route = mom.route.clone();
        }
    }
}
