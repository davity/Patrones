
package nettower.iterator;

import java.util.ArrayList;

public class ConcreteAggregate implements Aggregate {
    public ArrayList elements;
    
    /**
     * Constructor del agregador. Se inicializa con una lista de elementos
     * 
     * @param iniElements 
     */
    public ConcreteAggregate (ArrayList iniElements) {
        elements = iniElements;
    }

    /**
     * Devuelve un iterador concreto basado en el actual
     * 
     * @return Iterator
     */
    @Override
    public Iterator newIterator() {
        return new ConcreteIterator(this);
    }
}
