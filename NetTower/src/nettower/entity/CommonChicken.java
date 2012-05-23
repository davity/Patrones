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
public class CommonChicken extends Chicken{
    public CommonChicken(double level) {
        super(Art.commonChicken, Art.commonChickenI, 16, SingletonGame.getInstance().getRandomRoute(), (int)(20 * level), 2 + (int)(level / 10), (int)(20 * level), (int)(20 * level));
    }

    @Override
    public void onStep() {}
}