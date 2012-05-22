/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.singleton;

import java.awt.Point;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
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
    public Random random = new Random();
    
    private SingletonGame() {}
    
    public static SingletonGame getInstance() {
        return instance;
    }
    
    public void addRoute(ArrayList<Point.Double> route) {
        routesList.add(new ConcreteAggregate(route));
    }
    
    public void addChicken() {
        chickensList.add(chickenFactory.getChicken());
    }
    
    public void insertSpecificChicken(Chicken chicken) {
        chickensList.add(chicken);
    }
    
    public void removeChicken(Chicken chicken) {
        chickensList.remove(chicken);
    }
    
    public void addTower(int type) {
        Point.Double point = new Point.Double();
        point.setLocation(cursor.position);
        towersList.add(towerFactory.getTower(point, type));
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
        for (int n = 0; n < towersList.size(); n++) {
            towersList.get(n).step();
        }
        for (int n = 0; n < bulletsList.size(); n++) {
            bulletsList.get(n).step();
        }
    }
    
    public void draw() {
        map.draw();
        for (int n = 0; n < towersList.size(); n++) {
            towersList.get(n).draw();
        }
        for (int n = 0; n < chickensList.size(); n++) {
            chickensList.get(n).draw();
        }
        for (int n = 0; n < bulletsList.size(); n++) {
            bulletsList.get(n).draw();
        }
        if (cursor != null) cursor.draw();
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
    
    public void givePoints(int amount) {
        points += amount;
    }
    
    public void giveMoney(int amount) {
        money += amount;
    }
    
    public void takeMoney(int amount) {
        money -= amount;
    }
    
    public void takeALife() {
        lives--;
    }
    
    public void setMap(BufferedImage unmapa, int width, int height) {
        this.map = new Map(unmapa, width, width);
    }
}