/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.entity;

import nettower.Art;
import nettower.singleton.SingletonGame;

/**
 *
 * @author David
 */
public class HeavyChicken extends Chicken{
    public HeavyChicken() {
        super(16, Art.chicken, SingletonGame.getInstance().getRandomRoute(), 200, 1, 20, 20);
    }

    @Override
    public void onStep() {
        life++;
    }
}
