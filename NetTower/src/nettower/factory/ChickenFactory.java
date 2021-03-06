
package nettower.factory;

import nettower.entity.*;
import nettower.singleton.SingletonGame;

public class ChickenFactory extends Factory{
    
    /**
     * Fabrica de gallinas.
     * Dado un nivel, devuelve una gallina de un tipo aleatorio
     * 
     * @param level
     * @return 
     */
    public Chicken getChicken(double level) {
        switch (SingletonGame.getInstance().random.nextInt(4)) {
            case 1: return new HeavyChicken(level);
            case 2: return new QuickChicken(level);
            case 3: return new CompoundChickChicken(level);
            default: return new CommonChicken(level);
        }
    }
}
