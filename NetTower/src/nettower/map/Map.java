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