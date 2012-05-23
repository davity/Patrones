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
public class QuickChicken extends Chicken{
    public QuickChicken(double level) {
        super(Art.quickChicken, Art.quickChickenI, 16, SingletonGame.getInstance().getRandomRoute(), (int)(10 * level), 3 + (int)(level), (int)(20 * level), (int)(20 * level));
    }

    @Override
    public void onStep() {}
}