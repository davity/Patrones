/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

/**
 *
 * @author David Moran Diaz
 */
public class Chicken extends Entity {
    
    public int life;
    public double speed;

    public Chicken(int iniLife, double iniSpeed) {
        this.life = iniLife;
        this.speed = iniSpeed;
    }
}
