
package nettower.flyweight;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import nettower.factory.Factory;

public class ImageFactory extends Factory {

    private static final HashMap images = new HashMap();

    /**
     * Fabrica de imágenes.
     * Dada una imagen y su radio, busca (o crea una en el caso de no existir)
     * una instancia ya creada de la misma y la devuelve para su reutilización.
     * 
     * @param image
     * @param radiusSize
     * @return ImageFlyweight
     */
    public static ImageFlyweight getImage(BufferedImage image, int radiusSize) {
        ImageFlyweight imageFlyweight = (ImageFlyweight) images.get(image);
        if (imageFlyweight == null) {
            imageFlyweight = new ImageFlyweight(image, radiusSize);
            images.put(image, imageFlyweight);
        }
        return imageFlyweight;
    }
}
