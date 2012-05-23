/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.util.ArrayList;
import nettower.singleton.SingletonGame;

/**
 *
 * @author David
 */
public class CompoundChickChicken extends ChickChicken {
    ArrayList<ChickChicken> chicks = new ArrayList();
    
    /**
     * Constructor de la horda de gallinas. Recibe el nivel en el que se tiene
     * que crear la misma. Va a crear una gallina madre (lider) a la que van a
     * seguir el ressto de la horda.
     * 
     * @param level 
     */
    public CompoundChickChicken(double level) {
        super(SingletonGame.getInstance().getRandomRoute(), level);
        while (SingletonGame.getInstance().random.nextDouble() <= 0.9) {
            CompoundChickChicken chick = new CompoundChickChicken(this, 0.8, level);
            SingletonGame.getInstance().insertSpecificChicken(chick);
            chicks.add(chick);
        }
        while (SingletonGame.getInstance().random.nextDouble() <= 0.9) {
            LeafChickChicken chick = new LeafChickChicken(this, level);
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
    public CompoundChickChicken(ChickChicken iniMom, double probability, double level) {
        super(iniMom, level);
        double chickProbability = probability * 0.7;
        while (SingletonGame.getInstance().random.nextDouble() <= probability) {
            CompoundChickChicken chick = new CompoundChickChicken(this, chickProbability, level);
            SingletonGame.getInstance().insertSpecificChicken(chick);
            chicks.add(chick);
        }
        while (SingletonGame.getInstance().random.nextDouble() <= probability) {
            LeafChickChicken chick = new LeafChickChicken(this, level);
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
    @Override
    public void addChicks(ArrayList<ChickChicken> newChicks) {
        chicks.addAll(newChicks);
    }
    
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
