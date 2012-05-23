
package nettower.entity;

import java.awt.Point;
import java.awt.image.BufferedImage;
import nettower.singleton.SingletonGame;

public class Bullet extends MobileEntity{
    public Chicken target;
    public int damage;
    
    /**
     * Constructor de bala. Recibe una imagen, el radio de esta, una posicion
     * inicial, un objeto gallina objetivo, un daño y una velocidad.
     * 
     * @param iniImage
     * @param iniRadiusSize
     * @param iniPosition
     * @param iniTarget
     * @param iniDamage
     * @param iniSpeed 
     */
    public Bullet(BufferedImage iniImage, int iniRadiusSize, Point.Double iniPosition, Chicken iniTarget, int iniDamage, int iniSpeed) {
        super(iniImage, iniRadiusSize, iniPosition, iniSpeed);
        target = iniTarget;
        damage = iniDamage;
    }
    
    /**
     * Actualiza la posición de la gallina objetivo
     * 
     * @return Point.Double
     */
    @Override
    public Point.Double getPointTarget() {
        return target.position;
    }
    
    /**
     * Iteracion de la bala. Actualiza su posicion (se mueve hacia el objetivo)
     */
    @Override
    public void step() {
        advance();
    }
    
    /**
     * Funcion a ejecutar cunado la bala alcanza a su objetivo.
     * Hace daño al objetivo y se elimina la bala.
     */
    @Override
    public void onReach() {
        target.takeDamage(damage);
        remove();
    }

    /**
     * Elimina la bala del juego
     */
    @Override
    public void remove() {
        SingletonGame.getInstance().removeBullet(this);
    }
}
