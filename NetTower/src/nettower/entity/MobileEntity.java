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
    
    /**
     * Constructor. Recibe una imagen, el radio de la misma, una posicion inicial,
     * y una velocidad inicial
     * 
     * @param iniImage
     * @param iniRadiusSize
     * @param iniPosition
     * @param iniSpeed 
     */
    public MobileEntity(BufferedImage iniImage, int iniRadiusSize, Point.Double iniPosition, int iniSpeed) {
        super(iniImage, iniRadiusSize, iniPosition);
        speed = iniSpeed;
    }
    
    /**
     * 
     * @return 
     */
    public abstract Point.Double getPointTarget();
    
    /**
     * Ejecuta un paso de avance. Actualiza la posicion del objeto a cada paso.
     */
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
    
    /**
     * 
     */
    public abstract void onReach();
}
