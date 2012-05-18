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
public class EggChicken extends Chicken{
    public EggChicken() {
        super(Art.chicken, 16, SingletonGame.getInstance().getRandomRoute(), 40, 1, 20, 20);
    }

    @Override
    public void onStep() {}
}
