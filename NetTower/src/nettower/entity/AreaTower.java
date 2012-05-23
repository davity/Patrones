/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Point;
import java.util.ArrayList;
import nettower.Art;
import nettower.singleton.SingletonGame;

/**
 *
 * @author David
 */
public class AreaTower extends Tower {
    
    /**
     * Constructor. Recibe la posición inicial de la torreta.
     * El resto de parámetros son fijos y por defecto.
     * 
     * @param iniPosition 
     */
    public AreaTower(Point.Double iniPosition) {
        super(Art.areaTower, 16, iniPosition, 1, 200, 10, 40, 200);
    }
    
    /**
     * Accion de disparar. Busca a todas las gallinas dentro de su rango y 
     * crea una bala que se dirigira hacia cada una de ellas.
     */
    @Override
    public void shoot() {
        ArrayList<Chicken> targets = SingletonGame.getInstance().getInRangeChickens(position, range);
        for (int n = 0; n < targets.size(); n++) {
            SingletonGame.getInstance().addBullet(Art.blueBullet, 4, position, targets.get(n), damage, bulletsSpeed);
        }
    }

    /**
     * Funcion que se ejecuta al mejorar una torreta. Aumenta el daño, el rango,
     * la velocidad de las balas y el coste de mejora.
     */
    @Override
    public void onUpgrade() {
        range *= 1.4;
        upgradeCost *= 1.1;
    }
}
