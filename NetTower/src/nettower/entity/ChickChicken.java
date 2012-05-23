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
    
    /**
     * Constructor de la horda de gallinas. Recibe el nivel en el que se tiene
     * que crear la misma. Va a crear una gallina madre (lider) a la que van a
     * seguir el ressto de la horda.
     * 
     * @param level 
     */
    public ChickChicken(double level) {
        super(Art.chickChicken, Art.chickChickenI, 16, SingletonGame.getInstance().getRandomRoute(), (int)(10 * level), 2 + (int)(level / 100), (int)(4 * level), (int)(4 * level));
        double probability = level;
        while (SingletonGame.getInstance().random.nextDouble() <= 0.9) {
            ChickChicken chick = new ChickChicken(this, 0.8, level);
            SingletonGame.getInstance().insertSpecificChicken(chick);
            chicks.add(chick);
        }
        while (SingletonGame.getInstance().random.nextDouble() <= 0.9) {
            ChickChicken chick = new ChickChicken(this, 0.8, level);
            SingletonGame.getInstance().insertSpecificChicken(chick);
            chicks.add(chick);
        }
    }
    
    /**
     * Constructor de las gallinas seguidoras de la madre.
     * Recibe la madre, una probabilidad ¿¿¿DE QUE ES ESTA PROBABILIDAD??? y el nivel donde se crea. ###############
     * 
     * @param iniMom
     * @param probability
     * @param level 
     */
    public ChickChicken(ChickChicken iniMom, double probability, double level) {
        super(Art.chickChicken, Art.chickChickenI, 16, iniMom.route, (int)(10 * level), 2 + (int)(level / 100), (int)(4 * level), (int)(4 * level));
        mom = iniMom;
        double chickProbability = probability * 0.7;
        while (SingletonGame.getInstance().random.nextDouble() <= probability) {
            ChickChicken chick = new ChickChicken(this, chickProbability, level);
            SingletonGame.getInstance().insertSpecificChicken(chick);
            chicks.add(chick);
        }
        route = null;
    }
    
    /**
     * Añade una lista de gallinas a la lista de gallinas de la horda
     * 
     * @param newChicks 
     */
    public void addChicks(ArrayList<ChickChicken> newChicks) {
        chicks.addAll(newChicks);
    }
    
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
     * Funciona a ejecutar cuando llegan al castillo (fin del camino)
     */
    @Override
    public void onReach() {
        if (route != null) {
            super.onReach();
        }
    }
    
    /**
     * Funcion de transición
     */
    @Override
    public void onStep() {}
    
    /**
     * Elimina la horda de gallinas y actualiza el objetivo al que deben
     * dirigirse si el lider ha muerto (se crea un nuevo lider)
     */
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