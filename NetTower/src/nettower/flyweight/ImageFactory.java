/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.flyweight;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import nettower.factory.Factory;

/**
 *
 * @author David
 */
public class ImageFactory extends Factory{
    private static final HashMap images = new HashMap();
    
    public static ImageFlyweight getImage(BufferedImage image, int radiusSize) {
        ImageFlyweight imageFlyweight = (ImageFlyweight)images.get(image);
        if (imageFlyweight == null) {
            imageFlyweight = new ImageFlyweight(image, radiusSize);
            images.put(image, imageFlyweight);
        }
        return imageFlyweight;
    }
}
