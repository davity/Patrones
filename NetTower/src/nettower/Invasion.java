/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;

import java.util.*;
import java.awt.*;

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
        for (int n = 0; n < enemylist.size(); n++)
        {
            ((Enemy)enemylist.get(n)).advance();
        }
    }
    
    public void addEnemy() {
        enemylist.add(new Enemy());
    }
    
    public void draw(Graphics g) {
        for (int n = 0; n < enemylist.size(); n++)
        {
            ((Enemy)enemylist.get(n)).draw(g);
        }
    }
}
