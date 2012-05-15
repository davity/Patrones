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
    public int points;
    public int money;
    
    public Chicken(int iniLife, int iniSpeed, int iniPoints, int iniMoney) {
        super(new Point.Double(0, 0), 16, Art.chicken, iniSpeed);
        route = SingletonGame.getInstance().getRandomRoute();
        life = iniLife;
        points = iniPoints;
        money = iniMoney;
        
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
            SingletonGame.getInstance().takeALife();
            remove();
        }
    }
    
    public void takeDamage(int damage) {
        life -= damage;
    }
    
    public void slain() {
        SingletonGame.getInstance().givePoints(points);
        SingletonGame.getInstance().giveMoney(money);
        remove();
    }

    @Override
    public void remove() {
        SingletonGame.getInstance().removeChicken(this);
    }
}
