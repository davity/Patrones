/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entityList;

import java.awt.Graphics;
import java.util.ArrayList;
import nettower.entity.Tower;

/**
 *
 * @author David
 */
public class Defense {
    private ArrayList towerlist;
    
    public Defense() {
        towerlist = new ArrayList();
    }
    
    public void addTower(int x, int y) {
        towerlist.add(new Tower(3, 1, x, y));
    }
    
    public void draw(Graphics g) {
        for (int n = 0; n < towerlist.size(); n++)
        {
            ((Tower)towerlist.get(n)).draw(g);
        }
    }
}
