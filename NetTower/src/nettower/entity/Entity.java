/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Point;
import java.awt.image.BufferedImage;
import nettower.flyweight.ImageFactory;
import nettower.flyweight.ImageFlyweight;

/**
 *
 * @author David Moran Diaz
 */
public abstract class Entity {
    public ImageFlyweight image;
    public Point.Double position;
    
    public Entity(BufferedImage iniImage, int iniRadiusSize, Point.Double iniPosition) {
        image = ImageFactory.getImage(iniImage, iniRadiusSize);
        position = iniPosition;
    }
    
    public abstract void step();
    
    public void draw() {
        image.draw(position);
    }
    
    public abstract void remove();
}