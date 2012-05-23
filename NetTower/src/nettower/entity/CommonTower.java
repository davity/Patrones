
package nettower.entity;

import java.awt.Point;
import nettower.Art;
import nettower.singleton.SingletonGame;

public class CommonTower extends Tower {
    
    /**
     * Crea una torreta comun en una posicion dada
     * 
     * @param iniPosition 
     */
    public CommonTower(Point.Double iniPosition) {
        super(Art.commonTower, 16, iniPosition, 20, 100, 10, 100, 200);
    }
    
    /**
     * Funcion de disparo de la torreta. Obtiene la gallina más cercana
     * y crea una bala con objetivo a esa gallina.
     */
    @Override
    public void shoot() {
        Chicken target = SingletonGame.getInstance().getNearestInRangeChicken(position, range);
        if (target != null) {
            SingletonGame.getInstance().addBullet(Art.redBullet, 4, position, target, damage, bulletsSpeed);
        }
        recharge = fireRate;
    }

    /**
     * Funcion de actualización.
     * Aumenta el daño, aumenta la cadencia de fuego, aumenta la velocidad de
     * las balas, el rango de la torreta y el coste de mejora.
     */
    @Override
    public void onUpgrade() {
        damage *= 1.1;
        fireRate *= 0.9;
        bulletsSpeed *= 1.1;
        range *= 1.1;
        upgradeCost *= 1.1;
    }
}
