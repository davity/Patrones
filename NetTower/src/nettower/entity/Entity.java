
package nettower.entity;

import java.awt.Point;
import java.awt.image.BufferedImage;
import nettower.flyweight.ImageFactory;
import nettower.flyweight.ImageFlyweight;

public abstract class Entity {
    public ImageFlyweight image;
    public Point.Double position;
    
    /**
     * Constructor de Entidad.
     * Reciba una imagen, el tamaño del radio y una posicion inicial
     * 
     * @param iniImage
     * @param iniRadiusSize
     * @param iniPosition 
     */
    public Entity(BufferedImage iniImage, int iniRadiusSize, Point.Double iniPosition) {
        image = ImageFactory.getImage(iniImage, iniRadiusSize);
        position = iniPosition;
    }
    
    /**
     * Ejecuta un paso
     */
    public abstract void step();
    
    /**
     * Dibuja la imagen de la entidad
     */
    public void draw() {
        image.draw(position);
    }
    
    /**
     * Elimina la entidad
     */
    public abstract void remove();
}