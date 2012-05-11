package nettower.level;

import java.awt.*;
import java.util.*;
import java.util.List;

import nettower.*;
import nettower.Art;
import nettower.entity.*;
import nettower.screen.GameScreen;

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

    public List<Entity> entities = new ArrayList<Entity>();
    public List<Entity>[] entityMap;
    private int width, height;
    private Random random = new Random(1000);
    private GameScreen screen;
    private int respawnTime = 0;
    private int tick;
    public byte[] boxes;

    @SuppressWarnings("unchecked")
    public Level(GameScreen screen, int w, int h, int xo, int yo, int xSpawn, int ySpawn) {
        this.screen = screen;
        int[] pixels = new int[32 * 24];

        Art.map1.getRGB(xo * 31, yo * 23, 32, 24, pixels, 0, 32);

        boxes = new byte[w * h];
        entityMap = new ArrayList[w * h];
        this.width = w;
        this.height = h;

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                
            }
        }
    }

    /*
     * Esta funcionalidad debe ir dentro del constructor de Level.
     * De momento la dejo aquí para hacer pruebas.
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

    public void render(Graphics g) {
    }
}
