/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Point;
import java.awt.image.BufferedImage;
import nettower.iterator.Iterator;
import nettower.singleton.SingletonGame;

/**
 *
 * @author David Moran Diaz
 */
public abstract class Chicken extends MobileEntity {
    public Iterator route;
    public int life;
    public int points;
    public int money;
    private boolean exist = true;
    
    public Chicken(BufferedImage iniImage, int iniRadiusSize, Iterator iniRoute, int iniLife, int iniSpeed, int iniPoints, int iniMoney) {
        super(iniImage, iniRadiusSize, (Point.Double)iniRoute.first(), iniSpeed);
        route = iniRoute;
        life = iniLife;
        points = iniPoints;
        money = iniMoney;
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
            onStep();
            advance();
        }
    }
    
    public abstract void onStep();
    
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
        exist = false;
    }

    boolean exist() {
        return exist;
    }
}
