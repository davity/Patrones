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
public class ChickChicken extends Chicken {
    ArrayList<ChickChicken> chicks = new ArrayList();
    ChickChicken mom;
    
    public ChickChicken() {
        super(Art.chickChicken, Art.chickChickenI, 16, SingletonGame.getInstance().getRandomRoute(), 10, 4, 4, 4);
        while (SingletonGame.getInstance().random.nextDouble() <= 0.9) {
            ChickChicken chick = new ChickChicken(this, 0.8);
            SingletonGame.getInstance().insertSpecificChicken(chick);
            chicks.add(chick);
        }
        while (SingletonGame.getInstance().random.nextDouble() <= 0.9) {
            ChickChicken chick = new ChickChicken(this, 0.8);
            SingletonGame.getInstance().insertSpecificChicken(chick);
            chicks.add(chick);
        }
    }
    
    public ChickChicken(ChickChicken iniMom, double probability) {
        super(Art.chickChicken, Art.chickChickenI, 16, iniMom.route, 10, 4, 4, 4);
        mom = iniMom;
        double chickProbability = probability * 0.7;
        while (SingletonGame.getInstance().random.nextDouble() <= probability) {
            ChickChicken chick = new ChickChicken(this, chickProbability);
            SingletonGame.getInstance().insertSpecificChicken(chick);
            chicks.add(chick);
        }
        route = null;
    }
    
    public void addChicks(ArrayList<ChickChicken> newChicks) {
        chicks.addAll(newChicks);
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
    public void onStep() {}
    
    @Override
    public void remove() {
        super.remove();
        if (route != null) {
            for (int n = 0; n < chicks.size(); n++) {
                chicks.get(n).route = route.clone();
            }
        }
        else {
            mom.addChicks(chicks);
            for (int n = 0; n < chicks.size(); n++) {
                chicks.get(n).mom = mom;
            }
        }
    }
}