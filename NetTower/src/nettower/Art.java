package nettower;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Art {

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
}
