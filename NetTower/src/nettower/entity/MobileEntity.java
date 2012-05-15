/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author David
 */
public abstract class MobileEntity extends Entity {
    public int speed;
    
    public MobileEntity(Point.Double iniPosition, int iniRadiusSize, BufferedImage iniImage, int iniSpeed) {
        super(iniPosition, iniRadiusSize, iniImage);
        speed = iniSpeed;
    }
    
    public abstract Point.Double getPointTarget();
    
    public void advance() {
        double x = getPointTarget().x - getPosition().x;
        double y = getPointTarget().y - getPosition().y;
        double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        if (distance > speed) {
            setPosition(new Point.Double(getPosition().x + x * speed / distance, getPosition().y + y * speed / distance));
        }
        else {
            setPosition(getPointTarget());
            onReach();
        }
    }
    
    public abstract void onReach();
}
