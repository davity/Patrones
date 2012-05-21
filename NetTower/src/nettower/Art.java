package nettower;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Art {
//    public static BufferedImage[][] guys = split(load("/guys.png"), 6, 6);
//    public static BufferedImage[][] player1 = split(load("/player.png"), 16, 32);
//    public static BufferedImage[][] player2 = mirrorsplit(load("/player.png"), 16, 32);
//    public static BufferedImage[][] walls = split(load("/walls.png"), 10, 10);
//    public static BufferedImage[][] gremlins = split(load("/gremlins.png"), 30, 30);
//    public static BufferedImage bg = scale(load("/background.png"), 8);
//    public static BufferedImage level = load("/levels.png");
//    public static BufferedImage titleScreen = load("/titlescreen.png");
//    public static BufferedImage winScreen1 = load("/winscreen1.png");;
//    public static BufferedImage winScreen2 = load("/winscreen2.png");;

    public static BufferedImage commonChicken = load("/commonChicken.png");
    public static BufferedImage heavyChicken = load("/heavyChicken.png");
    public static BufferedImage quickChicken = load("/quickChicken.png");
    public static BufferedImage chickChicken = load("/chickChicken.png");
    public static BufferedImage commonChickenI = load("/commonChickenI.png");
    public static BufferedImage heavyChickenI = load("/heavyChickenI.png");
    public static BufferedImage quickChickenI = load("/quickChickenI.png");
    public static BufferedImage chickChickenI = load("/chickChickenI.png");
    public static BufferedImage tower = load("/tower2.png");
    public static BufferedImage heavyTower = load("/heavyTower.png");
    public static BufferedImage areaTower = load("/areaTower.png");
    public static BufferedImage cursor = load("/cursor.png");
    public static BufferedImage blueBullet = load("/blueShoot2.png");
    public static BufferedImage redBullet = load("/redShoot.png");
    public static BufferedImage purpleBullet = load("/purpleShoot2.png");
    public static BufferedImage map1 = load("/map_1.png");
    public static BufferedImage hierba = load("/hierba.png");
    public static BufferedImage roca = load("/roca.png");
    public static BufferedImage camino = load("/camino.png");
    public static BufferedImage gallinero = load("/gallinero.png");
    public static BufferedImage castillo = load("/castillo.png");

    public static BufferedImage load(String name) {
        try {
            BufferedImage org = ImageIO.read(Art.class.getResource(name));
            BufferedImage res = new BufferedImage(org.getWidth(), org.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g = res.getGraphics();
            g.drawImage(org, 0, 0, null, null);
            g.dispose();
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static BufferedImage scale(BufferedImage src, int scale) {
        int w = src.getWidth() * scale;
        int h = src.getHeight() * scale;
        BufferedImage res = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics g = res.getGraphics();
        g.drawImage(src.getScaledInstance(w, h, Image.SCALE_AREA_AVERAGING), 0, 0, null);
        g.dispose();
        return res;
    }

    private static BufferedImage[][] mirrorsplit(BufferedImage src, int xs, int ys) {
        int xSlices = src.getWidth() / xs;
        int ySlices = src.getHeight() / ys;
        BufferedImage[][] res = new BufferedImage[xSlices][ySlices];
        for (int x = 0; x < xSlices; x++) {
            for (int y = 0; y < ySlices; y++) {
                res[x][y] = new BufferedImage(xs, ys, BufferedImage.TYPE_INT_ARGB);
                Graphics g = res[x][y].getGraphics();
                g.drawImage(src, xs, 0, 0, ys, x * xs, y * ys, (x + 1) * xs, (y + 1) * ys, null);
                g.dispose();
            }
        }
        return res;
    }

    private static BufferedImage[][] split(BufferedImage src, int xs, int ys) {
        int xSlices = src.getWidth() / xs;
        int ySlices = src.getHeight() / ys;
        BufferedImage[][] res = new BufferedImage[xSlices][ySlices];
        for (int x = 0; x < xSlices; x++) {
            for (int y = 0; y < ySlices; y++) {
                res[x][y] = new BufferedImage(xs, ys, BufferedImage.TYPE_INT_ARGB);
                Graphics g = res[x][y].getGraphics();
                g.drawImage(src, -x * xs, -y * ys, null);
                g.dispose();
            }
        }
        return res;
    }
}
