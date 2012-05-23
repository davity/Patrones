/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.singleton;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author David
 */
public class SingletonGraphics {
    private static SingletonGraphics instancia = new SingletonGraphics();
    public Graphics graphics;
    
    /**
     * Constructor
     */
    private SingletonGraphics() {}
    
    /**
     * Devuelve la instancia del singleton
     * 
     * @return SingletonGraphics
     */
    public static SingletonGraphics getInstance() {
        return instancia;
    }
    
    /**
     * Devuelve el objeto Graphics del singleton
     * 
     * @return 
     */
    public Graphics getGraphics() {
        return graphics;
    }
    
    /**
     * Establece un objeto Graphics dado en el singleton
     * 
     * @param iniGraphics 
     */
    public void setGraphics(Graphics iniGraphics) {
        graphics = iniGraphics;
    }
    
    /**
     * Dada una imagen y un punto, dibuja la imagen en el punto dado
     * 
     * @param image
     * @param point 
     */
    public void drawImage(BufferedImage image, Point point) {
        graphics.drawImage(image, point.x, point.y, null);
    }
}
