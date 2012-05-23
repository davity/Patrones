
package nettower.entity;

import java.awt.Point;
import java.awt.image.BufferedImage;
import nettower.flyweight.ImageFactory;
import nettower.flyweight.ImageFlyweight;
import nettower.iterator.Iterator;
import nettower.singleton.SingletonGame;

public abstract class Chicken extends MobileEntity {
    public ImageFlyweight invertedImage;
    public boolean inverted;
    public Iterator route;
    public int life;
    public int points;
    public int money;
    
    /**
     * Constructor de Chicken. Recibe una imagen, su radio, una ruta (iterador),
     * la vida inicial, la velocidad inicial, los puntos y el dinero iniciales 
     * que se obtienen al matarla
     * 
     * @param iniImage
     * @param iniInvertedImage
     * @param iniRadiusSize
     * @param iniRoute
     * @param iniLife
     * @param iniSpeed
     * @param iniPoints
     * @param iniMoney 
     */
    public Chicken(BufferedImage iniImage, BufferedImage iniInvertedImage, int iniRadiusSize, Iterator iniRoute, int iniLife, int iniSpeed, int iniPoints, int iniMoney) {
        super(iniImage, iniRadiusSize, (Point.Double)iniRoute.first(), iniSpeed);
        invertedImage = ImageFactory.getImage(iniInvertedImage, iniRadiusSize);
        route = iniRoute;
        life = iniLife;
        points = iniPoints;
        money = iniMoney;
    }
    
    /**
     * Devuelve el siguiente punto al que tiene que ir la gallina dentro del
     * camino
     * 
     * @return Point.Double
     */
    @Override
    public Point.Double getPointTarget() {
        return (Point.Double)route.current();
    }
    
    /**
     * Funciona a ejecutar de la gallina en cada iteración del juego.
     * Actualiza posición, imagen y el estado de la gallina.
     */
    @Override
    public void step() {
        if (life <= 0) {
            slain();
        }
        else {
            onStep();
            if (inverted && getPointTarget().x - position.x > 0) {
                ImageFlyweight aux = image;
                image = invertedImage;
                invertedImage = aux;
                inverted = false;
            }
            if (!inverted && getPointTarget().x - position.x < 0) {
                ImageFlyweight aux = image;
                image = invertedImage;
                invertedImage = aux;
                inverted = true;
            }
            advance();
        }
    }
    
    /**
     * 
     */
    public abstract void onStep();
    
    /**
     * Determina si una gallina ha llegado al final. Si ha llegado, resta
     * una vida y se elimina.
     */
    @Override
    public void onReach() {
        if (route.hasNext()) {
            route.next();
        }
        else {
            SingletonGame.getInstance().takeALife();
            remove();
        }
    }
    
    /**
     * Resta un numero de vidas dado por el parámetro a la gallina.
     * 
     * @param damage 
     */
    public void takeDamage(int damage) {
        life -= damage;
    }
    
    /**
     * Accion a ejecutar cuando la gallina muere.
     * Suma puntos y dinero al jugador y se elimina.
     */
    public void slain() {
        SingletonGame.getInstance().givePoints(points);
        SingletonGame.getInstance().giveMoney(money);
        remove();
    }

    /**
     * Elimina la gallina del juego
     */
    @Override
    public void remove() {
        SingletonGame.getInstance().removeChicken(this);
    }
}