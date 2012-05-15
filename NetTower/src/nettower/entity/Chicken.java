/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Point;
import nettower.Art;
import nettower.iterator.Iterator;
import nettower.singleton.SingletonGame;

/**
 *
 * @author David Moran Diaz
 */
public class Chicken extends MobileEntity {
    private Iterator route;
    public int life;
    
    public Chicken(int iniLife, int iniSpeed) {
        super(new Point.Double(0, 0), 16, Art.chicken, iniSpeed);
        route = SingletonGame.getInstance().getRandomRoute();
        life = iniLife;
        
        setPosition((Point.Double)route.first());
    }
    
    @Override
    public Point.Double getPointTarget() {
        return (Point.Double)route.current();
    }
    
    @Override
    public void step() {
        if (life <= 0) {
            slain();
        }
        else {
            advance();
        }
    }
    
    @Override
    public void onReach() {
        if (route.hasNext()) {
            route.next();
        }
        else {
            remove();
        }
    }
    
    public void takeDamage(int damage) {
        life -= damage;
    }
    
    public void slain() {
        remove();
    }

    @Override
    public void remove() {
        SingletonGame.getInstance().removeChicken(this);
    }
}
