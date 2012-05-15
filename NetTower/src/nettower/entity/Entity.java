/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Point;
import java.awt.image.BufferedImage;
import nettower.singleton.SingletonGraphics;

/**
 *
 * @author David Moran Diaz
 */
public abstract class Entity {
    private Point realPosition = new Point();
    private Point.Double relativePosition;
    private int radiusSize;
    public BufferedImage image;
    
    public Entity(Point.Double iniPosition, int iniRadiusSize, BufferedImage iniImage) {
        relativePosition = iniPosition;
        radiusSize = iniRadiusSize;
        image = iniImage;
        
        realPosition.setLocation(relativePosition.x - radiusSize, relativePosition.y - radiusSize);
    }
    
    public void setPosition(Point.Double newPosition) {
        relativePosition = newPosition;
        realPosition.setLocation(relativePosition.x - radiusSize, relativePosition.y - radiusSize);
    }
    
    public Point.Double getPosition() {
        return relativePosition;
    }
    
    /* NOT USED
    public int getRadiusSize() {
        return radiusSize;
    }
    
    public void setRadiusSize(int newRadiusSize) {
        radiusSize = newRadiusSize;
        realPosition = new Point.Double(relativePosition.x - radiusSize, relativePosition.y - radiusSize);
    }
    */
    
    public abstract void step();
    
    public void draw() {
        SingletonGraphics.getInstance().drawImage(image, realPosition);
    }
    
    public abstract void remove();
}
