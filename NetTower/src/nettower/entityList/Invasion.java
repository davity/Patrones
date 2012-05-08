/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entityList;

import java.util.*;
import java.awt.*;
import nettower.entity.*;

/**
 *
 * @author David
 */
public class Invasion {
    private ArrayList enemylist;
    
    public Invasion() {
        enemylist = new ArrayList();
    }
    
    public void step() {
        for (int n = 0; n < enemylist.size(); n++) {
            ((Chicken)enemylist.get(n)).advance();
        }
    }
    
    public void addEnemy() {
        enemylist.add(new Chicken(1, 4, this));
    }
    
    public void delete(Chicken chicken) {
        enemylist.remove(chicken);
    }
    
    public void draw(Graphics g) {
        for (int n = 0; n < enemylist.size(); n++)
        {
            ((Chicken)enemylist.get(n)).draw(g);
        }
    }
}
