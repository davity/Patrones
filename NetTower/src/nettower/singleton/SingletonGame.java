/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.singleton;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import nettower.entity.Bullet;
import nettower.entity.Chicken;
import nettower.entity.Tower;
import nettower.iterator.Aggregate;
import nettower.iterator.ConcreteAggregate;
import nettower.iterator.Iterator;

/**
 *
 * @author David
 */
public class SingletonGame {
    private static SingletonGame instancia = new SingletonGame();
    private ArrayList<Aggregate> routesList = new ArrayList();
    private ArrayList<Chicken> chickensList = new ArrayList();
    private ArrayList<Tower> towersList = new ArrayList();
    private ArrayList<Bullet> bulletsList = new ArrayList();
    private int points = 0;
    private int money = 400;
    private int lives = 10;
    private Random random = new Random();
    
    private SingletonGame() {}
    
    public static SingletonGame getInstance() {
        return instancia;
    }
    
    public void addRoute(ArrayList<Point.Double> route) {
        routesList.add(new ConcreteAggregate(route));
    }
    
    public void addChicken() {
        chickensList.add(new Chicken(20, 1, 20, 20));
    }
    
    public void removeChicken(Chicken chicken) {
        chickensList.remove(chicken);
    }
    
    public void addTower(Point.Double position) {
        towersList.add(new Tower(position, 1, 16, 4, 400, 10));
    }
    
    public void removeTower(Tower tower) {
        towersList.remove(tower);
    }
    
    public void addBullet(Point.Double position, Chicken target, int damage, int speed) {
        bulletsList.add(new Bullet(position, target, damage, speed));
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
        for (int n = 0; n < chickensList.size(); n++) {
            chickensList.get(n).draw();
        }
        for (int n = 0; n < towersList.size(); n++) {
            towersList.get(n).draw();
        }
        for (int n = 0; n < bulletsList.size(); n++) {
            bulletsList.get(n).draw();
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
            currentDistance = point.distance(chickensList.get(n).getPosition());
            if (currentDistance <= shortestDistance) {
                chicken = chickensList.get(n);
                shortestDistance = currentDistance;
            }
        }
        return chicken;
    }
    
    public Tower getTowerAt(Point.Double point) {
        for (int n = 0; n < towersList.size(); n++) {
            if (towersList.get(n).getPosition().distance(point) == 0) {
                return towersList.get(n);
            }
        }
        return null;
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
}
