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
    public CommonChicken() {
        super(Art.commonChicken, Art.commonChickenI, 16, SingletonGame.getInstance().getRandomRoute(), 20, 4, 20, 20);
    }

    @Override
    public void onStep() {}
}