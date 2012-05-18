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
    
    public Bullet(BufferedImage iniImage, int iniRadiusSize, Point.Double iniPosition, Chicken iniTarget, int iniDamage, int iniSpeed) {
        super(iniImage, iniRadiusSize, iniPosition, iniSpeed);
        target = iniTarget;
        damage = iniDamage;
    }
    
    @Override
    public Point.Double getPointTarget() {
        return target.position;
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
