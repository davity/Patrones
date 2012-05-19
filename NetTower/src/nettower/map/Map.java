package nettower.map;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import nettower.Art;
import nettower.Grid;
import nettower.singleton.SingletonGraphics;

public final class Map {
    public static final int HIERBA = 0;
    public static final int CAMINO = 1;
    public static final int ROCA = 2;
    public static final int START = 3;
    public static final int END = 4;
    public int[] map;
    public ArrayList<Point> path;
    int width;
    int height;

    
    public Map(BufferedImage mapa, int w, int h) {
        this.width = w;
        this.height = h;
        
        this.map = parseMap(mapa);
        //path= getPaths();
    }

    /*
     * Devuelve un array con todos los tipos de casillas disponibles en el mapa
     */
    public int[] parseMap(BufferedImage mapa) {
        int[] pixels = new int[width * height];
        int[] m = new int[width * height];
        int indice;
        int y;
        int x;
        
        mapa.getRGB(0, 0, width, height, pixels, 0, 15);
        
        for (y = 0; y < height; y++) {
            for (x = 0; x < width; x++) {

                indice = x + y * width;

                if (pixels[indice] == 0xFF00FF00)       m[indice] = HIERBA;
                else if (pixels[indice] == 0xFFFFFF00)  m[indice] = CAMINO;
                else if (pixels[indice] == 0xFFA0A0A0)  m[indice] = ROCA;
                else if (pixels[indice] == 0xFF00FFFF)  m[indice] = START;
                else if (pixels[indice] == 0xFFFF0000)  m[indice] = END;
            }
        }
        
        return m;
    }
//    
//    public ArrayList<Point> getPaths() {
//        ArrayList<Point> path = new ArrayList();
//        ArrayList<Point> startPoints = getStartPositions();
//        ArrayList<Point> adyacentes;
//        int dirx;
//        int diry;
//        Point p;
////        Point t;
//        Point a;
//        Point b;
//        int i =-1;
//
//        p = startPoints.get(0);
//
//        while (map[p.x + p.y * width] != END) {
//            path.add((Point) p.clone());
//            adyacentes = getAdjacents(p);
//            a = adyacentes.get(0);
//            dirx = a.x - p.x;
//            diry = a.y - p.y;
//
//            if (dirx != 0) {
//                /* nos movemos en horizontal hasta encontrar una esquina */
//                while (dirx != 0) {
//                    p.x += dirx;
//                    adyacentes = getAdjacents(p);
//                    a = adyacentes.get(0);
//                    dirx = a.x - p.x;
//                }
//                path.add((Point) p.clone());
//
//            } else if (diry != 0) {
//                /* nos movemos en vertical hasta encontrar una esquina */
//                while (diry != 0) {
//                    b = (Point) p.clone();
//                    p.y += diry;
//                    adyacentes = getAdjacents(p);
//                    do {
//                        i++;
//                        a = adyacentes.get(i);
//                    } while (a.equals(b));
//                    diry = a.y - p.y;
//                }
//                path.add((Point) p.clone());
//            }
//
//        }
//
//    return null;
//    }
//    
//    private ArrayList<Point> getStartPositions() {
//        ArrayList<Point> startPoints = new ArrayList();
//        int x;
//        int y;
//        int i;
//        
//        for (y = 0; y < height; y++) {
//             for (x = 0; x < width; x++) {
//                 i = x + y * width;
//                 
//                 if (map[i] == START) {
//                     startPoints.add(new Point(x, y));
//                 }
//            }
//
//        }
//        
//        return startPoints;
//    }
//    
//    private ArrayList<Point> getAdjacents (Point p) {
//        ArrayList<Point> ady = new ArrayList();
//        
//        /* Up mid */
//        if ((p.x >= 0) && ((p.y - 1 ) >= 0)){
//            if (map[p.x + (p.y - 1) * width] == CAMINO)
//                ady.add(new Point(p.x, p.y -1));
//        }
//        
//        /* Mid left */
//        if (((p.x - 1) >= 0) && (p.y >= 0)){
//            if (map[(p.x - 1) + p.y  * width] == CAMINO)
//                ady.add(new Point(p.x - 1, p.y));
//        }
//        
//        /* Mid right */
//        if (((p.x + 1) >= 0) && (p.y >= 0)){
//            if (map[(p.x + 1) + p.y * width] == CAMINO)
//                ady.add(new Point(p.x + 1, p.y));
//        }
//        
//        /* Down mid */
//        if ((p.x >= 0) && ((p.y + 1 ) >= 0)){
//            if (map[p.x + (p.y + 1) * width] == CAMINO)
//                ady.add(new Point(p.x, p.y + 1));
//        }
//        
//        return ady;
//    }
//    private int[] getAdjacents(Point p) {
//        int[] ady = new int[] {0,0,0,0,0,0,0,0,0}; // [8]
////        int y;
////        int x;
////        int i;
////        int ex;
////        int ey;
//        
//        /* Up left */
//        if (((p.x - 1) >= 0) && ((p.y - 1 ) >= 0)){
//            if (map[(p.x - 1) + (p.y - 1) * width] == CAMINO)
//                ady[0] = 1;
//        }
//        
//        /* Up mid */
//        if ((p.x >= 0) && ((p.y - 1 ) >= 0)){
//            if (map[p.x + (p.y - 1) * width] == CAMINO)
//                ady[0] = 1;
//        }
//        
//        /* Up rigth */
//        if (((p.x + 1) >= 0) && ((p.y - 1 ) >= 0)){
//            if (map[(p.x + 1) + (p.y - 1) * width] == CAMINO)
//                ady[0] = 1;
//        }
//        
//        /* Mid left */
//        if (((p.x - 1) >= 0) && (p.y >= 0)){
//            if (map[(p.x - 1) + p.y  * width] == CAMINO)
//                ady[0] = 1;
//        }
//        
//        /* Mid right */
//        if (((p.x + 1) >= 0) && (p.y >= 0)){
//            if (map[(p.x + 1) + p.y * width] == CAMINO)
//                ady[0] = 1;
//        }
//        
//        /* Down left */
//        if (((p.x - 1) >= 0) && ((p.y + 1 ) >= 0)){
//            if (map[(p.x - 1) + (p.y + 1) * width] == CAMINO)
//                ady[0] = 1;
//        }
//        
//        /* Down mid */
//        if ((p.x >= 0) && ((p.y + 1 ) >= 0)){
//            if (map[p.x + (p.y + 1) * width] == CAMINO)
//                ady[0] = 1;
//        }
//        
//        /* Down right */
//        if (((p.x + 1) >= 0) && ((p.y + 1 ) >= 0)){
//            if (map[(p.x + 1) + (p.y + 1) * width] == CAMINO)
//                ady[0] = 1;
//        }
//        
////        for (y = -1; y <= 1; y++) {
////            for (x = -1; x <= 1; y++) {
////                ex = p.x + x;
////                ey = p.y + y;
////                if ((ex >= 0) && (ex <= 14) && (ey >= 0) && (ey <= 14)) {
////                    
////                }
////            }
////        }
//        return ady;
//    }
    public void draw() {
        int indice;
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                indice = x + y * width;

                if (map[indice] == 0) {
                    SingletonGraphics.getInstance().drawImage(Art.hierba, Grid.getBoxCorner(Grid.getBoxBigPosition(x,y)));
                } else if (map[indice] == 1) {
                    SingletonGraphics.getInstance().drawImage(Art.camino, Grid.getBoxCorner(Grid.getBoxBigPosition(x,y)));
                } else if (map[indice] == 2) {
                    SingletonGraphics.getInstance().drawImage(Art.roca, Grid.getBoxCorner(Grid.getBoxBigPosition(x,y)));
                } else if (map[indice] == 3) {
                    SingletonGraphics.getInstance().drawImage(Art.camino, Grid.getBoxCorner(Grid.getBoxBigPosition(x,y)));
                    SingletonGraphics.getInstance().drawImage(Art.gallinero, Grid.getBoxCorner(Grid.getBoxBigPosition(x,y)));
                } else if (map[indice] == 4) {
                    SingletonGraphics.getInstance().drawImage(Art.camino, Grid.getBoxCorner(Grid.getBoxBigPosition(x,y)));
                    SingletonGraphics.getInstance().drawImage(Art.castillo, Grid.getBoxCorner(Grid.getBoxBigPosition(x,y)));
                }
            }
        }
    }

    public boolean isBuildable(int x, int y) {
        return map[x + y * width] == HIERBA;
    }
}