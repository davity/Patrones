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
public class HordeChicken extends Chicken{
    public HordeChicken() {
        super(16, Art.chicken, SingletonGame.getInstance().getRandomRoute(), 10, 4, 4, 4);
    }

    @Override
    public void onStep() {}
}