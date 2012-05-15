/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Point;
import nettower.Art;
import nettower.entity.singleton.SingletonGame;

/**
 *
 * @author David Moran Diaz
 */
public class Bullet extends MobileEntity{
    public Chicken target;
    public int damage;
    
    public Bullet(Point.Double iniPosition, Chicken iniTarget, int iniDamage, int iniSpeed) {
        super(iniPosition, 4, Art.bullet, iniSpeed);
        target = iniTarget;
        damage = iniDamage;
    }
    
    @Override
    public Point.Double getPointTarget() {
        return target.getPosition();
    }
    
    @Override
    public void step() {
        advance();
    }
    
    @Override
    public void onReach() {
        target.takeDamage(damage);
        remove();
    }

    @Override
    public void remove() {
        SingletonGame.getInstance().removeBullet(this);
    }
}
