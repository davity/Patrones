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
    }
    
    @Override
    public Point.Double getPointTarget() {
        Point.Double target = new Point.Double();
        target.x += mom.position.x + 10;
        target.y += mom.position.y + 10;
        return target;
    }
    
    @Override
    public void onStep() {}
    
    @Override
    public void onReach() {
        if (!route.hasNext())
        {
            SingletonGame.getInstance().takeALife();
            remove();
        }
    }
}
