/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

/**
 *
 * @author David Moran Diaz
 */
public class Tower extends Entity {
    
    public int damage;
    public int fireRate;

    public Tower(int iniDamage, int iniFireRate) {
        this.damage = iniDamage;
        this.fireRate = iniFireRate;
    }
    
    
}
