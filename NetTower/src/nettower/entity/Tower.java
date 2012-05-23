/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import java.awt.Point;
import java.awt.image.BufferedImage;
import nettower.singleton.SingletonGame;

/**
 *
 * @author David Moran Diaz
 */
public abstract class Tower extends Entity {
    int damage;
    int fireRate;
    int bulletsSpeed;
    int range;
    public int upgradeCost;
    int recharge;
    
    /**
     * Constructor de torreta. Recibe una imagen, el radio de la misma,
     * una posicion inciial, un daño inicial, una cadencia de tiro incial,
     * una velocidad de bala inicial, un rango inicial y el coste de mejora
     * inicial.
     * 
     * @param iniImage
     * @param iniRadiusSize
     * @param iniPosition
     * @param iniDamage
     * @param iniFireRate
     * @param iniBulletsSpeed
     * @param iniRange
     * @param iniUpgradeCost 
     */
    public Tower(BufferedImage iniImage, int iniRadiusSize, Point.Double iniPosition, int iniDamage, int iniFireRate, int iniBulletsSpeed, int iniRange, int iniUpgradeCost) {
        super(iniImage, iniRadiusSize, iniPosition);
        damage = iniDamage;
        fireRate = iniFireRate;
        bulletsSpeed = iniBulletsSpeed;
        range = iniRange;
        upgradeCost = iniUpgradeCost;
        
        recharge = fireRate;
    }

    /**
     * Ejecuta un paso de torreta. Hace que esta dispare o recargue.
     */
    @Override
    public void step() {
        if (recharge == 0) {
            shoot();
        }
        else {
            recharge--;
        }
    }
    
    /**
     * Ejecuta el disparo de una torreta
     */
    public abstract void shoot();
    
    /**
     * Comprueba si el jugador tiene dinero, lo resta del total y 
     * actualiza los valores de disparo de la torreta.
     */
    public void upgrade() {
        if (SingletonGame.getInstance().canPay(upgradeCost)) {
            SingletonGame.getInstance().takeMoney(upgradeCost);
            onUpgrade();
            recharge = fireRate;
        }
    }
    
    /**
     * 
     */
    public abstract void onUpgrade();

    /**
     * Elimina la torreta
     */
    @Override
    public void remove() {
        SingletonGame.getInstance().removeTower(this);
    }
}
