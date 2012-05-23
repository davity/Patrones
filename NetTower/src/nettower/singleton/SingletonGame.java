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
    
    /**
     * Constructor
     */
    private SingletonGame() {}
    
    /**
     * Devuelve la instancia del singleton
     * 
     * @return SingletonGame
     */
    public static SingletonGame getInstance() {
        return instance;
    }
    
    /**
     * Añade una ruta al mapa actual
     * 
     * @param route 
     */
    public void addRoute(ArrayList<Point.Double> route) {
        routesList.add(new ConcreteAggregate(route));
    }
    
    /**
     * Añade una gallina al mapa actual
     * 
     * @param level 
     */
    public void addChicken(double level) {
        chickensList.add(chickenFactory.getChicken(level));
    }
    
    /**
     * Añade un objeto Gallina concreto al mapa actual
     * 
     * @param chicken 
     */
    public void insertSpecificChicken(Chicken chicken) {
        chickensList.add(chicken);
    }
    
    /**
     * Elimina un objeto gallina concreto del mapa actual
     * 
     * @param chicken 
     */
    public void removeChicken(Chicken chicken) {
        chickensList.remove(chicken);
    }
    
    /**
     * Añade una torreta al mapa actual del tipo dado
     * 
     * @param type 
     */
    public void addTower(int type) {
        if (canPay(COST_TOWER[type])) {
            takeMoney(COST_TOWER[type]);
            Point.Double point = new Point.Double();
            point.setLocation(cursor.position);
            towersList.add(towerFactory.getTower(point, type));
        }
    }
    
    /**
     * Elimina un objeto Torre concreto del mapa actual
     * 
     * @param tower 
     */
    public void removeTower(Tower tower) {
        towersList.remove(tower);
    }
    
    /**
     * Añade una bala al mapa actual.
     * Recibe como parametros la imagen de la bala, el radio de la imagen,
     * la posicion inicial, el objeto Gallina objetivo, el daño y la velocidad
     * inicial de la bala.
     * 
     * @param image
     * @param radiusSize
     * @param position
     * @param target
     * @param damage
     * @param speed 
     */
    public void addBullet(BufferedImage image, int radiusSize, Point.Double position, Chicken target, int damage, int speed) {
        bulletsList.add(new Bullet(image, radiusSize, position, target, damage, speed));
    }
    
    /**
     * Elimina un objeto Bala concreto del juego
     * 
     * @param bullet 
     */
    public void removeBullet(Bullet bullet) {
        bulletsList.remove(bullet);
    }
    
    /**
     * Funcion de transición.
     * Hace transitar un paso a todas las gallinas, torres y balas
     */
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
    
    /**
     * Dibuja el juego actual.
     * Hace que las torres, las gallinas y las balas se dibujen, dibuja el menu,
     * la puntuacion, las vidas restantes, el dinero disponible y el coste
     * de actualizacion de una torreta si el cursor esta sobre ella.
     */
    public void draw() {
        map.draw();
        SingletonGraphics.getInstance().drawImage(Art.menu, new Point(480,0));
        for (int n = 0; n < towersList.size(); n++) {
            towersList.get(n).draw();
        }
        for (int n = 0; n < chickensList.size(); n++) {
            chickensList.get(n).draw();
        }
        if (lives <= 0) {
            SingletonGraphics.getInstance().graphics.setColor(Color.RED);
            SingletonGraphics.getInstance().graphics.setFont(new Font("ARIAL", Font.BOLD, 40));
            SingletonGraphics.getInstance().graphics.drawString("YOU LOSE", 160, 240);
        }
        else {
            for (int n = 0; n < bulletsList.size(); n++) {
                bulletsList.get(n).draw();
            }
            if (cursor != null) cursor.draw();
        }
        SingletonGraphics.getInstance().graphics.setColor(Color.BLUE);
        SingletonGraphics.getInstance().graphics.setFont(new Font("Arial", Font.BOLD, 12));
        SingletonGraphics.getInstance().graphics.drawString("Puntos:", 496, 46);
        SingletonGraphics.getInstance().graphics.drawString(String.format("% 16d", points), 514, 58);
        SingletonGraphics.getInstance().graphics.setFont(new Font("Arial", Font.PLAIN, 12));
        SingletonGraphics.getInstance().graphics.drawString("Mejor:", 496, 72);
        SingletonGraphics.getInstance().graphics.drawString(record, 514, 76);
        SingletonGraphics.getInstance().graphics.setColor(Color.RED);
        SingletonGraphics.getInstance().graphics.setFont(new Font("Serif", Font.BOLD, 22));
        SingletonGraphics.getInstance().graphics.drawString(String.valueOf(lives), 520, 124);
        SingletonGraphics.getInstance().graphics.setColor(Color.YELLOW);
        if (money < 1000000) {
            SingletonGraphics.getInstance().graphics.setFont(new Font("Arial", Font.BOLD, 15));
            SingletonGraphics.getInstance().graphics.drawString(String.valueOf(money), 576, 123);
        }
        else {
            SingletonGraphics.getInstance().graphics.setFont(new Font("Arial", Font.BOLD, 13));
            SingletonGraphics.getInstance().graphics.drawString(money / 1000000 + "M", 576, 123);
        }
        SingletonGraphics.getInstance().graphics.setFont(new Font("Arial", Font.BOLD, 18));
        if (canPay(COST_TOWER[0]))
            SingletonGraphics.getInstance().graphics.drawString(String.valueOf(COST_TOWER[0]), 560, 175);
        if (canPay(COST_TOWER[1]))
            SingletonGraphics.getInstance().graphics.drawString(String.valueOf(COST_TOWER[1]), 560, 215);
        if (canPay(COST_TOWER[2]))
            SingletonGraphics.getInstance().graphics.drawString(String.valueOf(COST_TOWER[2]), 560, 255);
        SingletonGraphics.getInstance().graphics.setColor(new Color(128, 128, 0));
        if (!canPay(COST_TOWER[0]))
            SingletonGraphics.getInstance().graphics.drawString(String.valueOf(COST_TOWER[0]), 560, 175);
        if (!canPay(COST_TOWER[1]))
            SingletonGraphics.getInstance().graphics.drawString(String.valueOf(COST_TOWER[1]), 560, 215);
        if (!canPay(COST_TOWER[2]))
            SingletonGraphics.getInstance().graphics.drawString(String.valueOf(COST_TOWER[2]), 560, 255);
        if (getTowerAt() != null) {
            if (canPay(getTowerAt().upgradeCost)) {
                SingletonGraphics.getInstance().graphics.setColor(Color.YELLOW);
            }
            if (getTowerAt().upgradeCost < 1000000) {
                SingletonGraphics.getInstance().graphics.setFont(new Font("Arial", Font.BOLD, 15));
                SingletonGraphics.getInstance().graphics.drawString(String.format("% 6d", getTowerAt().upgradeCost), 551, 295);
            }
            else {
                SingletonGraphics.getInstance().graphics.setFont(new Font("Arial", Font.BOLD, 13));
                SingletonGraphics.getInstance().graphics.drawString(String.format("% 6dM", getTowerAt().upgradeCost / 1000000), 551, 295);
            }
        }
        else {
            SingletonGraphics.getInstance().graphics.setFont(new Font("Arial", Font.PLAIN, 28));
            SingletonGraphics.getInstance().graphics.drawString("---", 561, 296);
        }
        
    }
    
    /**
     * Devuelve un aruta al azar de todas las rutas posibles para el mapa actual
     * 
     * @return Iterator
     */
    public Iterator getRandomRoute() {
        return routesList.get(random.nextInt(routesList.size())).newIterator();
    }
    
    /**
     * Dado un punto del mapa, devuelve la gallina mas cercana a dicho punto
     * 
     * @param point
     * @param range
     * @return Chicken
     */
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
    
    /**
     * Dado un punto y un rango, devuelve una lista con las gallinas que se
     * encuentran dentro del rango a partir de dicho punto
     * 
     * @param point
     * @param range
     * @return ArrayList<Chicken>
     */
    public ArrayList<Chicken> getInRangeChickens(Double point, int range) {
        ArrayList<Chicken> chickens = new ArrayList();
        for (int n = 0; n < chickensList.size(); n++) {
            if (point.distance(chickensList.get(n).position) <= range) {
                chickens.add(chickensList.get(n));
            }
        }
        return chickens;
    }
    
    /**
     * Dado un punto del mapa, devuelve la torre que se encuentra en dicho
     * punto o null si no hay ninguna
     * 
     * @return Tower
     */
    public Tower getTowerAt() {
        for (int n = 0; n < towersList.size(); n++) {
            if (towersList.get(n).position.distance(cursor.position) == 0) {
                return towersList.get(n);
            }
        }
        return null;
    }
    
    /**
     * Dado un punto del mapa, si es una casilla del mapa devuelve true si
     * se puede construir ahi una torreta, es decir, si es una casilla de
     * hierba y no hay una torreta construida ya
     * 
     * @return boolean
     */
    public boolean isBuildable() {
        Point tmp = Grid.getBoxLittlePosition((int)cursor.position.x, (int)cursor.position.y);
        return map.isBuildable(tmp.x, tmp.y);
    }
    
    /**
     * Devuelve la puntuacion actual del juego
     * @return 
     */
    public int getPoints() {
        return points;
    }
    
    /**
     * Añade una cantidad de puntos dada al juego actual
     * 
     * @param amount 
     */
    public void givePoints(int amount) {
        points += amount;
    }
    
    /**
     * Devuelve el dinero actual disponible en el juego actual
     * @return 
     */
    public int getMoney() {
        return money;
    }
    
    /**
     * Añade dinero al total del juego actual
     * 
     * @param amount 
     */
    public void giveMoney(int amount) {
        money += amount;
    }
    
    /**
     * Resta dinero al total del juego actual
     * @param amount 
     */
    public void takeMoney(int amount) {
        money -= amount;
    }
    
    /**
     * Comprueba si se puede pagar una cantidad determinada de dinero
     * (e.d. si se puede restar ese dinero quedandose a un valor total >=0)
     * 
     * @param amount
     * @return 
     */
    public boolean canPay(int amount) {
        return amount <= money;
    }
    
    /**
     * Devuelve el total de vidas restante
     * 
     * @return 
     */
    public int getLife() {
        return lives;
    }
    
    /**
     * Resta una vida al total
     */
    public void takeALife() {
        if (lives > 0) {
            lives--;
        }
    }
    
    /**
     * Dada una imagen y un tamapo (ancho y alto), asigna un mapa al juego
     * actual
     * 
     * @param unmapa
     * @param width
     * @param height 
     */
    public void setMap(BufferedImage unmapa, int width, int height) {
        this.map = new Map(unmapa, width, width);
    }
    
    /**
     * Reinicia el juego actual eliminando todas las torretas, las gallinas,
     * las balas, y ademas reinicia la puntuacion, el dinero y las vidas
     */
    public void clear() {
        routesList.clear();
        chickensList.clear();
        towersList.clear();
        bulletsList.clear();
        points = 0;
        money = 0;
        lives = 10;
        record = "0";
    }
}