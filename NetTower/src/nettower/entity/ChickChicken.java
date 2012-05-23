/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Point;
import java.util.ArrayList;
import nettower.Art;
import nettower.iterator.Iterator;
import nettower.singleton.SingletonGame;

/**
 *
 * @author David
 */
public abstract class ChickChicken extends Chicken {
    ChickChicken mom;
    
    public ChickChicken(Iterator route, double level) {
        super(Art.chickChicken, Art.chickChickenI, 16, route, (int)(10 * level), 2 + (int)(level / 100), (int)(4 * level), (int)(4 * level));
    }
    
    public ChickChicken(ChickChicken iniMom, double level) {
        super(Art.chickChicken, Art.chickChickenI, 16, iniMom.route, (int)(10 * level), 2 + (int)(level / 100), (int)(4 * level), (int)(4 * level));
        mom = iniMom;
    }
    
    /**
     * Añade una lista de gallinas a la lista de gallinas de la horda
     * 
     * @param newChicks 
     */
    public abstract void addChicks(ArrayList<ChickChicken> newChicks);
    
    /**
     * Devuelve el punto al que deben dirigirse las gallinas de la horda (lider)
     */
    @Override
    public Point.Double getPointTarget() {
        if (route != null) {
            return super.getPointTarget();
        }
        else {
            return new Point.Double(mom.position.x + SingletonGame.getInstance().random.nextInt(16) - SingletonGame.getInstance().random.nextInt(16), mom.position.y + SingletonGame.getInstance().random.nextInt(16) - SingletonGame.getInstance().random.nextInt(16));
        }
    }
    
    /**
     * Funcion de transición
     */
    @Override
    public void onStep() {}
    
    /**
     * Funciona a ejecutar cuando llegan al castillo (fin del camino)
     */
    @Override
    public void onReach() {
        if (route != null) {
            super.onReach();
        }
    }
}