/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.iterator;

import java.util.ArrayList;

/**
 *
 * @author David
 */
public class ConcreteAggregate implements Aggregate {
    public ArrayList elements;
    
    public ConcreteAggregate (ArrayList iniElements) {
        elements = iniElements;
    }

    @Override
    public Iterator newIterator() {
        return new ConcreteIterator(this);
    }

    @Override
    public void addElement(Object element) {
        elements.add(element);
    }
    
    @Override
    public void removeElement(Object element) {
        elements.remove(element);
    }
}
