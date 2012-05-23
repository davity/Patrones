
package nettower.flyweight;

import java.awt.Point;
import java.awt.image.BufferedImage;
import nettower.singleton.SingletonGraphics;

public class ImageFlyweight {
    private BufferedImage image;
    private int radiusSize;
    
    /**
     * Inicializa una imagen con una imagen y un radio dados
     * 
     * @param iniImage
     * @param iniRadiusSize 
     */
    public ImageFlyweight(BufferedImage iniImage, int iniRadiusSize) {
        image = iniImage;
        radiusSize = iniRadiusSize;
    }
    
    /**
     * Devuelve la imagen
     * 
     * @return BufferedImage
     */
    public BufferedImage getImage() {
        return image;
    }
    
    /**
     * Devuelve el radio de la imagen
     * 
     * @return int
     */
    public int getRadiusSize() {
        return radiusSize;
    }
    
    /**
     * Dibuja la imagen
     * 
     * @param point 
     */
    public void draw(Point.Double point) {
        SingletonGraphics.getInstance().drawImage(getImage(), new Point ((int)(point.x - radiusSize), (int)(point.y - radiusSize)));
    }
}
