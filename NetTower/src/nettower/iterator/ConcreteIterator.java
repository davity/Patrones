/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.iterator;

/**
 *
 * @author David
 */
public class ConcreteIterator implements Iterator {
    private ConcreteAggregate aggregate;
    private int index = 0;
    
    public ConcreteIterator(ConcreteAggregate iniAggregate) {
        aggregate = iniAggregate;
    }
    
    @Override
    public Object first() {
        Object object = null;
        if (!aggregate.elements.isEmpty()) {
            index = 0;
            object = aggregate.elements.get(0);
        }
        return object;
    }

    @Override
    public Object current() {
        Object object = null;
        if (index < aggregate.elements.size()) {
            object = aggregate.elements.get(index);
        }
        return object;
    }

    @Override
    public Object next() {
        Object object = null;
        if (index < aggregate.elements.size()) {
            object = aggregate.elements.get(index);
            index++;
        }
        return object;
    }

    @Override
    public boolean hasNext() {
        boolean result = true;
        if (aggregate.elements.isEmpty() || index == aggregate.elements.size() - 1) {
            result = false;
        }
        return result;
    }
}
