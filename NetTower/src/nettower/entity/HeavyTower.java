/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Point;
import nettower.Art;
import nettower.singleton.SingletonGame;

/**
 *
 * @author David
 */
public class HeavyTower extends Tower {
    
    /**
     * Crea una torreta pesada en la posicion dada.
     * 
     * @param iniPosition 
     */
    public HeavyTower(Point.Double iniPosition) {
        super(Art.heavyTower, 16, iniPosition, 200, 400, 2, 200, 200);
    }
    
    /**
     * Funcion de disparo de la torreta.
     * Busca la gallina mas cercana y crea una bala dirigida hacia ella
     */
    @Override
    public void shoot() {
        Chicken target = SingletonGame.getInstance().getNearestInRangeChicken(position, range);
        if (target != null) {
            SingletonGame.getInstance().addBullet(Art.purpleBullet, 4, position, target, damage, bulletsSpeed);
        }
        recharge = fireRate;
    }

    /**
     * Funcion de actualizacion.
     * Aumenta el daño, el rango y el coste de mejora
     */
    @Override
    public void onUpgrade() {
        damage *= 2;
        range *= 1.1;
        upgradeCost *= 1.5;
    }
}
