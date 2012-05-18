/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Point;
import java.awt.image.BufferedImage;
import nettower.singleton.SingletonGame;

/**
 *
 * @author David Moran Diaz
 */
public class Bullet extends MobileEntity{
    public Chicken target;
    public int damage;
    
    public Bullet(Point.Double iniPosition, int iniRadiusSize, BufferedImage iniImage, Chicken iniTarget, int iniDamage, int iniSpeed) {
        super(iniPosition, iniRadiusSize, iniImage, iniSpeed);
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
