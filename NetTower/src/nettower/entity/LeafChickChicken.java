/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.util.ArrayList;

/**
 *
 * @author David
 */
public class LeafChickChicken extends ChickChicken {
    /**
     * Constructor de las gallinas hoja seguidoras de la madre.
     * Recibe la madre y el nivel donde se crea. ###############
     * 
     * @param iniMom
     * @param level 
     */
    public LeafChickChicken(ChickChicken iniMom, double level) {
        super(iniMom.route, level);
        mom = iniMom;
        route = null;
    }
    
    /**
     * Añade una lista de gallinas a la lista de gallinas de la horda
     * 
     * @param newChicks 
     */
    @Override
    public void addChicks(ArrayList<ChickChicken> newChicks) {}
    
    /**
     * Elimina la gallina
     */
    @Override
    public void remove() {
        super.remove();
    }
}
