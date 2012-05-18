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
    
    public MobileEntity(BufferedImage iniImage, int iniRadiusSize, Point.Double iniPosition, int iniSpeed) {
        super(iniImage, iniRadiusSize, iniPosition);
        speed = iniSpeed;
    }
    
    public abstract Point.Double getPointTarget();
    
    public void advance() {
        double x = getPointTarget().x - position.x;
        double y = getPointTarget().y - position.y;
        double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        if (distance > speed) {
            position = new Point.Double(position.x + x * speed / distance, position.y + y * speed / distance);
        }
        else {
            position = getPointTarget();
            onReach();
        }
    }
    
    public abstract void onReach();
}
