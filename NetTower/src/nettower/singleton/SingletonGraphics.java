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
    private Graphics graphics;
    
    private SingletonGraphics() {}
    
    public static SingletonGraphics getInstance() {
        return instancia;
    }
    
    /* NOT USED
    public Graphics getGraphics() {
        return graphics;
    }
    */
    
    public void setGraphics(Graphics iniGraphics) {
        graphics = iniGraphics;
    }
    
    public void drawImage(BufferedImage image, Point point) {
        graphics.drawImage(image, point.x, point.y, null);
    }
}
