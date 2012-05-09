/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.map;

import java.awt.Point;

/**
 *
 * @author David Moran Diaz
 */
public class Grid {

    /**
     * Devuelve las coordenadas de la casilla donde se encuetnra un punto
     *
     * @param ox
     * @param oy
     * @return Point
     */
    public Point getBoxPosition(int ox, int oy) {
        Point p = new Point();
        p.x = ox / 32;
        p.y = oy / 32;

        return p;
    }

    public Point getBoxPaintOrigin(Point position) {
        Point p = new Point();
        p.x = position.x * 32;
        p.y = position.y * 32;

        return p;
    }
    
    public Point getBoxPaintOrigin(int xbox, int ybox) {
        Point p = new Point();
        p.x = xbox * 32;
        p.y = ybox * 32;

        return p;
    }
}
