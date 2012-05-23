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
    public HeavyChicken(double level) {
        super(Art.heavyChicken, Art.heavyChickenI, 16, SingletonGame.getInstance().getRandomRoute(), (int)(200 * level), 1 + (int)(level / 100), (int)(20 * level), (int)(20 * level));
    }

    @Override
    public void onStep() {
        life++;
    }
}
