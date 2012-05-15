/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.iterator;

/**
 *
 * @author David
 */
public interface Aggregate {
    Iterator newIterator();
    void addElement(Object element);
    void removeElement(Object element);
}
