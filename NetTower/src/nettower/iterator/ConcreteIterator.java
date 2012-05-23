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
    
    /**
     * Constructor del iterador concreto. Recibe un agregador inicial
     * 
     * @param iniAggregate 
     */
    public ConcreteIterator(ConcreteAggregate iniAggregate) {
        aggregate = iniAggregate;
    }
    
    /**
     * Devuelve el primer elemento, o null en caso de que no exista
     * 
     * @return Object
     */
    @Override
    public Object first() {
        Object object = null;
        if (!aggregate.elements.isEmpty()) {
            index = 0;
            object = aggregate.elements.get(0);
        }
        return object;
    }

    /**
     * Devuelve el elemento actual o null en caso de que no exista ninguno
     * 
     * @return Object
     */
    @Override
    public Object current() {
        Object object = null;
        if (index < aggregate.elements.size()) {
            object = aggregate.elements.get(index);
        }
        return object;
    }

    /**
     * Devuelve el siguiente elemento o null si no existe ninguno
     * 
     * @return Object
     */
    @Override
    public Object next() {
        Object object = null;
        if (index < aggregate.elements.size()) {
            object = aggregate.elements.get(index);
            index++;
        }
        return object;
    }

    /**
     * Devuelve un valor boolean indicando si existe o no un elemento siguiente
     * 
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        boolean result = true;
        if (aggregate.elements.isEmpty() || index == aggregate.elements.size() - 1) {
            result = false;
        }
        return result;
    }
    
    /**
     * Clona el iterador actual y devuelve la copia
     * 
     * @return Iterator
     */
    @Override
    public Iterator clone() {
        Iterator iterator = aggregate.newIterator();
        for (int n = 0; n < index; n++) {
            iterator.next();
        }
        return iterator;
    }
}
