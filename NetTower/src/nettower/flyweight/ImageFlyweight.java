/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.flyweight;

import java.awt.Point;
import java.awt.image.BufferedImage;
import nettower.singleton.SingletonGraphics;

/**
 *
 * @author David
 */
public class ImageFlyweight {
    private BufferedImage image;
    private int radiusSize;
    
    public ImageFlyweight(BufferedImage iniImage, int iniRadiusSize) {
        image = iniImage;
        radiusSize = iniRadiusSize;
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    public int getRadiusSize() {
        return radiusSize;
    }
    
    public void draw(Point.Double point) {
        SingletonGraphics.getInstance().drawImage(getImage(), new Point ((int)(point.x - radiusSize), (int)(point.y - radiusSize)));
    }
}
