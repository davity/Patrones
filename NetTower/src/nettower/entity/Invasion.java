/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

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
            ((Chicken) enemylist.get(n)).advance();
        }
    }

    public void addEnemy() {
        enemylist.add(new Chicken(1, 4, this, 0, 0));
    }

    public void delete(Chicken chicken) {
        enemylist.remove(chicken);
    }

    //NOT USED FOR NOW
    public ArrayList onRange(double range, int x, int y) {
        ArrayList chickensOnRange;
        Chicken chicken;

        chickensOnRange = new ArrayList();
        for (int n = 0; n < enemylist.size(); n++) {
            chicken = (Chicken) enemylist.get(n);
            if (Math.cbrt(Math.pow(chicken.x - x, 2) + Math.pow(chicken.y - y, 2)) <= range) {
                chickensOnRange.add(chicken);
            }
        }
        return chickensOnRange;
    }

    public Chicken nearestChickenOnRange(double range, int x, int y) {
        Chicken actualChicken, nearestChicken;
        double actualDistance, nearestDistance;

        nearestChicken = null;
        nearestDistance = range;
        for (int n = 0; n < enemylist.size(); n++) {
            actualChicken = (Chicken) enemylist.get(n);
            actualDistance = Math.cbrt(Math.pow(actualChicken.x - x, 2) + Math.pow(actualChicken.y - y, 2));
            if (actualDistance <= nearestDistance) {
                nearestChicken = actualChicken;
                nearestDistance = actualDistance;
            }
        }
        return nearestChicken;
    }

    public void draw(Graphics g) {
        for (int n = 0; n < enemylist.size(); n++) {
            ((Chicken) enemylist.get(n)).draw(g);
        }
    }
}
