/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower;
import java.awt.Point;

/**
 *
 * @author David Moran Diaz
 */
public class Grid {

    /**
     * Devuelve las coordenadas de la casilla donde se encuentra un punto
     * dentro de una matrid de 15x15 (matriz reducida)
     *
     * @param ox
     * @param oy
     * @return Point
     */
    public static Point getBoxLittlePosition(int ox, int oy) {
        Point p = new Point();
        p.x = ox / 32;
        p.y = oy / 32;

        return p;
    }
    
    /**
     * Devuelve las coordenadas de la casilla donde se encuentra un punto
     * dentro de una matrid de 15x15 (matriz reducida)
     * 
     * @param op
     * @return 
     */
    public static Point getBoxLittlePosition(Point op) {
        Point p = new Point();
        p.x = op.x / 32;
        p.y = op.y / 32;

        return p;
    }
    
    
    public static Point getBoxBigPosition(Point op) {
        Point p = new Point();
        p.x = op.x * 32;
        p.y = op.y * 32;

        return p;
    }
    
    
    public static Point getBoxBigPosition(int ox, int oy) {
        Point p = new Point();
        p.x = ox * 32;
        p.y = oy * 32;

        return p;
    }

    /**
     * Devuelve las coordenadas del centro de una casilla en el mapa (240x240)
     * 
     * @param position
     * @return 
     */
    public static Point getBoxMiddle(Point position) {
        Point p = new Point();
        p.x = (position.x / 32) * 32 + 16;
        p.y = (position.y / 32) * 32 + 16;

        return p;
    }
    
    /**
     * Devuelve las coordenadas del centro de una casilla en el mapa (240x240)
     * 
     * @param ox
     * @param oy
     * @return 
     */
    public static Point getBoxMiddle(int ox, int oy) {
        Point p = new Point();
        p.x = (ox / 32) * 32 + 16;
        p.y = (oy / 32) * 32 + 16;

        return p;
    }
    
    /**
     * Devuelve la esquina superior izquierda de una casilla en el mapa (240x240)
     * 
     * @param position
     * @return 
     */
    public static Point getBoxCorner(Point position) {
        Point p = new Point();
        p.x = (position.x / 32) * 32;
        p.y = (position.y / 32) * 32;

        return p;
    }
}
