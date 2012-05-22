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
     * Dada un punto en el mapa grande (480x480) devuelve la casilla
     * correspondiente en el mapa pequeño (15x15)
     *
     * @param ox
     * @param oy
     * @return Point
     */
    public static Point getBoxLittlePosition(int ox, int oy) {
        Point p = new Point();
        p.x = ox / 32;
        p.y = oy / 32;

        if (p.x > 14) {
            p.x = 14;
        }
        if (p.y > 14) {
            p.y = 14;
        }

        return p;
    }

    /**
     * Dada un punto en el mapa grande (480x480) devuelve la casilla
     * correspondiente en el mapa pequeño (15x15)
     *
     * @param op
     * @return
     */
    public static Point getBoxLittlePosition(Point op) {
        Point p = new Point();
        p.x = op.x / 32;
        p.y = op.y / 32;

        if (p.x > 14) {
            p.x = 14;
        }
        if (p.y > 14) {
            p.y = 14;
        }

        return p;
    }

    /**
     * Dada una casilla en el mapa pequeño (15x15) devuelve la esquina de la
     * casilla correspondiente en el mapa grande (480x480)
     *
     * @param op
     * @return
     */
    public static Point getBoxBigPosition(Point op) {
        Point p = new Point();
        p.x = op.x * 32;
        p.y = op.y * 32;

        if (p.x > 448) {
            p.x = 448;
        }
        if (p.y > 448) {
            p.y = 448;
        }

        return p;
    }

    /**
     * Dada una casilla en el mapa pequeño (15x15) devuelve la esquina de la
     * casilla correspondiente en el mapa grande (480x480)
     *
     * @param ox
     * @param oy
     * @return
     */
    public static Point getBoxBigPosition(int ox, int oy) {
        Point p = new Point();
        p.x = ox * 32;
        p.y = oy * 32;

        if (p.x > 448) {
            p.x = 448;
        }
        if (p.y > 448) {
            p.y = 448;
        }

        return p;
    }

    /**
     * Devuelve las coordenadas del centro de una casilla en el mapa (480x480)
     *
     * @param position
     * @return
     */
    public static Point getBoxMiddle(Point position) {
        Point p = new Point();
        p.x = (position.x / 32);
        if (p.x > 14) {
            p.x = 14;
        }
        p.x = p.x * 32 + 16;
        p.y = (position.y / 32);
        if (p.y > 14) {
            p.y = 14;
        }
        p.y = p.y * 32 + 16;

        return p;
    }

    /**
     * Devuelve las coordenadas del centro de una casilla en el mapa (480x480)
     *
     * @param ox
     * @param oy
     * @return
     */
    public static Point getBoxMiddle(int ox, int oy) {
        Point p = new Point();
        p.x = (ox / 32);
        if (p.x > 14) {
            p.x = 14;
        }
        p.x = p.x * 32 + 16;
        p.y = (oy / 32);
        if (p.y > 14) {
            p.y = 14;
        }
        p.y = p.y * 32 + 16;

        return p;
    }

    /**
     * Devuelve la esquina superior izquierda de una casilla en el mapa
     * (480x480)
     *
     * @param position
     * @return
     */
    public static Point getBoxCorner(Point position) {
        Point p = new Point();
        p.x = (position.x / 32);
        if (p.x > 14) {
            p.x = 14;
        }
        p.x = p.x * 32;
        p.y = (position.y / 32);
        if (p.y > 14) {
            p.y = 14;
        }
        p.y = p.y * 32;

        return p;
    }

    /**
     * Dada una posicion dentro del menu, si pertenece a alguno de los
     * botones, devuelve la esquina sup. izq. del boton correspondiente
     * 
     * @param position
     * @return 
     */
    public static Point getMenuElementCorner(Point position) {
        Point p = new Point();
        
        if (position.x >= 489 && position.x <= 730) p.x = 489;
        /* Torreta normal */
        if (position.y >= 172 && position.y <= 208) p.y = 172;
        else
            /* Torreta de area */ 
            if (position.y >= 212 && position.y <= 248) p.y = 212;
        else 
            /* Torreta heavy */
            if (position.y >= 252 && position.y <= 288) p.y = 252;
        else 
            /* Menu principal */
            if (position.y >= 416 && position.y <= 454) p.y = 416;

        return p;
    }
    
    public static Point getMenuElementCorner(int ox, int oy) {
        Point p = new Point();
        
        if (ox >= 489 && ox <= 730) p.x = 489;
        /* Torreta normal */
        if (oy >= 172 && oy <= 208) p.y = 172;
        else
            /* Torreta de area */ 
            if (oy >= 212 && oy <= 248) p.y = 212;
        else 
            /* Torreta heavy */
            if (oy >= 252 && oy <= 288) p.y = 252;
        else 
            /* Menu principal */
            if (oy >= 416 && oy <= 454) p.y = 416;
        else
            p = null;

        return p;
    }
    
    public static String getMenuElementType(int ox, int oy) {
        String element="nothing";
        
        if (ox >= 489 && ox <= 630);
        /* Torreta normal */
        if (oy >= 172 && oy <= 208) element = "common";
        else
            /* Torreta de area */ 
            if (oy >= 212 && oy <= 248) element = "area";
        else 
            /* Torreta heavy */
            if (oy >= 252 && oy <= 288) element = "heavy";
        else 
            /* Menu principal */
            if (oy >= 416 && oy <= 454) element = "menu";

        return element;
    }
    
    /**
     * Comprueba si un punto dado pertenece al mapa de juego
     * 
     * @param position
     * @return 
     */
    public static boolean isAMapPosition (int ox, int oy) {
        if (ox >= 0 && ox < 480 && 
                oy >= 0 && oy < 480) 
            return true;
        else
            return false;
    }
    
    /**
     * Comprueba si un punto dado pertenece al menu de juego
     * 
     * @param position
     * @return 
     */
    public static boolean isAMenuPosition (int ox, int oy) {
        if (ox >= 480 && ox < 640 && oy >= 0 && oy < 480) {
            if (getMenuElementCorner(ox, oy) != null) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Devuelve la esquina sup. izq. donde hay que pintar la puntuación
     * @return 
     */
    public static Point getScorePosition() {
        return new Point(497,38);
    }
    
    /**
     * Devuelve la esquina sup. izq. donde hay que pintar el dinero
     * @return 
     */
    public static Point getMoneyPosition() {
        return new Point(497,128);
    }
}
