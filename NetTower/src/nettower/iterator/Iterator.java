/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.iterator;

/**
 *
 * @author David
 */
public interface Iterator {
    Object first();
    Object current();
    Object next();
    boolean hasNext();
    Iterator clone();
}
