/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Graphics;
import java.util.ArrayList;
import nettower.entity.Tower;

/**
 *
 * @author David
 */
public class Defense {

    private ArrayList towerlist;
    private ArrayList shootlist;
    private Invasion invasion;

    public Defense(Invasion iniInvasion) {
        towerlist = new ArrayList();
        shootlist = new ArrayList();
        invasion = iniInvasion;
    }

    public void addTower(int x, int y) {
        towerlist.add(new Tower(1, 80, 20, x, y, this));
    }

    public void addShoot(Shoot shoot) {
        shootlist.add(shoot);
    }

    public void delShoot(Shoot shoot) {
        shootlist.remove(shoot);
    }

    public void step() {
        for (int n = 0; n < towerlist.size(); n++) {
            ((Tower) towerlist.get(n)).shoot(invasion);
        }
        for (int n = 0; n < shootlist.size(); n++) {
            ((Shoot) shootlist.get(n)).step();
        }
    }

    public void draw(Graphics g) {
        for (int n = 0; n < towerlist.size(); n++) {
            ((Tower) towerlist.get(n)).draw(g);
        }
        for (int n = 0; n < shootlist.size(); n++) {
            ((Shoot) shootlist.get(n)).draw(g);
        }
    }
    
    public Tower getTowerAt(int x, int y) {
        Tower tower;
        for (int n = 0; n < towerlist.size(); n++) {
            tower = (Tower)towerlist.get(n);
            if (tower.x == x && tower.y == y) {
                return tower;
            }
        }
        return null;
    }
}
