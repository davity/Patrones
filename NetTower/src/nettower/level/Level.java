package nettower.level;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import nettower.Art;
import nettower.entity.Entity;
import nettower.screen.Grid;
import nettower.singleton.SingletonGraphics;

/*
 *
 *
 * BUENO, ESTO, OBVIAMENTE, HAY QUE LIMPIARLO PARA QUITAR TODO LO QUE NO NOS
 * HAGA FALTA, PERO DE MOMENTO LO DEJO ASI POR SI QUEREMOS REAPROVECHAR ALGO.
 *
 *
 * ¡¿DÓNDE ESTÁ EL BOTÓN DE LAS MAYUS?!
 *
 *
 *
 */
public class Level {

    public static final int HIERBA = 0;
    public static final int CAMINO = 1;
    public static final int ROCA = 2;
    public List<Entity>[] entityMap;
    private int tick;
    public int[] map;
    int w;
    int h;

    @SuppressWarnings("unchecked")
    public Level(int w, int h) {
        this.w = w;
        this.h = h;
        int[] pixels = new int[w * h];
        map = new int[w * h];
        Art.map1.getRGB(0, 0, w, h, pixels, 0, 15);
        int indice;

        /*
         * Parseamos el mapa y lo pasamos a una matriz de enteros
         */
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {

                indice = x + y * w;

                if (pixels[indice] == 0xFF00FF00) {
                    map[indice] = HIERBA;
                    SingletonGraphics.getInstance().drawImage(Art.hierba, new Point(x,y));
                } else if (pixels[indice] == 0xFFFFFF00) {
                    map[indice] = CAMINO;
                } else if (pixels[indice] == 0xFFA0A0A0) {
                    map[indice] = ROCA;
                }
            }
        }


    }

    /*
     * Esta funcionalidad debe ir dentro del constructor de Level. De momento la
     * dejo aquí para hacer pruebas.
     */
    public char[] parseMap(int level) {
        int[] pixels = new int[15 * 15];
        char[] boxes = new char[15 * 15];

        Art.map1.getRGB(0, 0, 15, 15, pixels, 0, 15);

        if (pixels[0] == 0xFFA0A0A0) {
            boxes[0] = 'R';
        }
        return boxes;
    }

    public void add(Entity e) {
    }

    public void tick() {
        tick++;

    }
    private List<Entity> hits = new ArrayList<Entity>();

    public List<Entity> getEntities(double xc, double yc, double w, double h) {
        hits.clear();

        return hits;
    }

    public void render() {
        int indice;
        
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {

                indice = x + y * w;

                if (map[indice] == 0) {
                    SingletonGraphics.getInstance().drawImage(Art.hierba, Grid.getBoxMiddleForPaint(new Point(x,y)));
                } else if (map[indice] == 1) {
                    SingletonGraphics.getInstance().drawImage(Art.camino, Grid.getBoxMiddleForPaint(new Point(x,y)));
                } else if (map[indice] == 2) {
                    SingletonGraphics.getInstance().drawImage(Art.roca, Grid.getBoxMiddleForPaint(new Point(x,y)));
                }
            }
        }
    }
}
