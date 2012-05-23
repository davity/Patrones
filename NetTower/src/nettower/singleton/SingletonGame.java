/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.singleton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import nettower.Art;
import nettower.Grid;
import nettower.entity.Bullet;
import nettower.entity.Chicken;
import nettower.entity.Cursor;
import nettower.entity.Tower;
import nettower.factory.ChickenFactory;
import nettower.factory.TowerFactory;
import nettower.iterator.Aggregate;
import nettower.iterator.ConcreteAggregate;
import nettower.iterator.Iterator;
import nettower.map.Map;

/**
 *
 * @author David
 */
public class SingletonGame {
    private static SingletonGame instance = new SingletonGame();
    private TowerFactory towerFactory = new TowerFactory();
    private ChickenFactory chickenFactory = new ChickenFactory();
    private ArrayList<Aggregate> routesList = new ArrayList();
    private ArrayList<Chicken> chickensList = new ArrayList();
    private ArrayList<Tower> towersList = new ArrayList();
    private ArrayList<Bullet> bulletsList = new ArrayList();
    private Map map = null;
    public Cursor cursor = new Cursor();
    private int points = 0;
    private int money = 400;
    private int lives = 10;
    public String record;
    public Random random = new Random();
    private static final int COST_TOWER[] = {100, 200, 300};
    
    private SingletonGame() {}
    
    public static SingletonGame getInstance() {
        return instance;
    }
    
    public void addRoute(ArrayList<Point.Double> route) {
        routesList.add(new ConcreteAggregate(route));
    }
    
    public void addChicken(double level) {
        chickensList.add(chickenFactory.getChicken(level));
    }
    
    public void insertSpecificChicken(Chicken chicken) {
        chickensList.add(chicken);
    }
    
    public void removeChicken(Chicken chicken) {
        chickensList.remove(chicken);
    }
    
    public void addTower(int type) {
        if (canPay(COST_TOWER[type])) {
            takeMoney(COST_TOWER[type]);
            Point.Double point = new Point.Double();
            point.setLocation(cursor.position);
            towersList.add(towerFactory.getTower(point, type));
        }
    }
    
    public void removeTower(Tower tower) {
        towersList.remove(tower);
    }
    
    public void addBullet(BufferedImage image, int radiusSize, Point.Double position, Chicken target, int damage, int speed) {
        bulletsList.add(new Bullet(image, radiusSize, position, target, damage, speed));
    }
    
    public void removeBullet(Bullet bullet) {
        bulletsList.remove(bullet);
    }
    
    public void step() {
        
        for (int n = 0; n < chickensList.size(); n++) {
            chickensList.get(n).step();
        }
        if (lives > 0) {
            for (int n = 0; n < towersList.size(); n++) {
                towersList.get(n).step();
            }
            for (int n = 0; n < bulletsList.size(); n++) {
                bulletsList.get(n).step();
            }
        }
    }
    
