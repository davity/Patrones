/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.factory;

import nettower.entity.*;
import nettower.singleton.SingletonGame;

/**
 *
 * @author David
 */
public class ChickenFactory extends Factory{
    public Chicken getChicken() {
        switch (SingletonGame.getInstance().random.nextInt(5)) {
            case 1: return new HeavyChicken();
            case 2: return new QuickChicken();
            case 3: return new HordeChicken();
            case 4: return new EggChicken();
            default: return new CommonChicken();
        }
    }
}