    public void draw() {
        map.draw();
        SingletonGraphics.getInstance().drawImage(Art.menu, new Point(480,0));
        for (int n = 0; n < towersList.size(); n++) {
            towersList.get(n).draw();
        }
        for (int n = 0; n < chickensList.size(); n++) {
            chickensList.get(n).draw();
        }
        for (int n = 0; n < bulletsList.size(); n++) {
            bulletsList.get(n).draw();
        }
        if (lives <= 0) {
            SingletonGraphics.getInstance().graphics.setColor(Color.RED);
            SingletonGraphics.getInstance().graphics.setFont(new Font("Arial", Font.BOLD, 22));
            SingletonGraphics.getInstance().graphics.drawString("YOU LOSE", 200, 200);
        }
        else {
            if (cursor != null) cursor.draw();
        }
        SingletonGraphics.getInstance().graphics.setColor(Color.BLUE);
        SingletonGraphics.getInstance().graphics.setFont(new Font("Arial", Font.BOLD, 12));
        SingletonGraphics.getInstance().graphics.drawString("Puntos:", 496, 42);
        SingletonGraphics.getInstance().graphics.drawString(String.format("%016d", points), 514, 54);
        SingletonGraphics.getInstance().graphics.setFont(new Font("Arial", Font.PLAIN, 12));
        SingletonGraphics.getInstance().graphics.drawString("Mejor:", 496, 66);
        SingletonGraphics.getInstance().graphics.drawString(record, 514, 78);
        SingletonGraphics.getInstance().graphics.setColor(Color.RED);
        SingletonGraphics.getInstance().graphics.setFont(new Font("Arial", Font.BOLD, 20));
        SingletonGraphics.getInstance().graphics.drawString(String.valueOf(lives), 514, 124);
        SingletonGraphics.getInstance().graphics.setColor(Color.YELLOW);
        SingletonGraphics.getInstance().graphics.setFont(new Font("Arial", Font.BOLD, 18));
        SingletonGraphics.getInstance().graphics.drawString(String.valueOf(money), 574, 123);
        if (canPay(COST_TOWER[0]))
            SingletonGraphics.getInstance().graphics.drawString(String.valueOf(COST_TOWER[0]), 574, 175);
        if (canPay(COST_TOWER[1]))
            SingletonGraphics.getInstance().graphics.drawString(String.valueOf(COST_TOWER[1]), 574, 215);
        if (canPay(COST_TOWER[2]))
            SingletonGraphics.getInstance().graphics.drawString(String.valueOf(COST_TOWER[2]), 574, 255);
        SingletonGraphics.getInstance().graphics.setColor(Color.BLACK);
        if (!canPay(COST_TOWER[0]))
            SingletonGraphics.getInstance().graphics.drawString(String.valueOf(COST_TOWER[0]), 574, 175);
        if (!canPay(COST_TOWER[1]))
            SingletonGraphics.getInstance().graphics.drawString(String.valueOf(COST_TOWER[1]), 574, 215);
        if (!canPay(COST_TOWER[2]))
            SingletonGraphics.getInstance().graphics.drawString(String.valueOf(COST_TOWER[2]), 574, 255);
        if (getTowerAt() != null) {
            if (canPay(getTowerAt().upgradeCost)) {
                SingletonGraphics.getInstance().graphics.setColor(Color.YELLOW);
            }
            SingletonGraphics.getInstance().graphics.drawString(String.valueOf(getTowerAt().upgradeCost), 574, 295);
        }
        else {
            SingletonGraphics.getInstance().graphics.drawString("----", 576, 295);
        }
        
    }
    
    public Iterator getRandomRoute() {
        return routesList.get(random.nextInt(routesList.size())).newIterator();
    }
    
    public Chicken getNearestInRangeChicken(Point.Double point, int range) {
        Chicken chicken = null;
        double shortestDistance = range;
        double currentDistance;
        for (int n = 0; n < chickensList.size(); n++) {
            currentDistance = point.distance(chickensList.get(n).position);
            if (currentDistance <= shortestDistance) {
                chicken = chickensList.get(n);
                shortestDistance = currentDistance;
            }
        }
        return chicken;
    }
    
    public ArrayList<Chicken> getInRangeChickens(Double point, int range) {
        ArrayList<Chicken> chickens = new ArrayList();
        for (int n = 0; n < chickensList.size(); n++) {
            if (point.distance(chickensList.get(n).position) <= range) {
                chickens.add(chickensList.get(n));
            }
        }
        return chickens;
    }
    
    public Tower getTowerAt() {
        for (int n = 0; n < towersList.size(); n++) {
            if (towersList.get(n).position.distance(cursor.position) == 0) {
                return towersList.get(n);
            }
        }
        return null;
    }
    
    public boolean isBuildable() {
        Point tmp = Grid.getBoxLittlePosition((int)cursor.position.x, (int)cursor.position.y);
        return map.isBuildable(tmp.x, tmp.y);
    }
    
    public int getPoints() {
        return points;
    }
    
    public void givePoints(int amount) {
        points += amount;
    }
    
    public int getMoney() {
        return money;
    }
    
    public void giveMoney(int amount) {
        money += amount;
    }
    
    public void takeMoney(int amount) {
        money -= amount;
    }
    
    public boolean canPay(int amount) {
        return amount <= money;
    }
    
    public int getLife() {
        return lives;
    }
    
    public void takeALife() {
        if (lives > 0) {
            lives--;
        }
    }
    
    public void setMap(BufferedImage unmapa, int width, int height) {
        this.map = new Map(unmapa, width, width);
    }
    
    public void clear() {
        chickensList.clear();
        towersList.clear();
        bulletsList.clear();
        points = 0;
        money = 400;
        lives = 10;
    }
}